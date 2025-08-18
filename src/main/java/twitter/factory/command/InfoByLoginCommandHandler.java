/*
package twitter.factory.impl;

import twitter.controller.v1.impl.InfoController;
import twitter.factory.command.CommandHandler;
import twitter.security.SecurityComponent;

public class InfoByLoginCommandHandler implements CommandHandler {

    private final InfoController infoController;
    private final SecurityComponent securityComponent;

    public InfoByLoginCommandHandler(InfoController infoController, SecurityComponent securityComponent) {
        this.infoController = infoController;
        this.securityComponent = securityComponent;
    }

    @Override
    public void handle() {
        if (this.securityComponent.getAuthentication() == null) {
            System.out.println("Для выполнения данной команды необходимо войти в систему.");
            return;
        }
        infoController.executeInfoByLogin();
    }
}
*/
