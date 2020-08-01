package com.android.oscarapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.android.oscarapp.model.Token;

public interface ITokenRepository extends JpaRepository<Token, Long>{

	List<Token> findAllByLoginAndTokenStatus(String login, String tokenStatus);
	
	@Query(value="select token from token where token_status = ?1", nativeQuery=true)
	List<Integer> findTokenByTokenStatus(String tokenStatus);
	
	Token findByTokenAndTokenStatus(int idToken, String tokenStatus);
	
}
