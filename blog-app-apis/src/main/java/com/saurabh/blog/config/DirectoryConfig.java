package com.saurabh.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@PropertySource("classpath:post.properties")
public class DirectoryConfig {

	@Value("${post.path}")
	private  String DIRECTORY_PATH ;

    @PostConstruct
    public void ensureDirectoryExists() {
        Path path = Paths.get(DIRECTORY_PATH);
        File directory = path.toFile();

        if (!directory.exists()) {
            try {
                // Create the directory if it doesn't exist
                Files.createDirectories(path);
                System.out.println("Directory created at: " + directory.getCanonicalPath());
            } catch (IOException e) {
                // Handle the exception (e.g., log it)
                e.printStackTrace();
            }
        } else {
            System.out.println("Directory already exists: " );
        }
    }
}

