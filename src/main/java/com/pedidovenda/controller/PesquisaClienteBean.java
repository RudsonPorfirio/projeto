package com.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pedidovenda.model.Cliente;
import com.pedidovenda.model.Produto;
import com.pedidovenda.repository.Clientes;
import com.pedidovenda.repository.Produtos;

@Named
@ViewScoped
public class PesquisaClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Clientes cliente;
	
	private String filtro;
	private List<Cliente> listaClientesFiltrados;
	
	private Cliente clienteSelecionado;
	
	public PesquisaClienteBean() {
	}
	
	public void inicializar(){
		listaClientesFiltrados = cliente.filtrados(filtro);
		
	}
	
	public void excluir() {
		cliente.remover(clienteSelecionado);
		listaClientesFiltrados.remove(clienteSelecionado);
		
	//	FacesUtil.addInfoMessage("Produto " + produtoSelecionado.getSku() 
	//			+ " excluído com sucesso.");
	}
	
	public void pesquisar() {
		listaClientesFiltrados = cliente.filtrados(filtro);
	}
	
	

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getListaClientesFiltrados() {
		return listaClientesFiltrados;
	}

	public void setListaClientesFiltrados(List<Cliente> listaClientesFiltrados) {
		this.listaClientesFiltrados = listaClientesFiltrados;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
	
	
	
	
}