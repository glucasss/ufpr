package com.android.oscarapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.oscarapp.exception.UserNotFoundException;
import com.android.oscarapp.model.Usuario;
import com.android.oscarapp.repository.ILoginRepository;
import com.android.oscarapp.util.ExceptionMessages;
import com.android.oscarapp.vo.LoginVO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginService {

	@Autowired
	private final ILoginRepository loginRepository;
	
	public LoginVO findByLogin(LoginVO loginVO) throws UserNotFoundException {
		Usuario user = loginRepository.findByLoginAndSenha(loginVO.getLogin(), loginVO.getSenha());
		
		if(user == null) {
			throw new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND);
		}
		
		LoginVO res = new LoginVO(user);
		
		return res;
	}
	
}
