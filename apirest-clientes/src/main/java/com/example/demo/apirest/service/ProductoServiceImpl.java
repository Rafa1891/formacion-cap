package com.example.demo.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.demo.apirest.dao.ProductoDao;

import com.example.demo.apirest.entity.Producto;
@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private ProductoDao productoDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<Producto> findAll() {
		
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	public Producto findById(Long id) {
		return productoDao.findById(id).orElse(null);
		
	}

	@Override
	public Producto save(Producto producto) {
		return productoDao.save(producto);
		
	}

	@Override
	public void delete(Long id) {
		productoDao.deleteById(id);
		
	}

}
