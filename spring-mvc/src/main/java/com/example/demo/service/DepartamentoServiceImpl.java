package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DepartamentoDao;

import com.example.demo.entity.Departamento;
@Service
public class DepartamentoServiceImpl implements DepartamentoService{

	@Autowired
	private DepartamentoDao repositorio;
	@Override
	public List<Departamento> listarTodosLosDepartamentos() {
		return repositorio.findAll();
		
	}

	@Override
	public Departamento guardarDepartamento(Departamento d) {
		return repositorio.save(d);
		
	}

	@Override
	public Departamento obtenerDepartamento(Long id) {
		return repositorio.findById(id).get();
		
	}

	@Override
	public void eliminarDepartamento(Long id) {
		repositorio.deleteById(id);
		
	}

}
