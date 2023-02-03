package com.alura.jdbc.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConexionFactory {
	
	private DataSource dataSource;
	
	public ConexionFactory() {
		var poolDataSource = new ComboPooledDataSource();
		poolDataSource.setJdbcUrl("jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC");
		poolDataSource.setUser("root");
		poolDataSource.setPassword("main123");
		poolDataSource.setMaxPoolSize(10);
		this.dataSource = poolDataSource;
	}
	
	public Connection recuperaConexion() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) { 
			throw new RuntimeException(); 
		}
	}
}
