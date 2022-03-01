package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProyectoDao;
import com.example.demo.entity.Proyecto;

@Service
public class ProyectoServiceImpl implements ProyectoService{
	@Autowired
	private ProyectoDao repositorio;
	
	public List<Proyecto> listarTodosLosProyectos(){
		return repositorio.findAll();
	}

	@Override
	public Proyecto guardarProyecto(Proyecto p) {
		
		return repositorio.save(p);
	}

	@Override
	public Proyecto obtenerProyecto(Long id) {
		
		return repositorio.findById(id).get();
	}

	@Override
	public void eliminarProyecto(Long id) {
		
		 repositorio.deleteById(id);
	}
	
	

}
