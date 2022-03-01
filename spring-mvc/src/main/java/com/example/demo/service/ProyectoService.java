package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Empleado;
import com.example.demo.entity.Proyecto;

public interface ProyectoService {

public List<Proyecto> listarTodosLosProyectos();
	
	public Proyecto guardarProyecto(Proyecto p);
	
	public Proyecto obtenerProyecto(Long id);
	
	public void eliminarProyecto(Long id);
}
