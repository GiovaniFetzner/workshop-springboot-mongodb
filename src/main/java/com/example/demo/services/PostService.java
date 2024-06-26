package com.example.demo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public List<Post> findAll(){
		return repo.findAll();
	}
	
	public Post findById(String id) {
		Optional<Post> post = repo.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado"));
	}
	
	public List<Post> findByTitle(String title){
		return repo.searchTitle(title);
	}
	
	public List<Post> fullSearch(String text, Date initialDate, Date finalDate){
		finalDate = new Date(finalDate.getTime() + (1000 * 60 * 60 * 24));
		return repo.fullsearch(text, initialDate, finalDate);
	}
 }
