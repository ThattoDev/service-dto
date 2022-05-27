package br.com.senai.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.senai.dto.UsuarioDTO;
import br.com.senai.dto.UsuarioInserirDTO;
import br.com.senai.exception.EmailException;
import br.com.senai.model.Usuario;
import br.com.senai.model.UsuarioPerfil;
import br.com.senai.repository.UsuarioPerfilRepository;
import br.com.senai.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private UsuarioPerfilRepository usuarioPerfilRepository ;
	
	public List<UsuarioDTO> listar(){
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = new ArrayList<>();
		for(Usuario u: usuarios) {
			UsuarioDTO usuarioDTO = new UsuarioDTO(u);
			usuariosDTO.add(usuarioDTO);
		}
		//return usuariosDTO ;
		return usuarios.stream().map(u -> new UsuarioDTO(u)).collect(Collectors.toList());
	}
	public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO) {
		
		if(usuarioRepository.findByEmail(usuarioInserirDTO.getEmail())!=null) {
			throw new EmailException("Este email ja está cadastrado!");
		}
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioInserirDTO.getNome());
		usuario.setEmail(usuarioInserirDTO.getEmail());
		usuario.setSenha(bCryptPasswordEncoder.encode(usuarioInserirDTO.getSenha()));
		usuarioRepository.save(usuario);
		
		for(UsuarioPerfil usuarioPerfil: usuarioInserirDTO.getUsuarioPerfils()) {
			usuarioPerfil.setUsuario(usuario);
			usuarioPerfil.setDataCriacao(LocalDate.now());
			usuarioPerfil.setPerfil(perfilService.buscar(usuarioPerfil.getPerfil().getId()));
			
		}
		usuarioPerfilRepository.saveAll(usuarioInserirDTO.getUsuarioPerfils());
		return new UsuarioDTO(usuario);
	}
}
