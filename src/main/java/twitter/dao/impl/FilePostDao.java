/*
package twitter.dao.impl;

import twitter.configuration.Component;
import twitter.configuration.Injection;
import twitter.dao.PostDAO;
import twitter.entity.post.Post;
import twitter.mapper.PostMapper;


import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

*/
/*@Component*//*

public class FilePostDao implements PostDAO {

    */
/*private static FilePostDao instance;*//*


    */
/*@Injection*//*

    public FilePostDao(PostMapper postMapper) {
        this.posts = new LinkedList<>();
        this.postMapper = postMapper;
        this.init();
    }

    */
/*private FilePostDao(PostMapper postMapper) {
        this.posts = new LinkedList<>();
        this.postMapper = postMapper;
        this.init();
    }*//*


    */
/*public static FilePostDao getInstance() {
        if (instance == null) {
            instance = new FilePostDao(PostMapper.getInstance());
        }
        return instance;
    }*//*


    private final String fileName = "PostData.txt";

    private final List<Post> posts;
    */
/*private int id = 1;*//*

    private int id;

    private final PostMapper postMapper;

    private void init(){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            List<String> lines = bufferedReader.lines().toList();
            for (String line : lines) {
                if (Objects.nonNull(line) && !line.isBlank()) {
                    Post post = this.postMapper.mapFileStringToPost(line);
                    this.posts.add(post);
                }
            }
            this.id = this.posts.size() + 1;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }

    @Override
    public synchronized Post saveNewPost(Post post) {
        post.setId(this.id);
        post.setCreationDate(LocalDateTime.now());
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.newLine();
            bufferedWriter.append(post.toFileString());
            id++;
            this.posts.add(post);
        }catch (IOException ex) {
            System.out.println("Не получилось создать публикацию. Причина: ");
            System.out.println(ex.getMessage());
        }
        return post;
    }

    */
/*@Override
    public synchronized Post[] getAllPosts() {
        if (this.posts.isEmpty()) {
            return new Post[0];
        }
        return this.posts.toArray(new Post[0]);
    }*//*

    @Override
    public synchronized List<Post> getAllPosts() {
        if (this.posts.isEmpty()) {
            return Collections.emptyList();
        }
        return this.posts;
    }

    */
/*@Override
    public synchronized Post[] getAllPostsByUser(int userId) {
        if (this.posts.isEmpty()) {
            return new Post[0];
        }
        return this.posts
                .stream()
                .filter(post -> post.getAuthorId().equals(userId))
                .toList()
                .toArray(new Post[0]);
    }*//*

    @Override
    public synchronized List<Post> getAllPostsByUser(int userId) {
        if (this.posts.isEmpty()) {
            return Collections.emptyList();
        }
        return this.posts
                .stream()
                .filter(post -> post.getAuthorId().equals(userId))
                .toList();
    }

    */
/*@Override
    public synchronized Post[] getAllPostsByTag(String tag) {
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
    }*//*

    @Override
    public synchronized List<Post> getAllPostsByTag(String tag) {
        if (this.posts.isEmpty()) {
            return Collections.emptyList();
        }
        return this.posts
                .stream()
                .filter(post -> {
                    Set<String> tags = Set.of(post.getTags());
                    return tags.contains(tag);
                })
                .toList();
    }

    */
/*@Override
    public synchronized Post[] getAllPostsByUserIdIn(int[] userIds) {
        if (this.posts.isEmpty()) {
            return new Post[0];
        }
        List<Integer> users = Arrays.stream(userIds).boxed().toList();
        return this.posts
                .stream()
                .filter(post -> users.contains(post.getAuthorId()))
                .toList()
                .toArray(new Post[0]);
    }*//*

    @Override
    public synchronized List<Post> getAllPostsByUserIdIn(int[] userIds) {
        if (this.posts.isEmpty()) {
            return Collections.emptyList();
        }
        List<Integer> users = Arrays.stream(userIds).boxed().toList();
        return this.posts
                .stream()
                .filter(post -> users.contains(post.getAuthorId()))
                .toList();
    }
}
*/
