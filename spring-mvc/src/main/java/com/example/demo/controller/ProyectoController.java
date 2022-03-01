package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Empleado;
import com.example.demo.entity.Proyecto;
import com.example.demo.service.EmpleadoService;
import com.example.demo.service.ProyectoService;

@Controller
public class ProyectoController {

	
	@Autowired
	private ProyectoService servicio;

@GetMapping("/proyectos")
public String proyectos(Model modelo) {
	
	modelo.addAttribute("dato",servicio.listarTodosLosProyectos());
	return "proyectos";
}

@GetMapping("proyectos/nuevo")
public String crearProyecto(Model modelo) {
	Proyecto p=new Proyecto();
	
	modelo.addAttribute("keyProyecto",p);
	return "nuevo_proyecto";
}

@PostMapping("proyecto")
public String guardarProyecto(@ModelAttribute("KeyProyecto") Proyecto p) {
	
	servicio.guardarProyecto(p);
	return "redirect:/proyectos";
}

@GetMapping("/proyectos/editar/{id}")
public String editarProyecto(@PathVariable Long id, Model modelo) {
	modelo.addAttribute("keyProyecto", servicio.obtenerProyecto(id));
	
	return "editar_proyecto";
}

@PostMapping("/proyectos/{id}")
public String actualizarProyecto(@PathVariable Long id,@ModelAttribute("keyProyecto")Proyecto p) {
	Proyecto proyExistente=servicio.obtenerProyecto(id);
	
	proyExistente.setId(id);
	proyExistente.setNombre(p.getNombre());
	proyExistente.setFecha_inicio(p.getFecha_inicio());
	proyExistente.setFecha_fin(p.getFecha_fin());
	proyExistente.setActivo(p.isActivo());
	
	servicio.guardarProyecto(proyExistente);
	
	
	
	return "redirect:/proyectos";
}

@GetMapping("/proyectos/eliminar/{id}")
public String eliminarProyecto(@PathVariable Long id) {
	
	
	servicio.eliminarProyecto(id);
	
	
	
	return "redirect:/proyectos";
}
}
