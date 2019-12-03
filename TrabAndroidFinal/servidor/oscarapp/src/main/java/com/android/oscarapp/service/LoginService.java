package com.android.oscarapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.oscarapp.exception.UserNotFoundException;
import com.android.oscarapp.model.Usuario;
import com.android.oscarapp.repository.ILoginRepository;
import com.android.oscarapp.repository.ITokenRepository;
import com.android.oscarapp.util.Constants;
import com.android.oscarapp.util.ExceptionMessages;
import com.android.oscarapp.vo.LoginVO;

import lombok.AllArgsConstructor;

import com.android.oscarapp.model.Token;

@Service
@AllArgsConstructor
public class LoginService {

	@Autowired
	private final ILoginRepository loginRepository;
	
	@Autowired
	private final ITokenRepository tokenRepository;
	
	public LoginVO findByLogin(LoginVO loginVO) throws UserNotFoundException {
		Usuario user = loginRepository.findByLoginAndSenha(loginVO.getLogin(), loginVO.getSenha());
		
		LoginVO res;
		Integer tokenValido = 0;
		
		if(user == null) {
			throw new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND);
		}
		
		if(user.getVoto() == null || user.getVoto().equals("")) {
		
			/*
			 * Busca atualiza os token antigos para INVALID
			 */
			List<Token> tokensUser = new ArrayList<Token>();
			tokensUser = tokenRepository.findAllByLoginAndTokenStatus(loginVO.getLogin(), Constants.IDLE_TOKEN);
			
			if(tokensUser.size() > 0) {
				tokensUser.forEach(item -> {
					item.setTokenStatus(Constants.INVALID_TOKEN);
					tokenRepository.save(item);
				});
			}
			
			/*
			 * Busca todos tokens ativos (IDLE)
			 */
			List<Integer> tokenList = new ArrayList<Integer>();
			tokenList = tokenRepository.findTokenByTokenStatus(Constants.IDLE_TOKEN);
			
			/*
			 * Gera int aleatório de 1 a 100 até encontrar um que
			 * não esteja na lista anterior
			 */
			tokenValido = new Random().nextInt(100)+1;
			
			while(tokenList.contains(tokenValido)) {
				tokenValido = new Random().nextInt(100)+1;
			}
			
			/*
			 * Salva o token gerado no momento do login
			 */
			Token tknFinal = new Token();
			tknFinal.setToken(tokenValido);
			tknFinal.setDtToken(new Date());
			tknFinal.setLogin(loginVO.getLogin());
			tknFinal.setTokenStatus(Constants.IDLE_TOKEN);
			tokenRepository.save(tknFinal);
			
		}
		
		/*
		 * Retorna para tela o objeto completo
		 */
		res = new LoginVO(user);
		res.setToken(tokenValido);
		
		return res;
		
	}
	
}
