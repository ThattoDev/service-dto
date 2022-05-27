package br.com.senai.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.senai.model.Usuario;
import br.com.senai.model.UsuarioPerfil;

public class UsuarioInserirDTO {
	private String nome;
	private String email;
	private String senha;
	
	private Set<UsuarioPerfil> usuarioPerfils = new HashSet<>();
	
	public Set<UsuarioPerfil> getUsuarioPerfils() {
		return usuarioPerfils;
	}
	public void setUsuarioPerfils(Set<UsuarioPerfil> usuarioPerfils) {
		this.usuarioPerfils = usuarioPerfils;
	}
	public UsuarioInserirDTO() {
		// TODO Auto-generated constructor stub
	}
	public UsuarioInserirDTO(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
