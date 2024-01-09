package nucleo;

import java.util.Date;

import definiciones.Definiciones;
import definiciones.Errores;
import utilidades.Auxiliares;
import utilidades.Validaciones;

public class Medico extends Persona {
	private String numeroRegistroMedico;
	private Date fechaInscripcionCMF;

	public Medico(String nombre, String primerApellido, String segundoApellido, String carnetIdentidad,String numeroRegistroMedico,Date fechaInscripcionCMF) {
		super(nombre, primerApellido, segundoApellido, carnetIdentidad, Definiciones.EDAD_MIN_TRABAJADOR_MEDICO, Definiciones.EDAD_MAX_TRABAJADOR_MEDICO);
		setNumeroRegistroMedico(numeroRegistroMedico);
		setFechaInscripcionCMF(fechaInscripcionCMF);
	}

	public String getNumeroRegistroMedico() {
		return numeroRegistroMedico;
	}

	private void setNumeroRegistroMedico(String numeroRegistroMedico) {
		if(Validaciones.validarNumeroRegistroMedico(numeroRegistroMedico))
			this.numeroRegistroMedico = numeroRegistroMedico;
		else
			throw new IllegalArgumentException(Errores.ERROR_NUMERO_REGISTRO_MEDICO);
	}

	public Date getFechaInscripcionCMF() {
		return fechaInscripcionCMF;
	}

	private void setFechaInscripcionCMF(Date fechaInscripcionCMF) { 
		if(fechaInscripcionCMF!=null && Validaciones.validarRangoFecha(fechaInscripcionCMF, Auxiliares.sumarAnyosFecha(getFechaNacimiento(), Definiciones.EDAD_MIN_TRABAJADOR_MEDICO), new Date()))
			this.fechaInscripcionCMF = fechaInscripcionCMF;
		else
			throw new IllegalArgumentException(Errores.ERROR_FECHA_INSCRIPCION_CMF_MEDICO);

	}
}
