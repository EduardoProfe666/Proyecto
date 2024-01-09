package nucleo;

import definiciones.Errores;
import utilidades.Validaciones;

public class Direccion implements Comparable<Direccion>{
	private String callePrincipal;
	private String numeroCasa;

	public Direccion(String callePrincipal, String numeroCasa) {
		setCallePrincipal(callePrincipal);
		setNumeroCasa(numeroCasa);
	}
	
	public String getCallePrincipal() {
		return callePrincipal;
	}

	private void setCallePrincipal(String callePrincipal) {
		if(Validaciones.validarStringNoVacio(callePrincipal)) 
			this.callePrincipal = callePrincipal;
		else
			throw new IllegalArgumentException(Errores.ERROR_CALLE_PRINCIPAL_DIRECCION);
	}

	public String getNumeroCasa() {
		return numeroCasa;
	}

	private void setNumeroCasa(String numeroCasa) {
		if(Validaciones.validarStringNoVacio(numeroCasa))
			this.numeroCasa = numeroCasa;
		else
			throw new IllegalArgumentException(Errores.ERROR_NUMERO_CASA_DIRECCION);
	}
	
	public String getDireccionCompleta() {
		return callePrincipal + " " + numeroCasa;
	}

	@Override
	public int compareTo(Direccion o) {
		return getDireccionCompleta().compareTo(o.getDireccionCompleta());
	}

}
