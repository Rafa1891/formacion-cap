package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Departamento;
import com.example.demo.service.DepartamentoService;



@Controller
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService servicio;

@GetMapping("/departamentos")
public String departamentos(Model modelo) {
	
	modelo.addAttribute("dato",servicio.listarTodosLosDepartamentos());
	return "departamentos";
}

@GetMapping("departamentos/nuevo")
public String crearDepartamentos(Model modelo) {
	Departamento d=new Departamento();
	
	modelo.addAttribute("keyDepartamento",d);
	return "nuevo_departamento";
}

@PostMapping("/departamento")
public String guardarDepartamento(@ModelAttribute("KeyDepartamento") Departamento d) {
	
	servicio.guardarDepartamento(d);
	return "redirect:/departamentos";
}

@GetMapping("/departamentos/editar/{id}")
public String editarDepartamento(@PathVariable Long id, Model modelo) {
	modelo.addAttribute("keyDepartamento", servicio.obtenerDepartamento(id));
	
	return "editar_departamento";
}

@PostMapping("/departamentos/{id}")
public String actualizarDepartamento(@PathVariable Long id,@ModelAttribute("keyDepartamento") Departamento d) {
	Departamento depExistente=servicio.obtenerDepartamento(id);
	
	depExistente.setId(id);
	depExistente.setNombre(d.getNombre());
	
	
	
	servicio.guardarDepartamento(depExistente);
	
	
	
	return "redirect:/departamentos";
}

@GetMapping("/departamentos/eliminar/{id}")
public String eliminarDepartamento(@PathVariable Long id) {
	
	
	servicio.eliminarDepartamento(id);
	
	
	
	return "redirect:/departamentos";
}

}
