package com.android.oscarapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.android.oscarapp.service.LoginService;
import com.android.oscarapp.service.UsuarioService;
import com.android.oscarapp.vo.LoginVO;
import com.android.oscarapp.vo.UsuarioVO;

@RestController
@RequestMapping({"/user"})
public class UserController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	UsuarioService usuarioService;

	@PostMapping(path="/login")
	public LoginVO findByLogin(@RequestBody LoginVO loginVO) throws Exception{
		return this.loginService.findByLogin(loginVO);
	}
	 	
	@PostMapping(path="/save")
	public void save(@RequestBody UsuarioVO usuarioVO) throws Exception{
		this.usuarioService.save(usuarioVO);
	}
	
}