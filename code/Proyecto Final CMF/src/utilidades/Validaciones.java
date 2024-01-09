package utilidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import definiciones.Definiciones;
import definiciones.DefinicionesInterfazGrafica;
import nucleo.CMF;
import nucleo.Direccion;
import nucleo.Paciente;
import nucleo.Visita;
import clases_auxiliares.VacunacionPiscinaDatos;

public final class Validaciones {

	private Validaciones() {}

	public static boolean validarEnteroRango(int i, int min, int max) {
		boolean esValido = min<=max;

		if(esValido)
			esValido = i>=min && i<=max;

			return esValido;
	}

	public static boolean validarTamString(String s, int min, int max) {
		boolean esValido = s!=null;

		if(esValido)
			esValido= min<=max;
		if(esValido)
			esValido = s.length()>=min && s.length()<=max;

			return esValido;
	}
	public static boolean validarStringTodoLetra(String s) {
		boolean esValido = s!=null;

		for(int i=0;i<s.length() && esValido;i++)
			esValido = Character.isLetter(s.charAt(i)) || Character.isWhitespace(s.charAt(i));

		return esValido;
	}
	public static boolean validarStringTodoDigito(String s) {
		boolean esValido = s!=null;

		for(int i=0;i<s.length() && esValido;i++)
			esValido = Character.isDigit(s.charAt(i));

		return esValido;
	}
	public static boolean validarStringTodoDigitoLetraSinEspacio(String s) {
		boolean esValido = s!=null;

		for(int i=0;i<s.length() && esValido;i++)
			esValido = Character.isDigit(s.charAt(i)) || Character.isUpperCase(s.charAt(i)) || Character.isLowerCase(s.charAt(i));

		return esValido;
	}
	public static boolean validarStringNoTodoEspacio(String s) {
		boolean esValido = false;  
		
		if(s!=null) {
			for(int i=0;i<s.length() && !esValido;i++)
				if(!Character.isWhitespace(s.charAt(i)))
					esValido = true;
		}
		
		return esValido;
	}
	public static boolean validarStringNoVacio(String s) {
		return (s!=null && !s.isEmpty());
	}
	public static boolean validarStringNoEspacio(String s) {
		boolean esValido = (s!=null);

		for(int i=0;i<s.length() && esValido;i++)
			esValido = !Character.isWhitespace(s.charAt(i));

		return esValido;
	}
	//Validaciones de fecha
	public static boolean validarFechaNacimientoCi(String ci) {
		boolean esValido = ci!=null;

		if(esValido) {
			int siglo = 0;
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
			SimpleDateFormat f = DefinicionesInterfazGrafica.FORMATO_BASE_FECHA;
			f.setLenient(false);
			try {
				f.parse(dia+"/"+mes+"/"+anyo);
			}catch(Exception e) {
				esValido=false;
			}
		}


		if(esValido)
			esValido=!(Auxiliares.convertirFechaNacimientoCiDate(ci).after(new Date()));

		return esValido;
	}
	public static boolean validarEdad(String ci,int edadMin, int edadMax) {
		int edad = Auxiliares.determinarEdad(Auxiliares.convertirFechaNacimientoCiDate(ci));
		return edad>=edadMin && edad<=edadMax;
	}
	public static boolean validarSexo(String ci,char sexo) {
		boolean esValido = ci!=null && (sexo=='F' || sexo=='f' || sexo=='M' || sexo=='m');

		if(esValido)
			esValido = Auxiliares.determinarSexo(ci) == sexo;

		return esValido;
	}
	
	public static boolean validarUnicidadCI(String ci) {
		boolean esValido = ci!=null;
		
		if(esValido) {
			ArrayList<Paciente> listado = CMF.getInstancia().getListadoPacientes();
			for(int i=0;i<listado.size() && esValido;i++) 
				esValido = !listado.get(i).getCarnetIdentidad().equals(ci);
		}
		
		return esValido;
	}
	
	public static boolean validarCI(String ci, int tamCi, int edadMin, int edadMax) {
		return validarStringNoVacio(ci) && validarTamString(ci,tamCi,tamCi) && validarStringTodoDigito(ci) && validarFechaNacimientoCi(ci) && validarEdad(ci, edadMin, edadMax);
	}

	public static boolean validarNumeroRegistroMedico(String s) {
		boolean esValida = validarStringNoVacio(s) && validarTamString(s, Definiciones.TAM_MIN_STRING_NUMERO_REGISTRO_MEDICO, Definiciones.TAM_MAX_STRING_NUMERO_REGISTRO_MEDICO) && validarStringTodoDigitoLetraSinEspacio(s);

		if(esValida) {
			for(int i=0, contador=0;i<s.length() &&  esValida;i++) {
				if(Character.isDigit(s.charAt(i))) {
					if(contador>=Definiciones.CANT_CARACTERES_MIN_IDENTIFICADOR_NUMERO_REGISTRO_MEDICO && contador<=Definiciones.CANT_CARACTERES_MAX_IDENTIFICADOR_NUMERO_REGISTRO_MEDICO)
						esValida = validarStringTodoDigito(s.substring(i));
					else
						esValida = false;
				}
				else
					contador++;
			}
		}

		return esValida;

	}

	public static boolean validarRangoFecha(Date fecha, Date fechaMin, Date fechaMax) {
		boolean esValida = (fecha!=null && fechaMin!=null && fechaMax!=null);
		
		if(esValida) {
			esValida = (fecha.after(fechaMin) && fecha.before(fechaMax));
			
			if(!esValida) {
				Calendar c = Calendar.getInstance();
				c.setTime(fecha);
				Calendar cMin = Calendar.getInstance();
				cMin.setTime(fechaMin);
				Calendar cMax = Calendar.getInstance();
				cMax.setTime(fechaMax);
				
				esValida = validarCalendarsMismoDia(c, cMin) || validarCalendarsMismoDia(c, cMax);
			}
		}
		

		return esValida;
	}

	//Validaciones con respecto a la Piscina de datos

	public static boolean validarCallePrincipal(String calle) {
		boolean esValida = calle!=null;

		if(esValida)
			esValida = validarRepeticionStringLista(PiscinaDatos.getListadoCallesPrincipales(), calle);

		return esValida;
	}
	public static boolean validarNumeroCasa(String calle, String numeroCasa) {
		boolean esValida = calle!=null && numeroCasa!=null;

		ArrayList<String> numeros = null;
		if(esValida) {
			numeros = PiscinaDatos.getListadoNumeroCasaCalle(calle);
			esValida = !numeros.isEmpty();
		}
		if(esValida) {
			esValida = validarRepeticionStringLista(numeros, numeroCasa);
		}

		return esValida;

	}

	public static boolean validarDireccion(Direccion d) {
		boolean esValida = d!=null;
		if(esValida) {
			String callePrincipal = d.getCallePrincipal();
			String numeroCasa = d.getNumeroCasa();
			esValida = validarCallePrincipal(callePrincipal) && validarNumeroCasa(callePrincipal, numeroCasa);
		}
		return esValida;
	}

	public static boolean validarTipoAnalisis(String tipoAnalisis){
		boolean esValida = tipoAnalisis!=null;

		if(esValida) 
			esValida = validarRepeticionStringLista(PiscinaDatos.getListadoTipoAnalisis(), tipoAnalisis);

		return esValida;
	}
	public static boolean validarEspecialidadRemitida(String especialidadRemitida) {
		boolean esValida = especialidadRemitida!=null;

		if(esValida) 
			esValida = validarRepeticionStringLista(PiscinaDatos.getListadoEspecialidadesRemitidas(), especialidadRemitida);

		return esValida;
	}

	public static boolean validarListadoTiposAnalisis(ArrayList<String> listadoTiposAnalisis) {
		boolean esValida = validarNoRepeticionLista(listadoTiposAnalisis);

		for(int i=0;i<listadoTiposAnalisis.size() && esValida;i++) 
			esValida = validarTipoAnalisis(listadoTiposAnalisis.get(i));

		return esValida;
	}

	public static boolean validarListadoEspecialidadRemitida(ArrayList<String> listadoEspecialidadesRemitidas) {
		boolean esValida = validarNoRepeticionLista(listadoEspecialidadesRemitidas);

		for(int i=0;i<listadoEspecialidadesRemitidas.size() && esValida;i++)
			esValida = validarEspecialidadRemitida(listadoEspecialidadesRemitidas.get(i));

		return esValida;

	}

	public static boolean validarNoRepeticionLista(List<?> lista) {
		boolean esValida = lista!=null;

		lista.sort(null);

		for(int i=0;i<lista.size()-1 && esValida;i++)
			esValida = !(lista.get(i).equals(lista.get(i+1)));

		return esValida;
	}

	public static boolean validarEnfermedadCronica(String enfermedadCronica) {
		boolean esValida = enfermedadCronica!=null;

		if(esValida) 
			esValida = validarRepeticionStringLista(PiscinaDatos.getListadoEnfermedadesCronicas(), enfermedadCronica);

		return esValida;
	}

	public static boolean validarNombreVacuna(String nombreVacuna) {
		boolean esValida = nombreVacuna!=null;

		if(esValida)
			esValida = validarRepeticionStringLista(PiscinaDatos.getListadoNombresVacunas(), nombreVacuna);

		return esValida;
	}

	public static boolean validarFechaVacunacion(String nombreVacuna, int edadVacunacion) {
		boolean esValida = nombreVacuna!=null;


		if(esValida) {
			VacunacionPiscinaDatos v = PiscinaDatos.getDatosVacunacion(nombreVacuna);
			esValida = validarEnteroRango(edadVacunacion, v.getEdadMin(), v.getEdadMax());
		}

		return esValida;
	}

	public static boolean validarNoRepeticionStringLista(List<String> lista, String s) {
		boolean esValida = lista!=null && s!=null;

		if(esValida) {
			lista.sort(null);
			esValida = Collections.binarySearch(lista,s)<0;
		}

		return esValida;
	}

	public static boolean validarNoRepeticionDosListasString(List<String> lista1, List<String> lista2) {
		boolean esValida = (lista1!=null && lista2!=null);

		if(esValida)
			esValida = Collections.disjoint(lista1, lista2);

		return esValida;
	}

	public static boolean validarRepeticionStringLista(List<String> lista, String s) {
		return !validarNoRepeticionStringLista(lista, s);
	}

	public static boolean validarAnalisisIndicadosNoRepetidosVisitasDia(ArrayList<Visita> listadoVisitasDia, ArrayList<String> analisisIndicados) {
		boolean esValida = (listadoVisitasDia!=null && analisisIndicados!=null);

		if(esValida) {
			for(int i=0;i<listadoVisitasDia.size() && esValida;i++) 
				esValida = validarNoRepeticionDosListasString(listadoVisitasDia.get(i).getAnalisisIndicados(), analisisIndicados);
		}

		return esValida;
	}
	
	public static boolean validarCalendarsMismoDia(Calendar c1, Calendar c2) {
		boolean esValido = c1!=null && c2!=null;
		
		if(esValido)
			esValido = c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR);
		if(esValido)
			esValido = c1.get(Calendar.MONTH)==c2.get(Calendar.MONTH);
		if(esValido)
			esValido = c1.get(Calendar.DAY_OF_MONTH)==c2.get(Calendar.DAY_OF_MONTH);
		
		return esValido;
	}

}
