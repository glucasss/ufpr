package com.android.oscarapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.android.oscarapp.model.Usuario;

@Repository
public interface ILoginRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByLoginAndSenha(String login, String senha);
	
}
