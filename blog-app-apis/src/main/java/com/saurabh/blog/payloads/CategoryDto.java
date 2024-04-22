package com.saurabh.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryDto {
	private int categoryId;
	@NotBlank(message="Title can not be blank")
	private String categoryTitle;
	@NotBlank(message="Description can not be blank")
	private String categoryDescription;
}
