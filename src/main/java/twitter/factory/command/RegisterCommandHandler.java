/*
package twitter.factory.impl;

import twitter.controller.v1.impl.RegistrationController;
import twitter.factory.command.CommandHandler;
import twitter.security.SecurityComponent;

public class RegisterCommandHandler implements CommandHandler {

    private final RegistrationController registrationController;
    private final SecurityComponent securityComponent;

    public RegisterCommandHandler(RegistrationController registrationController, SecurityComponent securityComponent) {
        this.registrationController = registrationController;
        this.securityComponent = securityComponent;
    }
    @Override
    public void handle() {
        if (this.securityComponent.getAuthentication() != null) {
            System.out.println("Для выполнения данной команды необходимо выйти из системы.");
            return;
        }
        registrationController.executeRegister();
    }
}
*/
