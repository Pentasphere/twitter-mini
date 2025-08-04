package twitter.dao;

import twitter.entity.user.User;
import twitter.exception.UnknownUserTypeException;
import twitter.exception.UserNotFoundException;

import java.util.List;

public interface UserDAO {

    User saveNewUser(User user);

    User getById(int id) throws UserNotFoundException;

    User getByLogin(String login) throws UserNotFoundException;

    /*User[] getAllUsers();*/
    List<User> getAllUsers();

    /*User[] getAllUsersByUserType(int userType) throws UnknownUserTypeException;*/
    List<User> getAllUsersByUserType(int userType) throws UnknownUserTypeException;

}
