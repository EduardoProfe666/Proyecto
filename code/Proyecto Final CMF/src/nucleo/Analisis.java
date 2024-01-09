package nucleo;

import java.util.Date;

import utilidades.Validaciones;
import definiciones.Definiciones;
import definiciones.Errores;

public class Analisis implements Comparable<Analisis>{ 
	private Date fechaIndicacion;
	private Date fechaAnalisis;
	private String tipoAnalisis; 
	private String resultadoAnalisis;
	private boolean tieneResultado; 

	public Analisis(Date fechaIndicacion, String tipoAnalisis) {
		setFechaIndicacion(fechaIndicacion);
		setTipoAnalisis(tipoAnalisis);
		fechaAnalisis = null;
		resultadoAnalisis = null;
		tieneResultado = false;
	}

	public Date getFechaIndicacion() {
		return fechaIndicacion;
	}

	private void setFechaIndicacion(Date fechaIndicacion) {
		if(fechaIndicacion!=null)
			this.fechaIndicacion = fechaIndicacion;
		else
			throw new IllegalArgumentException(Errores.ERROR_FECHA_INDICACION_ANALISIS);
	}
	
	public Date getFechaAnalisis() {
		return fechaAnalisis;
	}


	public String getTipoAnalisis() {
		return tipoAnalisis;
	}

	private void setTipoAnalisis(String tipoAnalisis) {
		if(Validaciones.validarTipoAnalisis(tipoAnalisis))
			this.tipoAnalisis = tipoAnalisis;
		else 
			throw new IllegalArgumentException(Errores.ERROR_TIPO_ANALISIS);
	}

	public String getResultadoAnalisis() {
		return resultadoAnalisis;
	}
	
	public boolean tieneResultado() {
		return tieneResultado;
	}
	
	public void setResultadoAnalisis(String resultadoAnalisis, Date fechaAnalisis) {
		if(!tieneResultado && Validaciones.validarStringNoVacio(resultadoAnalisis) && Validaciones.validarStringNoTodoEspacio(resultadoAnalisis) && Validaciones.validarTamString(resultadoAnalisis, Definiciones.TAM_MIN_STRING_RESULTADO_ANALISIS, Definiciones.TAM_MAX_STRING_RESULTADO_ANALISIS)){
			if(fechaAnalisis!=null && fechaAnalisis.compareTo(fechaIndicacion)>=0) {
				this.resultadoAnalisis = resultadoAnalisis;
				this.fechaAnalisis = fechaAnalisis;
				tieneResultado = true;
			}
			else
				throw new IllegalArgumentException(Errores.ERROR_FECHA_ANALISIS);
		}
		else 
			throw new IllegalArgumentException(Errores.ERROR_RESULTADO_ANALISIS);
	}

	@Override
	public int compareTo(Analisis o) {
		return this.getFechaIndicacion().compareTo(o.getFechaIndicacion());
	}

	

		
}
