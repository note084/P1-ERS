package com.revature.services;

import com.revature.exceptions.UserPasswordDoesNotMatchException;
import com.revature.exceptions.UsernameDoesNotExistException;
import com.revature.exceptions.NotUniqueException;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

import java.util.Optional;

/**
 * The AuthService should handle login and registration for the ERS application.
 *
 * {@code login} and {@code register} are the minimum methods required; however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Retrieve Currently Logged-in User</li>
 *     <li>Change Password</li>
 *     <li>Logout</li>
 * </ul>
 */
public class AuthService {

    /**
     * <ul>
     *     <li>Needs to check for existing users with username/email provided.</li>
     *     <li>Must throw exception if user does not exist.</li>
     *     <li>Must compare password provided and stored password for that user.</li>
     *     <li>Should throw exception if the passwords do not match.</li>
     *     <li>Must return user object if the user logs in successfully.</li>
     * </ul>
     */
    public User login(String username, String password) {
        UserDAO userModel = new UserDAO();
        User user = new User();
        try {
            if(userModel.getByUsername(username).isPresent()) {
                user = userModel.getByUsername(username).get();

                if (user.getPassword().equals(password)) {
                    System.out.println("logged in successfully");
                    return user;
                }
                else {
                    throw new UserPasswordDoesNotMatchException("Incorrect Password");
                }

            }
            else {
                throw new UsernameDoesNotExistException("Username does not exist");
            }
        } catch (UsernameDoesNotExistException | UserPasswordDoesNotMatchException e) {
            System.out.println(e.getMessage());
            if(e.getMessage().equals("Incorrect Password")) {
                user.setPassword(null);
                return user;
            }
            else if(e.getMessage().equals("Username does not exist")) {
                user.setUsername(null);
                return user;
            }
            else {
                System.out.println("No idea what happened: login - AuthService.java");
                System.out.println(user);
            }
        }
        return null;
    }

    /**
     * <ul>
     *     <li>Should ensure that the username/email provided is unique.</li>
     *     <li>Must throw exception if the username/email is not unique.</li>
     *     <li>Should persist the user object upon successful registration.</li>
     *     <li>Must throw exception if registration is unsuccessful.</li>
     *     <li>Must return user object if the user registers successfully.</li>
     *     <li>Must throw exception if provided user has a non-zero ID</li>
     * </ul>
     *
     * Note: userToBeRegistered will have an id=0, additional fields may be null.
     * After registration, the id will be a positive integer.
     */
    public User register(User userToBeRegistered) {
        UserDAO userModel = new UserDAO();
        User user = new User();

        try {
            if(userModel.getByUsername(userToBeRegistered.getUsername()).isPresent()) {
                throw new NotUniqueException("Username is taken");
            }
            else if (userModel.getByUserEmail(userToBeRegistered.getEmail()).isPresent()) {
                throw new NotUniqueException("Email is taken");
            }
            else {
                user = userModel.create(userToBeRegistered);
                return user;
            }
        } catch (NotUniqueException e) {
            System.out.println(e.getMessage());
            if (e.getMessage().equals("Username is taken")) {
                user = userModel.getByUsername(userToBeRegistered.getUsername()).get();
                user.setUsername(null);
                return user;
            }
            else if(e.getMessage().equals("Email is taken")) {
                user = userModel.getByUserEmail(userToBeRegistered.getEmail()).get();
                user.setEmail(null);
                return user;
            }
            else {
                System.out.println("No idea what happened: Auth Service");
            }
        }
        System.out.println("Registration did not persist");
        return null;
    }

    /**
     * This is an example method signature for retrieving the currently logged-in user.
     * It leverages the Optional type which is a useful interface to handle the
     * possibility of a user being unavailable.
     */
    public Optional<User> exampleRetrieveCurrentUser() {
        return Optional.empty();
    }


}

