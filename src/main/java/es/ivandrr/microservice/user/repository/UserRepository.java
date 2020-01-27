package es.ivandrr.microservice.user.repository;

import java.util.List;

import es.ivandrr.microservice.user.model.User;

public interface UserRepository {

	void addUser(User user);

	void modifyUser(String nickname, User user);

	void deleteUser(String nickname);

	List<User> searchUsersByCountry(String country);

	boolean userExists(String nickname);

	List<User> getAllUsers();

}
