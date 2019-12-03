package com.android.oscarapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.oscarapp.dto.VotoDTO;
import com.android.oscarapp.exception.InvalidTokenException;
import com.android.oscarapp.exception.UserAlreadySigned;
import com.android.oscarapp.model.Token;
import com.android.oscarapp.model.Usuario;
import com.android.oscarapp.model.VotoDiretor;
import com.android.oscarapp.model.VotoFilme;
import com.android.oscarapp.repository.ITokenRepository;
import com.android.oscarapp.repository.IUsuarioRepository;
import com.android.oscarapp.repository.IVotoDiretorRepository;
import com.android.oscarapp.repository.IVotoFilmeRepository;
import com.android.oscarapp.util.Constants;
import com.android.oscarapp.util.ExceptionMessages;
import com.android.oscarapp.vo.ResultadoVO;
import com.android.oscarapp.vo.UsuarioVO;
import com.android.oscarapp.vo.VotoVO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {
	
	@Autowired
	IUsuarioRepository usuarioRepository;
	
	@Autowired
	ITokenRepository tokenRepository;
	
	@Autowired
	IVotoDiretorRepository votoDiretorRepository;
	
	@Autowired
	IVotoFilmeRepository votoFilmeRepository;
	
	public void save(UsuarioVO usuarioVO) throws Exception {
		
		Usuario ch = usuarioRepository.findByLogin(usuarioVO.getLogin());
		if(ch != null) {
			throw new UserAlreadySigned(ExceptionMessages.USER_ALREADY_SIGNED);
		}
		
		Usuario usuario = new Usuario(usuarioVO);
		this.usuarioRepository.save(usuario);
	}
	
	public void vote(VotoDTO votoDto) throws Exception {
		String login;
		Token token =  tokenRepository.findByTokenAndTokenStatus(votoDto.getToken(), Constants.IDLE_TOKEN);
		
		if(token == null) {
			throw new InvalidTokenException(ExceptionMessages.INVALID_TOKEN);
		}
		
		login = token.getLogin();
		
		/*
		 * Registra os votos nas tabelas respectivas
		 */
		VotoDiretor vDiretor = new VotoDiretor(votoDto.getVotoDiretorVO());
		VotoFilme vFilme = new VotoFilme(votoDto.getVotoFilmeVO());
		vDiretor.setLogin(login);
		vFilme.setLogin(login);
		
		votoDiretorRepository.save(vDiretor);
		votoFilmeRepository.save(vFilme);
		
		/*
		 * Atualiza na tabela de usuários e diz que o usuário já votou
		 */
		token.setTokenStatus(Constants.INVALID_TOKEN);
		tokenRepository.save(token);
		Usuario usr = usuarioRepository.findByLogin(login);
		usr.setVoto("OK");
		usuarioRepository.save(usr);
		
	}
	
	public ResultadoVO getVotes(VotoVO votoVO) {
		
		ResultadoVO resultadoVO = new ResultadoVO();
		
		VotoDiretor vDiretor = votoDiretorRepository.findByLogin(votoVO.getLogin());
		VotoFilme vFilme = votoFilmeRepository.findByLogin(votoVO.getLogin());
			
		resultadoVO.setNomeDiretor(vDiretor.getNome());
		resultadoVO.setNomeFilme(vFilme.getNome());
		
		return resultadoVO;
	}

}
