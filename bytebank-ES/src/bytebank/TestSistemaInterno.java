package bytebank;

public class TestSistemaInterno {

	public static void main(String[] args) {
		SistemaInterno sistema = new SistemaInterno();
		Gerente gerente1 = new Gerente();
		gerente1.setClave("soygerente");
		Administrador admin = new Administrador();
		admin.setClave("soyadmin");
		
		sistema.autentica(gerente1);
		sistema.autentica(admin);
	}
}
