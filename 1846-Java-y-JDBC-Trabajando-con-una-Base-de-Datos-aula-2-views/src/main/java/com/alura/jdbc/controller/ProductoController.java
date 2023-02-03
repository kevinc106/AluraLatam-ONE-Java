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
		Connection con = new ConexionFactory().recuperaConexion();  
    	 
    	PreparedStatement statement = con.prepareStatement(
    			"UPDATE producto SET nombre=?,descripcion=?,cantidad=? WHERE id=?");
        statement.setString(1, nombre);
        statement.setString(2, descripcion);
        statement.setInt(3, Integer.valueOf(cantidad));
        statement.setInt(4, id);
    	statement.execute(); 
         
        con.close(); 
	}

	public void eliminar(Integer id) throws SQLException {
		Connection con = new ConexionFactory().recuperaConexion(); 
    	 
    	PreparedStatement statement = con.prepareStatement("DELETE FROM producto WHERE id=?");
    	statement.setInt(1, id);
        statement.execute(); 
         
        con.close(); 
	}

	public List<Map<String,String>> listar() throws SQLException {
		Connection con = new ConexionFactory().recuperaConexion();

		PreparedStatement statement = con.prepareStatement("SELECT id,nombre,descripcion,cantidad FROM producto");
        statement.execute(); 
         
        ResultSet resultSet = statement.getResultSet();
        
        List<Map<String,String>> resultado = new ArrayList<>();
        
        while (resultSet.next()) {
        	Map<String,String> fila = new HashMap<>();
        	fila.put("ID", String.valueOf(resultSet.getInt("id")));
        	fila.put("Nombre", String.valueOf(resultSet.getString("nombre")));
        	fila.put("Descripcion", String.valueOf(resultSet.getString("descripcion")));
        	fila.put("Cantidad", String.valueOf(resultSet.getString("cantidad")));
        	resultado.add(fila);
		}
        
        con.close();
		return resultado;
	}

    public void guardar(Map<String,String> producto) throws SQLException {
    	Connection con = new ConexionFactory().recuperaConexion();
    	String nombre = producto.get("Nombre");
    	String descripcion = producto.get("Descripcion");
    	Integer cantidad = Integer.valueOf(producto.get("Cantidad"));
    	 
    	String query = String.format("INSERT INTO producto(nombre,descripcion,cantidad) VALUES (?,?,?)");
    	PreparedStatement statement = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
    	statement.setString(1, nombre);
    	statement.setString(2, descripcion);
    	statement.setInt(3, cantidad);
        statement.execute(); 
         
        con.close(); 
    	
	}

}
