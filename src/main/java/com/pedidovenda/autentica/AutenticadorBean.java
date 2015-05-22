package com.pedidovenda.autentica;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.pedidovenda.model.NivelUsuario;
import com.pedidovenda.model.Usuario;
import com.pedidovenda.repository.Usuarios;
import com.pedidovenda.util.jsf.FacesUtil;
import com.pedidovenda.util.jsf.SessionUtil;

@Named
@ViewScoped
public class AutenticadorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String senha;
	
	
	@Inject
	private Usuarios repositorioUsuario;
	@Inject
	private Usuario usuario;

	public AutenticadorBean() {
	}

	public String autentica() {
		System.out.println("autentica..");
		usuario = repositorioUsuario.porEmail(email);

		if (usuario != null && usuario.getSenha().equals(senha)) {
			System.out.println("Confirmou  usuario e senha ...");

			SessionUtil.setParam(usuario.getNivelUsuario() + "Logado", usuario);

			SessionUtil.setParam("tipo", ""+usuario.getNivelUsuario());

			System.out.println(usuario.getNome()+", "+usuario.getNivelUsuario());
			
			
			if (usuario.getNivelUsuario().toString().equals(NivelUsuario.VENDEDOR+"")) {
				System.out.println(usuario.getNivelUsuario() + "Logado");
				return "/cliente/CadastroCliente.xhtml?faces-redirect=true";
			}
			else if (usuario.getNivelUsuario().toString().equals(NivelUsuario.ADMIN+"")) {
				System.out.println(usuario.getNivelUsuario()+"Logado");
				return "/admin/Principal.xhtml?faces-redirect=true";
			}
			else if (usuario.getNivelUsuario().toString().equals(NivelUsuario.CONFERIDOR+"")) {
				System.out.println(usuario.getNivelUsuario()+"Logado");
				return "/confirmacao/ListaPedidos.xhtml?faces-redirect=true";
			}	
			else if (usuario.getNivelUsuario().toString().equals(NivelUsuario.ESTOQUE+"")) {
					System.out.println(usuario.getNivelUsuario()+"Logado");
					return "/estoque/ListaPedidos.xhtml?faces-redirect=true";
			} 
			else {
				System.out.println("Redireciona para nda");
				return null;
			}
		} else {
			FacesUtil.addErrorMessage("Usuario o senha invalido");
			return null;
		}
	}

	public String registraSaida() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		try {
			session.removeAttribute("VENDEDORLogado");
			session.removeAttribute("ADMINLogado");
			session.removeAttribute("CONFERIDORLogado");
			session.removeAttribute("ESTOQUELogado");
		} catch (Exception exception) {
		}
		return "/Login?faces-redirect=true";
	}

	public String getUsuario() {
		return email;
	}

	public void setUsuario(String usuario) {
		email = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}