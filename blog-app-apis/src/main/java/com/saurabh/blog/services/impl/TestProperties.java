package com.saurabh.blog.services.impl;

import java.io.InputStream;
import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saurabh.blog.entities.Profession;

@Service
@PropertySource("classpath:sample_job.json")
public class TestProperties {
	
	
	public static void init() {
		Profession profession=new Profession();
		loadDataFromJsonConfig(profession);
		
		
	}
	 static void loadDataFromJsonConfig(Profession profession) {
	TypeReference<Profession> typeRefrene= new TypeReference<Profession>() {
		public Type getType() {return super.getType();}
	};
	InputStream inputstream=TypeReference.class.getResourceAsStream("/sample_job.json");
	try {
		profession=new ObjectMapper().readValue(inputstream, typeRefrene);
		System.out.println(profession.toString()+"hiiiiiii");
	}catch(Exception e) {e.printStackTrace();}
	}
}
