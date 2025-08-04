/*
package twitter.factory.impl;

import twitter.controller.impl.AuthenticationController;
import twitter.factory.command.CommandHandler;
import twitter.security.SecurityComponent;

public class LogoutCommandHandler implements CommandHandler {

    private final AuthenticationController authenticationController;
    private final SecurityComponent securityComponent;

    public LogoutCommandHandler(AuthenticationController authenticationController, SecurityComponent securityComponent) {
        this.authenticationController = authenticationController;
        this.securityComponent = securityComponent;
    }

    @Override
    public void handle() {
        if (this.securityComponent.getAuthentication() == null) {
            System.out.println("Для выполнения данной команды необходимо войти в систему.");
            return;
        }
        authenticationController.executeLogout();
    }
}
*/
