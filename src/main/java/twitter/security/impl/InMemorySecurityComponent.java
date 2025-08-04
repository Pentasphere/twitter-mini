package twitter.security.impl;

import twitter.configuration.Component;
import twitter.configuration.Injection;
import twitter.entity.user.User;
import twitter.security.SecurityComponent;

/*@Component*/
public class InMemorySecurityComponent implements SecurityComponent {

    /*private static InMemorySecurityComponent instance;

    public static InMemorySecurityComponent getInstance() {
        if (instance == null) {
            instance = new InMemorySecurityComponent();
        }
        return instance;
    }*/

    private User authenticatedUser;

    /*@Injection*/
    public InMemorySecurityComponent() {

    }

    @Override
    public User getAuthentication(String userIp) {
        return this.authenticatedUser;
    }

    @Override
    public void setAuthentication(String userIp, User user) {
        this.authenticatedUser = user;
    }

    @Override
    public void removeAuthentication(String userIp) {
        this.authenticatedUser = null;
    }

    /*private InMemorySecurityComponent() {

    }*/

    /*@Override
    public User getAuthentication() {
        return this.authenticatedUser;
    }

    @Override
    public void setAuthentication(User user) {
        this.authenticatedUser = user;
    }

    @Override
    public void removeAuthentication() {
        this.authenticatedUser = null;
    }*/
}
