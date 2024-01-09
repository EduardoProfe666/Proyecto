package definiciones;

import java.util.Calendar;
import clases_auxiliares.TipoCuentaUsuario;
import clases_auxiliares.Usuario;
import utilidades.Auxiliares;

public final class Definiciones {
	
	private Definiciones() {}
	
	//Usuarios del Sistema
	//Tener en cuenta que el mínimo de caracteres son 4
	public static final Usuario MEDICO = new Usuario("medico", "1234", TipoCuentaUsuario.MEDICO);
	public static final Usuario ENFERMERA = new Usuario("enfermera", "1234", TipoCuentaUsuario.ENFERMERA);
	
	//Campos de Texto
	public static final int CANT_MAX_CARACTERES_NOMBRE_USUARIO = 17;
	public static final int CANT_MAX_CARACTERES_CONTRASENYA = 15;
	public static final int CANT_MIN_CARACTERES_NOMBRE = 3;
	public static final int CANT_MAX_CARACTERES_NOMBRE = 30;
	public static final int CANT_MIN_CARACTERES_APELLIDO = 3;
	public static final int CANT_MAX_CARACTERES_APELLIDO = 25;
	public static final int TAM_MIN_STRING_DIAGNOSTICO = 3; 
	public static final int TAM_MAX_STRING_DIAGNOSTICO = 400;
	public static final int TAM_MIN_STRING_RESULTADO_ANALISIS = 3; 
	public static final int TAM_MAX_STRING_RESULTADO_ANALISIS = 400;
	public static final int TAM_MIN_STRING_TRATAMIENTO = 3;
	public static final int TAM_MAX_STRING_TRATAMIENTO = 400;
	
	//Definiciones Generales del consultorio
	public static final int NUMERO_CONSULTORIO = 13;
	public static final int AGNOS_OBSOLESCENCIA_SISTEMA = 10;
	public static final int CANT_AGNOS_RIESGO_PRUEBA_CITOLOGICA = 3;
	public static final int CANT_ENFERMEDADES_CRONICAS_RIESGO = 3;
	public static final int TAM_MAX_NUMERO_HC = 20;
	public static final int MAX_CANT_CARACTERES_NOMBRE_VACUNA = 20;
	
	//Piscina de Datos y Validaciones
	
	//Pacientes
	public static final int CANT_MIN_PACIENTES = 120;
	public static final int CANT_MAX_PACIENTES = 1200;
	public static final int EDAD_MIN = 0;
	public static final int EDAD_MAX = 130;
	public static final int EDAD_MIN_PRUEBAS_EMBARAZO_MUJER = 10;
	public static final int EDAD_MAX_PRUEBAS_EMBARAZO_MUJER = 70;
	public static final int EDAD_MIN_RECOMENDADA_PRUEBA_CITOLOGICA = 25;
	public static final int EDAD_MAX_RECOMENDADA_PRUEBA_CITOLOGICA = 65;
	public static final int EDAD_MIN_TRABAJADOR_MEDICO = 22;
	public static final int EDAD_MAX_TRABAJADOR_MEDICO = 80;
	
	//Intento de modificabilidad póstuma del formato del carnet de identidad  NO MODIFICAR
	public static final int TAM_STRING_CI = 11; 
	public static final int POSICION_CARACTER_SEXO = 9;
	
	//Número de Registro Médico
	public static final int NUMERO_REGISTRO_MEDICO_MIN = 100000; 
	public static final int NUMERO_REGISTRO_MEDICO_MAX = 999999; 
	public static final int CANT_CARACTERES_MIN_IDENTIFICADOR_NUMERO_REGISTRO_MEDICO = 1;
	public static final int CANT_CARACTERES_MAX_IDENTIFICADOR_NUMERO_REGISTRO_MEDICO = 3;
	public static final int TAM_MIN_STRING_NUMERO_REGISTRO_MEDICO = CANT_CARACTERES_MIN_IDENTIFICADOR_NUMERO_REGISTRO_MEDICO + 1; //<Identificador> + <Dígito>
	public static final int TAM_MAX_STRING_NUMERO_REGISTRO_MEDICO = CANT_CARACTERES_MAX_IDENTIFICADOR_NUMERO_REGISTRO_MEDICO + Auxiliares.cantidadCaracteresEntero(NUMERO_REGISTRO_MEDICO_MAX);
	
	//Sistema de direcciones
	public static final int CANTIDAD_CALLES_PRINCIPALES = 10;
	public static final int CANT_MIN_VIVIENDAS_CALLE = 10;
	public static final int CANT_MAX_VIVIENDAS_CALLE = 15;
	
	public static final Calendar FECHA_MAX_PISCINA_DATOS_CALENDAR = Calendar.getInstance();
	public static final Calendar FECHA_MIN_PISCINA_DATOS_CALENDAR = Auxiliares.sumarAnyosFecha(FECHA_MAX_PISCINA_DATOS_CALENDAR, -EDAD_MAX);
	public static final Calendar FECHA_NACIMIENTO_MIN_TRABAJADOR_MEDICO = Auxiliares.sumarAnyosFecha(FECHA_MAX_PISCINA_DATOS_CALENDAR, -EDAD_MIN_TRABAJADOR_MEDICO);
	public static final Calendar FECHA_NACIMIENTO_MAX_TRABAJADOR_MEDICO = Auxiliares.sumarAnyosFecha(FECHA_MAX_PISCINA_DATOS_CALENDAR, -EDAD_MAX_TRABAJADOR_MEDICO);
	
}
