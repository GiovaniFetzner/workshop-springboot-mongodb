package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	List<Post> findByTitleContainingIgnoreCase(String text);
	
	//@Query({<field>: { $regex: /pattern/, $options: '<options>' } })
	@Query("{'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
}
