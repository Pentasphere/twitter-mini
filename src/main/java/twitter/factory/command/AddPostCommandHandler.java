/*
package twitter.factory.impl;

import twitter.controller.v1.impl.PostController;
import twitter.factory.command.CommandHandler;
import twitter.security.SecurityComponent;

public class AddPostCommandHandler implements CommandHandler {

    private final PostController postController;
    private final SecurityComponent securityComponent;

    public AddPostCommandHandler(PostController postController, SecurityComponent securityComponent) {
        this.postController = postController;
        this.securityComponent = securityComponent;
    }

    @Override
    public void handle() {
        if (this.securityComponent.getAuthentication() == null) {
            System.out.println("Для выполнения данной команды необходимо войти в систему.");
            return;
        }
        postController.executeAddPost();
    }
}
*/
