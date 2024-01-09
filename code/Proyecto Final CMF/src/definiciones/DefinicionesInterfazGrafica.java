package definiciones;

import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;

public final class DefinicionesInterfazGrafica {
	
	private DefinicionesInterfazGrafica() {}
	
	//Dimensiones de cada pantalla de la aplicación  NO MODIFICAR
	//.PantallaCarga 512x385  4:3
	//.AppPrincipal 800x512  39:25
	//.App Principal 1200x675  16:9
	//.Paciente JDialog 600x589
	public static final Dimension DIMENSION_PANTALLA_CARGA = new Dimension(512,385);
	public static final Dimension DIMENSION_AUTENTICACION = new Dimension(800,512);
	public static final Dimension DIMENSION_APP_PRINCIPAL = new Dimension(1200,675);
	public static final Dimension DIMENSION_DIALOGOS = new Dimension(600,589);

	//Definiciones Paleta de Colores
	public static final Color AQUAMARINA = new Color(153,243,241);
	public static final Color TURQUESA_CLARO = new Color(111,233,238);
	public static final Color TURQUESA = new Color(51,202,223);
	public static final Color VERDE_AZULADO_1 = new Color(47,184,176);
	public static final Color VERDE_AZULADO_2 = new Color(17,195,171);
	public static final Color VERDE_VIVO_0 = new Color(75,226,181);
	public static final Color VERDE_VIVO_1 = new Color(29,190,141);
	public static final Color VERDE_OSCURO_0 = new Color(33,121,110);
	public static final Color VERDE_OSCURO_1 = new Color(20,78,74);

	//Definiciones Generales
	public static final SimpleDateFormat FORMATO_BASE_FECHA = new SimpleDateFormat("dd/MM/yyyy");
	public static final String MASCARA_CI = "###########";
	public static final int TAM_BORDE_AVATAR = 4;
	public static final int VALOR_ESQUINA_OVAL = 40;
	public static final int CANT_MAX_CARACTERES_GENERAL = 17;
	public static final String PREGUNTA_CERRAR_SESION = "Los cambios no guardados se perderán ¿Desea cerrar sesión?";
	public static final String PREGUNTA_SALIR = "¿Desea salir de la aplicación?";
	public static final String PREGUNTA_SALIR_PRINCIPAL = "Los cambios no guardados se perderán ¿Desea salir de la aplicación?";
	public static final String PREGUNTA_SALIR_EDITAR = "Los cambios no guardados se perderán ¿Desea continuar?";
	
	//Definiciones PantallaCarga
	public static final Color COLOR_GRADIENTE_A_PANTALLA_CARGA = AQUAMARINA;
	public static final Color COLOR_GRADIENTE_B_PANTALLA_CARGA = VERDE_VIVO_0;
	public static final Color COLOR_CURVA_INICIO_PANTALLA_CARGA = new Color(27,188,155,200);
	public static final Color COLOR_CURVA_FINAL_PANTALLA_CARGA = new Color(45,62,80,50);
	public static final int DEMORA_PROGRESSBAR_PANTALLA_CARGA = 80; //ms

	//Definiciones Autenticacion
	public static final String CAMPO_USUARIO_TEXTO_BASE = "Ingrese su nombre de usuario";
	public static final String CAMPO_CONTRASENYA_TEXTO_BASE = "???????????";
	public static final int CANT_MAX_CARACTERES_LOGIN = Definiciones.CANT_MAX_CARACTERES_NOMBRE_USUARIO;
	
	//Definiciones AppPrincipal
	public static final Color COLOR_GRADIENTE_A_PANEL_USUARIO = TURQUESA_CLARO;
	public static final Color COLOR_GRADIENTE_B_PANEL_USUARIO = VERDE_AZULADO_2;
	public static final Color COLOR_GRADIENTE_A_PANEL_SUPERIOR_SECUNDARIO = AQUAMARINA;
	public static final Color COLOR_GRADIENTE_B_PANEL_SUPERIOR_SECUNDARIO = TURQUESA;
	public static final Color COLOR_TARJETA_INICIO = AQUAMARINA;
	public static final Color COLOR_PANEL_OPCIONES = AQUAMARINA;
	public static final Color COLOR_OPCIONES_REPORTES = AQUAMARINA;
	public static final Color COLOR_OPCIONES_REPORTES_ANIM = TURQUESA;
	public static final Color COLOR_BORDE_JLABELS_APP_PRINCIPAL = VERDE_AZULADO_1;
	public static final String MENSAJE_BUSCADOR_PACIENTES = "Debe rellenar al menos uno (1) de los campos para efectuar la búsqueda";
	public static final String MENSAJE_ELIMINAR_PACIENTES = "¿Desea eliminar al paciente seleccionado? Esta acción será permanente";
	
	
}
