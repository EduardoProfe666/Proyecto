package pruebas;

import static org.junit.Assert.*;
import nucleo.PacienteFemenina;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilidades.PiscinaDatos;

public class PacienteFemeninaTestCase {
	private PacienteFemenina p;
	@Before
	public void setUp() throws Exception {
		p = new PacienteFemenina("Prueba","Prueba","Prueba", "02122368433", PiscinaDatos.generarDireccion(), null, false);
	}

	@After
	public void tearDown() throws Exception {
		p=null;
	}
	
	//  Prueba de caja negra
	
	//Prueba de setEstaEmbarazada
	@Test
	public void setEstaEmbarazadaTestCP1() {
		//CP1
		p = new PacienteFemenina("Prueba","Prueba","Prueba", "02101081297", PiscinaDatos.generarDireccion(), null, false);
		p.setEstaEmbarazada(true);
		assertTrue(p.getEstaEmbarazada());
	}
	@Test
	public void setEstaEmbarazadaTestCP2() {
		//CP2
		p = new PacienteFemenina("Prueba","Prueba","Prueba", "20101081297", PiscinaDatos.generarDireccion(), null, false);
		try{
			p.setEstaEmbarazada(true);
			fail();
		}catch(Exception e){
			assertFalse(p.getEstaEmbarazada());
		}
	}
	@Test
	public void setEstaEmbarazadaTestCP3() {
		//CP3
		p = new PacienteFemenina("Prueba","Prueba","Prueba", "30032534375", PiscinaDatos.generarDireccion(), null, false);
		try{
			p.setEstaEmbarazada(true);
			fail();
		}catch(Exception e){
			assertFalse(p.getEstaEmbarazada());
		}
	}

}
