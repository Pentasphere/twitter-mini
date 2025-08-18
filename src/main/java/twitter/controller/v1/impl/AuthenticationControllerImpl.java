package twitter.controller.v1.impl;

import twitter.controller.v1.AuthenticationController;
import twitter.entity.user.User;
import twitter.exception.ClientDisconnectedException;
import twitter.exception.UserNotFoundException;
import twitter.security.SecurityComponent;
import twitter.service.UserService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/*@Component*/
public class AuthenticationControllerImpl implements AuthenticationController {

    /*private static AuthenticationController instance;

    public static AuthenticationController getInstance() {
        if (instance == null) {
            instance = new AuthenticationController();
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
    public AuthenticationControllerImpl(
            UserService userService,
            SecurityComponent securityComponent,
            /*Scanner scanner*/
            BufferedReader in,
            BufferedWriter out,
            String userIp
    ) {
        /*this.scanner = scanner;*/
        this.userService = userService;
        this.securityComponent = securityComponent;
        this.in = in;
        this.out = out;
        this.userIp = userIp;
    }

    /*private AuthenticationController() {
        this.scanner = new Scanner(System.in);
        this.userService = UserServiceImpl.getInstance();
        this.securityComponent = InMemorySecurityComponent.getInstance();
    }*/

    @Override
    public void executeExit() throws IOException, ClientDisconnectedException {
        /*System.out.println("Спасибо, что используете Mini Twitter!");
        System.exit(0);*/
        out.append("<<<<<<Спасибо что используете Mini Twitter!>>>>>").append("\n");
        out.flush();
        throw new ClientDisconnectedException();
    }

    @Override
    public void executeLogin() throws IOException{
        /*if (this.securityComponent.getAuthentication() != null) {
            System.out.println("Для выполнения данной команды необходимо выйти из системы.");
            return;
        }

        System.out.println("Вход в систему!");

        System.out.print("Введите логин: ");
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

        try{
            User user = userService.getUserByLogin(login);
            System.out.print("Введите пароль: ");
            String password = this.scanner.nextLine();
            if(!user.getPassword().equals(password)){
                System.out.println("Пароль введен неверно");
                return;
            }

            securityComponent.setAuthentication(user);

            System.out.println("Добро пожаловать, " + user.whatIsYourName() + "!");
            System.out.println("Вход в систему произошел успешно!");
        }catch (UserNotFoundException ex){
            System.out.println(ex.getMessage());
        }*/

        /*User user = userService.getUserByLogin(login);
        if(user == null){
            System.out.println("Пользователь с таким логином не найден.");
            return;
        }

        System.out.print("Введите пароль: ");
        String password = this.scanner.nextLine();
        if(!user.getPassword().equals(password)){
            System.out.println("Пароль введен неверно");
            return;
        }

        securityComponent.setAuthentication(user);

        System.out.println("Добро пожаловать, " + user.whatIsYourName() + "!");
        System.out.println("Вход в систему произошел успешно!");*/

        if (this.securityComponent.getAuthentication(userIp) != null) {
            out.append("Для выполнения данной команды необходимо выйти из системы.").append("\n");
            out.flush();
            return;
        }

        out.append("<<<<<<  Вход в систему  >>>>>").append("\n");

        out.append("Введите логин: ");
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

            out.append("Введите пароль: ");
            out.flush();
            String password = in.readLine();
            if (!user.getPassword().equals(password)) {
                out.append("Пароль введен неверно").append("\n");
                out.flush();
                return;
            }

            securityComponent.setAuthentication(userIp, user);

            out.append("Добро пожаловать, " + user.whatIsYourName() + "!").append("\n");
            out.append("<<<<<<  Вход в систему прошел успешно >>>>>").append("\n");
            out.flush();
        } catch (UserNotFoundException ex) {
            out.append(ex.getMessage()).append("\n");
        }
    }

    @Override
    public void executeLogout() throws IOException{
        /*if (this.securityComponent.getAuthentication() == null) {
            System.out.println("Для выполнения данной команды необходимо войти в систему.");
            return;
        }

        System.out.println("Выход из системы!");
        System.out.println("До свидания, " + securityComponent.getAuthentication().whatIsYourName() + "!");
        securityComponent.removeAuthentication();*/

        if (this.securityComponent.getAuthentication(userIp) == null) {
            out.append("Для выполнения данной команды необходимо войти в систему.").append("\n");
            out.flush();
            return;
        }

        out.append("<<<<<<  Выход из системы  >>>>>").append("\n");
        out.append("До свидания, " + securityComponent.getAuthentication(userIp).whatIsYourName() + "!").append("\n");
        out.flush();
        securityComponent.removeAuthentication(userIp);
    }

}
