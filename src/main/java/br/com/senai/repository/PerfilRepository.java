package br.com.senai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

}
