package pruebas;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import definiciones.Definiciones;
import nucleo.CMF;
import nucleo.Enfermera;
import nucleo.Medico;
import nucleo.Paciente;
import nucleo.PacienteFemenina;
import utilidades.PiscinaDatos;

public class CMFTestCase {
	private CMF consultorio;
	@Before
	public void setUp() throws Exception {
		consultorio = CMF.getInstancia(Definiciones.NUMERO_CONSULTORIO,PiscinaDatos.generarNombreApellido(), PiscinaDatos.generarNombreApellido(), 
				new Medico("Prueba", "Prueba", "Prueba", "79122338433", "Prueba", new Date()),
				new Enfermera("Prueba", "Prueba", "Prueba", "89122338455", true, new Date(), new Date()));
	}

	@After
	public void tearDown() throws Exception {
		while(!consultorio.getListadoPacientes().isEmpty())
			consultorio.removePaciente(0);
	}	
			
	//          PRUEBAS DE CAJA BLANCA
	
	//Prueba de pacientesEnRiesgo
	@Test
	public void pacientesEnRiesgoTestCP1() {
		//CP 1
		consultorio.addPaciente("Prueba", "Prueba", "Prueba","02122368423", PiscinaDatos.generarDireccion());
		
		consultorio.getPaciente(0).addEnfermedadCronica("Asma");
		consultorio.getPaciente(0).addEnfermedadCronica("Diabetes");
		consultorio.getPaciente(0).addEnfermedadCronica("Epilepsia");
		consultorio.getPaciente(0).addEnfermedadCronica("Esclerosis Múltiple");
		
		ArrayList<Paciente> resultadoEsperado = new ArrayList<Paciente>();
		resultadoEsperado.add(consultorio.getPaciente(0));
		assertEquals(consultorio.pacientesEnRiesgo(),resultadoEsperado);
	}		
	@Test
	public void pacientesEnRiesgoTestCP2(){
		//CP2
		consultorio.addPaciente("Prueba", "Prueba",  "Prueba", "02122368423", PiscinaDatos.generarDireccion());
		
		consultorio.getPaciente(0).addEnfermedadCronica("Asma");
		assertTrue(consultorio.pacientesEnRiesgo().isEmpty());
	}
	@Test
	public void pacientesEnRiesgoTestCP3(){
		//CP3
		assertTrue(consultorio.pacientesEnRiesgo().isEmpty());
	}
	
	//Prueba de listadoPacientesFemeninas
	@Test
	public void listadoPacientesFemeninasTestCP1(){
		//CP1
		consultorio.addPaciente("Prueba", "Prueba",  "Prueba", "02101081224", PiscinaDatos.generarDireccion());
		
		assertTrue(consultorio.listadoPacientesFemeninas().isEmpty());
	}
	@Test
	public void listadoPacientesFemeninasTestCP2(){
		//CP2
		consultorio.addPacienteFemenina("Prueba", "Prueba",  "Prueba", "02101081295", PiscinaDatos.generarDireccion(), null,false);
		
		ArrayList<PacienteFemenina> resultadoEsperado = new ArrayList<PacienteFemenina>();
		resultadoEsperado.add((PacienteFemenina)consultorio.getPaciente(0));
		assertEquals(consultorio.listadoPacientesFemeninas(),resultadoEsperado);
	}
	@Test
	public void listadoPacientesFemeninasTestCP3(){
		//CP3
		assertTrue(consultorio.listadoPacientesFemeninas().isEmpty());
	}

	
	//         Prueba de caja negra
	
	//Prueba de listadoPacientesEnfermedad
	@Test
	public void listadoPacientesEnfermedadTestCP1(){
		//CP1
		consultorio.addPaciente("Lia", "Torres", "Torres","02101081293", PiscinaDatos.generarDireccion());
		consultorio.getPaciente(0).addEnfermedadCronica("Asma");	
		
		ArrayList<Paciente> resultadoEsperado = new ArrayList<Paciente>();
		resultadoEsperado.add(consultorio.getPaciente(0));
		assertEquals(consultorio.listadoPacientesEnfermedad("Asma"),resultadoEsperado);
	}
	@Test
	public void listadoPacientesEnfermedadTestCP2(){
		//CP2
		consultorio.addPaciente("Lia", "Torres", "Torres","02101081293", PiscinaDatos.generarDireccion());
		consultorio.getPaciente(0).addEnfermedadCronica("Asma");
		consultorio.getPaciente(0).addEnfermedadCronica("Diabetes");
		
		ArrayList<Paciente> resultadoEsperado = new ArrayList<Paciente>();
		resultadoEsperado.add(consultorio.getPaciente(0));
		assertEquals(consultorio.listadoPacientesEnfermedad(null),resultadoEsperado);
	}
	@Test
	public void listadoPacientesEnfermedadTestCP3(){
		//CP3
		consultorio.addPaciente("Lia", "Torres", "Torres","02101081293", PiscinaDatos.generarDireccion());
		consultorio.getPaciente(0).addEnfermedadCronica("Asma");
		consultorio.getPaciente(0).addEnfermedadCronica("Diabetes");
		
		ArrayList<Paciente> resultadoReal = null;
		
		try{
			resultadoReal = consultorio.listadoPacientesEnfermedad("Hola");
			fail();
		}catch(Exception e){
			assertTrue(resultadoReal==null);
		}
	}
	@Test
	public void listadoPacientesEnfermedadTestCP4(){
		//CP4
		consultorio.addPaciente("Lia", "Torres", "Torres","02101081293", PiscinaDatos.generarDireccion());
		consultorio.getPaciente(0).addEnfermedadCronica("Asma");
		consultorio.getPaciente(0).addEnfermedadCronica("Diabetes");
		
		assertTrue(consultorio.listadoPacientesEnfermedad("Epilepsia").isEmpty());
	}
	
	//Prueba de addPaciente
	@Test
	public void addPacienteTestCP1(){
		//CP1
		consultorio.addPaciente("Prueba", "Prueba", "Prueba","02101081294", PiscinaDatos.generarDireccion());
		assertTrue(!consultorio.getListadoPacientes().isEmpty());
	}
	@Test
	public void addPacienteTestCP2(){
		//CP2
		consultorio.addPaciente("Prueba", "Prueba", "Prueba","02101081294", PiscinaDatos.generarDireccion());
		
		try{
			consultorio.addPaciente("Prueba", "Prueba", "Prueba","02101081294", PiscinaDatos.generarDireccion());
			fail();
		}catch(Exception e){
			assertTrue(consultorio.getListadoPacientes().size()==1);
		}
	}
	@Test
	public void addPacienteTestCP3(){
		//CP3
		try{
			consultorio.addPaciente(null, null, null, null, null);
			fail();
		}catch(Exception e){
			assertTrue(consultorio.getListadoPacientes().isEmpty());
		}
	}
	
}