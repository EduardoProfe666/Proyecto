package definiciones;

public final class ErroresInterfazGrafica {
	private ErroresInterfazGrafica() {}
	
	//Login
	public static final String ERROR_CAMPO_VACIO = "No pueden existir campos de entrada vacíos";
	public static final String ERROR_CAMPO_VACIO_USUARIO = "El nombre de usuario no puede estar vacío";
	public static final String ERROR_CAMPO_VACIO_CONTRASENYA = "La contraseña no puede estar vacía";
	public static final String ERROR_USUARIO_NO_VALIDO = "El nombre de usuario no es válido";
	public static final String ERROR_CONTRASENYA_NO_VALIDA = "La contraseña no es correcta";
	
	//Agregar Paciente
	public static final String ERROR_CAMPOS_AGREGAR_PACIENTE = "Compruebe los datos de los campos señalados";
	
	//AppPrincipal
	public static final String ERROR_FECHA_SIN_VISITAS_REPORTE = "La fecha seleccionada no es válida";
	public static final String ERROR_FECHA_HOJA_CARGO =  "La fecha seleccionada no es válida";
	public static final String ERROR_FECHA_ANALISIS_FALTANTES_REPORTE = "La fecha seleccionada no es válida";
}
