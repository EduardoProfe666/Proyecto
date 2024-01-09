package inicializacion;

import java.util.ArrayList;
import java.util.Date;

import definiciones.Definiciones;
import nucleo.CMF;
import nucleo.Direccion;
import nucleo.Enfermera;
import nucleo.Medico;
import nucleo.Vacuna;
import utilidades.Auxiliares;
import utilidades.PiscinaDatos;

public final class Inicializadora {
	private Inicializadora() {}
	private static PiscinaDatos p = PiscinaDatos.getInstancia();
	
	public static void inicializarCMF() {
		CMF.getInstancia(Definiciones.NUMERO_CONSULTORIO,PiscinaDatos.generarNombreApellido(), PiscinaDatos.generarNombreApellido(), inicializarMedico(), inicializarEnfermera());
		inicializarPacientes();
	}
	private static Medico inicializarMedico() {
		String ci = p.generarCiMedico();
		char sexo = Auxiliares.determinarSexo(ci);
		String numeroMedico = PiscinaDatos.generarNumeroRegistroMedico();
		Date fechaInscripcionCmf = PiscinaDatos.generarFechaInscripcionCMF(PiscinaDatos.generarComienzoLaboral(Auxiliares.convertirFechaNacimientoCiDate(ci)));
		
		return new Medico(PiscinaDatos.generarNombreSimple(sexo), PiscinaDatos.generarApellido(), PiscinaDatos.generarApellido(), ci, numeroMedico, fechaInscripcionCmf);
	}
	private static Enfermera inicializarEnfermera() {
		String ci = p.generarCiEnfermera();
		Date comienzoLaboral = PiscinaDatos.generarComienzoLaboral(Auxiliares.convertirFechaNacimientoCiDate(ci));
		Date fechaInscripcionCmf = PiscinaDatos.generarFechaInscripcionCMF(comienzoLaboral);
		
 		return new Enfermera(PiscinaDatos.generarNombreSimple('F'), PiscinaDatos.generarApellido(), PiscinaDatos.generarApellido(), ci,
 							PiscinaDatos.generarTieneLicenciaturaEnfermera(), fechaInscripcionCmf, comienzoLaboral);
	}
	private static void inicializarPacientes() {
		CMF cmf = CMF.getInstancia();
		int cantCi = p.cantidadCiGenerados();
		
		for(int i=0;i<cantCi;i++) {
			String ci = p.generarCi();
			char sexo = Auxiliares.determinarSexo(ci);
			String nombre = PiscinaDatos.generarNombre(sexo);
			String primerApellido = PiscinaDatos.generarApellido();
			String segundoApellido = PiscinaDatos.generarApellido();
			Direccion direccion = PiscinaDatos.generarDireccion();
			ArrayList<String> enfermedadesCronicas = PiscinaDatos.generarEnfermedadesCronicas();
			ArrayList<Vacuna> vacunaciones = PiscinaDatos.generarVacunaciones(Auxiliares.convertirFechaNacimientoCiDate(ci));
			
			if(sexo=='F' || sexo =='f') {
				Date fechaNac = Auxiliares.convertirFechaNacimientoCiDate(ci);
				cmf.addPacienteFemenina(nombre, primerApellido, segundoApellido, ci, direccion, PiscinaDatos.generarFechaUltimaPruebaCitologica(fechaNac), PiscinaDatos.generarEstaEmbarazada(fechaNac));
			}
			else
				cmf.addPaciente(nombre, primerApellido, segundoApellido, ci, direccion);
			
			for(String s : enfermedadesCronicas)
				cmf.getPaciente(i).addEnfermedadCronica(s);
			for(Vacuna v : vacunaciones)
				cmf.getPaciente(i).addVacunacion(v.getFechaVacunacion(), v.getNombreVacuna());
		}
	}
}

