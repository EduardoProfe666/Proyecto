package clases_auxiliares;

import definiciones.Errores;
import nucleo.Direccion;

public class DireccionPiscinaDatos {
	private Direccion direccion;
	private int numeroVivienda;
	private int ultimoIdentificadorHabitante;
	
	public DireccionPiscinaDatos(Direccion direccion,int numeroVivienda){
		setDireccion(direccion);
		setNumeroVivienda(numeroVivienda);
		ultimoIdentificadorHabitante = 0;
	}
	
	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		if(direccion!=null)
			this.direccion = direccion;
		else
			throw new IllegalArgumentException(Errores.ERROR_DIRECCION_PISCINA_DATOS);
	}

	public int getNumeroVivienda() {
		return numeroVivienda;
	}

	public void setNumeroVivienda(int numeroVivienda) {
		if(numeroVivienda>0)
			this.numeroVivienda = numeroVivienda;
		else
			throw new IllegalArgumentException(Errores.ERROR_NUMERO_VIVIENDA);
	}

	public int generarIdentificadorHabitante() {
		return ++ultimoIdentificadorHabitante;
		
	}
	public int getUltimoIdentificadorHabitante() {
		return this.ultimoIdentificadorHabitante;
	}
}	
