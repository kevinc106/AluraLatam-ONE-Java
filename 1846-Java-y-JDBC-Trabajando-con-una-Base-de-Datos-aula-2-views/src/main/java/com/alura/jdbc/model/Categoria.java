package com.alura.jdbc.model;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
	private Integer id;
	private String nombre; 
	
	private List<Producto> productos;
	
	public Categoria(String nombre) { 
		this.nombre = nombre;
	}
	public Categoria(int id, String nombre) {
		this.id=id;
		this.nombre=nombre;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	} 
	
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	@Override
	public String toString(){
		return this.nombre;
	}
	public void agregar(Producto producto) {
		if(this.productos == null) {
			this.productos = new ArrayList<>();
		}	
		this.productos.add(producto);
	}
	
}
