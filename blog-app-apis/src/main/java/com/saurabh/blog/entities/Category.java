package com.saurabh.blog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="category")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Category {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int categoryId;
	
	@Column(name="title", length=100, nullable=false)
	private String categoryTitle;
	
	@Column(name="description", length=400, nullable=false)
	private String categoryDescription;
	
}
