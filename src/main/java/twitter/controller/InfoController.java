package twitter.controller;

import java.io.IOException;

public interface InfoController {
    void executeHelp()throws IOException;
    void executeInfo()throws IOException;
    void executeInfoByLogin()throws IOException;
    void executeInfoAll() throws IOException;
}
