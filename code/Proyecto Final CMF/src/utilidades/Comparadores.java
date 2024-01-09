package utilidades;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;

import definiciones.DefinicionesInterfazGrafica;
import definiciones.Errores;
import nucleo.Paciente;
import nucleo.Persona;

public class Comparadores {
	private Comparadores() {}
	
	//Códigos de comparadores de Persona
	public static final int PERSONA_CARNET_IDENTIDAD = 0;
	public static final int PERSONA_NOMBRE_SIMPLE = 1;
	public static final int PERSONA_NOMBRE_COMPLETO = 2;
	public static final int PERSONA_PRIMER_APELLIDO = 3;
	public static final int PERSONA_SEGUNDO_APELLIDO = 4;
	
	//Códigos de comparadores de Paciente
	public static final int PACIENTE_CALLE_PRINCIPAL = 0;
	public static final int PACIENTE_HISTORIA_CLINICA = 1;
	public static final int PACIENTE_NUMERO_CASA = 2;
	public static final int PACIENTE_SEXO = 3;
	
	
	public static Comparator<Persona> comparadorPersona(int codigoComparador){
		Comparator<Persona> c = null;
		
		switch(codigoComparador) {
		case PERSONA_CARNET_IDENTIDAD:
			c= new Comparator<Persona>() {

				@Override
				public int compare(Persona p1, Persona p2) {
					return p1.getCarnetIdentidad().compareTo(p2.getCarnetIdentidad());
				}};
			break;
		case PERSONA_NOMBRE_SIMPLE:
			c= new Comparator<Persona>() {

				@Override
				public int compare(Persona p1, Persona p2) {
					return p1.getNombre().compareTo(p2.getNombre());
				}};
			break;
		case PERSONA_NOMBRE_COMPLETO:
			c= new Comparator<Persona>() {

				@Override
				public int compare(Persona p1, Persona p2) {
					return p1.getNombreCompleto().compareToIgnoreCase(p2.getNombreCompleto());
				}};
			break;
		case PERSONA_PRIMER_APELLIDO:
			c= new Comparator<Persona>() {

				@Override
				public int compare(Persona p1, Persona p2) {
					return p1.getPrimerApellido().compareTo(p2.getPrimerApellido());
				}};
			break;
		case PERSONA_SEGUNDO_APELLIDO:
			c= new Comparator<Persona>() {

				@Override
				public int compare(Persona p1, Persona p2) {
					return p1.getSegundoApellido().compareTo(p2.getSegundoApellido());
				}};
			break;
		default:
			throw new IllegalArgumentException(Errores.ERROR_CODIGO_COMPARADOR_PERSONA);
		}
		
		return c;
	}
	public static Comparator<Paciente> comparadorPaciente(int codigoComparador){
		Comparator<Paciente> c = null;
		
		switch(codigoComparador) {
		case PACIENTE_CALLE_PRINCIPAL:
			c= new Comparator<Paciente>() {

				@Override
				public int compare(Paciente p1, Paciente p2) {
					return p1.getDireccion().getCallePrincipal().compareTo(p2.getDireccion().getCallePrincipal());
				}};
			break;
		case PACIENTE_HISTORIA_CLINICA:
			c= new Comparator<Paciente>() {
				//Mejorar Algoritmo
				//Hacer un algoritmo específico con el formato de Historia Clinica escogido
				//Considerar hacer HistoriaClinica comparable con este algoritmo y solo comparar las historias clínicas
				@Override
				public int compare(Paciente p1, Paciente p2) {
					return p1.getHistoriaClinica().getNumeroHistoriaClinica().compareTo(p2.getHistoriaClinica().getNumeroHistoriaClinica());
				}};
			break;
		case PACIENTE_NUMERO_CASA:
			c= new Comparator<Paciente>() {

				@Override
				public int compare(Paciente p1, Paciente p2) {
					return p1.getDireccion().getNumeroCasa().compareTo(p2.getDireccion().getNumeroCasa());
				}};
			break;
		case PACIENTE_SEXO:
			c= new Comparator<Paciente>() {

				@Override
				public int compare(Paciente p1, Paciente p2) {
					return Character.compare(p1.getSexo(), p2.getSexo());
				}};
			break;
		default:
			throw new IllegalArgumentException(Errores.ERROR_CODIGO_COMPARADOR_PACIENTE);
		}
		
		return c;
	}
	
	public static Comparator<String> comparadorFechaString(){
		return new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				Date fecha1=null;
				Date fecha2=null;
				try {
					fecha1 = DefinicionesInterfazGrafica.FORMATO_BASE_FECHA.parse(o1);
					fecha2 = DefinicionesInterfazGrafica.FORMATO_BASE_FECHA.parse(o2);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				return fecha1.compareTo(fecha2);
			}};
	}
}
