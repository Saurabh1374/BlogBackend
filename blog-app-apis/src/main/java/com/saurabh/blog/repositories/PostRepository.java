package com.saurabh.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saurabh.blog.entities.Category;
import com.saurabh.blog.entities.Post;
import com.saurabh.blog.entities.User;

public interface PostRepository extends JpaRepository<Post,  Integer>{
		List<Post> findByUser(User user);
		List<Category> findByCategory(Category category);
}
