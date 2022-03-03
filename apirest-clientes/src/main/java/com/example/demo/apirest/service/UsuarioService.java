package com.example.demo.apirest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.apirest.dao.UsuarioDao;
import com.example.demo.apirest.entity.Usuario;

public class UsuarioService implements UserDetailsService{
	//slf4j
	private Logger logger=LoggerFactory.getLogger(UsuarioService.class);
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Usuario usuario=usuarioDao.findByUsername(username);
		
		if(usuario==null) {
			logger.error(username);
		}
	}

}
