package com.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pedidovenda.model.Produto;
import com.pedidovenda.repository.Produtos;
import com.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Produto produto;
	@Inject
	private Produtos produtos;

	public void cadastrar() {
		System.out.println("Metodo de cadastro...");
		System.out.println(produto.toString());
		produtos.guardar(produto);
		FacesUtil.addInfoMessage(produto.getNome()+" cadastrado com sucesso");
		
		limpar();
		
	}
	
	
	
	public void limpar(){
		
		produto = new Produto();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}