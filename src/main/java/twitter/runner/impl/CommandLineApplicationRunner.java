package twitter.runner.impl;

import twitter.configuration.Component;
import twitter.configuration.Injection;
import twitter.exception.UnknownCommandException;
import twitter.factory.CommandFactory;
import twitter.factory.CommandFactoryBuilder;
import twitter.factory.command.CommandHandler;
import twitter.runner.ApplicationRunner;

import java.io.*;
import java.util.Scanner;

/*@Component*/
public class CommandLineApplicationRunner implements ApplicationRunner {

    /*private static CommandLineListener instance;

    public static CommandLineListener getInstance() {
        if (instance == null) {
            instance = new CommandLineListener();
        }
        return instance;
    }*/

    /*private final SecurityComponent securityComponent;*/

    /*private final CommandFactory commandFactory;*/

    private final CommandFactoryBuilder commandFactoryBuilder;

    /*@Injection
    public CommandLineApplicationRunner(CommandFactory commandFactory) {
        *//*this.securityComponent = InMemorySecurityComponent.getInstance();*//*
        this.commandFactory = commandFactory;
    }*/

    //    @Injection
    public CommandLineApplicationRunner(CommandFactoryBuilder commandFactoryBuilder) {
        this.commandFactoryBuilder = commandFactoryBuilder;
    }

    /*private CommandLineListener() {
        *//*this.securityComponent = InMemorySecurityComponent.getInstance();*//*
    }*/

//    public void listen() {
//        Scanner scanner = new Scanner(System.in);
//        AuthenticationController authenticationController = AuthenticationController.getInstance();
//        RegistrationController registrationController = RegistrationController.getInstance();
//        InfoController infoController = InfoController.getInstance();
//        PostController postController = PostController.getInstance();
//        String command = "";
//        while (true) {
//            System.out.println("Для получения помощи по командам, используйте команду help.");
//            System.out.print("Введите команду: ");
//            command = scanner.nextLine();
//            switch (command) {
//                case TwitterCommand.EXIT_COMMAND: {
//                    scanner.close();
//                    authenticationController.executeExit();
//                    break;
//                }
//                case TwitterCommand.HELP_COMMAND: {
//                    infoController.executeHelp();
//                    break;
//                }
//                case TwitterCommand.REGISTER_COMMAND: {
//                    if (this.securityComponent.getAuthentication() != null) {
//                        System.out.println("Для выполнения данной команды необходимо выйти из системы.");
//                        break;
//                    }
//                    registrationController.executeRegister();
//                    break;
//                }
//                case TwitterCommand.LOGIN_COMMAND: {
//                    if (this.securityComponent.getAuthentication() != null) {
//                        System.out.println("Для выполнения данной команды необходимо выйти из системы.");
//                        break;
//                    }
//                    authenticationController.executeLogin();
//                    break;
//                }
//                case TwitterCommand.LOGOUT_COMMAND: {
//                    if (this.securityComponent.getAuthentication() == null) {
//                        System.out.println("Для выполнения данной команды необходимо войти в систему.");
//                        break;
//                    }
//                    authenticationController.executeLogout();
//                    break;
//                }
//                case TwitterCommand.INFO_COMMAND: {
//                    if (this.securityComponent.getAuthentication() == null) {
//                        System.out.println("Для выполнения данной команды необходимо войти в систему.");
//                        break;
//                    }
//                    infoController.executeInfo();
//                    break;
//                }
//                case TwitterCommand.INFO_BY_LOGIN_COMMAND: {
//                    if (this.securityComponent.getAuthentication() == null) {
//                        System.out.println("Для выполнения данной команды необходимо войти в систему.");
//                        break;
//                    }
//                    infoController.executeInfoByLogin();
//                    break;
//                }
//                case TwitterCommand.INFO_ALL_COMMAND: {
//                    if (this.securityComponent.getAuthentication() == null) {
//                        System.out.println("Для выполнения данной команды необходимо войти в систему.");
//                        break;
//                    }
//                    infoController.executeInfoAll();
//                    break;
//                }
//                case TwitterCommand.ADD_POST_COMMAND: {
//                    if (this.securityComponent.getAuthentication() == null) {
//                        System.out.println("Для выполнения данной команды необходимо войти в систему.");
//                        break;
//                    }
//                    postController.executeAddPost();
//                    break;
//                }
//                case TwitterCommand.MY_POSTS_COMMAND: {
//                    if (this.securityComponent.getAuthentication() == null) {
//                        System.out.println("Для выполнения данной команды необходимо войти в систему.");
//                        break;
//                    }
//                    postController.executeMyPosts();
//                    break;
//                }
//                case TwitterCommand.ALL_POSTS_COMMAND: {
//                    if (this.securityComponent.getAuthentication() == null) {
//                        System.out.println("Для выполнения данной команды необходимо войти в систему.");
//                        break;
//                    }
//                    postController.executeAllPosts();
//                    break;
//                }
//                case TwitterCommand.POSTS_BY_TAG_COMMAND: {
//                    if (this.securityComponent.getAuthentication() == null) {
//                        System.out.println("Для выполнения данной команды необходимо войти в систему.");
//                        break;
//                    }
//                    postController.executePostsByTag();
//                    break;
//                }
//                case TwitterCommand.POSTS_BY_LOGIN_COMMAND: {
//                    if (this.securityComponent.getAuthentication() == null) {
//                        System.out.println("Для выполнения данной команды необходимо войти в систему.");
//                        break;
//                    }
//                    postController.executePostsByLogin();
//                    break;
//                }
//                case TwitterCommand.POSTS_BY_USER_TYPE_COMMAND: {
//                    if (this.securityComponent.getAuthentication() == null) {
//                        System.out.println("Для выполнения данной команды необходимо войти в систему.");
//                        break;
//                    }
//                    postController.executePostsByUserType();
//                    break;
//                }
//                default: {
//                    System.out.println("Команда неопознана, проверьте список команд и попробуйте снова.");
//                    break;
//                }
//            }
//        }
//    }

    /*@Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        *//*CommandFactory factory = CommandFactory.getInstance();*//*
        CommandHandler handler;
        while (true) {
            System.out.println("Для получения помощи по командам, используйте команду help.");
            System.out.print("Введите команду: ");
            command = scanner.next();
            *//*handler = twitter.factory.getHandler(command);*//*

            try {
                *//*handler = factory.getHandler(command);*//*
                handler = this.commandFactory.getHandler(command);
                handler.handle();
            } catch (UnknownCommandException ex){
                System.out.println("Команда неопознана, проверьте список команд и попробуйте снова.");
            }
//            finally {
//                System.out.println("Блок finally");
//            }

            *//*if (handler == null) {
                System.out.println("Команда неопознана, проверьте список команд и попробуйте снова.");
                continue;
            }
            handler.handle();*//*
        }
    }*/

    @Override
    public void run() {
        String command = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        CommandFactory commandFactory = commandFactoryBuilder.buildCommandFactoryForUser(null, reader, writer);
        while (true) {
            try {
                writer.append("Для получения помощи по командам, используйте команду help.").append("\n");
                writer.append("Введите команду: ");
                writer.flush();
                command = reader.readLine();
                commandFactory.getHandler(command).handle();
            } catch (UnknownCommandException ex) {
                System.out.println("Команда неопознана, проверьте список команд и попробуйте снова.");
            } catch (IOException ex) {
                System.out.println("Что то сломалось");
            }
        }
    }
}
