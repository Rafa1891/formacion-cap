package com.example.demo.apirest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.apirest.entity.Cliente;
@Repository
public interface ClienteDao extends CrudRepository<Cliente,Long>{

}
