package com.pe.aws.enterpriseti.dao;

import java.util.List;

import com.pe.aws.enterpriseti.entity.Usuario;

public interface UserDao {
	
	public List<Usuario> getListUser();
	public Long addUser (Usuario user);
	public Usuario getUser(Long id);
	public boolean deleteUser(Long id);
	public boolean updateUser(Long id, Usuario user);
	
	public Long totalUsers();
	public List<Usuario> getListCursorUsers(String alias);
	

}
