package twitter.runner.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter.configuration.Component;
import twitter.configuration.Injection;
import twitter.configuration.Value;
import twitter.factory.CommandFactoryBuilder;
import twitter.runner.ApplicationRunner;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*@Component*/
public class TelnetServerApplicationRunner implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(TelnetServerApplicationRunner.class);

    @Value(key = "application.port")
    private Integer port;

    @Value(key = "max.users.count")
    private Integer maxUsersCount;

    /*private final int port = 8080;*/
    /*private final ExecutorService threadPool;*/
    private ExecutorService threadPool;
    private volatile boolean running;

    private final CommandFactoryBuilder commandFactoryBuilder;

    /*@Injection*/
    public TelnetServerApplicationRunner(CommandFactoryBuilder commandFactoryBuilder) {
        this.commandFactoryBuilder = commandFactoryBuilder;
        /*this.threadPool = Executors.newFixedThreadPool(20);*/
        /*this.threadPool = Executors.newFixedThreadPool(maxUsersCount);*/
    }

    @Override
    public void run() {
        this.threadPool = Executors.newFixedThreadPool(maxUsersCount);
        new Thread(() -> {
            running = true;
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                /*System.out.println("Telnet server started on port " + port);*/
                logger.info("Telnet server started on port " + port);

                while (running) {
                    try /*(
                            Socket clientSocket = serverSocket.accept();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    )*/ {
                        Socket clientSocket = serverSocket.accept();
                       /* System.out.println("New client connected: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());*/
                        /*BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));*/
                        /*String clientId = clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort();*/
                        Runnable clientHandling = new TelnetClientHandler(clientSocket, commandFactoryBuilder);
                        threadPool.execute(clientHandling);
                    } catch (IOException e) {
                        if (!running) {
                            /*System.out.println("Server stopped.");*/
                            logger.error("Server stopped.");
                            break;
                        }
                        /*System.err.println("Error with client connection: " + e.getMessage());*/
                        logger.error("Error with client connection: " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                /*System.err.println("Error starting Telnet server: " + e.getMessage());*/
                logger.error("Error starting Telnet server: " + e.getMessage());
            } finally {
                threadPool.shutdown();
            }
        }).start();
    }

    /*private Runnable clientHandling(String userIp, BufferedReader reader, BufferedWriter writer) {
        return () -> {
            String command = "";
            CommandFactory commandFactory = commandFactoryBuilder.buildCommandFactoryForUser(userIp, reader, writer);
            while (true) {
                try {
                    writer.append("Для получения помощи по командам, используйте команду help.").append("\n");
                    writer.append("Введите команду: ");
                    writer.flush();
                    command = reader.readLine();
                    commandFactory.getHandler(command).handle();
                } catch (UnknownCommandException ex) {
                    try {
                        writer.write("Команда неопознана, проверьте список команд и попробуйте снова.");
                    } catch (IOException ex1) {
                        System.out.println(ex.getMessage());
                    }
                } catch (ClientDisconnectedException ex) {
                    System.out.println("Client with IP " + userIp + " disconnected.");
                    break;
                    return;
                } catch (IOException ex) {
                    System.out.println("Что то сломалось, сообщение: " + ex.getMessage());
                    System.out.println(ex.getClass().getName());
                    System.out.println(ex.getMessage());
                    break;
                    return;
                } catch (ClientDisconnectedException ex) {
                    System.out.println("Client with IP " + userIp + " disconnected.");
                    break;
                }
            }
        };
    }*/
}
