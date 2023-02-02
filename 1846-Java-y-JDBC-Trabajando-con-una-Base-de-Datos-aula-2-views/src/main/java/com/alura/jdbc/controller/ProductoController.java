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

public class ProductoController {

	public void modificar(String nombre, String descripcion, Integer id) {
		// TODO
	}

	public void eliminar(Integer id) {
		// TODO
	}

	public List<Map<String,String>> listar() throws SQLException {
		Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC",
                "root",
                "main123");

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

    public void guardar(Object producto) {
		// TODO
	}

}
