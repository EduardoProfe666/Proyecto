package definiciones;

public final class ErroresInterfazGrafica {
	private ErroresInterfazGrafica() {}
	
	//Login
	public static final String ERROR_CAMPO_VACIO = "No pueden existir campos de entrada vac�os";
	public static final String ERROR_CAMPO_VACIO_USUARIO = "El nombre de usuario no puede estar vac�o";
	public static final String ERROR_CAMPO_VACIO_CONTRASENYA = "La contrase�a no puede estar vac�a";
	public static final String ERROR_USUARIO_NO_VALIDO = "El nombre de usuario no es v�lido";
	public static final String ERROR_CONTRASENYA_NO_VALIDA = "La contrase�a no es correcta";
	
	//Agregar Paciente
	public static final String ERROR_CAMPOS_AGREGAR_PACIENTE = "Compruebe los datos de los campos se�alados";
	
	//AppPrincipal
	public static final String ERROR_FECHA_SIN_VISITAS_REPORTE = "La fecha seleccionada no es v�lida";
	public static final String ERROR_FECHA_HOJA_CARGO =  "La fecha seleccionada no es v�lida";
	public static final String ERROR_FECHA_ANALISIS_FALTANTES_REPORTE = "La fecha seleccionada no es v�lida";
}
