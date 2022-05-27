package br.com.senai.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="usuario_perfil")
public class UsuarioPerfil {
	
	@EmbeddedId
	private UsuarioPerfilPK id = new UsuarioPerfilPK();
	
	@Column(name="data_criacao")
	private LocalDate dataCriacao;

	public UsuarioPerfilPK getId(){
		return id;
	}
	
	public void setId(UsuarioPerfilPK id) {
		this.id = id;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public void setUsuario(Usuario usuario) {
		id.setUsuario(usuario);
	}
	public void setPerfil(Perfil perfil) {
		id.setPerfil(perfil);
	}
	public Usuario getUsuario(){
		return id.getUsuario();
	}
	public Perfil getPerfil(){
		return id.getPerfil();
	}
	
	

}
