package com.example.demo.apirest.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.apirest.dao.ClienteDao;
import com.example.demo.apirest.entity.Cliente;
import com.example.demo.apirest.entity.Region;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteDao clienteDao;
	@Override
	@Transactional(readOnly=true)//mejor rendimiento consultas
	public List<Cliente> findAll() {
		
		return (List<Cliente>) clienteDao.findAll();
	}
	@Override
	@Transactional(readOnly=true)
	public Cliente findById(Long id) {
		
		return clienteDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional//como va por post no es readOnly
	public Cliente save(Cliente cliente) {
		
		return clienteDao.save(cliente);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		
		clienteDao.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Region> findAllRegions() {
		return clienteDao.findAllRegions();
		
	}
	
	

}
