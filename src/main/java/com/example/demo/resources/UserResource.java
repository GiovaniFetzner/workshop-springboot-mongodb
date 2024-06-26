package com.example.demo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	UserService service;
	
	//@GetMapping Forma alternativa de fazer
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity <List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)) .collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity <UserDTO> findById(@PathVariable String id){
		User user = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity <Void> insert(@RequestBody UserDTO userDTO) {
		User user = service.fromDTO(userDTO);
		service.insertUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build(); // Created: Retorno o código 201
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDTO userDTO, @PathVariable String id){
		User user = service.fromDTO(userDTO);
		user.setId(id);
		user = service.update(user);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
	public ResponseEntity <List<Post>> findPosts(@PathVariable String id){	
		User user = service.findById(id);
		return ResponseEntity.ok().body(user.getPosts());
	}
}
