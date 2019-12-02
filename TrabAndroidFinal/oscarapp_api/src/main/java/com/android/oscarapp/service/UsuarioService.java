package com.android.oscarapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.oscarapp.exception.UserAlreadySigned;
import com.android.oscarapp.model.Usuario;
import com.android.oscarapp.repository.IUsuarioRepository;
import com.android.oscarapp.util.ExceptionMessages;
import com.android.oscarapp.vo.UsuarioVO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {
	
	@Autowired
	IUsuarioRepository usuarioRepository;
	
	public void save(UsuarioVO usuarioVO) throws Exception {
		
		Usuario ch = usuarioRepository.findByLogin(usuarioVO.getLogin());
		if(ch != null) {
			throw new UserAlreadySigned(ExceptionMessages.USER_ALREADY_SIGNED);
		}
		
		Usuario usuario = new Usuario(usuarioVO);
		this.usuarioRepository.save(usuario);
	}

}
