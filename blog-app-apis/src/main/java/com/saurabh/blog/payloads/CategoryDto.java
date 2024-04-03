package com.saurabh.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	private int categoryId;
	@NotBlank(message="Title can not be blank")
	private String CategoryTitle;
	private String categoryDescription;
}
