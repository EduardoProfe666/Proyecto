package nucleo;

import java.util.ArrayList;
import java.util.Date;

import definiciones.Definiciones;
import definiciones.Errores;
import utilidades.Validaciones;

public class Visita {
	private Date fecha;
	private String diagnostico; 
	private String tratamiento; 
	private ArrayList<String> analisisIndicados; 
	private ArrayList<String> especialidadesRemitidas; 

	public Visita(Date fecha,String diagnostico, String tratamiento, ArrayList<String> analisisIndicados,
			ArrayList<String> especialidadesRemitidas) {
		setFecha(fecha);
		setDiagnostico(diagnostico);
		setTratamiento(tratamiento);
		setAnalisisIndicados(analisisIndicados);
		setEspecialidadesRemitidas(especialidadesRemitidas);
	}

	public Date getFecha() {
		return fecha;
	}

	private void setFecha(Date fecha) {
		if(fecha!=null)
			this.fecha = fecha;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	private void setDiagnostico(String diagnostico) {
		if(Validaciones.validarStringNoVacio(diagnostico) && Validaciones.validarStringNoTodoEspacio(diagnostico) && Validaciones.validarTamString(diagnostico, Definiciones.TAM_MIN_STRING_DIAGNOSTICO, Definiciones.TAM_MAX_STRING_DIAGNOSTICO))
			this.diagnostico = diagnostico;
		else
			throw new IllegalArgumentException(Errores.ERROR_DIAGNOSTICO);
	}

	public String getTratamiento() {
		return tratamiento;
	}

	private void setTratamiento(String tratamiento) {
		if(Validaciones.validarStringNoVacio(tratamiento) && Validaciones.validarStringNoTodoEspacio(tratamiento) && Validaciones.validarTamString(tratamiento, Definiciones.TAM_MIN_STRING_TRATAMIENTO, Definiciones.TAM_MAX_STRING_TRATAMIENTO))
			this.tratamiento = tratamiento;
		else
			throw new IllegalArgumentException(Errores.ERROR_TRATAMIENTO);
	}

	public ArrayList<String> getAnalisisIndicados() {
		return new ArrayList<String>(analisisIndicados);
	}

	private void setAnalisisIndicados(ArrayList<String> analisisIndicados) {
		if(Validaciones.validarListadoTiposAnalisis(analisisIndicados))
			this.analisisIndicados = new ArrayList<String>(analisisIndicados);
		else
			throw new IllegalArgumentException(Errores.ERROR_ANALISIS_INDICADOS);
	}

	public ArrayList<String> getEspecialidadesRemitidas() {
		return new ArrayList<String>(especialidadesRemitidas);
	}

	private void setEspecialidadesRemitidas(ArrayList<String> especialidadesRemitidas) {
		if(Validaciones.validarListadoEspecialidadRemitida(especialidadesRemitidas))
			this.especialidadesRemitidas = new ArrayList<String>(especialidadesRemitidas);
		else
			throw new IllegalArgumentException(Errores.ERROR_ESPECIALIDADES_REMITIDAS);
	} 

	public boolean tieneAnalisisIndicados() {
		return !analisisIndicados.isEmpty();
	}
	
	public boolean tieneEspecialidadesRemitidas() {
		return !especialidadesRemitidas.isEmpty();
	}


}
