package nucleo;

import java.util.Date;

import definiciones.Definiciones;
import definiciones.Errores;
import utilidades.Auxiliares;
import utilidades.Validaciones;

public class Enfermera extends Persona {
	private boolean tieneLicenciatura;
	private Date fechaInscripcionCMF;
	private Date comienzoLaboral;
	
	public Enfermera(String nombre, String primerApellido, String segundoApellido, String carnetIdentidad,
			boolean tieneLicenciatura, Date fechaInscripcionCMF, Date comienzoLaboral) {
		super(nombre, primerApellido, segundoApellido, carnetIdentidad, Definiciones.EDAD_MIN_TRABAJADOR_MEDICO, Definiciones.EDAD_MAX_TRABAJADOR_MEDICO);
		setTieneLicenciatura(tieneLicenciatura);
		setComienzoLaboral(comienzoLaboral);
		setFechaInscripcionCMF(fechaInscripcionCMF);
		
		if(!Validaciones.validarSexo(carnetIdentidad, 'F'))
			throw new IllegalArgumentException(Errores.ERROR_SEXO_ENFERMERA);
	}
	
	public Date getFechaInscripcionCMF() {
		return fechaInscripcionCMF;
	}

	private void setFechaInscripcionCMF(Date fechaInscripcionCMF) {
		if(fechaInscripcionCMF!=null && comienzoLaboral!=null && Validaciones.validarRangoFecha(fechaInscripcionCMF,comienzoLaboral,new Date()))
			this.fechaInscripcionCMF = fechaInscripcionCMF;
		else
			throw new IllegalArgumentException(Errores.ERROR_FECHA_INSCRIPCION_CMF_ENFERMERA);
	}

	public Date getComienzoLaboral() {
		return comienzoLaboral;
	}

	private void setComienzoLaboral(Date comienzoLaboral) { 
		if(comienzoLaboral!=null && Validaciones.validarRangoFecha(comienzoLaboral, Auxiliares.sumarAnyosFecha(getFechaNacimiento(), Definiciones.EDAD_MIN_TRABAJADOR_MEDICO), new Date()))
			this.comienzoLaboral = comienzoLaboral;
		else
			throw new IllegalArgumentException(Errores.ERROR_COMIENZO_LABORAL_ENFERMERA);
	}

	public boolean getTieneLicenciatura() {
		return tieneLicenciatura;
	}
	
	public void setTieneLicenciatura(boolean licenciatura) {
		this.tieneLicenciatura = licenciatura;
	}
		
	public int getCantidadAgnosExperiencia() { 
		return Auxiliares.determinarDiferenciaAnyos(comienzoLaboral, new Date()); 
	}

}
