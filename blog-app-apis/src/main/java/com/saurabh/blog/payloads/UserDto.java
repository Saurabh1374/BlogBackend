package com.saurabh.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private Integer id;
	@NotBlank(message="username can not be blank")
	private String name;
	@Email(message="enter a valid email")
	private String email;
	@NotBlank(message="enter a valid password")
	private String password;
	@NotNull(message="about can not be blank")
	private String about;
}
