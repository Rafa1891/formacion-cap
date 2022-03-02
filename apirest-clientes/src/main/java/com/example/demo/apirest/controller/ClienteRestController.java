package com.example.demo.apirest.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	/*
	@GetMapping("/clientes/{id}")
	public Cliente buscarClienteporId(@PathVariable Long id){
		return servicio.findById(id);
	}*/
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> buscarClienteporId(@PathVariable Long id){
		
		Cliente cliente=null;
		
		Map<String,Object> response=new HashMap<>();
		
		try {
			cliente=  servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(cliente==null) {
			response.put("mensaje", "El cliente id:".concat(id.toString().concat(" no existe en la BD.")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
	}
	
	/*
	@PostMapping("/cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente guardarCliente(@RequestBody Cliente cliente) {// recibe el body y lo guarda en obj cliente
		return servicio.save(cliente);
	}
	*/
	@PostMapping("/cliente")
	public ResponseEntity<?> guardarCliente(@RequestBody Cliente cliente) {
		
		Cliente clienteNuevo=null;
		Map<String,Object> response=new HashMap<>();
		
		try {
			clienteNuevo=servicio.save(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar inserción.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "EL cliente fue creado con éxito.");
		response.put("cliente", clienteNuevo);
		
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	/*
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
	*/
	@PutMapping("/cliente/{id}")
	public ResponseEntity<?> actualizarCliente(@RequestBody Cliente c,@PathVariable Long id) {
		
		Cliente cliActualizar=servicio.findById(id);
		
		Map<String,Object> response=new HashMap<>();
		
		if(cliActualizar==null) {
			response.put("mensaje", "El cliente id:".concat(id.toString().concat(" no existe en la BD.")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
				
		try {
			cliActualizar.setNombre(c.getNombre());
			cliActualizar.setApellido(c.getApellido());
			cliActualizar.setEmail(c.getEmail());
			cliActualizar.setTelefono(c.getTelefono());
			cliActualizar.setCreatedAt(c.getCreatedAt());
			
			servicio.save(cliActualizar);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar actualización.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "EL cliente fue actualizado con éxito.");
		response.put("cliente", cliActualizar);
		
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
		
	}
	
	
	/*
	@DeleteMapping("/cliente/{id}")
	public void eliminarCliente(@PathVariable Long id) {
		servicio.delete(id);
	}
	
	*/
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
		Cliente cliActualizar=servicio.findById(id);	
		
		Map<String,Object> response=new HashMap<>();
		
		try {
			servicio.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(cliActualizar==null) {
			response.put("mensaje", "El cliente id:".concat(id.toString().concat(" no existe en la BD.")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "Eliminado con éxito.");
		response.put("cliente", cliActualizar);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@PostMapping("cliente/subida")
	public ResponseEntity<?> subida(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){// requestparamasocia un archivo a una imagen
		Map<String,Object> response=new HashMap<>();
		
		Cliente c=servicio.findById(id);
		
		if(!archivo.isEmpty()) {
			String nombreArchivo=archivo.getOriginalFilename();//RECOGEMOS EL NOMBRE DEL ARCHIVO
			Path rutaArchivo=Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();//GUARDAMOS LA RUTA DE LA CARPETA UPLOADS
			
			
		
		try {
			Files.copy(archivo.getInputStream(), rutaArchivo);
		} catch (IOException e) {
			response.put("mensaje", "Error al subir la imagen");
			response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		c.setImagen(nombreArchivo);
		servicio.save(c);
		
		response.put("cliente", c);
		response.put("mensaje", "Has subido correctamente la imagen"+nombreArchivo);
		}
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
}
