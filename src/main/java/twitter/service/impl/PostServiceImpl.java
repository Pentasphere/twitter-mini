package twitter.service.impl;

import twitter.configuration.Component;
import twitter.configuration.Injection;
import twitter.dao.PostDAO;
import twitter.entity.post.Post;
import twitter.entity.user.User;
import twitter.exception.UnknownUserTypeException;
import twitter.service.PostService;
import twitter.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class PostServiceImpl implements PostService {

    /*private static PostService instance;

    public static PostService getInstance() {
        if (instance == null) {
            instance = new PostService(
                    FilePostDao.getInstance(),
                    UserServiceImpl.getInstance()
            );
        }
        return instance;
    }*/

    private final PostDAO postDAO;
    private final UserService userService;

    @Injection
    public PostServiceImpl(
            PostDAO postDAO,
            UserService userService
    ) {
        this.postDAO = postDAO;
        this.userService = userService;
    }

    /*private PostService(
            PostDAO postDAO,
            UserService userService
    ) {
        this.postDAO = postDAO;
        this.userService = userService;
    }*/

    @Override
    public Post createPost(Post post) {
        return this.postDAO.saveNewPost(post);
    }

    /*@Override
    public Post[] getAllPostsByUser(User user) {
        Post[] userPosts = postDAO.getAllPostsByUser(user.getId());
        if (userPosts.length == 0) {
            return new Post[0];
        }
        Post[] result = new Post[userPosts.length];
        int index = 0;
        for (int i = userPosts.length - 1; i >= 0; i--) {
            result[index] = userPosts[i];
            index++;
        }
        return result;
    }*/
    @Override
    public List<Post> getAllPostsByUser(User user) {
        List<Post> userPosts = postDAO.getAllPostsByUser(user.getId());
        if (userPosts.isEmpty()) {
            return List.of();
        }
        List<Post> result = new java.util.ArrayList<>();
        for (int i = userPosts.size() - 1; i >= 0; i--) {
            result.add(userPosts.get(i));
        }
        return result;
    }

    /*@Override
    public Post[] getAllPosts() {
        Post[] posts = postDAO.getAllPosts();
        if (posts.length == 0) {
            return new Post[0];
        }
        Post[] result = new Post[posts.length];
        int index = 0;
        for (int i = posts.length - 1; i >= 0; i--) {
            result[index] = posts[i];
            index++;
        }
        return result;
    }*/
    @Override
    public List<Post> getAllPosts() {
        /*List<Post> posts = postDAO.getAllPosts();
        if (posts.isEmpty()) {
            return List.of();
        }
        List<Post> result = new java.util.ArrayList<>();
        for (int i = posts.size() - 1; i >= 0; i--) {
            result.add(posts.get(i));
        }
        return result;*/

        return postDAO.getAllPosts();
    }

    /*@Override
    public Post[] getAllPostsByTag(String tag) {
        Post[] postsByTag = postDAO.getAllPostsByTag(tag);
        if (postsByTag.length == 0) {
            return new Post[0];
        }
        Post[] result = new Post[postsByTag.length];
        int index = 0;
        for (int i = postsByTag.length - 1; i >= 0; i--) {
            result[index] = postsByTag[i];
            index++;
        }
        return result;
    }*/
    @Override
    public List<Post> getAllPostsByTag(String tag) {
        /*List<Post> postsByTag = postDAO.getAllPostsByTag(tag);
        if (postsByTag.isEmpty()) {
            return List.of();
        }
        List<Post> result = new java.util.ArrayList<>();
        for (int i = postsByTag.size() - 1; i >= 0; i--) {
            result.add(postsByTag.get(i));
        }
        return result;*/

        return postDAO.getAllPostsByTag(tag);
    }

    /*@Override
    public Post[] getAllPostsByUserType(int userType) throws UnknownUserTypeException {
        *//*User[] usersByType = userService.getUsersByType(userType);*//*
        List<User> usersByType = userService.getUsersByType(userType);
        *//*if (usersByType.length == 0) {
            return new Post[0];
        }*//*
        if (usersByType.isEmpty()) {
            return new Post[0];
        }
        *//*int[] usersIds = new int[usersByType.length];*//*
        int[] usersIds = new int [usersByType.size()];
        for (int i = 0; i < usersByType.size(); i++) {
        *//*for (int i = 0; i < usersByType.length; i++) {*//*
            *//*usersIds[i] = usersByType[i].getId();*//*
            usersIds[i] = usersByType.get(i).getId();
        }
        return postDAO.getAllPostsByUserIdIn(usersIds);
    }*/
    @Override
    public List<Post> getAllPostsByUserType(int userType) throws UnknownUserTypeException {
        /*User[] usersByType = userService.getUsersByType(userType);*/
        List<User> usersByType = userService.getUsersByType(userType);
        /*if (usersByType.length == 0) {
            return new Post[0];
        }*/
        if (usersByType.isEmpty()) {
            return List.of();
        }
        /*int[] usersIds = new int[usersByType.length];*/
        int[] usersIds = new int [usersByType.size()];
        for (int i = 0; i < usersByType.size(); i++) {
            /*for (int i = 0; i < usersByType.length; i++) {*/
            /*usersIds[i] = usersByType[i].getId();*/
            usersIds[i] = usersByType.get(i).getId();
        }
        return postDAO.getAllPostsByUserIdIn(usersIds);
    }

    /*@Override
    public Post[] createSeveralPosts(List<Post> posts) {
        if(Objects.isNull(posts) || posts.isEmpty()) {
            return new Post[0];
        }
        for (Post post : posts) {
            createPost(post);
        }
        return posts.toArray(new Post[0]);
    }*/
    @Override
    public List<Post> createSeveralPosts(List<Post> posts) {
        if(Objects.isNull(posts) || posts.isEmpty()) {
            return List.of();
        }
        for (Post post : posts) {
            createPost(post);
        }
        return posts;
    }
}
