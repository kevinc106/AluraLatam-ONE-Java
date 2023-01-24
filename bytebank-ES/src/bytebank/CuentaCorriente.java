package bytebank;

public class CuentaCorriente extends Cuenta implements ObtenerComision{
 
	public CuentaCorriente( int agencia, int numero) {
		super(agencia, numero);
	}

	@Override 
	public boolean saca(double valor) {
		double valorTotal = obtenerComision(valor);
		if(this.saldo >= valorTotal) {
            this.saldo -= valorTotal;
            return true;
        } else {
            return false;
        }
	}

	@Override
	public void deposita(double valor) {
		if(valor>0)
			this.saldo+=valor;
	}

	@Override
	public double obtenerComision(double valor) {
		return valor + 0.2;
	}
	
}
