package clases_auxiliares;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import definiciones.Errores;
import nucleo.Analisis;
import nucleo.Paciente;
import utilidades.Validaciones;

public class AnalisisIndicados {
	private Paciente paciente;
	private ArrayList<Analisis> listadoAnalisis;
	
	public AnalisisIndicados(Paciente paciente, ArrayList<Analisis> listadoAnalisis) {
		setPaciente(paciente);
		setListadoAnalisis(listadoAnalisis);
	}

	public Paciente getPaciente() {
		return paciente;
	}

	private void setPaciente(Paciente paciente) {
		if(paciente!=null)
			this.paciente = paciente;
		else
			throw new IllegalArgumentException(Errores.ERROR_PACIENTE_ANALISIS_INDICADOS);
	}

	public ArrayList<Analisis> getListadoAnalisis() {
		return new ArrayList<Analisis>(listadoAnalisis);
	}
	public void registrarResultadoAnalisis(String resultadoAnalisis, int indiceAnalisis) {
		try {
			listadoAnalisis.get(indiceAnalisis).setResultadoAnalisis(resultadoAnalisis,new Date());
			listadoAnalisis.remove(indiceAnalisis);
		}catch(Exception e) {
			throw new IllegalArgumentException(Errores.ERROR_REGISTRO_ANALISIS_INDICADOS);
		}
	}

	private void setListadoAnalisis(ArrayList<Analisis> listadoAnalisis) {
		if(listadoAnalisis==null)
			throw new IllegalArgumentException(Errores.ERROR_LISTADO_ANALISIS_INDICADOS);
		
		boolean aux = true;
		for(int i=0;i<listadoAnalisis.size() && aux;i++) {
			aux = !listadoAnalisis.get(i).tieneResultado();
		}
		
		if(!aux)
			throw new IllegalArgumentException(Errores.ERROR_LISTADO_ANALISIS_INDICADOS);
		
		this.listadoAnalisis = listadoAnalisis;
	}
	
	public Analisis getAnalisis(Date fechaIndicacion, String tipoAnalisis) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(fechaIndicacion);
		
		Analisis analisisAAgregar=null;
		
		for(int i=0;i<listadoAnalisis.size() && analisisAAgregar==null;i++) {
			Calendar c2 = Calendar.getInstance();
			c2.setTime(listadoAnalisis.get(i).getFechaIndicacion());
			if(listadoAnalisis.get(i).getTipoAnalisis().equals(tipoAnalisis) && Validaciones.validarCalendarsMismoDia(c1, c2))
				analisisAAgregar = listadoAnalisis.get(i);
		}
		
		return analisisAAgregar;
	}
	
	public boolean sinAnalisisIndicados() {
		return listadoAnalisis.isEmpty();
	}
}
