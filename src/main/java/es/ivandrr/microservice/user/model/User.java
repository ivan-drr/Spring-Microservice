package es.ivandrr.microservice.user.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(of = "nickname")
public class User {

	private final String firstName;
	private final String lastName;
	private final String nickname;
	private final String password;
	private final String email;
	private final String country;

}
