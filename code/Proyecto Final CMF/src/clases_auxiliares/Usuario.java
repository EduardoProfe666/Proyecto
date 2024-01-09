package clases_auxiliares;

import definiciones.Definiciones;
import definiciones.Errores;
import utilidades.Validaciones;

public class Usuario { 
	private String nombreUsuario;
	private String contrasenya;
	private TipoCuentaUsuario tipoCuenta;
	
	public Usuario(String nombreUsuario, String contrasenya, TipoCuentaUsuario tipoCuenta) {
		setNombreUsuario(nombreUsuario);
		setContrasenya(contrasenya);
		this.tipoCuenta = tipoCuenta;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		if(Validaciones.validarStringNoVacio(nombreUsuario) && Validaciones.validarStringNoTodoEspacio(nombreUsuario) && Validaciones.validarStringTodoLetra(nombreUsuario) && Validaciones.validarTamString(nombreUsuario, 4, Definiciones.CANT_MAX_CARACTERES_NOMBRE_USUARIO))
			this.nombreUsuario = nombreUsuario;
		else
			throw new IllegalArgumentException(Errores.ERROR_NOMBRE_USUARIO);
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		if(Validaciones.validarStringNoVacio(contrasenya) && Validaciones.validarStringNoTodoEspacio(contrasenya) && Validaciones.validarTamString(contrasenya, 4, Definiciones.CANT_MAX_CARACTERES_CONTRASENYA))
			this.contrasenya = contrasenya;
		else
			throw new IllegalArgumentException(Errores.ERROR_CONTRASENYA);
	}

	public TipoCuentaUsuario getTipoCuenta() {
		return tipoCuenta;
	}
}

