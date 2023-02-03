package com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.model.Categoria;
import com.alura.jdbc.model.Producto;

public class CategoriaDAO {
	final private Connection con;
	
	public CategoriaDAO(Connection con) {
		this.con=con;
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) { 
			throw new RuntimeException(e);
		}
	}
	
	
	public List<Categoria> ListarCategorias(){
		List<Categoria> resultado = new ArrayList<>();  
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT id,nombre FROM categoria");
			try(statement){				
				statement.execute();  
				final ResultSet resultSet = statement.getResultSet(); 
				
				try(resultSet){					
					while (resultSet.next()) {
						Categoria categoria = new Categoria(
						resultSet.getInt("id"),
						resultSet.getString("nombre"));
						resultado.add(categoria);
					} 
				} catch (SQLException e) { throw new RuntimeException(e); }
			} catch (SQLException e) { throw new RuntimeException(e); }
		} catch (SQLException e) { throw new RuntimeException(e); }
		return resultado;
	}


	public List<Categoria> listarConProductos() {
		List<Categoria> resultado = new ArrayList<>();  
		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT categoria.id, categoria.nombre, producto.id, producto.nombre, producto.cantidad "
					+ "FROM categoria "
					+ "INNER JOIN producto ON categoria.id = producto.categoria_id");
			try(statement){				
				statement.execute();  
				final ResultSet resultSet = statement.getResultSet(); 
				
				try(resultSet){					
					while (resultSet.next()) {
						Integer categoriaId = resultSet.getInt("categoria.id");
						String categoriaNombre = resultSet.getString("categoria.nombre");
						Categoria categoria = resultado
								.stream()
								.filter(cat -> cat.getId().equals(categoriaId))
								.findAny().orElseGet(() -> {
									Categoria cat = new Categoria(categoriaId,categoriaNombre);
									resultado.add(cat);
									return cat;
								});
						 Producto producto = new Producto(
								 	resultSet.getInt("producto.id"),
								 	resultSet.getString("producto.nombre"),
									resultSet.getInt("producto.cantidad"));
						 System.out.println(producto);
						 categoria.agregar(producto);
					} 
				} catch (SQLException e) { throw new RuntimeException(e); }
			} catch (SQLException e) { throw new RuntimeException(e); }
		} catch (SQLException e) { throw new RuntimeException(e); }
		return resultado;
	}
}
