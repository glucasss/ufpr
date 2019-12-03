package com.android.oscarapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.android.oscarapp.vo.UsuarioVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="usuario")
public class Usuario {

	@Id
	@Column(name="login")
	private String login;
	
	@Column(name="senha")
	private String senha;
	
	@Column(name="voto")
	private String voto;
	
	public Usuario(UsuarioVO usuarioVO) {
		this.login = usuarioVO.getLogin();
		this.senha = usuarioVO.getSenha();
		this.voto = usuarioVO.getVoto();
	}
	
}
