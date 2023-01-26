
public class TestConexion {
	public static void main(String[] args) {
		
		//Use with resources that need to be close 
		try (Conexion con = new Conexion()) {
			con.leerDatos();
		} catch (Exception e) {
			System.out.println("recibiendo exception");
			e.printStackTrace();
		} 
		//Use this with objects
		/*Conexion con = new Conexion();
		try {			
			con.leerDatos();
		} catch (IllegalStateException e) {
			System.out.println("recibiendo exception");
			e.printStackTrace();
		}finally {			
			con.cerrar();
		}*/
	}
}
