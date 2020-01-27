package es.ivandrr.microservice.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ivandrr.microservice.user.exceptions.UserException;
import es.ivandrr.microservice.user.model.User;
import es.ivandrr.microservice.user.repository.UserRepository;
import es.ivandrr.microservice.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void addUser(User user) {
		if (this.userRepository.userExists(user.getNickname())) {
			throw new UserException("error.user.already.exists");
		}

		this.userRepository.addUser(user);
	}

	@Override
	public void modifyUser(String nickname, User modifiedUser) {
		if (!this.userRepository.userExists(nickname)) {
			throw new UserException("error.user.not.found");
		}

		if (this.isNicknameToModifyInUse(nickname, modifiedUser)) {
			throw new UserException("error.nickname.in.use");
		}

		this.userRepository.modifyUser(nickname, modifiedUser);
	}

	@Override
	public void deleteUser(String nickname) {
		this.userRepository.deleteUser(nickname);
	}

	@Override
	public List<User> searchUsersByCountry(String country) {
		return this.userRepository.searchUsersByCountry(country);
	}

	private boolean isNicknameToModifyInUse(String nickname, User modifiedUser) {
		return !nickname.equals(modifiedUser.getNickname())
				&& this.userRepository.userExists(modifiedUser.getNickname());
	}

	@Override
	public List<User> getAllUsers() {
		return this.userRepository.getAllUsers();
	}

}
