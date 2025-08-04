/*
package twitter.dao.impl;

import twitter.dao.PostDAO;
import twitter.entity.post.Post;

import java.time.LocalDateTime;
import java.util.*;


public class ListPostDAO implements PostDAO {

    private static ListPostDAO instance;

    private final List<Post> posts;
    private int id = 1;

    public static ListPostDAO getInstance() {
        if (instance == null) {
            instance = new ListPostDAO();
        }
        return instance;
    }

    private ListPostDAO() {
        this.posts = new LinkedList<>();
    }

    @Override
    public Post saveNewPost(Post post) {
        post.setId(this.id);
        post.setCreationDate(LocalDateTime.now());
        this.id++;
        this.posts.add(post);

        return post;
    }

    @Override
    public Post[] getAllPosts() {
        if (this.posts.isEmpty()) {
            return new Post[0];
        }
        return this.posts.toArray(new Post[0]);
    }

    @Override
    public Post[] getAllPostsByUser(int userId) {
        if (this.posts.isEmpty()) {
            return new Post[0];
        }
        return this.posts
                .stream()
                .filter(post -> post.getAuthorId().equals(userId))
                .toList()
                .toArray(new Post[0]);

        */
/*List<Post> userPosts = new LinkedList<>();
        for (Post post : this.posts) {
            if (post.getAuthorId() == userId) {
                userPosts.add(post);
            }
        }
        return userPosts.toArray(new Post[0]);*//*

    }

    @Override
    public Post[] getAllPostsByTag(String tag) {
        if (this.posts.isEmpty()) {
            return new Post[0];
        }
        return this.posts
                .stream()
                .filter(post -> {
                    Set<String> tags = Set.of(post.getTags());
                    return tags.contains(tag);
                })
                .toList()
                .toArray(new Post[0]);

        */
/*List<Post> userPosts = new LinkedList<>();
        for (Post post : this.posts) {
            Set<String> tags = Set.of(post.getTags());
            if (tags.contains(tag)) {
                userPosts.add(post);
            }
        }
        return userPosts.toArray(new Post[0]);*//*

    }

    @Override
    public Post[] getAllPostsByUserIdIn(int[] userIds) {
        if (this.posts.isEmpty()) {
            return new Post[0];
        }
        List<Integer> users = Arrays.stream(userIds).boxed().toList();
        return this.posts
                .stream()
                .filter(post -> users.contains(post.getAuthorId()))
                .toList()
                .toArray(new Post[0]);

        */
/*List<Post> userPosts = new LinkedList<>();
        Set<Integer> users = new HashSet<>();
        for (int id : userIds) {
            users.add(id);
        }

        for (Post post : this.posts) {
            if (users.contains(post.getAuthorId())) {
                userPosts.add(post);
            }
        }
        return userPosts.toArray(new Post[0]);*//*

    }
}
*/
