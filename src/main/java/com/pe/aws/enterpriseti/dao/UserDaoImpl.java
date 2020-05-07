package com.pe.aws.enterpriseti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.pe.aws.enterpriseti.entity.Usuario;

@Repository("usuarioDao")
@Transactional
public class UserDaoImpl implements UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Usuario> getListUser() {

		String hql = "FROM Usuario";
		return (List<Usuario>)entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Long addUser(Usuario user) {
		
		entityManager.persist(user);
		return user.getUsuarioId();
	}

	@Override
	public Usuario getUser(Long id) {
		
		return entityManager.find(Usuario.class, id);
	}

	@Override
	public boolean deleteUser(Long id) {
		
		boolean flagComplete = false;
		entityManager.remove(getUser(id));
		
		return flagComplete = true;
		 
	}

	@Override
	public boolean updateUser(Long id, Usuario user) {
		boolean flagComplete = false;
		Usuario usuario = getUser(id);
		
		usuario.setUsuarioAlias(user.getUsuarioAlias());
		usuario.setUsuarioDni(user.getUsuarioDni());
		usuario.setUsuarioEmail(user.getUsuarioEmail());
		usuario.setUsuarioNames(user.getUsuarioNames());
		usuario.setUsuarioPassword(user.getUsuarioPassword());
		
		entityManager.flush();
		return flagComplete;
	}

	@Override
	public Long totalUsers() {
		
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("NP_MANAGEMENT_USER_PKG.SP_GET_TOTAL_USERS");
		
		query.registerStoredProcedureParameter("AN_TOTAL", Long.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("AV_MESSAGE", String.class, ParameterMode.OUT);
		
		query.execute();
		
		Long AN_TOTAL = (Long)query.getOutputParameterValue("AN_TOTAL");
		
		return AN_TOTAL;
	}

	@Override
	public List<Usuario> getListCursorUsers(String alias) {
		
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("NP_MANAGEMENT_USER_PKG.SP_GET_LIST_USERS", Usuario.class);
		
		query.registerStoredProcedureParameter("AC_USERS", void.class, ParameterMode.REF_CURSOR);
		query.registerStoredProcedureParameter("username", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("AV_MESSAGE", String.class, ParameterMode.OUT);
		
		query.setParameter("username", alias);
		
		query.execute();
		
		List<Usuario> listUsers = (List<Usuario>)query.getResultList();
		
		return listUsers;
	}

	
	
}
