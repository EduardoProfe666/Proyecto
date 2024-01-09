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
	public static final String ERROR_NUMERO_CONSULTORIO = "Numero de Consultorio no válido";
	public static final String ERROR_NOMBRE_POLICLINICO = "Nombre de policlínico no válido";
	public static final String ERROR_NOMBRE_DIRECTOR_POLICLINICO = "Nombre del director del policlínico no válido";
	public static final String ERROR_DOCTOR = "Doctor no válido";
	public static final String ERROR_ENFERMERA = "Enfermera no válida";
	public static final String ERROR_INDICE_PACIENTE = "El índice del listado de pacientes no es válido";
	public static final String ERROR_PACIENTE = "Paciente no válido";
	public static final String ERROR_INDICE_FECHA_HOJA_CARGOS = "La fecha de la hoja de cargos no es válida";
	public static final String ERROR_VACUNA_REPORTE = "La vacuna no es válida";
	public static final String ERROR_ENFERMEDAD_CRONICA_REPORTE = "La enfermedad crónica no es válida";
	public static final String ERROR_FECHA_SIN_VISITA_REPORTE = "La fecha no es válida";
	public static final String ERROR_PACIENTE_REPORTE_ANALISIS_FALTANTES = "El paciente no es válido";
	public static final String ERROR_FECHA_ANALISIS_FALTANTES_REPORTE = "La fecha no es válida";
	public static final String ERROR_INSTANCIA_CMF_NULA = "El consultorio aún no ha sido instanciado";
	public static final String ERROR_CI = "El carnet de identidad no es válido";
	
	
	//Persona
	public static final String ERROR_NOMBRE_PERSONA = "Nombre de persona no válido";
	public static final String ERROR_APELLIDO_PERSONA ="Apellido de persona no válido";
	public static final String ERROR_CI_PERSONA = "Carnet de Identidad no válido";
	
	//Medico
	public static final String ERROR_FECHA_INSCRIPCION_CMF_MEDICO = "Fecha de inscripción no válida";
	public static final String ERROR_NUMERO_REGISTRO_MEDICO = "Número de registro médico no válido";
	
	//Enfermera
	public static final String ERROR_FECHA_INSCRIPCION_CMF_ENFERMERA = "Fecha de inscripción no válida";
	public static final String ERROR_COMIENZO_LABORAL_ENFERMERA = "Comienzo Laboral no válido";
	public static final String ERROR_SEXO_ENFERMERA = "Carnet de Identidad no válido";
	
	//Paciente
	public static final String ERROR_ENFERMEDAD_CRONICA = "Enfermedad crónica no válida";
	public static final String ERROR_DIRECCION = "La dirección no es válida";
	public static final String ERROR_INDICE_ENFERMEDAD_CRONICA = "El índice del listado de enfermedades crónicas no es válido";
	public static final String ERROR_INDICE_VACUNA = "El índice del listado de vacunaciones no es válido";
	
	//Paciente Femenina
	public static final String ERROR_SEXO_PACIENTE_FEMENINA = "El sexo de la paciente femenina no es válido";
	public static final String ERROR_EDAD_PRUEBA_CITOLOGICA = "La paciente no tiene la edad válida para realizarse la prueba citológica";
	public static final String ERROR_PRUEBA_CITOLOGICA = "La fecha de la última prueba citológica no es válida";
	public static final String ERROR_EDAD_EMBARAZO = "La paciente no tiene la edad válida de embarazo";
	public static final String ERROR_VACUNACION = "Vacunación no válida";
	
	//Vacuna
	public static final String ERROR_NOMBRE_VACUNA = "Nombre de vacuna no válido";
	public static final String ERROR_FECHA_VACUNACION = "Fecha de vacunación no válida";
	
	//VisitaHojaDeCargos Y HojaCargos
	public static final String ERROR_PACIENTE_VISITA_HOJA_CARGOS = "Paciente no válido";
	public static final String ERROR_DIAGNOSTICO = "Diagnóstico no válido";  //También es de Visita
	public static final String ERROR_FECHA_HOJA_CARGOS_DIA = "La fecha de la hoja de cargos no es válida";
	
	//Direccion
	public static final String ERROR_CALLE_PRINCIPAL_DIRECCION = "Calle principal no válida";
	public static final String ERROR_NUMERO_CASA_DIRECCION = "Número de casa no válida";
	
	//Analisis
	public static final String ERROR_FECHA_INDICACION_ANALISIS = "Fecha de indicación de análisis no válida";
	public static final String ERROR_TIPO_ANALISIS = "Tipo de análisis no válido";
	public static final String ERROR_RESULTADO_ANALISIS = "Resultado de análisis no válido";
	public static final String ERROR_FECHA_ANALISIS = "Fecha de análisis no válido";
	
	//Visita
	public static final String ERROR_TRATAMIENTO = "Tratamiento no válido";
	public static final String ERROR_ANALISIS_INDICADOS = "Listado de análisis indicados no válido";
	public static final String ERROR_ESPECIALIDADES_REMITIDAS = "Listado de especialidades remitidas no válida";
	
	//Historia Clinica
	public static final String ERROR_NUMERO_HISTORIA_CLINICA = "Número de historia clínica no válido";
	public static final String ERROR_INDICE_VISITAS = "El índice del listado de visitas no es válido";
	public static final String ERROR_INDICE_ANALISIS = "El índice del listado de análisis no es válido";
	public static final String ERROR_ANALISIS_REPETIDOS_DIA = "Los análisis no se pueden indicar múltiples veces en un mismo día";
	public static final String ERROR_FECHA_ANALISIS_INDICADOS_HC = "Fecha de análisis indicados no válida";
	
	
	/*Clases Auxiliares
	 *
	 *
	 *
	 *
	 */
	
	//PiscinaDatos
	public static final String ERROR_MAX_CI_GENERADOS= "Se alcanzó la máxima cantidad de ci generados";
	
	//DireccionPiscinaDatos
	public static final String ERROR_DIRECCION_PISCINA_DATOS = "Dirección no válida";
	public static final String ERROR_NUMERO_VIVIENDA = "Número de vivienda no válido";
	
	//VacunacionPiscinaDatos
	public static final String ERROR_EDAD_MIN = "Edad mínima no válida";
	public static final String ERROR_EDAD_MAX = "Edad máxima no válida";
	public static final String ERROR_EDAD_MIN_MAYOR_MAX = "La edad mínima no puede ser mayor que la edad máxima";
	public static final String ERROR_NOMBRE_VACUNACION = "Nombre de vacunación no válido";
	
	//Usuario
	public static final String ERROR_TIPO_CUENTA = "No existe el tipo de cuenta asignado";
	public static final String ERROR_NOMBRE_USUARIO = "Nombre de usuario no válido";
	public static final String ERROR_CONTRASENYA = "Contraseña no válida";
	
	//AnalisisIndicados
	public static final String ERROR_PACIENTE_ANALISIS_INDICADOS = "Paciente no válido";
	public static final String ERROR_LISTADO_ANALISIS_INDICADOS = "Listado de análisis indicados no válido";
	public static final String ERROR_INDICE_ANALISIS_INDICADOS = "El índice del listado de análisis indicados no es válido";
	public static final String ERROR_REGISTRO_ANALISIS_INDICADOS = "Error en el registro de análisis indicados";
	
	//Comparadores
	public static final String ERROR_CODIGO_COMPARADOR_PACIENTE = "Código de comparador de Paciente no válido";
	public static final String ERROR_CODIGO_COMPARADOR_PERSONA = "Código de comparador de Persona no válido";
	public static final String ERROR_CODIGO_COMPARADOR_PISCINA_DATOS = "Código de comparador de PiscinaDatos no válido";
}
