package com.alura.jdbc.controller;

import java.sql.Connection;
import java.sql.DriverManager;
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
    	 
    	String query = String.format("UPDATE producto SET nombre='%s',descripcion='%s',cantidad=%s WHERE id=%s",
    			nombre,
    			descripcion,
    			cantidad,
    			String.valueOf(id));
    	Statement statement = con.createStatement();
        statement.execute(query); 
         
        con.close(); 
	}

	public void eliminar(Integer id) throws SQLException {
		Connection con = new ConexionFactory().recuperaConexion(); 
    	
    	String query = String.format("DELETE FROM producto WHERE id=%s", String.valueOf(id));
    	Statement statement = con.createStatement();
        statement.execute(query); 
         
        con.close(); 
	}

	public List<Map<String,String>> listar() throws SQLException {
		Connection con = new ConexionFactory().recuperaConexion();

        Statement statement = con.createStatement();
        statement.execute("SELECT id,nombre,descripcion,cantidad FROM producto"); 
         
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
    	String cantidad = producto.get("Cantidad");
    	
    	String query = String.format("INSERT INTO producto(nombre,descripcion,cantidad) VALUES ('%s','%s',%s)", nombre,descripcion,cantidad);
    	Statement statement = con.createStatement();
        statement.execute(query); 
         
        con.close(); 
    	
	}

}
