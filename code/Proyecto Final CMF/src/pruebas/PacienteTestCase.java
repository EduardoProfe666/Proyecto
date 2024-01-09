package pruebas;

import static org.junit.Assert.*;
import nucleo.Paciente;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilidades.PiscinaDatos;

public class PacienteTestCase {
	private Paciente p;
	@Before
	public void setUp() throws Exception {
		p = new Paciente("Prueba","Prueba","Prueba", "02122368423", PiscinaDatos.generarDireccion());
	}

	@After
	public void tearDown() throws Exception {
		p=null;
	}
	
	//     Pruebas de caja negra
	
	//Prueba de setNombre
	@Test
	public void setNombreTestCp1() {
		//CP1
		String nombre = "Vicente";
		
		p.setNombre(nombre);
		assertEquals(p.getNombre(),nombre);
	}
	@Test
	public void setNombreTestCp2() {
		//CP2
		String nombre = "qwertyuiopasdfghjlzxcvbnmqwerty";
		try{
			p.setNombre(nombre);
			fail();
		}catch(Exception e){
			assertFalse(p.getNombre().equals(nombre));
		}
	}
	@Test
	public void setNombreTestCp3() {
		//CP3
		String nombre = "";
		try{
			p.setNombre(nombre);
			fail();
		}catch(Exception e){
			assertFalse(p.getNombre().equals(nombre));
		}
	}
}
