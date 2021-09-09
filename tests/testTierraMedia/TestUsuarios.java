package testTierraMedia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import trabajoPracticco.*;


public class TestUsuarios {

	Usuario u1;

	@Before
	public void iniciar() {
		u1 = new Usuario("spider",1000, 100, "paisaje");
	}

	@Test
	public void queSeCreanUsuarios() {
		assertNotNull(u1);
	}

	
	@Test (expected = Error.class)
	public void queNoSeCreanUsuariosConConDineroNegativo() {
		u1 = new Usuario("spider",-1000, 100, "paisaje");
	}
	
	@Test (expected = Error.class)
	public void queNoSeCreanUsuariosConConTiempoNegativo() {
		u1 = new Usuario("spider",1000, -100, "paisaje");
	}
	
	
	@Test
	public void queGastaDinero() {
		double dinero = u1.getDineroDisponible();
		assertEquals(1000, dinero, 0.0);
		u1.gastarDinero(900);
		assertEquals(100, u1.getDineroDisponible(), 0.0);
	}

	@Test(expected = Error.class)
	public void queNoSePuedeGastarMasDineroDelQueSeTiene() {
		u1.gastarDinero(1001);

	}

	@Test(expected = Error.class)
	public void queUsuarioNoPuedeComprarProductosSiNoLeAlcanzaElDinero() {

		Atraccion impagable = new Atraccion("Mordor uno",  25000 , 10, 5, "Aventura");
		u1.comprarProducto(impagable);
	}
	
	@Test
	public void queElUsuarioSabeCuandoUnaAtraccionLeGusta() {
		Atraccion legusta = new Atraccion("bree",  25 , 1000000, 5, "paisaje");
		Atraccion noLegusta = new Atraccion("Mordor",  25 , 1000000, 5, "Aventura");
		assertFalse(u1.estaAtraccionMegusta(noLegusta));
		assertTrue(u1.estaAtraccionMegusta(legusta));
	}
	
	@Test
	public void queCompraAtracciones() {

		Atraccion atra3 = new Atraccion("Mordor tres", 25, 10, 5, "Aventura");
		Atraccion []esperada = {atra3};
		u1.comprarProducto(atra3);
		assertArrayEquals(esperada,u1.getproductosComprados().toArray());	
		
	}
	
	@Test
	public void quePagaLoQueCompra() {

		Atraccion atra3 = new Atraccion("Mordor tres", 25, 10, 5, "Aventura");
		u1.comprarProducto(atra3);
		
		assertEquals(975, u1.getDineroDisponible(),0);
	}
	
	@Test
	public void queRestaTiempoCuandoCompra() {

		Atraccion atra3 = new Atraccion("Mordor tres", 25, 10, 5, "Aventura");
		u1.comprarProducto(atra3);
		
		assertEquals(90, u1.getTiempoDisponible(),0);	
	}
	
	@Test
	public void queCompraPromociones() {

		Atraccion atra = new Atraccion("Mordor uno",  25 , 10, 5, "Aventura");
		Atraccion atra2 = new Atraccion("Mordor dos", 25, 10, 5, "Aventura");
		Atraccion atra3 = new Atraccion("Mordor tres", 25, 10, 5, "Aventura");
		Atraccion itinerario[] = { atra, atra2, atra3 };
		Promocion oferta= new PromoAbsoluta("nombre",itinerario, "aventura", 20);
		
		Promocion []esperada = {oferta};
		u1.comprarProducto(oferta);
		
		assertEquals(980, u1.getDineroDisponible(),0);
		assertEquals(70, u1.getTiempoDisponible(),0);
		assertArrayEquals(esperada,u1.getproductosComprados().toArray());		
	}
	
	@Test
	public void quePagaPromocionesQueCOmpra() {

		Atraccion atra = new Atraccion("Mordor uno",  25 , 10, 5, "Aventura");
		Atraccion atra2 = new Atraccion("Mordor dos", 25, 10, 5, "Aventura");
		Atraccion atra3 = new Atraccion("Mordor tres", 25, 10, 5, "Aventura");
		Atraccion itinerario[] = { atra, atra2, atra3 };
		Promocion oferta= new PromoAbsoluta("nombre",itinerario, "aventura", 20);
		
		u1.comprarProducto(oferta);
		assertEquals(980, u1.getDineroDisponible(),0);	
	}

	@Test
	public void queRestaTiempoDePromoComprada() {

		Atraccion atra = new Atraccion("Mordor uno",  25 , 10, 5, "Aventura");
		Atraccion atra2 = new Atraccion("Mordor dos", 25, 10, 5, "Aventura");
		Atraccion atra3 = new Atraccion("Mordor tres", 25, 10, 5, "Aventura");
		Atraccion itinerario[] = { atra, atra2, atra3 };
		Promocion oferta= new PromoAbsoluta("nombre",itinerario, "aventura", 20);
	
		u1.comprarProducto(oferta);
		
		assertEquals(70, u1.getTiempoDisponible(),0);
	}
	
	@Test
	public void quePagaConDineroLasAtraccionse() {

		Atraccion atra = new Atraccion("Mordor uno", 25, 10, 5, "Aventura");
		u1.comprarProducto(atra);
		assertEquals(975, u1.getDineroDisponible(), 0);
	}

	@Test
	public void queCalculaElTiempo() {

		Atraccion atra = new Atraccion("Mordor uno", 25, 10, 5, "Aventura");
		u1.comprarProducto(atra);
		assertEquals(90, u1.getTiempoDisponible(), 0);
	}
	
	@Test
	public void queSabeCuandoUnaAtraccionYaEstaIncluidaEnUnaPromocionQueCompro() {
		Atraccion atra = new Atraccion("Mordor uno",  25 , 10, 5, "Aventura");
		Atraccion atra2 = new Atraccion("Mordor dos", 25, 10, 5, "Aventura");
		Atraccion atra3 = new Atraccion("Mordor tres", 25, 10, 5, "Aventura");
		Atraccion itinerario[] = { atra, atra2 };
		Promocion oferta= new PromoAbsoluta("nombre",itinerario, "aventura", 20);
	
		u1.comprarProducto(oferta);
		
		assertTrue(u1.atraccionIncluidaEnPromocionComprada(atra));
		assertFalse(u1.atraccionIncluidaEnPromocionComprada(atra3));
	}
}
