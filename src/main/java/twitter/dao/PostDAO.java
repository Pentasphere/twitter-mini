package twitter.dao;

import twitter.entity.post.Post;

import java.util.List;

public interface PostDAO {

    Post saveNewPost(Post post);

    /*Post[] getAllPosts();*/
    List<Post> getAllPosts();

    /*Post[] getAllPostsByUser(int userId);*/
    List<Post> getAllPostsByUser(int userId);

    /*Post[] getAllPostsByTag(String tag);*/
    List<Post> getAllPostsByTag(String tag);

    /*Post[] getAllPostsByUserIdIn(int[] userIds);*/
    List<Post> getAllPostsByUserIdIn(int[] userIds);
}
