package com.pedidovenda.model;

public enum StatusPedido {

	ORCAMENTO("Orçamento"),
	EMITIDO("Emitido"),// Vai conferir - Vendedor
	CONFIRMADO("Confirmado"),//Vai pro estoque - Admin
	PAGAMENTOPENDENTE("Pagamento Pendente"),
	ENVIADO("Enviado");
	
	
	private String descricao;
	
	StatusPedido(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
