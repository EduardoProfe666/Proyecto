package nucleo;

import java.util.Date;

import definiciones.Errores;
import utilidades.Validaciones;

public class Vacuna {
	private Date fechaVacunacion;
	private String nombreVacuna;

	public Vacuna(Date fechaVacunacion, String nombreVacuna) {
		setNombreVacuna(nombreVacuna); 
		setFechaVacunacion(fechaVacunacion);
	}

	public Date getFechaVacunacion() {
		return fechaVacunacion;
	}

	private void setFechaVacunacion(Date fechaVacunacion) {
		if(fechaVacunacion!=null)
			this.fechaVacunacion = fechaVacunacion;
		else
			throw new IllegalArgumentException(Errores.ERROR_FECHA_VACUNACION);
	}

	public String getNombreVacuna() {
		return nombreVacuna;
	}

	private void setNombreVacuna(String nombreVacuna) {
		if(Validaciones.validarNombreVacuna(nombreVacuna))
			this.nombreVacuna = nombreVacuna;  
		else
			throw new IllegalArgumentException(Errores.ERROR_NOMBRE_VACUNA);
	}	
}
