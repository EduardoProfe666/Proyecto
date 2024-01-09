package utilidades;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import definiciones.Definiciones;
import nucleo.Direccion;

public final class Auxiliares {
	private Auxiliares() {}

	public static final Random r = new Random();

	public static Date convertirFechaNacimientoCiDate(String ci) { //Debe estar previamente validado
		int siglo = 0;
		Date fechaNacimiento = null;

		int digitoMilenio = Byte.valueOf(""+ci.charAt(6));

		if(digitoMilenio<=5)
			siglo=20;
		else if(digitoMilenio<=8)
			siglo=21;
		else
			siglo=19;

		String anyo = (siglo-1)+ci.substring(0, 2);
		String mes = ci.substring(2,4);
		String dia = ci.substring(4,6);
		Calendar c = new GregorianCalendar(Integer.valueOf(anyo),Integer.valueOf(mes)-1, Integer.valueOf(dia));
		fechaNacimiento = c.getTime();


		return fechaNacimiento;
	}
	public static int determinarEdad(Date fechaNacimientoCi) {
		return determinarDiferenciaAnyos(fechaNacimientoCi, new Date());
	}

	public static int determinarDiferenciaAnyos(Date fechaInicial, Date fechaFinal) {
		int difAnyos=-1;

		if(fechaInicial!=null && fechaFinal!=null) {
			Calendar fechaInic = new GregorianCalendar();
			fechaInic.setTime(fechaInicial);
			Calendar fechaFin = new GregorianCalendar();
			fechaFin.setTime(fechaFinal);

			difAnyos = fechaFin.get(Calendar.YEAR) - fechaInic.get(Calendar.YEAR);
			int difMeses = fechaFin.get(Calendar.MONTH) - fechaInic.get(Calendar.MONTH);
			int difDias = fechaFin.get(Calendar.DAY_OF_MONTH) - fechaInic.get(Calendar.DAY_OF_MONTH);

			if(difMeses<0 || (difMeses==0 && difDias<0))
				difAnyos--;
		}

		return difAnyos;
	}
	public static char determinarSexo(String ci) {
		char sexo = 'F';
		int aux = ci.charAt(Definiciones.POSICION_CARACTER_SEXO);
		if(aux%2==0)
			sexo = 'M';
		return sexo;
	}
	public static String determinarSexoString(String ci) {
		char sexo = determinarSexo(ci);
		return sexo == 'F' ? "Femenino" : "Masculino";
	}
	public static String convertirSexoString(char sexo) {
		return 'F'==Character.toUpperCase(sexo) ? "Femenino" : "Masculino";
	}
	public static String eliminarEspaciosVaciosString(String s) {
		int tamString = s.length();
		StringBuilder f = new StringBuilder(s);
		for(int i=0;i<tamString;i++) {
			if(s.charAt(i)==' ') {
				f.deleteCharAt(i);
				tamString--;
				i--;
			}
		}
		return f.toString();
	}
	public static Date sumarAnyosFecha(Date fecha, int cantAnyos) {
		Calendar fechaCalendar = new GregorianCalendar();
		fechaCalendar.setTime(fecha);
		fechaCalendar.set(Calendar.YEAR, fechaCalendar.get(Calendar.YEAR)+cantAnyos);
		return fechaCalendar.getTime();
	}
	public static Calendar sumarAnyosFecha(Calendar fecha, int cantAnyos) {
		Calendar fechaCalendar = (Calendar)fecha.clone();
		fechaCalendar.set(Calendar.YEAR, fechaCalendar.get(Calendar.YEAR)+cantAnyos);
		return fechaCalendar;
	}
	public static int cantidadCaracteresEntero(int i) {
		return String.valueOf(i).length();
	}

	//Formato de número Historia Clinica >> HC-(número del consultorio)-(Número de la vivienda según listado de consultorio)-(numero generado que indica el número del paciente en su dirección)
	public static String generarNumeroHistoriaClinica(Direccion d) { 
		int numeroVivienda = PiscinaDatos.getNumeroVivienda(d);
		int numeroGenerado = PiscinaDatos.generarNumeroHabitanteVivienda(numeroVivienda);

		return "HC" + "-" + Definiciones.NUMERO_CONSULTORIO + "-" + numeroVivienda + "-" + numeroGenerado;

	}

	public static void introducirStringListado(String s, List<String> listado) {
		boolean aux=true;
		for(int i=0;i<listado.size() && aux;i++) {
			if(s.compareTo(listado.get(i))<0) {
				listado.add(i, s);
				aux=false;
			}
		}
		if(aux)
			listado.add(s);
	}
	
	public static ArrayList<String> listadoStringsNoComunes(List<String> listadoA, List<String> listadoB) {
		ArrayList<String> listado = new ArrayList<String>();
		
		if(listadoA!=null && listadoB!=null) {
			Collections.sort(listadoA);
			listado = new ArrayList<String>(listadoA);
			
			for(String s : listadoB) {
				if(Collections.binarySearch(listadoA, s)>=0)
					listado.remove(s);
			}
		}
		return listado;
	}
	
	//Se toma de base que los análisis indicados faltantes vencen al mes
	public static Date fechaListadoAnalisisFaltantesEnfermera() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, c.get(Calendar.MONTH)-1);
		return c.getTime();
	}

}
