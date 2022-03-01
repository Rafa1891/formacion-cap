package com.example.demo.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.apirest.entity.Cliente;
import com.example.demo.apirest.service.ClienteService;

@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private ClienteService servicio;
	@GetMapping({"/clientes","/todos"})//peticion tipo get rutas clientes y todos
	public List<Cliente> index(){
		return servicio.findAll();
	}
	
	@GetMapping("/clientes/{id}")
	public Cliente buscarClienteporId(@PathVariable Long id){
		return servicio.findById(id);
	}
	
	@PostMapping("/cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente guardarCliente(@RequestBody Cliente cliente) {// recibe el body y lo guarda en obj cliente
		return servicio.save(cliente);
	}
	@PutMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente actualizarCliente(@RequestBody Cliente c,@PathVariable Long id) {
		Cliente cliActualizar=servicio.findById(id);
		
		cliActualizar.setNombre(c.getNombre());
		cliActualizar.setApellido(c.getApellido());
		cliActualizar.setEmail(c.getEmail());
		cliActualizar.setTelefono(c.getTelefono());
		cliActualizar.setCreatedAt(c.getCreatedAt());
		
		return servicio.save(cliActualizar);
	}
	
	@DeleteMapping("/cliente/{id}")
	public void eliminarCliente(@PathVariable Long id) {
		servicio.delete(id);
	}
	
}
