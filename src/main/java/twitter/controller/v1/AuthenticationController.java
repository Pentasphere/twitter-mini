package twitter.controller.v1;

import twitter.exception.ClientDisconnectedException;

import java.io.IOException;

public interface AuthenticationController {

    void executeExit() throws IOException, ClientDisconnectedException;
    void executeLogin() throws IOException;
    void executeLogout() throws IOException;
}
