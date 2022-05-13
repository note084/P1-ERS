package com.revature.services;

import java.util.Optional;

import com.revature.models.User;
import com.revature.repositories.UserDAO;

import javax.swing.text.html.Option;

/**
 * The UserService should handle the processing and retrieval of Users for the ERS application.
 *
 * {@code getByUsername} is the only method required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create User</li>
 *     <li>Update User Information</li>
 *     <li>Get Users by ID</li>
 *     <li>Get Users by Email</li>
 *     <li>Get All Users</li>
 * </ul>
 */
public class UserService {
	private AuthService authService = new AuthService();


	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
     */
	public Optional<User> getByUsername(String username) {
		UserDAO userModel = new UserDAO();
		User user = new User();
		user = userModel.getByUsername(username).get();

		return Optional.of(user);
	}

	public User createUser (User userToBeCreated) {

		return authService.register(userToBeCreated);
	}

	public User loginUser (String username, String password) {

		return authService.login(username, password);
	}

	public Optional<User> getByUserID (int id) {
		UserDAO userModel = new UserDAO();
		User user = userModel.getByUserID(id).get();

		return Optional.of(user);
	}
}
