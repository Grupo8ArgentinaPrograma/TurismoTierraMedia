package testTierraMedia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import trabajoPracticco.*;


public class TestSistema {

	PromoAbsoluta pp;
	Atraccion a1;
	Atraccion a2;
	Atraccion a3;
	Atraccion a4;
	Atraccion atracciones[] = new Atraccion[4];

	Usuario u1;
	Sistema s1; 

	@Before
	public void test() {
		a1 = new Atraccion("Mordor uno", 25, 10, 5, "Aventura");
		a2 = new Atraccion("Mordor dos", 25, 10, 5, "Aventura");
		a3 = new Atraccion("Mordor tres", 25, 10, 5, "Aventura");
		a4 = new Atraccion("Mordor cuatro", 25, 10, 5, "Aventura");
		u1 = new Usuario("nombre", 1000, 1000, "Aventura");
		s1 = new Sistema();
		s1.cargarUsuarios("visitantes.txt");
		s1.cargarAtracciones("atracciones.txt");	
		s1.cargarPromociones("promociones.txt");
		atracciones[0] = a1;
		atracciones[1] = a2; 
		atracciones[2] = a3;
		atracciones[3] = a4;

		pp = new PromoAbsoluta("nombre",atracciones, "Paisaje", 25);
	}

	@Test
	public void queSeCreaSistema() {
		Sistema s1 = new Sistema();		
		assertNotNull(s1);
	}

	@Test
	public void queSistemaAgregaUsuariosDesdeArchivo() {
		s1.cargarUsuarios("visitantes.txt");
		assertNotNull(s1.getVisitantes());
	}
	
	@Test
	public void queSistemaAgregaAtraccionesDesdeArchivo() {
		s1.cargarAtracciones("atracciones.txt");
		assertNotNull(s1.getAtracciones());
	}
	
	@Test
	public void queSistemaAgregapromocionesDesdeArchivo() {
		s1.cargarAtracciones("atracciones.txt");	
		s1.cargarPromociones("promociones.txt");
		assertNotNull(s1.getPromociones());	}

}
