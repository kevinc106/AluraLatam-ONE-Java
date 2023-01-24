package bytebank;

//                  extiende de
public class Gerente extends Funcionario implements Autenticable {

	private AutenticacionUtil util;
     
    public double getBonificacion() {
        System.out.println("EJECUTANDO DESDE GERENTE");
        return 2000;
    } 
    
	public Gerente() {
		this.util = new AutenticacionUtil();
	} 

	@Override
	public void setClave(String clave) {
		this.util.setClave(clave);
	}

	@Override
	public boolean iniciarSesion(String clave) {
		return this.util.iniciarSesion(clave);
	}
}
