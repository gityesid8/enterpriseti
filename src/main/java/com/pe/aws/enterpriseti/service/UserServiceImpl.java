package com.pe.aws.enterpriseti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pe.aws.enterpriseti.dao.UserDao;
import com.pe.aws.enterpriseti.entity.Usuario;

@Service("usuarioService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	@Qualifier("usuarioDao")
	UserDao userDaoImpl;

	@Override
	public List<Usuario> getListUser() {

		return userDaoImpl.getListUser();
	}

	@Override
	public Long addUser(Usuario user) {

		return userDaoImpl.addUser(user);
	}

	@Override
	public Usuario getUser(Long id) {

		return userDaoImpl.getUser(id);
	}

	@Override
	public boolean deleteUser(Long id) {

		return userDaoImpl.deleteUser(id);
	}

	@Override
	public boolean updateUser(Long id, Usuario user) {

		return userDaoImpl.updateUser(id, user);
	}

	@Override
	public Long totalUsers() {

		return userDaoImpl.totalUsers();
	}

	@Override
	public List<Usuario> getListCursorUsers(String alias) {

		return userDaoImpl.getListCursorUsers(alias);
	}

}
