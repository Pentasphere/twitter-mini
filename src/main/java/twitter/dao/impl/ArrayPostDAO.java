/*
package twitter.dao.impl;

import twitter.dao.PostDAO;
import twitter.entity.post.Post;

import java.time.LocalDateTime;

public class ArrayPostDAO implements PostDAO {

    private static ArrayPostDAO instance;

    public static ArrayPostDAO getInstance() {
        if (instance == null) {
            instance = new ArrayPostDAO();
        }
        return instance;
    }

    private final Post[] posts = new Post[5];
    private int id = 0;

    private ArrayPostDAO() {

    }

    @Override
    public Post saveNewPost(Post post) {
        post.setId(this.id + 1);
        post.setCreationDate(LocalDateTime.now());
        this.posts[this.id] = post;

        this.id++;

        return post;
    }

    @Override
    public Post[] getAllPosts() {
        if (this.posts[0] == null) {
            return new Post[0];
        }
        int i = 0;
        while (i < this.posts.length && this.posts[i] != null) {
            i++;
        }
        Post[] currentPosts = new Post[i];
        i = 0;
        while (i < this.posts.length && this.posts[i] != null) {
            currentPosts[i] = this.posts[i];
            i++;
        }
        return currentPosts;
    }

    @Override
    public Post[] getAllPostsByUser(int userId) {
        if (this.posts[0] == null) {
            return new Post[0];
        }
        int i = 0;
        int counter = 0;
        while (i < this.posts.length && this.posts[i] != null) {
            if (this.posts[i].getAuthorId() == userId) {
                counter++;
            }
            i++;
        }
        if (counter == 0) {
            return new Post[0];
        }
        Post[] userPosts = new Post[counter];
        i = 0;
        int j = 0;
        while (i < this.posts.length && this.posts[i] != null) {
            if (this.posts[i].getAuthorId() == userId) {
                userPosts[j] = this.posts[i];
                j++;
            }
            i++;
        }
        return userPosts;
    }

    @Override
    public Post[] getAllPostsByTag(String tag) {
        if (this.posts[0] == null) {
            return new Post[0];
        }
        int i = 0;
        int counter = 0;
        while (i < this.posts.length && this.posts[i] != null) {
            if (this.posts[i].hasTag(tag)) {
                counter++;
            }
            i++;
        }
        if (counter == 0) {
            return new Post[0];
        }
        Post[] postsByTag = new Post[counter];
        i = 0;
        int j = 0;
        while (i < this.posts.length && this.posts[i] != null) {
            if (this.posts[i].hasTag(tag)) {
                postsByTag[j] = this.posts[i];
                j++;
            }
            i++;
        }
        return postsByTag;
    }

    @Override
    public Post[] getAllPostsByUserIdIn(int[] userIds) {
        if (this.posts[0] == null) {
            return new Post[0];
        }
        int i = 0;
        int counter = 0;
        while (i < this.posts.length && this.posts[i] != null) {
            if (this.isAuthorIdInArray(this.posts[i].getAuthorId(), userIds)) {
                counter++;
            }
            i++;
        }
        if (counter == 0) {
            return new Post[0];
        }
        Post[] postsByUserId = new Post[counter];
        i = 0;
        int j = 0;
        while (i < this.posts.length && this.posts[i] != null) {
            if (this.isAuthorIdInArray(this.posts[i].getAuthorId(), userIds)) {
                postsByUserId[j] = this.posts[i];
                j++;
            }
            i++;
        }
        return postsByUserId;
    }

    private boolean isAuthorIdInArray(int authorId, int[] array) {
        if(array == null){
            return false;
        }
        for(int id : array){
            if(id == authorId){
                return true;
            }
        }
        return false;
    }
}
*/
