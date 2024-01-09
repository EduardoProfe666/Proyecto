package clases_auxiliares;

import definiciones.Definiciones;
import definiciones.Errores;
import utilidades.Validaciones;

public class VacunacionPiscinaDatos implements Comparable<VacunacionPiscinaDatos>{
	private String nombreVacuna;
	private int edadMin; 
	private int edadMax;
	
	public VacunacionPiscinaDatos(String nombreVacuna, int edadMin, int edadMax) {
		setNombreVacuna(nombreVacuna);
		if(edadMin>edadMax)
			throw new IllegalArgumentException(Errores.ERROR_EDAD_MIN_MAYOR_MAX);
		setEdadMin(edadMin);
		setEdadMax(edadMax);
	}

	public int getEdadMin() {
		return edadMin;
	}

	private void setEdadMin(int edadMin) {
		if(Validaciones.validarEnteroRango(edadMin, Definiciones.EDAD_MIN, Definiciones.EDAD_MAX))
			this.edadMin = edadMin;
		else
			throw new IllegalArgumentException(Errores.ERROR_EDAD_MIN);
	}

	public String getNombreVacuna() {
		return nombreVacuna;
	}

	private void setNombreVacuna(String nombreVacuna) {
		if(Validaciones.validarStringNoVacio(nombreVacuna) && Validaciones.validarTamString(nombreVacuna, 1, Definiciones.MAX_CANT_CARACTERES_NOMBRE_VACUNA))
			this.nombreVacuna = nombreVacuna;
		else
			throw new IllegalArgumentException(Errores.ERROR_EDAD_MAX);
	}
	
	public int getEdadMax() {
		return edadMax;
	}

	private void setEdadMax(int edadMax) {
		if(Validaciones.validarEnteroRango(edadMax, Definiciones.EDAD_MIN, Definiciones.EDAD_MAX))
			this.edadMax = edadMax;
		else
			throw new IllegalArgumentException(Errores.ERROR_EDAD_MAX);
	}

	@Override
	public int compareTo(VacunacionPiscinaDatos o) {
		return this.nombreVacuna.compareTo(o.nombreVacuna);
	}
	
}
