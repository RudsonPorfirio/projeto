package com.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import com.pedidovenda.model.Cliente;
import com.pedidovenda.model.Pedido;
import com.pedidovenda.model.Produto;
import com.pedidovenda.model.StatusPedido;
import com.pedidovenda.model.Usuario;
import com.pedidovenda.repository.Clientes;
import com.pedidovenda.repository.Pedidos;
import com.pedidovenda.repository.Produtos;
import com.pedidovenda.service.NegocioException;
import com.pedidovenda.util.jpa.Transactional;
import com.pedidovenda.util.jsf.FacesUtil;
import com.pedidovenda.util.jsf.SessionUtil;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Produto produto;
	@Inject
	private Produtos produtos;
	@Inject
	private Usuario usuarioLogado;
	@Inject
	private Pedido pedido;
	@Inject
	private Clientes clientes;
	@Inject
	private Cliente cliente;
	
	@Inject
	private Pedidos pedidos;

	private String comprovanteCaminho;

	private UploadedFile file;

	private List<Cliente> listaClientes;

	public CadastroPedidoBean() {

	}

	public void salvar() {
		System.out.println("Metodo de cadastro...");
		
		
		pedidos.guardar(processarPedido(this.pedido));
		
		System.out.println("comprovanteCaminho :" + comprovanteCaminho);
		FacesUtil.addInfoMessage("Pedido salva com sucesso");

	}

	public List<Cliente> completarCliente(String consulta) {
		List<Cliente> listaSugestao = new ArrayList<Cliente>();
		for (Cliente cliente : listaClientes) {
			if (cliente.getNome().toLowerCase()
					.startsWith(consulta.toLowerCase())) {
				listaSugestao.add(cliente);
			}
		}
		return listaSugestao;
	}

	public void inicializar() {
		System.out.println("Inicializar ..");

		dadosVendedor();

		System.out.println("Usuario Logado : " + usuarioLogado.getNome());

		listaClientes = clientes.todos();

		System.out.println(listaClientes.size());

	}

	private void dadosVendedor() {
		System.out.println("DADOS VENDEDOR ..");

		String tipo = (String) SessionUtil.getParam("tipo");

		System.out.println("Tipo : " + tipo);

		setUsuarioLogado((Usuario) SessionUtil.getParam(tipo + "Logado"));
	}

	@Transactional
	public Pedido processarPedido(Pedido pedido) {
		if (pedido.isNovo()) {
			pedido.setDataCriacao(new Date());
			pedido.setStatus(StatusPedido.EMITIDO);
			pedido.setVendedor(this.usuarioLogado);

			pedido.setComprovante((String) SessionUtil.getParam("comprovanteCaminho"));
		}

		pedido.recalcularValorTotal();

		if (pedido.isNaoAlteravel()) {
			throw new NegocioException(
					"Pedido não pode ser alterado no status "
							+ pedido.getStatus().getDescricao() + ".");
		}

//		if (pedido.getItens().isEmpty()) {
//			throw new NegocioException(
//					"O pedido deve possuir pelo menos um item.");
//		}

//		if (pedido.isValorTotalNegativo()) {
//			throw new NegocioException(
//					"Valor total do pedido não pode ser negativo.");
//		}

		return pedido;
	}

	public void limpar() {

	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}

	public Clientes getClientes() {
		return clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

}