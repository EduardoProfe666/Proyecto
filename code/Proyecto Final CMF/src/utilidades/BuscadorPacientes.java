package utilidades;
import java.util.ArrayList;
import nucleo.Paciente;

public final class BuscadorPacientes {
	private BuscadorPacientes() {}
	
	private static final int FILTRO_NOMBRE = 0;
	private static final int FILTRO_PRIMER_APELLIDO = 1;
	private static final int FILTRO_SEGUNDO_APELLIDO = 2;
	private static final int FILTRO_CARNET_IDENTIDAD = 3;
	private static final int FILTRO_CALLE_PRINCIPAL = 4;
	private static final int FILTRO_NUMERO_CASA = 5;
	private static final int FILTRO_HISTORIA_CLINICA = 6;
	
	public static ArrayList<Paciente> buscadorPacientes(ArrayList<Paciente> listadoBase, String nombre, String primerApellido, String segundoApellido, String carnetIdentidad, String callePrincipal, String numeroCasa, String historiaClinica){
		ArrayList<Paciente> listadoResultante = filtrar(listadoBase,nombre, FILTRO_NOMBRE);
		listadoResultante = filtrar(listadoResultante, primerApellido, FILTRO_PRIMER_APELLIDO);
		listadoResultante = filtrar(listadoResultante, segundoApellido, FILTRO_SEGUNDO_APELLIDO);
		listadoResultante = filtrar(listadoResultante, carnetIdentidad, FILTRO_CARNET_IDENTIDAD);
		listadoResultante = filtrar(listadoResultante, callePrincipal, FILTRO_CALLE_PRINCIPAL);
		listadoResultante = filtrar(listadoResultante, numeroCasa, FILTRO_NUMERO_CASA);
		listadoResultante = filtrar(listadoResultante, historiaClinica, FILTRO_HISTORIA_CLINICA);
		
		return listadoResultante;
	}
	
	private static ArrayList<Paciente> filtrar(ArrayList<Paciente> listadoBase, String s, int tipoFiltro){
		ArrayList<Paciente> listadoResultante = new ArrayList<Paciente>();
		
		if(Validaciones.validarStringNoVacio(s)) {
			ArrayList<String> listadoAuxiliar = extraerListadoInformacion(listadoBase, tipoFiltro);
			s = s.trim().toUpperCase();
			
			if(tipoFiltro == FILTRO_CARNET_IDENTIDAD) {
				for(int i=0;i<listadoAuxiliar.size();i++) {
					if(listadoAuxiliar.get(i).startsWith(s))
						listadoResultante.add(listadoBase.get(i));
				}
			}
			else {
				for(int i=0;i<listadoAuxiliar.size();i++) {
					if(listadoAuxiliar.get(i).contains(s))
						listadoResultante.add(listadoBase.get(i));
				}
			}
		}
		else
			listadoResultante = listadoBase;
		
		return listadoResultante;
	}
	
	private static ArrayList<String> extraerListadoInformacion(ArrayList<Paciente> listado,int tipoFiltro){
		ArrayList<String> listadoInfo = new ArrayList<String>();
		
		switch(tipoFiltro) {
		case FILTRO_NOMBRE:
			for(Paciente p : listado)
				listadoInfo.add(p.getNombre().toUpperCase());
			break;
		case FILTRO_PRIMER_APELLIDO:
			for(Paciente p : listado)
				listadoInfo.add(p.getPrimerApellido().toUpperCase());
			break;
		case FILTRO_SEGUNDO_APELLIDO:
			for(Paciente p : listado)
				listadoInfo.add(p.getSegundoApellido().toUpperCase());
			break;
		case FILTRO_CARNET_IDENTIDAD:
			for(Paciente p : listado)
				listadoInfo.add(p.getCarnetIdentidad().toUpperCase());
			break;
		case FILTRO_CALLE_PRINCIPAL:
			for(Paciente p : listado)
				listadoInfo.add(p.getDireccion().getCallePrincipal().toUpperCase());
			break;
		case FILTRO_NUMERO_CASA:
			for(Paciente p : listado)
				listadoInfo.add(p.getDireccion().getNumeroCasa().toUpperCase());
			break;
		case FILTRO_HISTORIA_CLINICA:
			for(Paciente p : listado)
				listadoInfo.add(p.getHistoriaClinica().getNumeroHistoriaClinica());
			break;
		default:
			break;
		}
		
		return listadoInfo;
	}
	
}
