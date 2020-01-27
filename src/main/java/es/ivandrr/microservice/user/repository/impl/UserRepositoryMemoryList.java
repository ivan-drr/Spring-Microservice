package es.ivandrr.microservice.user.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import es.ivandrr.microservice.user.exceptions.UserException;
import es.ivandrr.microservice.user.model.User;
import es.ivandrr.microservice.user.repository.UserRepository;

@Repository
public class UserRepositoryMemoryList implements UserRepository {

	final List<User> users = new ArrayList<User>();
	
	@Override
	public void addUser(User user) {
		users.add(user);
	}

	@Override
	public void modifyUser(String nickname, User user) {
		final int position = users.indexOf(User.builder().nickname(nickname).build());
		
		if (position == -1) {
			throw new UserException("error.user.not.exist");
		}
		
		users.set(position, user);
	}

	@Override
	public void deleteUser(String nickname) {
		users.remove(
				User.builder()
					.nickname(nickname)
					.build());
	}

	@Override
	public List<User> searchUsersByCountry(String country) {
		return users.stream()
				.filter(user -> 
					country.equals(user.getCountry()))
						.collect(Collectors.toList());
	}

	@Override
	public boolean userExists(String nickname) {
		return users.contains(User.builder()
				.nickname(nickname)
				.build());
	}

	@Override
	public List<User> getAllUsers() {
		return this.users.stream().collect(Collectors.toList());
	}

}
