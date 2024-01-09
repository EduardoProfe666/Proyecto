package utilidades;

import java.util.Calendar;

import definiciones.Definiciones;
import definiciones.DefinicionesInterfazGrafica;

public final class ValidacionesDefiniciones {
	private ValidacionesDefiniciones() {}
	
	public static void validarDefiniciones() {
		validarDefinicionesLogicas();
		validarDefinicionesInterfazGrafica();
	}
	
	private static void validarDefinicionesLogicas() {
		//Definiciones Usuario
		validarObjetoNoNull(Definiciones.MEDICO, "MEDICO");
		validarObjetoNoNull(Definiciones.ENFERMERA, "ENFERMERA");
		validarEnteroMayorIgualCero(Definiciones.CANT_MAX_CARACTERES_NOMBRE_USUARIO, "CANT_MAX_CARACTERES_NOMBRE_USUARIO");
		validarEnteroMayorIgualCero(Definiciones.CANT_MAX_CARACTERES_CONTRASENYA, "CANT_MAX_CARACTERES_CONTRASENYA");
		
		//Definiciones Generales para la generación y validación de los datos del consultorio
		validarCalendarsMinMax(Definiciones.FECHA_MIN_PISCINA_DATOS_CALENDAR, Definiciones.FECHA_MAX_PISCINA_DATOS_CALENDAR, "FECHA_MIN_PISCINA_DATOS_CALENDAR", "FECHA_MAX_PISCINA_DATOS_CALENDAR");
		validarCalendarsMinMax(Definiciones.FECHA_NACIMIENTO_MAX_TRABAJADOR_MEDICO,Definiciones.FECHA_NACIMIENTO_MIN_TRABAJADOR_MEDICO,"FECHA_NACIMIENTO_MIN_TRABAJADOR_MEDICO","FECHA_NACIMIENTO_MAX_TRABAJADOR_MEDICO");
		validarCalendarsMinMax(Definiciones.FECHA_MAX_PISCINA_DATOS_CALENDAR,Calendar.getInstance(),"FECHA_MAX_PISCINA_DATOS_CALENDAR","Fecha Actual");
		validarCalendarsMinMax(Definiciones.FECHA_NACIMIENTO_MIN_TRABAJADOR_MEDICO,Calendar.getInstance(),"","Fecha Actual");
		
		validarEnterosMinMax(Definiciones.EDAD_MIN, Definiciones.EDAD_MAX, "EDAD_MIN", "EDAD_MAX");
		validarEnterosMinMax(Definiciones.CANT_MIN_PACIENTES,Definiciones.CANT_MAX_PACIENTES,"CANT_MIN_PACIENTES","CANT_MAX_PACIENTES");
		validarEnterosMinMax(Definiciones.EDAD_MIN_PRUEBAS_EMBARAZO_MUJER, Definiciones.EDAD_MAX_PRUEBAS_EMBARAZO_MUJER, "EDAD_MIN_PRUEBAS_MUJER", "EDAD_MAX_PRUEBAS_MUJER");
		validarEnterosMinMax(Definiciones.EDAD_MIN, Definiciones.EDAD_MIN_PRUEBAS_EMBARAZO_MUJER, "EDAD_MIN", "EDAD_MIN_PRUEBAS_MUJER");
		validarEnterosMinMax(Definiciones.EDAD_MAX_PRUEBAS_EMBARAZO_MUJER,Definiciones.EDAD_MAX,"EDAD_MAX","EDAD_MAX_PRUEBAS_MUJER");
		validarEnterosMinMax(Definiciones.EDAD_MIN_TRABAJADOR_MEDICO, Definiciones.EDAD_MAX_TRABAJADOR_MEDICO, "EDAD_MIN_TRABAJADOR_MEDICO", "EDAD_MAX_TRABAJADOR_MEDICO");
		validarEnterosMinMax(Definiciones.EDAD_MIN,Definiciones.EDAD_MIN_TRABAJADOR_MEDICO,"EDAD_MIN","EDAD_MIN_TRABAJADOR_MEDICO");
		validarEnterosMinMax(Definiciones.EDAD_MAX_TRABAJADOR_MEDICO,Definiciones.EDAD_MAX,"EDAD_MAX","EDAD_MAX_TRABAJADOR_MEDICO");
		validarEnterosMinMax(Definiciones.CANT_MIN_CARACTERES_NOMBRE,Definiciones.CANT_MAX_CARACTERES_NOMBRE,"CANT_MIN_CARACTERES_NOMBRE","CANT_MAX_CARACTERES_NOMBRE");
		validarEnterosMinMax(Definiciones.CANT_MIN_CARACTERES_APELLIDO,Definiciones.CANT_MAX_CARACTERES_APELLIDO,"CANT_MIN_CARACTERES_APELLIDO","CANT_MAX_CARACTERES_APELLIDO");
		validarEnteroMayorIgualCero(Definiciones.TAM_STRING_CI, "TAM_STRING_CI");
		validarEnteroMayorIgualCero(Definiciones.POSICION_CARACTER_SEXO,"POSICION_CARACTER_SEXO");
		validarEnterosMinMax(Definiciones.NUMERO_REGISTRO_MEDICO_MIN, Definiciones.NUMERO_REGISTRO_MEDICO_MAX, "NUMERO_REGISTRO_MEDICO_MIN", "NUMERO_REGISTRO_MEDICO_MAX");
		validarEnterosMinMax(Definiciones.CANT_CARACTERES_MIN_IDENTIFICADOR_NUMERO_REGISTRO_MEDICO,Definiciones.CANT_CARACTERES_MAX_IDENTIFICADOR_NUMERO_REGISTRO_MEDICO,"CANT_CARACTERES_MIN_IDENTIFICADOR_NUMERO_REGISTRO_MEDICO","CANT_CARACTERES_MAX_IDENTIFICADOR_NUMERO_REGISTRO_MEDICO");
		validarEnterosMinMax(Definiciones.TAM_MIN_STRING_NUMERO_REGISTRO_MEDICO,Definiciones.TAM_MAX_STRING_NUMERO_REGISTRO_MEDICO,"TAM_MIN_STRING_NUMERO_REGISTRO_MEDICO","TAM_MAX_STRING_NUMERO_REGISTRO_MEDICO");
		validarEnterosMinMax(Definiciones.TAM_MIN_STRING_DIAGNOSTICO,Definiciones.TAM_MAX_STRING_DIAGNOSTICO,"TAM_MIN_STRING_DIAGNOSTICO","TAM_MAX_STRING_DIAGNOSTICO");
		validarEnteroMayorIgualCero(Definiciones.CANT_AGNOS_RIESGO_PRUEBA_CITOLOGICA, "CANT_AGNOS_RIESGO_PRUEBA_CITOLOGICA");
		validarEnteroMayorIgualCero(Definiciones.CANT_ENFERMEDADES_CRONICAS_RIESGO,"CANT_ENFERMEDADES_CRONICAS_RIESGO");
		validarEnterosMinMax(Definiciones.TAM_MIN_STRING_RESULTADO_ANALISIS,Definiciones.TAM_MAX_STRING_RESULTADO_ANALISIS,"TAM_MIN_STRING_RESULTADO_ANALISIS","TAM_MAX_STRING_RESULTADO_ANALISIS");
		validarEnterosMinMax(Definiciones.TAM_MIN_STRING_TRATAMIENTO,Definiciones.TAM_MAX_STRING_TRATAMIENTO,"TAM_MIN_STRING_TRATAMIENTO","TAM_MAX_STRING_TRATAMIENTO");
		validarEnteroMayorCero(Definiciones.NUMERO_CONSULTORIO, "NUMERO_CONSULTORIO");
		validarEnterosMinMax(Definiciones.EDAD_MIN_RECOMENDADA_PRUEBA_CITOLOGICA,Definiciones.EDAD_MAX_RECOMENDADA_PRUEBA_CITOLOGICA,"EDAD_MIN_RECOMENDADA_PRUEBA_CITOLOGICA","EDAD_MAX_RECOMENDADA_PRUEBA_CITOLOGICA");
		validarEnterosMinMax(Definiciones.EDAD_MIN_PRUEBAS_EMBARAZO_MUJER,Definiciones.EDAD_MIN_RECOMENDADA_PRUEBA_CITOLOGICA,"EDAD_MIN","EDAD_MIN_RECOMENDADA_PRUEBA_CITOLOGICA");
		validarEnterosMinMax(Definiciones.EDAD_MAX_RECOMENDADA_PRUEBA_CITOLOGICA,Definiciones.EDAD_MAX_PRUEBAS_EMBARAZO_MUJER,"EDAD_MAX_RECOMENDADA_PRUEBA_CITOLOGICA","EDAD_MAX_PRUEBAS_MUJER");
		validarEnteroMayorIgualCero(Definiciones.AGNOS_OBSOLESCENCIA_SISTEMA, "AGNOS_OBSOLESCENCIA_SISTEMA");
		validarEnterosMinMax(Definiciones.CANT_MIN_VIVIENDAS_CALLE,Definiciones.CANT_MAX_VIVIENDAS_CALLE,"CANT_MIN_VIVIENDAS_CALLE","CANT_MAX_VIVIENDAS_CALLE");
		validarEnteroMayorCero(Definiciones.CANTIDAD_CALLES_PRINCIPALES, "CANTIDAD_CALLES_PRINCIPALES");
		validarEnteroMayorCero(Definiciones.CANT_MIN_VIVIENDAS_CALLE, "CANT_MIN_VIVIENDAS_CALLE");
		validarEnteroMayorCero(Definiciones.CANT_MAX_VIVIENDAS_CALLE, "CANT_MAX_VIVIENDAS_CALLE");
		validarEnteroMayorCero(Definiciones.TAM_MAX_NUMERO_HC,"TAM_MAX_NUMERO_HC");
		validarEnteroMayorCero(Definiciones.MAX_CANT_CARACTERES_NOMBRE_VACUNA, "MAX_CANT_CARACTERES_NOMBRE_VACUNA");
		
	}
	
	private static void validarDefinicionesInterfazGrafica() {
		
		//Dimensiones
		validarObjetoNoNull(DefinicionesInterfazGrafica.DIMENSION_PANTALLA_CARGA, "DIMENSION_PANTALLA_CARGA");
		validarObjetoNoNull(DefinicionesInterfazGrafica.DIMENSION_AUTENTICACION, "DIMENSION_AUTENTICACION");
		validarObjetoNoNull(DefinicionesInterfazGrafica.DIMENSION_APP_PRINCIPAL, "DIMENSION_APP_PRINCIPAL");
		validarObjetoNoNull(DefinicionesInterfazGrafica.DIMENSION_DIALOGOS, "DIMENSION_DIALOGOS");
		
		//Paleta de Colores
		validarObjetoNoNull(DefinicionesInterfazGrafica.AQUAMARINA, "AQUAMARINA");
		validarObjetoNoNull(DefinicionesInterfazGrafica.TURQUESA_CLARO, "TURQUESA_CLARO");
		validarObjetoNoNull(DefinicionesInterfazGrafica.TURQUESA, "TURQUESA");
		validarObjetoNoNull(DefinicionesInterfazGrafica.VERDE_AZULADO_1, "VERDE_AZULADO_1");
		validarObjetoNoNull(DefinicionesInterfazGrafica.VERDE_AZULADO_2, "VERDE_AZULADO_2");
		validarObjetoNoNull(DefinicionesInterfazGrafica.VERDE_OSCURO_0, "VERDE_OSCURO_0");
		validarObjetoNoNull(DefinicionesInterfazGrafica.VERDE_OSCURO_1, "VERDE_OSCURO_1");
		validarObjetoNoNull(DefinicionesInterfazGrafica.VERDE_VIVO_0, "VERDE_VIVO_0");
		validarObjetoNoNull(DefinicionesInterfazGrafica.VERDE_VIVO_1, "VERDE_VIVO_1");
		
		//Definiciones Generales
		validarObjetoNoNull(DefinicionesInterfazGrafica.FORMATO_BASE_FECHA, "FORMATO_BASE_FECHA");
		validarObjetoNoNull(DefinicionesInterfazGrafica.MASCARA_CI, "MASCARA_CI");
		validarEnteroMayorIgualCero(DefinicionesInterfazGrafica.TAM_BORDE_AVATAR, "TAM_BORDE_AVATAR");
		validarEnteroMayorIgualCero(DefinicionesInterfazGrafica.VALOR_ESQUINA_OVAL, "VALOR_ESQUINA_OVAL");
		validarEnteroMayorIgualCero(DefinicionesInterfazGrafica.CANT_MAX_CARACTERES_GENERAL, "CANT_MAX_CARACTERES_GENERAL");
		validarStringNoVacio(DefinicionesInterfazGrafica.PREGUNTA_CERRAR_SESION, "PREGUNTA_CERRAR_SESION");
		validarStringNoVacio(DefinicionesInterfazGrafica.PREGUNTA_SALIR, "PREGUNTA_SALIR");
		validarStringNoVacio(DefinicionesInterfazGrafica.PREGUNTA_SALIR_EDITAR, "PREGUNTA_SALIR_EDITAR");
		validarStringNoVacio(DefinicionesInterfazGrafica.PREGUNTA_SALIR_PRINCIPAL, "PREGUNTA_SALIR_PRINCIPAL");
		
		//Definiciones PantallaCarga
		validarObjetoNoNull(DefinicionesInterfazGrafica.COLOR_GRADIENTE_A_PANTALLA_CARGA, "COLOR_GRADIENTE_A_PANTALLA_CARGA");
		validarObjetoNoNull(DefinicionesInterfazGrafica.COLOR_GRADIENTE_B_PANTALLA_CARGA, "COLOR_GRADIENTE_B_PANTALLA_CARGA");
		validarObjetoNoNull(DefinicionesInterfazGrafica.COLOR_CURVA_INICIO_PANTALLA_CARGA, "COLOR_CURVA_INICIO_PANTALLA_CARGA");
		validarObjetoNoNull(DefinicionesInterfazGrafica.COLOR_CURVA_FINAL_PANTALLA_CARGA, "COLOR_CURVA_FINAL_PANTALLA_CARGA");
		validarEnteroMayorIgualCero(DefinicionesInterfazGrafica.DEMORA_PROGRESSBAR_PANTALLA_CARGA, "DEMORA_PROGRESSBAR_PANTALLA_CARGA");
		
		//Definiciones Autenticacion
		validarStringNoVacio(DefinicionesInterfazGrafica.CAMPO_USUARIO_TEXTO_BASE, "CAMPO_USUARIO_TEXTO_BASE");
		validarStringNoVacio(DefinicionesInterfazGrafica.CAMPO_CONTRASENYA_TEXTO_BASE, "CAMPO_CONTRASENYA_TEXTO_BASE");
		validarEnteroMayorIgualCero(DefinicionesInterfazGrafica.CANT_MAX_CARACTERES_LOGIN, "CANT_MAX_CARACTERES_LOGIN");
		
		//Definiciones AppPrincipal
		validarObjetoNoNull(DefinicionesInterfazGrafica.COLOR_GRADIENTE_A_PANEL_USUARIO, "COLOR_GRADIENTE_A_PANEL_USUARIO");
		validarObjetoNoNull(DefinicionesInterfazGrafica.COLOR_GRADIENTE_B_PANEL_USUARIO, "COLOR_GRADIENTE_B_PANEL_USUARIO");
		validarObjetoNoNull(DefinicionesInterfazGrafica.COLOR_GRADIENTE_A_PANEL_SUPERIOR_SECUNDARIO, "COLOR_GRADIENTE_A_PANEL_SUPERIOR_SECUNDARIO");
		validarObjetoNoNull(DefinicionesInterfazGrafica.COLOR_GRADIENTE_B_PANEL_SUPERIOR_SECUNDARIO, "COLOR_GRADIENTE_B_PANEL_SUPERIOR_SECUNDARIO");
		validarObjetoNoNull(DefinicionesInterfazGrafica.COLOR_TARJETA_INICIO, "COLOR_TARJETA_INICIO");
		validarObjetoNoNull(DefinicionesInterfazGrafica.COLOR_PANEL_OPCIONES, "COLOR_PANEL_OPCIONES");
		validarObjetoNoNull(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES, "COLOR_OPCIONES_REPORTES");
		validarObjetoNoNull(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES_ANIM, "COLOR_OPCIONES_REPORTES_ANIM");
		validarObjetoNoNull(DefinicionesInterfazGrafica.COLOR_BORDE_JLABELS_APP_PRINCIPAL, "COLOR_BORDE_JLABELS_APP_PRINCIPAL");
		validarStringNoVacio(DefinicionesInterfazGrafica.MENSAJE_BUSCADOR_PACIENTES, "MENSAJE_BUSCADOR_PACIENTES");
		validarStringNoVacio(DefinicionesInterfazGrafica.MENSAJE_ELIMINAR_PACIENTES, "MENSAJE_ELIMINAR_PACIENTES");
		
	}
	
	private static void validarObjetoNoNull(Object o, String nombreDefinicion) {
		if(o==null)
			throw new IllegalArgumentException("Error Fatal: "+nombreDefinicion+" no puede ser null");
	}
	
	private static void validarStringNoVacio(String s, String nombreDefinicion) {
		if(!Validaciones.validarStringNoVacio(s))
			throw new IllegalArgumentException("Error Fatal: "+nombreDefinicion+" no puede estar vacío");
	}
	
	private static void validarEnteroMayorIgualCero(int entero, String nombreDefinicion) {
		if(entero<0) {
			throw new IllegalArgumentException("Error Fatal: "+nombreDefinicion+" no puede ser menor que cero");
		}
	}
	
	private static void validarEnteroMayorCero(int entero, String nombreDefinicion) {
		if(entero<=0)
			throw new IllegalArgumentException("Error Fatal: "+nombreDefinicion+" no puede ser menor o igual que cero");
	}
	
	private static void validarEnterosMinMax(int min, int max, String nombreDefinicionMin, String nombreDefinicionMax) {
		validarEnteroMayorIgualCero(min, nombreDefinicionMin);
		validarEnteroMayorIgualCero(max, nombreDefinicionMax);
		if(min>max)
			throw new IllegalArgumentException("Error Fatal: "+nombreDefinicionMin+" no puede ser mayor que "+nombreDefinicionMax);
	}
	private static void validarCalendarsMinMax(Calendar min, Calendar max, String nombreDefinicionMin, String nombreDefinicionMax) {
		validarObjetoNoNull(min, nombreDefinicionMin);
		validarObjetoNoNull(max, nombreDefinicionMax);
		if(min.after(max))
			throw new IllegalArgumentException("Error Fatal: "+nombreDefinicionMin+" no puede ser mayor que "+nombreDefinicionMax);
	}
	
}
