/*
package twitter.factory.impl;

import twitter.controller.impl.InfoController;
import twitter.factory.command.CommandHandler;
import twitter.security.SecurityComponent;

public class InfoCommandHandler implements CommandHandler {

    private final InfoController infoController;
    private final SecurityComponent securityComponent;

    public InfoCommandHandler(InfoController infoController, SecurityComponent securityComponent) {
        this.infoController = infoController;
        this.securityComponent = securityComponent;
    }

    @Override
    public void handle() {
        if (this.securityComponent.getAuthentication() == null) {
            System.out.println("Для выполнения данной команды необходимо войти в систему.");
            return;
        }
        infoController.executeInfo();
    }
}
*/
