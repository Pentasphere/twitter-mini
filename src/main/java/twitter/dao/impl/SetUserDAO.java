/*
package twitter.dao.impl;

import twitter.dao.UserDAO;
import twitter.entity.user.User;
import twitter.entity.user.UserType;
import twitter.exception.UnknownUserTypeException;
import twitter.exception.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class SetUserDAO implements UserDAO {

    private static SetUserDAO instance;

    private final Set<User> users;
    private int id = 1;

    public static SetUserDAO getInstance() {
        if (instance == null) {
            instance = new SetUserDAO();
        }
        return instance;
    }

    private SetUserDAO() {
        this.users = new HashSet<>();
    }

    @Override
    public User saveNewUser(User user) {
        user.setId(this.id);
        user.setRegistrationDate(LocalDateTime.now());
        id++;
        this.users.add(user);
        return user;
    }

    @Override
    public User getById(int id) throws UserNotFoundException {
        if (this.users.isEmpty()){
            throw new UserNotFoundException("Пользователь с ID: " + id + " не найден");
            */
/*return null;*//*

        }
        return this.users
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(()-> new UserNotFoundException("Пользователь с ID: " + id + " не найден"));

        */
/*for (User user : this.users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;*//*

    }

    @Override
    public User getByLogin(String login) throws UserNotFoundException {
        if (this.users.isEmpty()){
            throw new UserNotFoundException("Пользователь с логином: " + login + " не найден");
            */
/*return null;*//*

        }
        return this.users
                .stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst()
                */
/*.orElse(null);*//*

                .orElseThrow(*/
/*null*//*
()->new UserNotFoundException("Пользователь с логином: " + login + " не найден"));

        */
/*for (User user : this.users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;*//*

    }

    @Override
    public User[] getAllUsers() {
        if (this.users.isEmpty()){
            return new User[0];
        }
        return this.users.toArray(new User[0]);
    }

    @Override
    public User[] getAllUsersByUserType(int userType) throws UnknownUserTypeException {
        if (this.users.isEmpty()){
            return new User[0];
        }

        UserType type = UserType.getUserType(userType);
        return this.users
                .stream()
                .filter(user -> */
/*user.getUserType().equals(userType)*//*

                                        type.equals(user.getUserType()))
                .toList()
                .toArray(new User[0]);

        */
/*Set<User> usersByType = new HashSet<>();
        for (User user : this.users) {
            if (user.getUserType() == userType) {
                usersByType.add(user);
            }
        }
        return usersByType.toArray(new User[0]);*//*

    }
}
*/
