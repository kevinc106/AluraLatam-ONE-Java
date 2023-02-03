package com.alura.jdbc.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alura.jdbc.factory.ConexionFactory;

public class ProductoController {

	public void modificar(String nombre, String descripcion, String cantidad, Integer id) throws SQLException {
		final Connection con = new ConexionFactory().recuperaConexion();  
    	 
    	try(con) {
			final PreparedStatement statement = con.prepareStatement(
					"UPDATE producto SET nombre=?,descripcion=?,cantidad=? WHERE id=?");
			try(statement) {
				statement.setString(1, nombre);
				statement.setString(2, descripcion);
				statement.setInt(3, Integer.valueOf(cantidad));
				statement.setInt(4, id);
				statement.execute();
			}
		} 
         
	}

	public void eliminar(Integer id) throws SQLException {
		final Connection con = new ConexionFactory().recuperaConexion(); 
    	try(con){    		
    		final PreparedStatement statement = con.prepareStatement("DELETE FROM producto WHERE id=?");
    		try(statement){    		
    			statement.setInt(1, id);
    			statement.execute(); 
    		}
    	} 
	}

	public List<Map<String,String>> listar() throws SQLException {
		List<Map<String,String>> resultado = new ArrayList<>();
		final Connection con = new ConexionFactory().recuperaConexion(); 
		 
		try(con) {
			final PreparedStatement statement = con.prepareStatement("SELECT id,nombre,descripcion,cantidad FROM producto");
			try(statement){				
				statement.execute();  
				final ResultSet resultSet = statement.getResultSet(); 
				try(resultSet){					
					while (resultSet.next()) {
						Map<String,String> fila = new HashMap<>();
						fila.put("ID", String.valueOf(resultSet.getInt("id")));
						fila.put("Nombre", String.valueOf(resultSet.getString("nombre")));
						fila.put("Descripcion", String.valueOf(resultSet.getString("descripcion")));
						fila.put("Cantidad", String.valueOf(resultSet.getString("cantidad")));
						resultado.add(fila);
					} 
				}
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return resultado;
	}

    public void guardar(Map<String,String> producto) throws SQLException {
    	
    	String nombre = producto.get("Nombre");
    	String descripcion = producto.get("Descripcion");
    	Integer cantidad = Integer.valueOf(producto.get("Cantidad"));
    	Integer cantidadMax = 50;
    	
    	final Connection con = new ConexionFactory().recuperaConexion();
    	con.setAutoCommit(false);
        try(con) {
        	String query = String.format("INSERT INTO producto(nombre,descripcion,cantidad) VALUES (?,?,?)");
        	final PreparedStatement statement = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			try(statement){				
				do {
					
					int cantidadAGuardar = Math.min(cantidad, cantidadMax);
					ejecutarRegistro(nombre, descripcion, cantidadAGuardar, statement);         	
					cantidad -= cantidadMax;
					
				} while (cantidad>0);  
				con.commit();
				System.out.println("Commited");
			} catch (SQLException e) {
				System.out.println("Rollback");
				con.rollback();
			} 
        } 
        System.out.println("Close");
	}

	private void ejecutarRegistro(String nombre, String descripcion, Integer cantidad, PreparedStatement statement)
			throws SQLException { 
		statement.setString(1, nombre);
    	statement.setString(2, descripcion);
    	statement.setInt(3, cantidad);
        statement.execute();
        
	}

}
