package com.example.demo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com"); 
		User alex = new User(null, "Alex Green", "alex@gmail.com"); 
		User bob = new User(null, "Bob Grey", "bob@gmail.com"); 
		
		Post post1 = new Post(null,sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para Sao Paulo", maria);
		Post post2 = new Post(null,sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para Sao Paulo", maria);
		
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		postRepository.saveAll(Arrays.asList(post1, post2));
	}

}
