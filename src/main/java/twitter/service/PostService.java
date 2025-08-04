package twitter.service;

import twitter.entity.post.Post;
import twitter.entity.user.User;
import twitter.exception.UnknownUserTypeException;

import java.util.List;

public interface PostService {

    Post createPost(Post post);

    /*Post[] getAllPostsByUser(User user);*/
    List<Post> getAllPostsByUser(User user);

    /*Post[] getAllPosts();*/
    List<Post> getAllPosts();

    /*Post[] getAllPostsByTag(String tag);*/
    List<Post> getAllPostsByTag(String tag);

    /*Post[] getAllPostsByUserType(int userType) throws UnknownUserTypeException;*/
    List<Post> getAllPostsByUserType(int userType) throws UnknownUserTypeException;

    /*Post[] createSeveralPosts(List<Post> posts);*/
    List<Post> createSeveralPosts(List<Post> posts);
}
