package com.pe.aws.enterpriseti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.aws.enterpriseti.entity.Usuario;

import com.pe.aws.enterpriseti.service.UserServiceImpl;

@RestController
@RequestMapping("uri")
public class UserController {

	@Autowired
	@Qualifier("usuarioService")
	UserServiceImpl userServiceImpl;
	
	@GetMapping(path="/users", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Usuario> getListUsers(){
		System.out.println("TOTAL_USUARIOS :" + userServiceImpl.totalUsers());
		System.out.println("TOTAL_USUARIOS - CURSOR :" + userServiceImpl.getListCursorUsers("Luis").size());
		return userServiceImpl.getListUser();
		
	}
	
	@GetMapping(path="/user/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Usuario getUserId(@PathVariable Long id) {
		
		return userServiceImpl.getUser(id);
		
	}
	
	@PostMapping("/user")
	public ResponseEntity<?> save(@RequestBody Usuario user){
	
		Long id = userServiceImpl.addUser(user);
		
		return ResponseEntity.ok().body("EL USUARIO HA SIDO GUARDADO CON EL ID: " + id);
		
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		
		userServiceImpl.deleteUser(id);
		
		return ResponseEntity.ok().body("EL USUARIO HA SIDO EILIMINA CON EL ID: " + id);
		
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Usuario user){
		
		userServiceImpl.updateUser(id, user);
		
		return ResponseEntity.ok().body("EL USUARIO CON ID: "+ id + " HA SIDO ACTUALIZADO CORRECTAMENTE");
		
	}
	
}
