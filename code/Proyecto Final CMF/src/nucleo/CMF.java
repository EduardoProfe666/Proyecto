package nucleo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import clases_auxiliares.AnalisisIndicados;
import definiciones.Errores;
import utilidades.Comparadores;
import utilidades.Validaciones;

public class CMF {
	private int numeroConsultorio;
	private String nombrePoliclinico;
	private String nombreDirectorPoliclinico;
	private Medico doctor;
	private Enfermera enfermera;
	private ArrayList<Paciente> listadoPacientes;
	private ArrayList<HojaDeCargosDia> listadoHojaDeCargo;
	private static CMF instancia;
	
	private CMF(int numeroConsultorio ,String nombrePoliclinico, String nombreDirectorPoliclinico, 
			Medico doctor, Enfermera enfermera) {
		setNumeroConsultorio(numeroConsultorio);
		setNombrePoliclinico(nombrePoliclinico);
		setNombreDirectorPoliclinico(nombreDirectorPoliclinico);
		setDoctor(doctor);
		setEnfermera(enfermera);
		listadoPacientes = new ArrayList<Paciente>();
		listadoHojaDeCargo = new ArrayList<HojaDeCargosDia>();
	}
	
	public static CMF getInstancia(int numeroConsultorio,String nombrePoliclinico, String nombreDirectorPoliclinico, 
			Medico doctor, Enfermera enfermera) {
		if(instancia == null)
			instancia = new CMF(numeroConsultorio,nombrePoliclinico, nombreDirectorPoliclinico, doctor, enfermera);
		return instancia;
	}
	
	public static CMF getInstancia() {
		if(instancia == null)
			throw new NullPointerException(Errores.ERROR_INSTANCIA_CMF_NULA);
		return instancia;
	}

	public int getNumeroConsultorio() {
		return numeroConsultorio;
	}
	private void setNumeroConsultorio(int numeroConsultorio) {
		if(numeroConsultorio>0)
			this.numeroConsultorio = numeroConsultorio;
		else
			throw new IllegalArgumentException();
	}

	public String getNombrePoliclinico() {
		return nombrePoliclinico;
	}

	public	 void setNombrePoliclinico(String nombrePoliclinico) {
		if(Validaciones.validarStringNoVacio(nombrePoliclinico) && Validaciones.validarStringNoTodoEspacio(nombrePoliclinico))
			this.nombrePoliclinico = nombrePoliclinico;
		else
			throw new IllegalArgumentException(Errores.ERROR_NOMBRE_POLICLINICO);
	}

	public String getNombreDirectorPoliclinico() {
		return nombreDirectorPoliclinico;
	}

	public void setNombreDirectorPoliclinico(String nombreDirectorPoliclinico) {
		if(Validaciones.validarStringNoVacio(nombreDirectorPoliclinico) && Validaciones.validarStringNoTodoEspacio(nombreDirectorPoliclinico))
			this.nombreDirectorPoliclinico = nombreDirectorPoliclinico;
		else
			throw new IllegalArgumentException(Errores.ERROR_NOMBRE_DIRECTOR_POLICLINICO);
	}

	public Medico getDoctor() {
		return doctor;
	}

	public void setDoctor(Medico doctor) {
		if(doctor!=null)
			this.doctor = doctor;
		else
			throw new IllegalArgumentException(Errores.ERROR_DOCTOR);
	}

	public Enfermera getEnfermera() {
		return enfermera;
	}

	public void setEnfermera(Enfermera enfermera) {
		if(enfermera!=null)
			this.enfermera = enfermera;
		else
			throw new IllegalArgumentException(Errores.ERROR_ENFERMERA);
	}

	public ArrayList<Paciente> getListadoPacientes() {
		return new ArrayList<Paciente>(listadoPacientes);
	}
	
	public Paciente getPaciente(int indicePaciente) {
		try {
			return listadoPacientes.get(indicePaciente);
		}catch(Exception e) {
			throw new IllegalArgumentException(Errores.ERROR_INDICE_PACIENTE);
		}
	}
	public Paciente getPaciente(String carnetIdentidad) { 
		organizarListadoPacientes(Comparadores.comparadorPersona(Comparadores.PERSONA_CARNET_IDENTIDAD));
		ArrayList<String> listadoCi = getListadoCI();
		
		try {
			Paciente p = getPaciente(Collections.binarySearch(listadoCi, carnetIdentidad));
			organizarListadoPacientes(Comparadores.comparadorPersona(Comparadores.PERSONA_NOMBRE_COMPLETO));
			return p;
		}catch(Exception e) {
			throw new IllegalArgumentException(Errores.ERROR_CI);
		}
	}
	public ArrayList<String> getListadoCI(){
		ArrayList<String> listado = new ArrayList<String>();
		for(Paciente p : listadoPacientes)
			listado.add(p.getCarnetIdentidad());
		Collections.sort(listado);
		return listado;
	}
	public int getIndicePaciente(Paciente p) {
		int indice;
		if(p==null)
			indice=-1;
		else
			indice = listadoPacientes.indexOf(p);
		return indice;
	}
	public int getIndicePaciente(String carnetIdentidad) {
		return getIndicePaciente(getPaciente(carnetIdentidad));
	}
	
	public void removePaciente(int indicePaciente) {
		try {
			listadoPacientes.remove(indicePaciente);
		}catch(Exception e) {
			throw new IllegalArgumentException(Errores.ERROR_INDICE_PACIENTE);
		}
	}
	
	public void addPaciente(String nombre, String primerApellido, String segundoApellido, String carnetIdentidad, Direccion direccion) {
		if(Validaciones.validarUnicidadCI(carnetIdentidad))
			listadoPacientes.add(new Paciente(nombre, primerApellido, segundoApellido, carnetIdentidad, direccion));
		else
			throw new IllegalArgumentException(Errores.ERROR_PACIENTE);
	}
	public void addPacienteFemenina(String nombre, String primerApellido, String segundoApellido, String carnetIdentidad, Direccion direccion, Date fechaUltimaPruebaCitologica, boolean estaEmbarazada) {
		if(Validaciones.validarUnicidadCI(carnetIdentidad))
			listadoPacientes.add(new PacienteFemenina(nombre, primerApellido, segundoApellido, carnetIdentidad, direccion, fechaUltimaPruebaCitologica, estaEmbarazada));
		else
			throw new IllegalArgumentException(Errores.ERROR_PACIENTE);
	}
	
	public void addVisita(String diagnostico, String ci, String tratamiento, ArrayList<String> analisisIndicados, ArrayList<String> especialidadesRemitidas) {
		ArrayList<Calendar> aux = listadoFechasHojasDeCargos();
		
		Calendar fechaActual = Calendar.getInstance();
		
		boolean seEncontro = false;
		
		for(int i=0;i<aux.size() && !seEncontro;i++) {
			seEncontro = Validaciones.validarCalendarsMismoDia(fechaActual, aux.get(i));
		}
		
		if(!seEncontro)
			listadoHojaDeCargo.add(new HojaDeCargosDia(fechaActual));
		
		Paciente p = getPaciente(ci);
		
		listadoHojaDeCargo.get(listadoHojaDeCargo.size()-1).addVisitaDePaciente(diagnostico, p);
		
		p.getHistoriaClinica().addVisita(diagnostico, tratamiento, analisisIndicados, especialidadesRemitidas);
		
	}
	public ArrayList<HojaDeCargosDia> getListadoHojaDeCargo() {
		return new ArrayList<HojaDeCargosDia>(listadoHojaDeCargo);
	}
	
	public HojaDeCargosDia getHojaDeCargosDia(Calendar fecha){
		HojaDeCargosDia hoja=null;
		
		for(int i=0;i<listadoHojaDeCargo.size() && hoja==null;i++) {
			if(Validaciones.validarCalendarsMismoDia(fecha, listadoHojaDeCargo.get(i).getFecha()))
				hoja = listadoHojaDeCargo.get(i).clone();
		}
		
		if(hoja==null)
			throw new IllegalArgumentException(Errores.ERROR_INDICE_FECHA_HOJA_CARGOS);
		return hoja;
		
	}
	private ArrayList<Calendar> listadoFechasHojasDeCargos(){
		ArrayList<Calendar> listado = new ArrayList<Calendar>();
		
		for(HojaDeCargosDia h : listadoHojaDeCargo) { 
			listado.add(h.getFecha());
		}
		
		return listado;
	}
	
	public ArrayList<PacienteFemenina> listadoPacientesFemeninas() {
		ArrayList<PacienteFemenina> listado = new ArrayList<PacienteFemenina>();
		
		for(Paciente paciente : listadoPacientes) 
			if(paciente instanceof PacienteFemenina) 
				listado.add((PacienteFemenina)paciente);
		
		return listado;
	}
	
	public boolean organizarListadoPacientes(Comparator<? super Paciente> c) {
		boolean seOrdeno = c!=null;
		
		if(seOrdeno)
			Collections.sort(listadoPacientes,c);
		
		return seOrdeno;
		
	}
	
	public ArrayList<AnalisisIndicados> listadoAnalisisFaltantes(Date fecha) { //Funcionalidad enfermera
		if(fecha==null)
			throw new IllegalArgumentException(Errores.ERROR_FECHA_ANALISIS_FALTANTES_REPORTE);
		
		ArrayList<AnalisisIndicados> listado = new ArrayList<AnalisisIndicados>();
		
		for(Paciente p : listadoPacientes) {
			ArrayList<Analisis> l = p.getHistoriaClinica().analisisIndicadosFaltantesEnYDespuesFecha(fecha);
			if(!l.isEmpty())
				listado.add(new AnalisisIndicados(p,l));
		}
		
		return listado;
	}
	
	/*Reportes
	 * 
	 * 
	 */
	public ArrayList<Paciente> pacientesEnRiesgo(){
		ArrayList<Paciente> listado = new ArrayList<Paciente>();
		
		for(Paciente paciente : listadoPacientes) {
			if(paciente.enRiesgo())
				listado.add(paciente);
		}
		
		Collections.sort(listado, Comparadores.comparadorPersona(Comparadores.PERSONA_NOMBRE_COMPLETO));
		
		return listado;
	}

	public ArrayList<PacienteFemenina> listadoEmbarazadas(){
		ArrayList<PacienteFemenina> listado = new ArrayList<PacienteFemenina>();
		
		//Buscar las pacientes embarazadas
		for(Paciente paciente : listadoPacientes) {
			if(paciente instanceof PacienteFemenina && ((PacienteFemenina)paciente).getEstaEmbarazada()) {
				listado.add(((PacienteFemenina)paciente));
			}
		}
		
		Collections.sort(listado, Comparadores.comparadorPersona(Comparadores.PERSONA_NOMBRE_COMPLETO));

		return listado;
	}

	public ArrayList<Paciente> listadoPacientesVacuna(String vacuna){
		ArrayList<Paciente> listado = new ArrayList<Paciente>();
		//Buscar los pacientes vacunados
		if(vacuna==null) {
			for(Paciente paciente : listadoPacientes) {
				if(!paciente.listadoVacunasPuestas().isEmpty())
					listado.add(paciente);
			}
		}
		else if(!Validaciones.validarNombreVacuna(vacuna))
			throw new IllegalArgumentException(Errores.ERROR_VACUNA_REPORTE);
		
		//Buscar los pacientes con una vacuna dada
		else {
			for(Paciente paciente : listadoPacientes) {
				if(Validaciones.validarRepeticionStringLista(paciente.listadoVacunasPuestas(), vacuna))
					listado.add(paciente);
			}
		}
		
		return listado;
	}

	public ArrayList<Paciente> listadoPacientesEnfermedad(String enfermedad){
		ArrayList<Paciente> listado = new ArrayList<Paciente>();
		
		//Buscar los pacientes con enfermedades crónicas
		if(enfermedad==null) {
			for(Paciente paciente : listadoPacientes) {
				if(!paciente.getEnfermedadesCronicas().isEmpty())
					listado.add(paciente);
			}
		}
		else if(!Validaciones.validarEnfermedadCronica(enfermedad))
			throw new IllegalArgumentException(Errores.ERROR_ENFERMEDAD_CRONICA_REPORTE);
		
		//Buscar los pacientes con una enfermedad crónica dada
		else {
			for(Paciente paciente : listadoPacientes) {
				if(Validaciones.validarRepeticionStringLista(paciente.getEnfermedadesCronicas(), enfermedad))
					listado.add(paciente);
			}
		}
		
		return listado;
	}

	public ArrayList<Paciente> listadoPacientesSinVisita(Date fecha){
		ArrayList<Paciente> listado = new ArrayList<Paciente>();
		
		//Buscar los pacientes sin visitas después de una fecha dada (inclusivo)
		if(fecha!=null){
			for(Paciente paciente : listadoPacientes) {	
				if(paciente.getHistoriaClinica().visitasEnYDespuesFechaDada(fecha).isEmpty())
					listado.add(paciente);
			}
		}
		
		return listado;
	}

	public AnalisisIndicados listadoAnalisisFaltantesPaciente(Paciente p ,Date fecha) { 
		if(fecha==null)
			throw new IllegalArgumentException(Errores.ERROR_FECHA_ANALISIS_FALTANTES_REPORTE);
		if(p==null)
			throw new IllegalArgumentException(Errores.ERROR_PACIENTE_REPORTE_ANALISIS_FALTANTES);
		
		return new AnalisisIndicados(p,p.getHistoriaClinica().analisisIndicadosFaltantesEnYDespuesFecha(fecha));
		
	}


}
