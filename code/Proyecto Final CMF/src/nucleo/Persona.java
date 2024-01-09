package nucleo;

import java.util.Date;

import definiciones.Definiciones;
import definiciones.Errores;
import utilidades.Auxiliares;
import utilidades.Validaciones;

public abstract class Persona{
	protected String nombre;
	protected String primerApellido;
	protected String segundoApellido;
	protected String carnetIdentidad;
	
	protected Persona(String nombre, String primerApellido,String segundoApellido,String carnetIdentidad, int edadMin, int edadMax) {
		setNombre(nombre);  
		setPrimerApellido(primerApellido);
		setSegundoApellido(segundoApellido);
		setCarnetIdentidad(carnetIdentidad,edadMin,edadMax);
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		if(Validaciones.validarStringNoVacio(nombre) && Validaciones.validarStringNoTodoEspacio(nombre) && Validaciones.validarStringTodoLetra(nombre) && Validaciones.validarTamString(nombre, Definiciones.CANT_MIN_CARACTERES_NOMBRE, Definiciones.CANT_MAX_CARACTERES_NOMBRE))
			this.nombre = nombre;
		else
			throw new IllegalArgumentException(Errores.ERROR_NOMBRE_PERSONA);
	}
	
	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		if(Validaciones.validarStringNoVacio(primerApellido) && Validaciones.validarStringNoTodoEspacio(primerApellido) && Validaciones.validarStringTodoLetra(primerApellido) && Validaciones.validarTamString(primerApellido, Definiciones.CANT_MIN_CARACTERES_APELLIDO, Definiciones.CANT_MAX_CARACTERES_APELLIDO))
			this.primerApellido = primerApellido;
		else
			throw new IllegalArgumentException(Errores.ERROR_APELLIDO_PERSONA);
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}
	
	public void setSegundoApellido(String segundoApellido) {
		if(Validaciones.validarStringNoVacio(segundoApellido) && Validaciones.validarStringNoTodoEspacio(segundoApellido) && Validaciones.validarStringTodoLetra(segundoApellido) && Validaciones.validarTamString(segundoApellido, Definiciones.CANT_MIN_CARACTERES_APELLIDO, Definiciones.CANT_MAX_CARACTERES_APELLIDO))
			this.segundoApellido = segundoApellido;
		else
			throw new IllegalArgumentException(Errores.ERROR_APELLIDO_PERSONA);
	}

	public String getCarnetIdentidad() {
		return carnetIdentidad;
	}

	private void setCarnetIdentidad(String carnetIdentidad, int edadMin, int edadMax) {
		if(Validaciones.validarCI(carnetIdentidad, Definiciones.TAM_STRING_CI, edadMin, edadMax))
			this.carnetIdentidad = carnetIdentidad;
		else
			throw new IllegalArgumentException(Errores.ERROR_CI_PERSONA);
	}

	public String getNombreCompleto() {
		return this.nombre+" " + this.primerApellido + " " + this.segundoApellido;
	}
	
	public String getNombreSimple() {
		return this.nombre+" "+this.primerApellido;
	}
	
	public Date getFechaNacimiento() {
		return Auxiliares.convertirFechaNacimientoCiDate(carnetIdentidad);
	}
	
	public int getEdad(){
		return Auxiliares.determinarEdad(getFechaNacimiento());                                 
	}
}
