package com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.factory.ConexionFactory;
import com.alura.jdbc.model.Producto;

public class ProductoDAO {
	
	final private Connection con;
	
	public ProductoDAO(Connection con) {
		this.con=con;
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) { 
			throw new RuntimeException(e);
		}
	}
	
	public void guardarProducto(Producto producto){

    	String nombre = producto.getNombre();
    	String descripcion = producto.getDescripcion();
    	Integer cantidad = producto.getCantidad();
    	Integer cantidadMax = 50;   
        try { 
        	String query = String.format("INSERT INTO producto(nombre,descripcion,cantidad) VALUES (?,?,?)");
        	final PreparedStatement statement = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			try(statement){				
				do {
					
					int cantidadAGuardar = Math.min(cantidad, cantidadMax);
					ejecutarRegistro(nombre, descripcion, cantidadAGuardar, statement);         	
					cantidad -= cantidadMax;
					
				} while (cantidad>0);  
				con.commit();
				System.out.println("Commited product: "+producto.toString());
			} catch (SQLException e) {
				con.rollback();
			} 
        } catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}
	

	private void ejecutarRegistro(String nombre, String descripcion, Integer cantidad, PreparedStatement statement)
			throws SQLException { 
		statement.setString(1, nombre);
    	statement.setString(2, descripcion);
    	statement.setInt(3, cantidad);
        statement.execute();
        
	}
	
	public List<Producto> ListarProductos(){
		List<Producto> resultado = new ArrayList<>();  
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT id,nombre,descripcion,cantidad FROM producto");
			try(statement){				
				statement.execute();  
				final ResultSet resultSet = statement.getResultSet(); 
				
				try(resultSet){					
					while (resultSet.next()) {
						Producto producto = new Producto(
						resultSet.getInt("id"),
						resultSet.getString("nombre"),
						resultSet.getString("descripcion"),
						resultSet.getInt("cantidad"));
						resultado.add(producto);
					} 
				} catch (SQLException e) { throw new RuntimeException(e); }
			} catch (SQLException e) { throw new RuntimeException(e); }
		} catch (SQLException e) { throw new RuntimeException(e); }
		return resultado;
	}

	public void eliminarProducto(Integer id) { 
    	try{    		
    		final PreparedStatement statement = con.prepareStatement("DELETE FROM producto WHERE id=?");
    		try(statement){    		
    			statement.setInt(1, id);
    			statement.execute();
    			con.commit();
    		} catch (SQLException e) { con.rollback();throw new RuntimeException(e); }
    	}  catch (SQLException e) { throw new RuntimeException(e); }
	}

	public void modificarProducto(Producto producto) { 
    	try {
			final PreparedStatement statement = con.prepareStatement(
					"UPDATE producto SET nombre=?,descripcion=?,cantidad=? WHERE id=?");
			try(statement) {
				statement.setString(1, producto.getNombre());
				statement.setString(2, producto.getDescripcion());
				statement.setInt(3, producto.getCantidad());
				statement.setInt(4, producto.getId());
				statement.execute();
				con.commit();
			} catch (SQLException e) { con.rollback();throw new RuntimeException(e); }
		} catch (SQLException e) { throw new RuntimeException(e); }
	}
}
