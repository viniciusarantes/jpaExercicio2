package br.gov.sp.fatec.web.controller;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.gov.sp.fatec.model.Dono;
import br.gov.sp.fatec.security.JwtUtils;
import br.gov.sp.fatec.security.Login;

@RestController
@RequestMapping(value = "/access")
public class LoginController {
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager auth;
	public void setAuth(AuthenticationManager auth) {
		this.auth = auth;
	}
	
	@RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Dono login(@RequestBody Login login, HttpServletResponse response) throws JsonProcessingException {
		Authentication credentials = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
		Dono usuario = (Dono) auth.authenticate(credentials).getPrincipal();
		usuario.setSenha(null);
		response.setHeader("Token", JwtUtils.generateToken(usuario));
		return usuario;
	}
}

