package com.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pedidovenda.model.Usuario;
import com.pedidovenda.repository.Usuarios;


@Named
@ViewScoped
public class PesquisaVendedorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	private String filtro;
	private List<Usuario> usuarioFiltrados;
	@Inject
	private Usuario usuarioSelecionado;
	
	public PesquisaVendedorBean() {
	}
	
	public void excluir() {
		usuarios.remover(usuarioSelecionado);
		usuarioFiltrados.remove(usuarioSelecionado);
		
	//	FacesUtil.addInfoMessage("Produto " + produtoSelecionado.getSku() 
	//			+ " excluído com sucesso.");
	}
	
	public void pesquisar() {
		usuarioFiltrados = usuarios.filtrados(filtro);
	}
	
	

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getUsuarioFiltrados() {
		return usuarioFiltrados;
	}

	public void setUsuarioFiltrados(List<Usuario> usuarioFiltrados) {
		this.usuarioFiltrados = usuarioFiltrados;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
	

	
}