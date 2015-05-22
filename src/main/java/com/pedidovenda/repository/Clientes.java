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

import com.pedidovenda.model.Cliente;
import com.pedidovenda.util.jpa.Transactional;

public class Clientes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	public void CadastrarUsuario(Cliente cliente){
		
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		manager.merge(cliente);
		trx.commit();
		
	}
	

	
	@SuppressWarnings("unchecked")
	public List<Cliente> filtrados(String usuario) {
		
		
			Session session = manager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Cliente.class);
			
			if (StringUtils.isNotBlank(usuario)) {
				criteria.add(Restrictions.ilike("nome", usuario, MatchMode.ANYWHERE));
			}
			
			return criteria.addOrder(Order.asc("nome")).list();
		
		
	}
	
	public List<Cliente> todos() {
		try {
			return manager
					.createQuery("from Cliente ",
							Cliente.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Cliente porId(Long id) {
		return manager.find(Cliente.class, id);
	}
	
	

	@Transactional
	public void remover(Cliente usuario) {

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
