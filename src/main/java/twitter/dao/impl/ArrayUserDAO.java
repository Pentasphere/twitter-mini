/*
package twitter.dao.impl;

import twitter.dao.UserDAO;
import twitter.entity.user.User;
import twitter.entity.user.UserType;
import twitter.exception.UnknownUserTypeException;
import twitter.exception.UserNotFoundException;

import java.time.LocalDateTime;

public class ArrayUserDAO implements UserDAO {

    private static ArrayUserDAO instance;

    private final User[] users = new User[5];
    private int id = 0;

    private ArrayUserDAO() {

    }

    public static ArrayUserDAO getInstance() {
        if (instance == null) {
            instance = new ArrayUserDAO();
        }
        return instance;
    }

    @Override
    public User saveNewUser(User user) {
        user.setId(this.id + 1);
        user.setRegistrationDate(LocalDateTime.now());
        this.users[this.id] = user;
        this.id++;
        return user;
    }

    @Override
    public User getById(int id) {
        if (this.users[0] == null) {
            return null;
        }
        int i = 0;
        while (i < this.users.length && this.users[i] != null) {
            if (this.users[i].getId() == id) {
                return this.users[i];
            }
            i++;
        }
        return null;
    }

    @Override
    public User getByLogin(String login) throws UserNotFoundException {
        if (this.users[0] == null) {
            throw new UserNotFoundException("Пользователь с логином: " + login + " не найден");
            */
/*return null;*//*

        }
        int i = 0;
        while (i < this.users.length && this.users[i] != null) {
            if (this.users[i].getLogin().equals(login)) {
                return this.users[i];
            }
            i++;
        }
        throw new UserNotFoundException("Пользователь с логином: " + login + " не найден");
        */
/*return null;*//*

    }

    @Override
    public User[] getAllUsers() {
        User[] currentUsers = new User[this.id];
        for (int i = 0; i < this.id; i++) {
            currentUsers[i] = this.users[i];
        }
        return currentUsers;
    }

    @Override
    public User[] getAllUsersByUserType(int userType) throws UnknownUserTypeException {
        if (this.users[0] == null) {
            return new User[0];
        }
        int i = 0;
        int counter = 0;
        while (i < this.users.length && this.users[i] != null) {
            if (UserType.getUserType(userType).equals(this.users[i].getUserType())){
                counter++;
            }
            */
/*if (this.users[i].getUserType() == userType) {
                counter++;
            }*//*

            i++;
        }
        if (counter == 0) {
            return new User[0];
        }
        User[] userByType = new User[counter];
        i = 0;
        int j = 0;
        while (i < this.users.length && this.users[i] != null) {
            if (UserType.getUserType(userType).equals(this.users[i].getUserType())){
                userByType[j] = this.users[i];
                j++;
            }
            */
/*if (this.users[i].getUserType() == userType) {
                userByType[j] = this.users[i];
                j++;
            }*//*

            i++;
        }
        return userByType;
    }

}
*/
