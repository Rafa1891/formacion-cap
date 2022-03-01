package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Empleado;
import com.example.demo.service.EmpleadoService;

@Controller
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService servicio;

@GetMapping("/empleados")
public String empleados(Model modelo) {
	
	modelo.addAttribute("dato",servicio.listarTodosLosEmpleados());
	return "vista";
}

@GetMapping("empleados/nuevo")
public String crearEmpleados(Model modelo) {
	Empleado e=new Empleado();
	
	modelo.addAttribute("keyEmpleado",e);
	return "nuevo_empleado";
}

@PostMapping("/empleado")
public String guardarEmpleado(@ModelAttribute("KeyEmpleado") Empleado e) {
	
	servicio.guardarEmpleado(e);
	return "redirect:/empleados";
}

@GetMapping("/empleados/editar/{id}")
public String editarEmpleado(@PathVariable Long id, Model modelo) {
	modelo.addAttribute("keyEmpleado", servicio.obtenerEmpleado(id));
	
	return "editar_empleado";
}

@PostMapping("/empleados/{id}")
public String actualizarEmpleado(@PathVariable Long id,@ModelAttribute("keyEmpleado") Empleado e) {
	Empleado empExistente=servicio.obtenerEmpleado(id);
	
	empExistente.setId(id);
	empExistente.setNombre(e.getNombre());
	empExistente.setApellido(e.getApellido());
	empExistente.setEmail(e.getEmail());
	empExistente.setTelefono(e.getTelefono());
	
	servicio.guardarEmpleado(empExistente);
	
	
	
	return "redirect:/empleados";
}

@GetMapping("/empleados/eliminar/{id}")
public String eliminarEmpleado(@PathVariable Long id) {
	
	
	servicio.eliminarEmpleado(id);
	
	
	
	return "redirect:/empleados";
}

}
