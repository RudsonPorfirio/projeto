package com.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pedidovenda.model.Usuario;
import com.pedidovenda.repository.Clientes;
import com.pedidovenda.repository.Usuarios;

@Named
@ViewScoped
public class CadastroVendedorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuario usuario;
	@Inject
	private Usuarios usuarios;

	private Object estadoEndereco;

	

	private void limpar() {
		usuario = new Usuario();
	}

	public void dadoSession() {
		
	}

	public void salvar() /*throws EmailException*/ {
		
		
		usuarios.CadastrarUsuario(usuario);
		
		limpar();
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario cliente) {
		this.usuario = cliente;
	}

	public Object getEstadoEndereco() {
		return estadoEndereco;
	}

	public void setEstadoEndereco(Object estadoEndereco) {
		this.estadoEndereco = estadoEndereco;
	}

	

}
