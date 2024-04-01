package com.saurabh.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private Integer id;
	@NotNull
	private String name;
	@Email
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String about;
}
