package com.alura.jdbc.controller;
 
import java.util.List; 

import com.alura.jdbc.dao.ProductoDAO;
import com.alura.jdbc.factory.ConexionFactory;
import com.alura.jdbc.model.Producto;

public class ProductoController {

	private ProductoDAO productoDAO;
	
	public ProductoController() {
		var factory = new ConexionFactory();
		this.productoDAO = new ProductoDAO(factory.recuperaConexion());
	}
	
	public void modificar(Producto producto) { 
		this.productoDAO.modificarProducto(producto); 
	}

	public void eliminar(Integer id) {
		this.productoDAO.eliminarProducto(id); 
	}

	public List<Producto> listar(){
		return this.productoDAO.ListarProductos();
	}
	
	public List<Producto> listar(Integer categoriaId){
		return this.productoDAO.ListarProductos(categoriaId);
	}

    public void guardar(Producto producto){ 
    	this.productoDAO.guardarProducto(producto);
	}


}
