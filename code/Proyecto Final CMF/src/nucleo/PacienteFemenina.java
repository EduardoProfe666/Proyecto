package nucleo;

import java.util.Date;

import definiciones.Definiciones;
import definiciones.Errores;
import utilidades.Auxiliares;
import utilidades.Validaciones;

public class PacienteFemenina extends Paciente{
	private Date fechaUltimaPruebaCitologica;
	private boolean embarazada;

	public PacienteFemenina(String nombre, String primerApellido, String segundoApellido, String carnetIdentidad, 
			Direccion direccion, Date fechaUltimaPruebaCitologica, boolean estaEmbarazada) {
		super(nombre, primerApellido, segundoApellido, carnetIdentidad, direccion);

		if(Character.toUpperCase(getSexo())!='F')
			throw new IllegalArgumentException(Errores.ERROR_SEXO_PACIENTE_FEMENINA);

		setFechaUltimaPruebaCitologica(fechaUltimaPruebaCitologica);
		setEstaEmbarazada(estaEmbarazada);
	}

	public Date getFechaUltimaPruebaCitologica() {
		return fechaUltimaPruebaCitologica;
	}
	public void setFechaUltimaPruebaCitologica(Date fechaUltimaPruebaCitologica) {
		boolean edadValida = Validaciones.validarEnteroRango(getEdad(), Definiciones.EDAD_MIN_PRUEBAS_EMBARAZO_MUJER, Definiciones.EDAD_MAX_PRUEBAS_EMBARAZO_MUJER);

		if(fechaUltimaPruebaCitologica!=null && !edadValida)
			throw new IllegalArgumentException(Errores.ERROR_EDAD_PRUEBA_CITOLOGICA);

		if(fechaUltimaPruebaCitologica==null) {
			if(this.fechaUltimaPruebaCitologica==null)
				this.fechaUltimaPruebaCitologica=null;
			else
				throw new IllegalArgumentException(Errores.ERROR_PRUEBA_CITOLOGICA);
		}
		else { //Al entrar la edad se encuentra en el rango admisible
			Date fechaNac = Auxiliares.convertirFechaNacimientoCiDate(carnetIdentidad);
			Date fechaMin = this.fechaUltimaPruebaCitologica==null ? Auxiliares.sumarAnyosFecha(fechaNac, Definiciones.EDAD_MIN_PRUEBAS_EMBARAZO_MUJER) : this.fechaUltimaPruebaCitologica;
			if(Validaciones.validarRangoFecha(fechaUltimaPruebaCitologica, fechaMin, new Date()))
				this.fechaUltimaPruebaCitologica = fechaUltimaPruebaCitologica;
			else throw new IllegalArgumentException(Errores.ERROR_PRUEBA_CITOLOGICA);
		}
	}
	public boolean getEstaEmbarazada() {
		return embarazada;
	}
	public void setEstaEmbarazada(boolean estaEmbarazada) {
		if(estaEmbarazada && !Validaciones.validarEnteroRango(getEdad(), Definiciones.EDAD_MIN_PRUEBAS_EMBARAZO_MUJER, Definiciones.EDAD_MAX_PRUEBAS_EMBARAZO_MUJER))
			throw new IllegalArgumentException(Errores.ERROR_EDAD_EMBARAZO);
		
		this.embarazada = estaEmbarazada;
	}

	@Override
	public boolean enRiesgo() {
		boolean enRiesgo = super.enRiesgo();
		
		if(!enRiesgo && Validaciones.validarEnteroRango(getEdad(), Definiciones.EDAD_MIN_RECOMENDADA_PRUEBA_CITOLOGICA, Definiciones.EDAD_MAX_RECOMENDADA_PRUEBA_CITOLOGICA)) {
			int agnosUltimaPruebaCitologica = fechaUltimaPruebaCitologica==null ? getEdad()-Definiciones.EDAD_MIN_RECOMENDADA_PRUEBA_CITOLOGICA : Auxiliares.determinarDiferenciaAnyos(fechaUltimaPruebaCitologica, new Date());
			enRiesgo = agnosUltimaPruebaCitologica>Definiciones.CANT_AGNOS_RIESGO_PRUEBA_CITOLOGICA;
		}

		return enRiesgo;                                            
	}
}
