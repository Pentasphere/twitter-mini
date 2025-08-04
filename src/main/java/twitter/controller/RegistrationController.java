package twitter.controller;

import twitter.entity.user.User;

import java.io.IOException;

public interface RegistrationController {
    void executeReadUsers() throws IOException;
    void executeRegister() throws IOException;
    /*User registerPerson();
    User registerOrganization();*/
}
