package com.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.pedidovenda.model.Usuario;
import com.pedidovenda.util.jpa.Transactional;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
public void CadastrarUsuario(Usuario usuario){
		
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		manager.merge(usuario);
		trx.commit();
		
	}
	public List<Usuario> vendedores() {
		// TODO filtrar apenas vendedores (por um grupo específico)
		return this.manager.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> filtrados(String usuario) {
		
		
			Session session = manager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Usuario.class);
			
			if (StringUtils.isNotBlank(usuario)) {
				criteria.add(Restrictions.ilike("nome", usuario, MatchMode.ANYWHERE));
			}
			
			return criteria.addOrder(Order.asc("nome")).list();
		
		
	}
	
	public List<Usuario> todos() {
		try {
			return manager
					.createQuery("from Usuario ",
							Usuario.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Usuario porId(Long id) {
		return manager.find(Usuario.class, id);
	}
	
	public Usuario porEmail(String email) {
		try {
			return manager.createQuery("from Usuario where upper(email) = :email", Usuario.class)
				.setParameter("email", email.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Transactional
	public void remover(Usuario usuario) {

		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		try {
		
		
		usuario = porId(usuario.getId());
		manager.remove(usuario);
		trx.commit();
		manager.flush();

		
		} catch (Exception e) {
			
		}
		
	}
	
}