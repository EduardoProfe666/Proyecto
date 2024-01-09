package definiciones;

public final class Errores {
	
	private Errores() {}
	
	/*Clases del Nucleo
	 * 
	 * 
	 * 
	 * 
	 */
	
	//CMF
	public static final String ERROR_NUMERO_CONSULTORIO = "Numero de Consultorio no v�lido";
	public static final String ERROR_NOMBRE_POLICLINICO = "Nombre de policl�nico no v�lido";
	public static final String ERROR_NOMBRE_DIRECTOR_POLICLINICO = "Nombre del director del policl�nico no v�lido";
	public static final String ERROR_DOCTOR = "Doctor no v�lido";
	public static final String ERROR_ENFERMERA = "Enfermera no v�lida";
	public static final String ERROR_INDICE_PACIENTE = "El �ndice del listado de pacientes no es v�lido";
	public static final String ERROR_PACIENTE = "Paciente no v�lido";
	public static final String ERROR_INDICE_FECHA_HOJA_CARGOS = "La fecha de la hoja de cargos no es v�lida";
	public static final String ERROR_VACUNA_REPORTE = "La vacuna no es v�lida";
	public static final String ERROR_ENFERMEDAD_CRONICA_REPORTE = "La enfermedad cr�nica no es v�lida";
	public static final String ERROR_FECHA_SIN_VISITA_REPORTE = "La fecha no es v�lida";
	public static final String ERROR_PACIENTE_REPORTE_ANALISIS_FALTANTES = "El paciente no es v�lido";
	public static final String ERROR_FECHA_ANALISIS_FALTANTES_REPORTE = "La fecha no es v�lida";
	public static final String ERROR_INSTANCIA_CMF_NULA = "El consultorio a�n no ha sido instanciado";
	public static final String ERROR_CI = "El carnet de identidad no es v�lido";
	
	
	//Persona
	public static final String ERROR_NOMBRE_PERSONA = "Nombre de persona no v�lido";
	public static final String ERROR_APELLIDO_PERSONA ="Apellido de persona no v�lido";
	public static final String ERROR_CI_PERSONA = "Carnet de Identidad no v�lido";
	
	//Medico
	public static final String ERROR_FECHA_INSCRIPCION_CMF_MEDICO = "Fecha de inscripci�n no v�lida";
	public static final String ERROR_NUMERO_REGISTRO_MEDICO = "N�mero de registro m�dico no v�lido";
	
	//Enfermera
	public static final String ERROR_FECHA_INSCRIPCION_CMF_ENFERMERA = "Fecha de inscripci�n no v�lida";
	public static final String ERROR_COMIENZO_LABORAL_ENFERMERA = "Comienzo Laboral no v�lido";
	public static final String ERROR_SEXO_ENFERMERA = "Carnet de Identidad no v�lido";
	
	//Paciente
	public static final String ERROR_ENFERMEDAD_CRONICA = "Enfermedad cr�nica no v�lida";
	public static final String ERROR_DIRECCION = "La direcci�n no es v�lida";
	public static final String ERROR_INDICE_ENFERMEDAD_CRONICA = "El �ndice del listado de enfermedades cr�nicas no es v�lido";
	public static final String ERROR_INDICE_VACUNA = "El �ndice del listado de vacunaciones no es v�lido";
	
	//Paciente Femenina
	public static final String ERROR_SEXO_PACIENTE_FEMENINA = "El sexo de la paciente femenina no es v�lido";
	public static final String ERROR_EDAD_PRUEBA_CITOLOGICA = "La paciente no tiene la edad v�lida para realizarse la prueba citol�gica";
	public static final String ERROR_PRUEBA_CITOLOGICA = "La fecha de la �ltima prueba citol�gica no es v�lida";
	public static final String ERROR_EDAD_EMBARAZO = "La paciente no tiene la edad v�lida de embarazo";
	public static final String ERROR_VACUNACION = "Vacunaci�n no v�lida";
	
	//Vacuna
	public static final String ERROR_NOMBRE_VACUNA = "Nombre de vacuna no v�lido";
	public static final String ERROR_FECHA_VACUNACION = "Fecha de vacunaci�n no v�lida";
	
	//VisitaHojaDeCargos Y HojaCargos
	public static final String ERROR_PACIENTE_VISITA_HOJA_CARGOS = "Paciente no v�lido";
	public static final String ERROR_DIAGNOSTICO = "Diagn�stico no v�lido";  //Tambi�n es de Visita
	public static final String ERROR_FECHA_HOJA_CARGOS_DIA = "La fecha de la hoja de cargos no es v�lida";
	
	//Direccion
	public static final String ERROR_CALLE_PRINCIPAL_DIRECCION = "Calle principal no v�lida";
	public static final String ERROR_NUMERO_CASA_DIRECCION = "N�mero de casa no v�lida";
	
	//Analisis
	public static final String ERROR_FECHA_INDICACION_ANALISIS = "Fecha de indicaci�n de an�lisis no v�lida";
	public static final String ERROR_TIPO_ANALISIS = "Tipo de an�lisis no v�lido";
	public static final String ERROR_RESULTADO_ANALISIS = "Resultado de an�lisis no v�lido";
	public static final String ERROR_FECHA_ANALISIS = "Fecha de an�lisis no v�lido";
	
	//Visita
	public static final String ERROR_TRATAMIENTO = "Tratamiento no v�lido";
	public static final String ERROR_ANALISIS_INDICADOS = "Listado de an�lisis indicados no v�lido";
	public static final String ERROR_ESPECIALIDADES_REMITIDAS = "Listado de especialidades remitidas no v�lida";
	
	//Historia Clinica
	public static final String ERROR_NUMERO_HISTORIA_CLINICA = "N�mero de historia cl�nica no v�lido";
	public static final String ERROR_INDICE_VISITAS = "El �ndice del listado de visitas no es v�lido";
	public static final String ERROR_INDICE_ANALISIS = "El �ndice del listado de an�lisis no es v�lido";
	public static final String ERROR_ANALISIS_REPETIDOS_DIA = "Los an�lisis no se pueden indicar m�ltiples veces en un mismo d�a";
	public static final String ERROR_FECHA_ANALISIS_INDICADOS_HC = "Fecha de an�lisis indicados no v�lida";
	
	
	/*Clases Auxiliares
	 *
	 *
	 *
	 *
	 */
	
	//PiscinaDatos
	public static final String ERROR_MAX_CI_GENERADOS= "Se alcanz� la m�xima cantidad de ci generados";
	
	//DireccionPiscinaDatos
	public static final String ERROR_DIRECCION_PISCINA_DATOS = "Direcci�n no v�lida";
	public static final String ERROR_NUMERO_VIVIENDA = "N�mero de vivienda no v�lido";
	
	//VacunacionPiscinaDatos
	public static final String ERROR_EDAD_MIN = "Edad m�nima no v�lida";
	public static final String ERROR_EDAD_MAX = "Edad m�xima no v�lida";
	public static final String ERROR_EDAD_MIN_MAYOR_MAX = "La edad m�nima no puede ser mayor que la edad m�xima";
	public static final String ERROR_NOMBRE_VACUNACION = "Nombre de vacunaci�n no v�lido";
	
	//Usuario
	public static final String ERROR_TIPO_CUENTA = "No existe el tipo de cuenta asignado";
	public static final String ERROR_NOMBRE_USUARIO = "Nombre de usuario no v�lido";
	public static final String ERROR_CONTRASENYA = "Contrase�a no v�lida";
	
	//AnalisisIndicados
	public static final String ERROR_PACIENTE_ANALISIS_INDICADOS = "Paciente no v�lido";
	public static final String ERROR_LISTADO_ANALISIS_INDICADOS = "Listado de an�lisis indicados no v�lido";
	public static final String ERROR_INDICE_ANALISIS_INDICADOS = "El �ndice del listado de an�lisis indicados no es v�lido";
	public static final String ERROR_REGISTRO_ANALISIS_INDICADOS = "Error en el registro de an�lisis indicados";
	
	//Comparadores
	public static final String ERROR_CODIGO_COMPARADOR_PACIENTE = "C�digo de comparador de Paciente no v�lido";
	public static final String ERROR_CODIGO_COMPARADOR_PERSONA = "C�digo de comparador de Persona no v�lido";
	public static final String ERROR_CODIGO_COMPARADOR_PISCINA_DATOS = "C�digo de comparador de PiscinaDatos no v�lido";
}
