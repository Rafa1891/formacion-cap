package com.example.demo.apirest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.apirest.entity.Producto;


@Repository
public interface ProductoDao extends CrudRepository<Producto,Long>{

}
