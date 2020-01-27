package es.ivandrr.microservice.user.service;

import java.util.List;

import es.ivandrr.microservice.user.model.User;

public interface UserService {

	void addUser(User user);

	void modifyUser(String nickname, User user);

	void deleteUser(String nickname);

	List<User> searchUsersByCountry(String country);

	List<User> getAllUsers();

}
