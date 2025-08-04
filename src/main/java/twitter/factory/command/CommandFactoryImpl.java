package twitter.factory.command;

import twitter.configuration.Component;
import twitter.configuration.Injection;

import twitter.controller.AuthenticationController;

import twitter.controller.InfoController;

import twitter.controller.PostController;

/*import twitter.factory.impl.*;*/
/*import twitter.listener.TwitterCommand;*/
import twitter.controller.RegistrationController;
import twitter.exception.UnknownCommandException;
import twitter.factory.CommandFactory;
import twitter.runner.TwitterCommandEnum;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*@Component*/
public class CommandFactoryImpl implements CommandFactory {
    private final Map<TwitterCommandEnum, CommandHandler> factory;

    private final AuthenticationController authenticationController;
    private final RegistrationController registrationController;
    private final InfoController infoController;
    private final PostController postController;
    /*private final SecurityComponent securityComponent;*/

    /*private static CommandFactory instance;

    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }*/

    /*@Injection*/
    public CommandFactoryImpl(
            AuthenticationController authenticationController,
            RegistrationController registrationController,
            InfoController infoController,
            PostController postController
            /*SecurityComponent securityComponent*/
    ) {
        this.factory = new HashMap<>();
        this.authenticationController = authenticationController;
        this.registrationController = registrationController;
        this.infoController = infoController;
        this.postController = postController;
        /*this.securityComponent = securityComponent;*/
        this.init();
    }

    /*private CommandFactory() {
        this.factory = new HashMap<>();
        this.authenticationController = AuthenticationController.getInstance();
        this.registrationController = RegistrationController.getInstance();
        this.infoController = InfoController.getInstance();
        this.postController = PostController.getInstance();
        this.securityComponent = InMemorySecurityComponent.getInstance();
        this.init();
    }*/

    /*private void init() {
        this.factory.put(TwitterCommandEnum.EXIT_COMMAND, *//*new ExitCommandHandler(this.authenticationController)*//* ()->authenticationController.executeExit());
        this.factory.put(TwitterCommandEnum.HELP_COMMAND, *//*new HelpCommandHandler(this.infoController)*//* ()->infoController.executeHelp());
        this.factory.put(TwitterCommandEnum.REGISTER_COMMAND, *//*new RegisterCommandHandler(this.registrationController, this.securityComponent)*//* ()->registrationController.executeRegister()*//*{
            if (this.securityComponent.getAuthentication() != null) {
                System.out.println("Для выполнения данной команды необходимо выйти из системы.");
                return;
            }
            registrationController.executeRegister();
        }*//*);
        this.factory.put(TwitterCommandEnum.LOGIN_COMMAND, *//*new LoginCommandHandler(this.authenticationController, this.securityComponent)*//* ()->authenticationController.executeLogin()*//*{
            if (this.securityComponent.getAuthentication() != null) {
                System.out.println("Для выполнения данной команды необходимо выйти из системы.");
                return;
            }
            authenticationController.executeLogin();
        }*//*);
        this.factory.put(TwitterCommandEnum.LOGOUT_COMMAND, *//*new LogoutCommandHandler(this.authenticationController, this.securityComponent)*//* ()->authenticationController.executeLogout()*//*{
            if (this.securityComponent.getAuthentication() == null) {
                System.out.println("Для выполнения данной команды необходимо войти в систему.");
                return;
            }
            authenticationController.executeLogout();
        }*//*);
        this.factory.put(TwitterCommandEnum.INFO_COMMAND, *//*new InfoCommandHandler(this.infoController, this.securityComponent)*//*()->infoController.executeInfo()*//*{
            if (this.securityComponent.getAuthentication() == null) {
                System.out.println("Для выполнения данной команды необходимо войти в систему.");
                return;
            }
            infoController.executeInfo();
        }*//*);
        this.factory.put(TwitterCommandEnum.INFO_BY_LOGIN_COMMAND, *//*new InfoByLoginCommandHandler(this.infoController, this.securityComponent)*//* ()->infoController.executeInfoByLogin()*//*{
            if (this.securityComponent.getAuthentication() == null) {
                System.out.println("Для выполнения данной команды необходимо войти в систему.");
                return;
            }
            infoController.executeInfoByLogin();
        }*//*);
        this.factory.put(TwitterCommandEnum.INFO_ALL_COMMAND, *//*new InfoAllCommandHandler(this.infoController, this.securityComponent)*//* ()->infoController.executeInfoAll()*//*{
            if (this.securityComponent.getAuthentication() == null) {
                System.out.println("Для выполнения данной команды необходимо войти в систему.");
                return;
            }
            infoController.executeInfoAll();
        }*//*);
        this.factory.put(TwitterCommandEnum.ADD_POST_COMMAND, *//*new AddPostCommandHandler(this.postController, this.securityComponent)*//* ()->postController.executeAddPost()*//*{
            if (this.securityComponent.getAuthentication() == null) {
                System.out.println("Для выполнения данной команды необходимо войти в систему.");
                return;
            }
            postController.executeAddPost();
        }*//*);
        this.factory.put(TwitterCommandEnum.MY_POSTS_COMMAND, *//*new MyPostsCommandHandler(this.postController, this.securityComponent)*//* ()->postController.executeMyPosts()*//*{
            if (this.securityComponent.getAuthentication() == null) {
                System.out.println("Для выполнения данной команды необходимо войти в систему.");
                return;
            }
            postController.executeMyPosts();
        }*//*);
        this.factory.put(TwitterCommandEnum.ALL_POSTS_COMMAND, *//*new AllPostsCommandHandler(this.postController, this.securityComponent)*//* ()->postController.executeAllPosts()*//*{
            if (this.securityComponent.getAuthentication() == null) {
                System.out.println("Для выполнения данной команды необходимо войти в систему.");
                return;
            }
            postController.executeAllPosts();
        }*//*);
        this.factory.put(TwitterCommandEnum.POSTS_BY_TAG_COMMAND, *//*new PostsByTagCommandHandler(this.postController, this.securityComponent)*//* ()->postController.executePostsByTag()*//*{
            if (this.securityComponent.getAuthentication() == null) {
                System.out.println("Для выполнения данной команды необходимо войти в систему.");
                return;
            }
            postController.executePostsByTag();
        }*//*);
        this.factory.put(TwitterCommandEnum.POSTS_BY_LOGIN_COMMAND, *//*new PostsByLoginCommandHandler(this.postController, this.securityComponent)*//* ()->postController.executePostsByLogin()*//*{
            if (this.securityComponent.getAuthentication() == null) {
                System.out.println("Для выполнения данной команды необходимо войти в систему.");
                return;
            }
            postController.executePostsByLogin();
        }*//*);
        this.factory.put(TwitterCommandEnum.POSTS_BY_USER_TYPE_COMMAND, *//*new PostsByUserTypeCommandHandler(this.postController, this.securityComponent)*//* ()->postController.executePostsByUserType()*//*{
            if (this.securityComponent.getAuthentication() == null) {
                System.out.println("Для выполнения данной команды необходимо войти в систему.");
                return;
            }
            postController.executePostsByUserType();
        }*//*);
        this.factory.put(TwitterCommandEnum.READ_USERS_COMMAND, ()->registrationController.executeReadUsers()*//*{
            if (this.securityComponent.getAuthentication() == null) {
                System.out.println("Для выполнения данной команды необходимо войти в систему.");
                return;
            }
            registrationController.executeReadUsers();
        }*//*);
        this.factory.put(TwitterCommandEnum.READ_POSTS_COMMAND, ()->postController.executeReadPosts()*//*{
            if (this.securityComponent.getAuthentication() == null) {
                System.out.println("Для выполнения данной команды необходимо войти в систему.");
                return;
            }
            postController.executeReadPosts();
        }*//*);
    }*/

    private void init() {
        this.factory.put(TwitterCommandEnum.EXIT_COMMAND, () -> {
            try {
                authenticationController.executeExit();
            } catch (IOException e) {
                System.out.println("Ошибка при выполнении команды выхода: " + e.getMessage());
            }
        });
        this.factory.put(TwitterCommandEnum.HELP_COMMAND, () -> {
            try {
                infoController.executeHelp();
            } catch (IOException e) {
                System.out.println("Ошибка при выполнении команды помощи: " + e.getMessage());
            }
        });
        this.factory.put(TwitterCommandEnum.REGISTER_COMMAND, () -> {
            try {
                registrationController.executeRegister();
            } catch (IOException e) {
                System.out.println("Ошибка при выполнении регистрации: " + e.getMessage());
            }
        });
        this.factory.put(TwitterCommandEnum.LOGIN_COMMAND, () -> {
            try {
                authenticationController.executeLogin();
            } catch (IOException e) {
                System.out.println("Ошибка при выполнении входа: " + e.getMessage());
            }
        });
        this.factory.put(TwitterCommandEnum.LOGOUT_COMMAND, () -> {
            try {
                authenticationController.executeLogout();
            } catch (IOException e) {
                System.out.println("Ошибка при выполнении выхода: " + e.getMessage());
            }
        });
        this.factory.put(TwitterCommandEnum.INFO_COMMAND, () -> {
            try {
                infoController.executeInfo();
            } catch (IOException e) {
                System.out.println("Ошибка при получении информации: " + e.getMessage());
            }
        });
        this.factory.put(TwitterCommandEnum.INFO_BY_LOGIN_COMMAND, () -> {
            try {
                infoController.executeInfoByLogin();
            } catch (IOException e) {
                System.out.println("Ошибка при получении информации по логину: " + e.getMessage());
            }
        });
        this.factory.put(TwitterCommandEnum.INFO_ALL_COMMAND, () -> {
            try {
                infoController.executeInfoAll();
            } catch (IOException e) {
                System.out.println("Ошибка при получении общей информации: " + e.getMessage());
            }
        });
        this.factory.put(TwitterCommandEnum.ADD_POST_COMMAND, () -> {
            try {
                postController.executeAddPost();
            } catch (IOException e) {
                System.out.println("Ошибка при добавлении поста: " + e.getMessage());
            }
        });
        this.factory.put(TwitterCommandEnum.MY_POSTS_COMMAND, () -> {
            try {
                postController.executeMyPosts();
            } catch (IOException e) {
                System.out.println("Ошибка при получении ваших постов: " + e.getMessage());
            }
        });
        this.factory.put(TwitterCommandEnum.ALL_POSTS_COMMAND, () -> {
            try {
                postController.executeAllPosts();
            } catch (IOException e) {
                System.out.println("Ошибка при получении всех постов: " + e.getMessage());
            }
        });
        this.factory.put(TwitterCommandEnum.POSTS_BY_TAG_COMMAND, () -> {
            try {
                postController.executePostsByTag();
            } catch (IOException e) {
                System.out.println("Ошибка при поиске постов по тегу: " + e.getMessage());
            }
        });
        this.factory.put(TwitterCommandEnum.POSTS_BY_LOGIN_COMMAND, () -> {
            try {
                postController.executePostsByLogin();
            } catch (IOException e) {
                System.out.println("Ошибка при поиске постов по логину: " + e.getMessage());
            }
        });
        this.factory.put(TwitterCommandEnum.POSTS_BY_USER_TYPE_COMMAND, () -> {
            try {
                postController.executePostsByUserType();
            } catch (IOException e) {
                System.out.println("Ошибка при поиске постов по типу пользователя: " + e.getMessage());
            }
        });
        this.factory.put(TwitterCommandEnum.READ_USERS_COMMAND, () -> {
            try {
                registrationController.executeReadUsers();
            } catch (IOException e) {
                System.out.println("Ошибка при чтении пользователей: " + e.getMessage());
            }
        });
        this.factory.put(TwitterCommandEnum.READ_POSTS_COMMAND, () -> {
            try {
                postController.executeReadPosts();
            } catch (IOException e) {
                System.out.println("Ошибка при чтении постов: " + e.getMessage());
            }
        });
    }

    @Override
    public CommandHandler getHandler(String command) throws UnknownCommandException {
        if (!this.factory.containsKey(TwitterCommandEnum.getCommandByCommand(command))) {
            throw new UnknownCommandException("Команда " + command + " не распознана");
        }
        return this.factory.get(TwitterCommandEnum.getCommandByCommand(command));

    }
}
