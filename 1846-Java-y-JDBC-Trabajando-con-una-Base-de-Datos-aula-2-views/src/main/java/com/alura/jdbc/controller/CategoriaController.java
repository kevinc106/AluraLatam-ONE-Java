package com.alura.jdbc.controller;

import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.dao.CategoriaDAO;
import com.alura.jdbc.dao.ProductoDAO;
import com.alura.jdbc.factory.ConexionFactory;
import com.alura.jdbc.model.Categoria;

public class CategoriaController {
	
	private CategoriaDAO categoriaDAO;
	
	public CategoriaController() {
		var factory = new ConexionFactory();
		this.categoriaDAO = new CategoriaDAO(factory.recuperaConexion());
	}
	public List<Categoria> listar() { 
		return this.categoriaDAO.ListarCategorias();
	}

    public List<Categoria> cargaReporte() {
    	return this.categoriaDAO.listarConProductos(); 
    }

}
