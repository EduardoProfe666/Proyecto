package nucleo;

import java.util.ArrayList;
import java.util.Date;

import utilidades.Auxiliares;
import utilidades.Validaciones;
import definiciones.Definiciones;
import definiciones.Errores;

public class Paciente extends Persona implements Comparable<Paciente>{	
	protected ArrayList <String> enfermedadesCronicas;
	protected ArrayList <Vacuna> vacunaciones;
	protected HistoriaClinica historiaClinica;
	protected Direccion direccion;

	public Paciente(String nombre, String primerApellido, String segundoApellido, String carnetIdentidad, 
			Direccion direccion) {
		super(nombre, primerApellido, segundoApellido, carnetIdentidad, Definiciones.EDAD_MIN, Definiciones.EDAD_MAX);
		setDireccion(direccion);
		enfermedadesCronicas = new ArrayList<String>();
		vacunaciones = new ArrayList<Vacuna>();
		historiaClinica = new HistoriaClinica(Auxiliares.generarNumeroHistoriaClinica(this.direccion));    //El orden de inicializacion debe ser este           

	}

	public ArrayList<String> getEnfermedadesCronicas() {
		return new ArrayList<String>(enfermedadesCronicas);
	}

	public ArrayList<Vacuna> getVacunaciones() {
		return new ArrayList<Vacuna>(vacunaciones);
	}

	public HistoriaClinica getHistoriaClinica() {
		return historiaClinica;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		if(Validaciones.validarDireccion(direccion))
			this.direccion = direccion;
		else
			throw new IllegalArgumentException(Errores.ERROR_DIRECCION);
	}

	public char getSexo() {
		return Auxiliares.determinarSexo(carnetIdentidad);
	}

	public void addEnfermedadCronica(String enfermedadCronica) {
		if(Validaciones.validarEnfermedadCronica(enfermedadCronica) && Validaciones.validarNoRepeticionStringLista(enfermedadesCronicas, enfermedadCronica))
			enfermedadesCronicas.add(enfermedadCronica);        
		else
			throw new IllegalArgumentException(Errores.ERROR_ENFERMEDAD_CRONICA);
	}
	
	public String getEnfermedadCronica(int indiceEnfermedadCronica) {
		try {
			return enfermedadesCronicas.get(indiceEnfermedadCronica);
		}
		catch(Exception e) {
			throw new IllegalArgumentException(Errores.ERROR_INDICE_ENFERMEDAD_CRONICA);
		}
	}

	public void removeEnfermedadCronica(int indiceEnfermedadCronica) {
		try {
			enfermedadesCronicas.remove(indiceEnfermedadCronica);
		}
		catch(Exception e) {
			throw new IllegalArgumentException(Errores.ERROR_INDICE_ENFERMEDAD_CRONICA);
		}
	}

	public void addVacunacion(Date fecha,String nombreVacuna) {
		if(Validaciones.validarNoRepeticionStringLista(listadoVacunasPuestas(), nombreVacuna))
			vacunaciones.add(new Vacuna(fecha,nombreVacuna));
		else
			throw new IllegalArgumentException(Errores.ERROR_VACUNACION);
	}
	
	public ArrayList<String> listadoVacunasPuestas(){
		ArrayList<String> listado = new ArrayList<String>();
		
		for(Vacuna v : vacunaciones) {
			listado.add(v.getNombreVacuna());
		}
		
		return listado;
	}
	
	public boolean enRiesgo() {
		return enfermedadesCronicas.size() > Definiciones.CANT_ENFERMEDADES_CRONICAS_RIESGO;
	}

	@Override
	public int compareTo(Paciente o) {
		return this.carnetIdentidad.compareTo(o.getCarnetIdentidad());
	}
}

