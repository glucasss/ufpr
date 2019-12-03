package com.android.oscarapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.android.oscarapp.model.VotoDiretor;

public interface IVotoDiretorRepository extends JpaRepository<VotoDiretor, Long>{
	
	VotoDiretor findByLogin(String login);

}
