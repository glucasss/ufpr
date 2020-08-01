package com.android.oscarapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.android.oscarapp.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByLogin(String login);

}
