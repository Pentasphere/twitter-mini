package twitter.controller.v1.impl;

import twitter.controller.v1.PostController;
import twitter.dto.v1.PostResponseDto;
import twitter.entity.post.Post;
import twitter.entity.user.User;
import twitter.exception.TwitterUploadException;
import twitter.exception.UnknownUserTypeException;
import twitter.exception.UserNotFoundException;

import twitter.mapper.PostMapper;
import twitter.security.SecurityComponent;
import twitter.service.FileUploadService;

import twitter.service.PostService;
import twitter.service.UserService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/*@Component*/
public class PostControllerImpl implements PostController {

    /*private static PostController instance;

    public static PostController getInstance() {
        if (instance == null) {
            instance = new PostController(
                    PostService.getInstance(),
                    InMemorySecurityComponent.getInstance(),
                    new Scanner(System.in),
                    PostMapper.getInstance(),
                    UserServiceImpl.getInstance(),
                    FileUploadServiceImpl.getInstance()
            );
        }
        return instance;
    }*/

    /*private final Scanner scanner;*/
    private final PostService postService;
    private final SecurityComponent securityComponent;
    private final PostMapper postMapper;
    private final UserService userService;
    private final FileUploadService fileUploadService;
    private final BufferedReader in;
    private final BufferedWriter out;
    private final String userIp;

    /*@Injection*/
    public PostControllerImpl(
            PostService postService,
            SecurityComponent securityComponent,
            PostMapper postMapper,
            UserService userService,
            FileUploadService fileUploadService,
            BufferedReader in,
            BufferedWriter out,
            String userIp
    ) {
        this.postService = postService;
        this.securityComponent = securityComponent;
        /*this.scanner = new Scanner(System.in);*/
        this.postMapper = postMapper;
        this.userService = userService;
        this.fileUploadService = fileUploadService;
        this.in = in;
        this.out = out;
        this.userIp = userIp;
    }

    /*private PostController(
            PostService postService,
            SecurityComponent securityComponent,
            Scanner scanner,
            PostMapper postMapper,
            UserServiceImpl userService,
            FileUploadService fileUploadService
    ) {
        this.postService = postService;
        this.securityComponent = securityComponent;
        this.scanner = scanner;
        this.postMapper = postMapper;
        this.userService = userService;
        this.fileUploadService = fileUploadService;
    }*/

    @Override
    public void executeReadPosts() throws IOException {
        /*if (this.securityComponent.getAuthentication() == null) {
            System.out.println("Для выполнения данной команды необходимо войти в систему.");
            return;
        }

        System.out.print("Файл для чтения: ");
        String fileName = scanner.nextLine();
        System.out.println("Считывание публикаций...");

        Thread thread = new Thread(() -> {
            try {
                List<Post> newPosts = this.fileUploadService.uploadPosts(fileName);
                Post[] createdPosts = this.postService.createSeveralPosts(newPosts);

                System.out.println("Было добавлено " + createdPosts.length + " публикаций");
            } catch (TwitterUploadException ex) {
                System.out.println(ex.getMessage());
            }
        });
        thread.start();*/

        if (this.securityComponent.getAuthentication(userIp) == null) {
            out.append("Для выполнения данной команды необходимо войти в систему.").append("\n");
            out.flush();
            return;
        }

        out.append("Файл для чтения: ");
        out.flush();
        String filename = in.readLine();
        out.append("Считывание публикаций...").append("\n");
        out.flush();

        Thread thread = new Thread(() -> {
            try {
                List<Post> newPosts = this.fileUploadService.uploadPosts(filename);
                /*Post[] createdPosts = this.postService.createSeveralPosts(newPosts);*/
                List<Post> createdPosts = this.postService.createSeveralPosts(newPosts);

                /*out.append("Было добавлено " + createdPosts.length + " публикаций").append("\n");*/
                out.append("Было добавлено " + createdPosts.size() + " публикаций").append("\n");
                out.flush();
            } catch (TwitterUploadException | IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        thread.start();
    }

    @Override
    public void executeAddPost() throws IOException{
        /*if (this.securityComponent.getAuthentication() == null) {
            System.out.println("Для выполнения данной команды необходимо войти в систему.");
            return;
        }

        System.out.println("Создание новой публикации!");

        System.out.print("Введите тему публикации: ");
        String topic = scanner.nextLine();
        if (topic == null || topic.trim().isEmpty()) {
            System.out.println("Тема публикации не может быть пустой.");
            return;
        }

        System.out.print("Введите текст публикации: ");
        String text = scanner.nextLine();
        if (text == null || text.trim().isEmpty()) {
            System.out.println("Текст публикации не может быть пустым.");
            return;
        }

        System.out.print("Введите теги публикации(может быть пустым, для отделения тегов использовать ','): ");
        String tags = scanner.nextLine();

        Post post = new Post();

        User autenticatedUser = securityComponent.getAuthentication();
        post.setAuthorId(autenticatedUser.getId());

        post.setTopic(topic);
        post.setText(text);

        String[] tagArray = tags.split(",");
        post.setTags(tagArray);

        try {
            Post createdPost = postService.createPost(post);
            PostResponseDto responseDto = postMapper.mapToDto(createdPost);

            System.out.println(responseDto);
            System.out.println("Конец создания публикации!");
        } catch (UserNotFoundException ex) {
            System.out.println(ex.getMessage());
        }*/

        /*Post createdPost = postService.createPost(post);
        PostResponseDto responseDto = postMapper.mapToDto(createdPost);

        System.out.println(responseDto);
        System.out.println("Конец создания публикации!");*/

        if (this.securityComponent.getAuthentication(userIp) == null) {
            out.append("Для выполнения данной команды необходимо войти в систему.").append("\n");
            out.flush();
            return;
        }

        out.append("<<<<<<  Создание новой публикации  >>>>>").append("\n");

        out.append("Введите тему публикации: ");
        out.flush();
        String topic = in.readLine();
        if (topic == null || topic.trim().isEmpty()) {
            out.append("Тема публикации не может быть пустой").append("\n");
            out.flush();
            return;
        }

        out.append("Введите текст публикации: ");
        out.flush();
        String text = in.readLine();
        if (text == null || text.trim().isEmpty()) {
            out.append("Текст публикации не может быть пустой").append("\n");
            out.flush();
            return;
        }

        out.append("Введите теги публикации(может быть пустым, для отделения тегов использовать ','): ");
        out.flush();
        String tags = in.readLine();

        Post post = new Post();

        User authenticatedUser = securityComponent.getAuthentication(userIp);
        /*post.setAuthorId(authenticatedUser.getId());*/
        post.setAuthor(authenticatedUser);

        post.setTopic(topic);
        post.setText(text);

        String[] tagArray = tags.split(",");
        post.setTags(tagArray);

        try {
            Post createdPost = postService.createPost(post);
            PostResponseDto responseDto = postMapper.mapToDto(createdPost);

            out.append(responseDto.toString()).append("\n");
            out.append("<<<<<<  Конец создания публикации  >>>>>").append("\n");
            out.flush();
        } catch (UserNotFoundException ex) {
            out.append(ex.getMessage()).append("\n");
            out.flush();
        }
    }

    @Override
    public void executeMyPosts() throws IOException{
        /*if (this.securityComponent.getAuthentication() == null) {
            System.out.println("Для выполнения данной команды необходимо войти в систему.");
            return;
        }

        System.out.println("Мои публикации!");
        User autenticatedUser = securityComponent.getAuthentication();
        Post[] posts = postService.getAllPostsByUser(autenticatedUser);

        try {
            for (Post post : posts) {
                PostResponseDto responseDto = postMapper.mapToDto(post);
                System.out.println(responseDto);
            }
            System.out.println("Конец моих публикаций!");
        } catch (UserNotFoundException ex) {
            System.out.println(ex.getMessage());
        }*/

        /*for (Post post : posts) {
            PostResponseDto responseDto = postMapper.mapToDto(post);
            System.out.println(responseDto);
        }
        System.out.println("Конец моих публикаций!");*/

        if (this.securityComponent.getAuthentication(userIp) == null) {
            out.append("Для выполнения данной команды необходимо войти в систему.").append("\n");
            out.flush();
            return;
        }

        out.append("<<<<<<  Мои публикации  >>>>>").append("\n");
        User authenticatedUser = securityComponent.getAuthentication(userIp);
        /*Post[] posts = postService.getAllPostsByUser(authenticatedUser);*/
        List<Post> posts = postService.getAllPostsByUser(authenticatedUser);
        try {
            for (Post post : posts) {
                PostResponseDto responseDto = postMapper.mapToDto(post);
                out.append(responseDto.toString()).append("\n");
            }
            out.append("<<<<<<  Конец моих публикаций  >>>>>").append("\n");
            out.flush();
        } catch (UserNotFoundException ex) {
            out.append(ex.getMessage()).append("\n");
            out.flush();
        }
    }

    @Override
    public void executeAllPosts() throws IOException{
        /*if (this.securityComponent.getAuthentication() == null) {
            System.out.println("Для выполнения данной команды необходимо войти в систему.");
            return;
        }

        System.out.println("Все публикации!");
        Post[] posts = postService.getAllPosts();

        try {
            for (Post post : posts) {
                PostResponseDto responseDto = postMapper.mapToDto(post);
                System.out.println(responseDto);
            }
            System.out.println("Конец всех публикаций!");
        } catch (UserNotFoundException ex) {
            System.out.println(ex.getMessage());
        }*/

        /*for (Post post : posts) {
            PostResponseDto responseDto = postMapper.mapToDto(post);
            System.out.println(responseDto);
        }
        System.out.println("Конец всех публикаций!");*/

        if (this.securityComponent.getAuthentication(userIp) == null) {
            out.append("Для выполнения данной команды необходимо войти в систему.").append("\n");
            out.flush();
            return;
        }

        out.append("<<<<<<  Все публикации  >>>>>").append("\n");
        /*Post[] posts = postService.getAllPosts();*/
        List<Post> posts = postService.getAllPosts();
        try {
            for (Post post : posts) {
                PostResponseDto responseDto = postMapper.mapToDto(post);
                out.append(responseDto.toString()).append("\n");
            }
            out.append("<<<<<<  Конец всех публикаций  >>>>>").append("\n");
            out.flush();
        } catch (UserNotFoundException ex) {
            out.append(ex.getMessage()).append("\n");
            out.flush();
        }
    }

    @Override
    public void executePostsByTag() throws IOException{
        /*if (this.securityComponent.getAuthentication() == null) {
            System.out.println("Для выполнения данной команды необходимо войти в систему.");
            return;
        }

        System.out.print("Введите тег публикаций: ");
        String tag = scanner.nextLine();
        if (tag == null || tag.trim().isEmpty()) {
            System.out.println("Тег не может быть пустым");
            return;
        }

        try {
            System.out.println("Все публикации по тегу!");
            Post[] posts = postService.getAllPostsByTag(tag);
            for (Post post : posts) {
                PostResponseDto responseDto = postMapper.mapToDto(post);
                System.out.println(responseDto);
            }
            System.out.println("Конец всех публикаций по тегу!");
        } catch (UserNotFoundException ex) {
            System.out.println(ex.getMessage());
        }*/

        /*System.out.println("Все публикации по тегу!");
        Post[] posts = postService.getAllPostsByTag(tag);
        for (Post post : posts) {
            PostResponseDto responseDto = postMapper.mapToDto(post);
            System.out.println(responseDto);
        }
        System.out.println("Конец всех публикаций по тегу!");*/

        if (this.securityComponent.getAuthentication(userIp) == null) {
            out.append("Для выполнения данной команды необходимо войти в систему.").append("\n");
            out.flush();
            return;
        }

        out.append("Введите тег публикаций: ");
        out.flush();
        String tag = in.readLine();
        if (tag == null || tag.trim().isEmpty()) {
            out.append("Тег не может быть пустым").append("\n");
            out.flush();
            return;
        }
        try {
            out.append("<<<<<<  Все публикации по тегу >>>>>").append("\n");
            /*Post[] posts = postService.getAllPostsByTag(tag);*/
            List<Post> posts = postService.getAllPostsByTag(tag);
            for (Post post : posts) {
                PostResponseDto responseDto = postMapper.mapToDto(post);
                out.append(responseDto.toString()).append("\n");
            }
            out.append("<<<<<<  Конец всех публикаций по тегу  >>>>>").append("\n");
            out.flush();
        } catch (UserNotFoundException ex) {
            out.append(ex.getMessage()).append("\n");
            out.flush();
        }
    }

    @Override
    public void executePostsByLogin() throws IOException{
        /*if (this.securityComponent.getAuthentication() == null) {
            System.out.println("Для выполнения данной команды необходимо войти в систему.");
            return;
        }

        System.out.print("Введите логин, чьи публикации показать: ");
        String login = scanner.nextLine();
        if (login == null || login.trim().isEmpty()) {
            System.out.println("Логин не может быть пустым");
            return;
        }
        login = login.trim();
        if (login.contains(" ")) {
            System.out.println("Логин не может содержать пробелы.");
            return;
        }*/
        /*User user = userService.getUserByLogin(login);
        if (user == null) {
            System.out.println("Пользователь с таким логином не найден.");
            return;
        }*/

        /*try {
            User user = userService.getUserByLogin(login);
            System.out.println("Публикации по логину!");
            Post[] posts = postService.getAllPostsByUser(user);
            for (Post post : posts) {
                PostResponseDto responseDto = postMapper.mapToDto(post);
                System.out.println(responseDto);
            }
            System.out.println("Конец публикаций по логину!");
        } catch (UserNotFoundException ex) {
            System.out.println(ex.getMessage());
        }*/

        /*System.out.println("Публикации по логину!");
        Post[] posts = postService.getAllPostsByUser(user);
        for (Post post : posts) {
            PostResponseDto responseDto = postMapper.mapToDto(post);
            System.out.println(responseDto);
        }
        System.out.println("Конец публикаций по логину!");*/

        if (this.securityComponent.getAuthentication(userIp) == null) {
            out.append("Для выполнения данной команды необходимо войти в систему.").append("\n");
            out.flush();
            return;
        }

        out.append("Введите логин, чьи публикации показать: ");
        out.flush();
        String login = in.readLine();
        if (login == null || login.trim().isEmpty()) {
            out.append("Логин не может быть пустым").append("\n");
            out.flush();
            return;
        }
        login = login.trim();
        if (login.contains(" ")) {
            out.append("Логин не может содержать пробелы");
            out.flush();
            return;
        }

        try {
            User user = userService.getUserByLogin(login);

            out.append("<<<<<<  Публикации по логину  >>>>>").append("\n");
            /*Post[] posts = postService.getAllPostsByUser(user);*/
            List<Post> posts = postService.getAllPostsByUser(user);
            for (Post post : posts) {
                PostResponseDto responseDto = postMapper.mapToDto(post);
                out.append(responseDto.toString()).append("\n");
            }
            out.append("<<<<<<  Конец публикаций по логину  >>>>>").append("\n");
            out.flush();
        } catch (UserNotFoundException ex) {
            out.append(ex.getMessage()).append("\n");
            out.flush();
        }
    }

    @Override
    public void executePostsByUserType() throws IOException{
        /*if (this.securityComponent.getAuthentication() == null) {
            System.out.println("Для выполнения данной команды необходимо войти в систему.");
            return;
        }

        System.out.print("Введите тип пользователя (0 - человек, 1 - организация): ");
        int userType = Integer.parseInt(this.scanner.nextLine());
        if (userType != 0 && userType != 1) {
            System.out.println("Введен неверный тип пользователя.");
            return;
        }

        try {
            System.out.println("Публикации по типу пользователя!");
            Post[] posts = postService.getAllPostsByUserType(userType);
            for (Post post : posts) {
                PostResponseDto responseDto = postMapper.mapToDto(post);
                System.out.println(responseDto);
            }
            System.out.println("Конец публикаций по типу пользователя!");
        } catch (UserNotFoundException | UnknownUserTypeException ex) {
            System.out.println(ex.getMessage());
        }*/

        /*System.out.println("Публикации по типу пользователя!");
        Post[] posts = postService.getAllPostsByUserType(userType);
        for (Post post : posts) {
            PostResponseDto responseDto = postMapper.mapToDto(post);
            System.out.println(responseDto);
        }
        System.out.println("Конец публикаций по типу пользователя!");*/

        if (this.securityComponent.getAuthentication(userIp) == null) {
            out.append("Для выполнения данной команды необходимо войти в систему.").append("\n");
            out.flush();
            return;
        }

        out.append("Введите тип пользователя (0 - человек, 1 - организация): ");
        out.flush();
        int userType = Integer.parseInt(in.readLine());
        if (userType != 0 && userType != 1) {
            out.append("Введен неверный тип пользователя.").append("\n");
            out.flush();
            return;
        }

        try {
            out.append("<<<<<<  Публикации по типу пользователя  >>>>>").append("\n");
            /*Post[] posts = postService.getAllPostsByUserType(userType);*/
            List<Post> posts = postService.getAllPostsByUserType(userType);
            for (Post post : posts) {
                PostResponseDto responseDto = postMapper.mapToDto(post);
                out.append(responseDto.toString()).append("\n");
            }
            out.append("<<<<<<  Конец публикаций по типу пользователя  >>>>>").append("\n");
            out.flush();
        } catch (UserNotFoundException | UnknownUserTypeException ex) {
            out.append(ex.getMessage()).append("\n");
            out.flush();
        }
    }
}
