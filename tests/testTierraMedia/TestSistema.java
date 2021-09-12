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
		u1 = new Usuario("nombre", 2000,20000, "Aventura");
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
		assertNotNull(s1.getPromociones());	
		}

	@Test
	public void  queSistemaCuentaTodasLasPromocionesAofrecer() {
		s1.setOfertas(u1);
		assertEquals(34,s1.cantidadDeAOfertas(u1));
	}
	
	
	@Test
	public void  queSistemaSoloCuentaLasPromocionesyAtraccionesQueElUsuarioPuedePgar() {
		u1 = new Usuario("nombre", 10,20000, "Aventura");
		s1.setOfertas(u1);
		assertEquals(14,s1.cantidadDeAOfertas(u1));
	}
	
	@Test
	public void  queSistemaSoloCuentaLasPromocionesyAtraccionesQueElUsuarioPuedeRecorrer() {
		u1 = new Usuario("nombre", 2000,2, "Aventura");
		s1.setOfertas(u1);
		assertEquals(8,s1.cantidadDeAOfertas(u1));
	}
	
	@Test
	public void  queSistemaSoloCuentaLasAtraccioensQueTienenCupo() {
		u1 = new Usuario("nombre", 2000,20000, "Aventura");
		s1.setOfertas(u1);
		u1.comprarProducto(s1.getAtracciones().get(21));
		assertEquals(33,s1.cantidadDeAOfertas(u1));
	}
	
	@Test
	public void  queSistemaSoloCuentaLasPromocionesQueTienenCupo() {
		u1 = new Usuario("nombre", 2000,20000, "Aventura");
		s1.setOfertas(u1);
		u1.comprarProducto(s1.getOfertasDiaria().get(3));
		u1.comprarProducto(s1.getOfertasDiaria().get(3));
		u1.comprarProducto(s1.getOfertasDiaria().get(3));
		u1.comprarProducto(s1.getOfertasDiaria().get(3));
		assertEquals(32,s1.cantidadDeAOfertas(u1));
	}
	
	@Test
	public void  queSistemaNoCuentaOfertasYaCompradas() {
		u1 = new Usuario("nombre", 2000,20000, "Aventura");
		u1.comprarProducto(s1.getAtracciones().get(0));			
		s1.setOfertas(u1);
		
		assertEquals(33,s1.cantidadDeAOfertas(u1));
	
	}
	
	@Test
	public void  queSistemaOrdenaDeMayorAmenor() {
		u1 = new Usuario("nombre", 2000,20000, "Aventura");		
		s1.setOfertas(u1);
		double primerCosto=s1.getOfertasDiaria().get(0).getCosto();
		double segundoCosto=s1.getOfertasDiaria().get(1).getCosto();
		assertTrue(primerCosto>=segundoCosto);	
	}
	
	@Test
	public void  queSistemaOrdenaPrimeroPromocionesLuegoAtracciones() {
		u1 = new Usuario("nombre", 2000,20000, "Aventura");		
		s1.setOfertas(u1);
		assertTrue(s1.getOfertasDiaria().get(0) instanceof Promocion);
		assertTrue(s1.getOfertasDiaria().get(1) instanceof Promocion);
		assertTrue(s1.getOfertasDiaria().get(2) instanceof Promocion);
		assertTrue(s1.getOfertasDiaria().get(3) instanceof Atraccion);
		
	}
	
	@Test
	public void queSistemaOrdenaPrimeroPromocionesConElTipoPreferidoDelUsuarioAtracciones() {
		u1 = new Usuario("nombre", 2000, 20000, "Aventura");
		s1.setOfertas(u1);
		for (int i = 0; i < 34; i++) {
			if(i<11) {
				assertTrue(s1.getOfertasDiaria().get(i).getTipo().equals("Aventura"));
			}else{
				assertFalse(s1.getOfertasDiaria().get(i).getTipo().equals("Aventura"));
			}
		}	
	}
	
}
