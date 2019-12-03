package com.android.oscarapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.android.oscarapp.model.VotoFilme;

public interface IVotoFilmeRepository extends JpaRepository<VotoFilme, Long> {

	VotoFilme findByLogin(String login);
	
}
