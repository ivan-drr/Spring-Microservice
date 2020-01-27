package es.ivandrr.microservice.user.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.ivandrr.microservice.user.exceptions.UserException;
import es.ivandrr.microservice.user.model.User;
import es.ivandrr.microservice.user.repository.UserRepository;

public class UserServiceImplTest {

	private static final String NICK_NAME = "ivan-drr";
	private UserServiceImpl userService;
	private UserRepository userRepository = mock(UserRepository.class);
	private final User modifiedUser = mock(User.class);

	@BeforeEach
	public void initializeBeforeEachTest() {
		this.userService = new UserServiceImpl(this.userRepository);
	}

	@Test
	public void shouldThrowExceptionIfUserNotExist() {
		when(userRepository.userExists("notFound"))
			.thenReturn(false);
		
		assertThrows(UserException.class, () -> this.userService.modifyUser("notFound", this.modifiedUser));
	}

	@Test
	public void shouldThrowExceptionIfModifiedUserExist() {
		when(this.userRepository.userExists(NICK_NAME))
			.thenReturn(true);
		when(modifiedUser.getNickname())
			.thenReturn("updateNickname");
		when(this.userRepository.userExists("updateNickname"))
			.thenReturn(true);
		
		assertThrows(UserException.class, () -> this.userService.modifyUser(NICK_NAME, this.modifiedUser));
	}
	
	@Test
	public void shouldModifyUser() {
		when(this.userRepository.userExists(NICK_NAME))
			.thenReturn(true);
		when(modifiedUser.getNickname())
			.thenReturn("modify");
		when(this.userRepository.userExists("modify"))
			.thenReturn(false);
		
		this.userService.modifyUser(NICK_NAME, modifiedUser);
		
		verify(this.userRepository, times(1)).modifyUser(NICK_NAME, modifiedUser);
	}
	
	@Test
	public void shouldSearchByCountry() {
		final List<User> usersReturnedByRepository = new ArrayList<User>();
		
		when(this.userRepository.searchUsersByCountry("country"))
			.thenReturn(usersReturnedByRepository);
		
		final List<User> result = this.userService.searchUsersByCountry("country");
		
		assertSame(this.userRepository.searchUsersByCountry("country"), result);
	}

}
