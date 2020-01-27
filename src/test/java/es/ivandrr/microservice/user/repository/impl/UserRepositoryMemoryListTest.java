package es.ivandrr.microservice.user.repository.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.ivandrr.microservice.user.exceptions.UserException;
import es.ivandrr.microservice.user.model.User;

public class UserRepositoryMemoryListTest {

	private static final String NICK_NAME = "ivan-drr";
	private static final String NICK_NAME2 = "ivan-drr2";
	
	private UserRepositoryMemoryList userRepository;
	private final User user = User.builder().nickname(NICK_NAME).build();
	private final User user2 = User.builder().nickname(NICK_NAME2).build();
	private final User user3 = mock(User.class);

	@BeforeEach
	public void initializeBeforeEachTest() {
		this.userRepository = new UserRepositoryMemoryList();
	}

	@Test
	public void shouldThrowExceptionIfUserNotExist() {
		assertThrows(UserException.class, () -> this.userRepository.modifyUser(NICK_NAME, this.user));
	}
	
	@Test
	public void shouldModifyUser() {
		this.userRepository.users.add(user);
		this.userRepository.users.add(user2);
		
		this.userRepository.modifyUser(NICK_NAME, this.user3);
		
		assertSame(this.user3, this.userRepository.users.get(0));
	}
	
}
