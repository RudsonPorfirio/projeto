package com.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pedidovenda.model.Cliente;
import com.pedidovenda.repository.Clientes;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Cliente cliente;
	@Inject
	private Clientes repositorioClientes;

	private Object estadoEndereco;

	

	private void limpar() {
		cliente = new Cliente();
	}

	public void dadoSession() {
		
	}

	public void salvar() /*throws EmailException*/ {
		
		cliente.setEstado((String) estadoEndereco);
		
		repositorioClientes.CadastrarUsuario(cliente);
		
		limpar();
		
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Object getEstadoEndereco() {
		return estadoEndereco;
	}

	public void setEstadoEndereco(Object estadoEndereco) {
		this.estadoEndereco = estadoEndereco;
	}

	

}
