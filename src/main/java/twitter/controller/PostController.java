package twitter.controller;

import java.io.IOException;

public interface PostController {
    void executeReadPosts()throws IOException;
    void executeAddPost() throws IOException;
    void executeMyPosts() throws IOException;
    void executeAllPosts() throws IOException;
    void executePostsByTag() throws IOException;
    void executePostsByLogin() throws IOException;
    void executePostsByUserType() throws IOException;
}
