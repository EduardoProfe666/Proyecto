package nucleo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import definiciones.Errores;
import utilidades.Validaciones;

public class HistoriaClinica {
	private String numeroHistoriaClinica;
	private ArrayList<Visita> listadoVisitas;
	private ArrayList<Analisis> listadoAnalisis;

	public HistoriaClinica(String numeroHistoriaClinica) {
		setNumeroHistoriaClinica(numeroHistoriaClinica);
		listadoVisitas = new ArrayList<Visita>();
		listadoAnalisis = new ArrayList<Analisis>();
	}

	public String getNumeroHistoriaClinica() {
		return numeroHistoriaClinica;
	}

	public void setNumeroHistoriaClinica(String numeroHistoriaClinica) { //No es necesario validar el formato puesto que el sistema lo genera
		if(numeroHistoriaClinica!=null)
			this.numeroHistoriaClinica = numeroHistoriaClinica;   
		else
			throw new IllegalArgumentException(Errores.ERROR_NUMERO_HISTORIA_CLINICA);
	}

	public ArrayList<Visita> getListadoVisitas() {
		return new ArrayList<Visita>(listadoVisitas);
	}
	public Visita getVisita(int indiceVisita){		
		try {
			return listadoVisitas.get(indiceVisita);
		}
		catch(Exception e) {
			throw new IllegalArgumentException(Errores.ERROR_INDICE_VISITAS);
		}
	}

	public ArrayList<Analisis> getListadoAnalisis(){
		return new ArrayList<Analisis>(listadoAnalisis);
	}
	public Analisis getAnalisis(int indiceAnalisis) {
		try {
			return listadoAnalisis.get(indiceAnalisis);
		}
		catch(Exception e) {
			throw new IllegalArgumentException(Errores.ERROR_INDICE_ANALISIS);
		}
	}

	public void addVisita(String diagnostico, String tratamiento, ArrayList<String> analisisIndicados, ArrayList<String> especialidadesRemitidas) {
		Date fecha = new Date();
		
		if(Validaciones.validarAnalisisIndicadosNoRepetidosVisitasDia(visitasFechaDada(fecha), analisisIndicados)) {
			listadoVisitas.add(new Visita(fecha, diagnostico, tratamiento, analisisIndicados, especialidadesRemitidas));
			
			for(String s : analisisIndicados) 
				listadoAnalisis.add(new Analisis(fecha, s));
		}
		else 
			throw new IllegalArgumentException(Errores.ERROR_ANALISIS_REPETIDOS_DIA);
	}

	public ArrayList<Visita> visitasFechaDada(Date fecha){
		ArrayList<Visita> visitas = new ArrayList<Visita>();
		if(fecha!=null) {
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			for(Visita v : listadoVisitas) {
				Calendar c1 = Calendar.getInstance();
				c1.setTime(v.getFecha());
				if(Validaciones.validarCalendarsMismoDia(c, c1))
					visitas.add(v);
			}
		}

		return visitas;
	}
	public ArrayList<Visita> visitasEnYDespuesFechaDada(Date fecha) {
		ArrayList<Visita> visitas = new ArrayList<Visita>();
		
		if(fecha!=null) {
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			for(Visita v : listadoVisitas) {
				Calendar c1 = Calendar.getInstance();
				c1.setTime(v.getFecha());
				if(Validaciones.validarCalendarsMismoDia(c, c1) || v.getFecha().after(fecha))
					visitas.add(v);
			}
		}
		
		return visitas;
	}

	public void addResultadoAnalisis(int indiceAnalisis, String resultadoAnalisis) {
		getAnalisis(indiceAnalisis).setResultadoAnalisis(resultadoAnalisis,new Date());
	}
	
	public ArrayList<Analisis> analisisIndicadosFaltantesEnYDespuesFecha(Date fecha){
		if(fecha == null)
			throw new IllegalArgumentException(Errores.ERROR_FECHA_ANALISIS_INDICADOS_HC);
		
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		ArrayList<Analisis> listado = new ArrayList<Analisis>();
		
		for(Analisis a : listadoAnalisis) { 
			Calendar c1 = Calendar.getInstance();
			c1.setTime(a.getFechaIndicacion());
			if(Validaciones.validarCalendarsMismoDia(c, c1) || a.getFechaIndicacion().after(fecha))
				if(!a.tieneResultado())
					listado.add(a);
		}
		
		return listado;
	}
	
	public ArrayList<Analisis> analisisConResultados(){
		ArrayList<Analisis> listado = new ArrayList<Analisis>();
		
		for(Analisis a : listadoAnalisis)
			if(a.tieneResultado())
				listado.add(a);
		
		return listado;
	}
}

