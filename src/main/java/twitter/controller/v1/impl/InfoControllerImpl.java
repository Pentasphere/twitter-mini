package twitter.controller.v1.impl;

import twitter.controller.v1.InfoController;
import twitter.entity.user.User;
/*import twitter.listener.TwitterCommand;*/
import twitter.exception.UserNotFoundException;
import twitter.runner.TwitterCommandEnum;
import twitter.security.SecurityComponent;
import twitter.service.UserService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/*@Component*/
public class InfoControllerImpl implements InfoController {

    /*private static InfoController instance;

    public static InfoController getInstance(){
        if(instance == null){
            instance = new InfoController(new Scanner(System.in), UserServiceImpl.getInstance(), InMemorySecurityComponent.getInstance());
        }
        return instance;
    }*/

    /*private final Scanner scanner;*/
    private final UserService userService;
    private final SecurityComponent securityComponent;
    private final BufferedReader in;
    private final BufferedWriter out;
    private final String userIp;

    /*@Injection*/
    public InfoControllerImpl(
            UserService userService,
            SecurityComponent securityComponent,
            BufferedReader in,
            BufferedWriter out,
            String userIp
    ) {
        /*this.scanner = new Scanner(System.in);*/
        this.userService = userService;
        this.securityComponent = securityComponent;
        this.in = in;
        this.out = out;
        this.userIp = userIp;
    }

    /*private InfoController(
            Scanner scanner,
            UserServiceImpl userService,
            SecurityComponent securityComponent
    ) {
        this.scanner = scanner;
        this.userService = userService;
        this.securityComponent = securityComponent;
    }*/

    @Override
    public void executeHelp() throws IOException {
        /*TwitterCommand.info();*/
        /*TwitterCommandEnum.info();*/
        out.write(TwitterCommandEnum.info());
        out.flush();
    }

    @Override
    public void executeInfo() throws IOException{
        /*if (this.securityComponent.getAuthentication() == null) {
            System.out.println("Для выполнения данной команды необходимо войти в систему.");
            return;
        }

        System.out.println("Информация о пользователе!");
        System.out.println(securityComponent.getAuthentication().beautify());
        System.out.println("Конец информации!");*/

        if (this.securityComponent.getAuthentication(userIp) == null) {
            out.append("Для выполнения данной команды необходимо войти в систему.").append("\n");
            out.flush();
            return;
        }

        out.append("<<<<<<  Информация о пользователе  >>>>>").append("\n");
        out.append(securityComponent.getAuthentication(userIp).beautify()).append("\n");
        out.append("<<<<<<  Конец информации  >>>>>").append("\n");
        out.flush();
    }

    @Override
    public void executeInfoByLogin() throws IOException{
        /*if (this.securityComponent.getAuthentication() == null) {
            System.out.println("Для выполнения данной команды необходимо войти в систему.");
            return;
        }

        System.out.print("Введите логин пользователя для получения информации: ");
        String login = this.scanner.nextLine();
        if(login == null || login.trim().isEmpty()){
            System.out.println("Логин не может быть пустым");
            return;
        }
        login = login.trim();
        if(login.contains(" ")){
            System.out.println("Логин не может содержать пробелы.");
            return;
        }
        try {
            User user = userService.getUserByLogin(login);
            System.out.println("Информация о пользователе!");
            System.out.println(user.beautify());
            System.out.println("Конец информации!");
        }catch (UserNotFoundException ex){
            System.out.println(ex.getMessage());
        }*/

        /*User user = userService.getUserByLogin(login);
        if(user == null){
            System.out.println("Пользователь с таким логином не найден.");
            return;
        }

        System.out.println("Информация о пользователе!");
        System.out.println(user.beautify());
        System.out.println("Конец информации!");*/

        if (this.securityComponent.getAuthentication(userIp) == null) {
            out.append("Для выполнения данной команды необходимо войти в систему.").append("\n");
            out.flush();
            return;
        }

        out.append("Введите логин пользователя для получения информации: ");
        out.flush();
        String login = in.readLine();
        if (login == null || login.trim().isEmpty()) {
            out.append("Логин не может быть пустым").append("\n");
            out.flush();
            return;
        }
        login = login.trim();
        if (login.contains(" ")) {
            out.append("Логин не может содержать пробелы").append("\n");
            out.flush();
            return;
        }
        try {
            User user = userService.getUserByLogin(login);

            out.append("<<<<<<  Информация о пользователе  >>>>>").append("\n");
            out.append(user.beautify()).append("\n");
            out.append("<<<<<<  Конец информации  >>>>>").append("\n");
            out.flush();
        } catch (UserNotFoundException ex) {
            out.append(ex.getMessage()).append("\n");
            out.flush();
        }
    }

    @Override
    public void executeInfoAll() throws IOException{
        /*if (this.securityComponent.getAuthentication() == null) {
            System.out.println("Для выполнения данной команды необходимо войти в систему.");
            return;
        }

        System.out.println("Информация о всех пользователях системы!");
        User[] users = userService.getAllUsers();
        for(User user : users){
            System.out.println(user.beautify());
            System.out.println("-------------------------------------------------");
        }
        System.out.println("Конец информации!");*/

        if (this.securityComponent.getAuthentication(userIp) == null) {
            out.append("Для выполнения данной команды необходимо войти в систему.").append("\n");
            out.flush();
            return;
        }

        out.append("<<<<<<  Информация о всех пользователях системы  >>>>>").append("\n");
        /*User[] users = userService.getAllUsers();*/
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            out.append(user.beautify()).append("\n");
            out.append("-----------------------------------------------------------------------------------------").append("\n");
        }
        out.append("<<<<<<  Конец информации  >>>>>").append("\n");
        out.flush();
    }
}
