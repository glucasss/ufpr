package com.android.oscarapp.vo;

import com.android.oscarapp.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {

	private String login;
	private String senha;
	private String voto;
	private int token;
	
	public LoginVO(Usuario user) {
		this.login = user.getLogin();
		this.senha = user.getSenha();
		this.token = 10;
		this.voto = user.getVoto();
	}
	
}
