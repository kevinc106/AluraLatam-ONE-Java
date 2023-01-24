package bytebank;

public class SistemaInterno { 
	
	private String clave="soygerente";

	public boolean autentica(Autenticable autenticable){
		boolean puedeIniciarSesion = autenticable.iniciarSesion(clave);
		if(puedeIniciarSesion) {
			System.out.println("Bien");
			return true;
		}else {
			System.out.println("No");
			return false;
		}
	}
}
