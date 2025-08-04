package twitter.service;

import twitter.entity.user.User;
import twitter.exception.UnknownUserTypeException;
import twitter.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    boolean isUserExists(String login);

    User createUser(User user);

    User getUserById(int id) throws UserNotFoundException;

    User getUserByLogin(String login) throws UserNotFoundException;

    /*User[] getAllUsers();*/
    List<User> getAllUsers();

    /*User[] getUsersByType(int userType) throws UnknownUserTypeException;*/
    List<User> getUsersByType(int userType) throws UnknownUserTypeException;

    /*User[] createSeveralUsers(List<User> users);*/
    List<User> createSeveralUsers(List<User> users);
}
