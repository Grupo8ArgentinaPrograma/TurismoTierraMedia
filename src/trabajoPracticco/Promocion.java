package trabajoPracticco;

import java.util.*;

public abstract class Promocion extends Ofertable {

	private Atraccion[] itinerario;
	private LinkedHashSet<String> atraccionesIncluidas;

	public Promocion(String nombre, Atraccion[] paquete, String tipo) {
		super(nombre, tipo);
		this.itinerario = paquete;
		this.atraccionesIncluidas = new LinkedHashSet<String>();
		super.setTiempo(getTiempoRecorrido());
		
	}

	@Override
	public double getCosto() {
		double total = 0;
		for (Atraccion a : itinerario) {
			total += a.getCosto();
		}
		
		if(total < 0) {
			throw new Error("El costo no puede ser negativo");
		}
		
		return total;
	}

	@Override
	public double getTiempoRecorrido() {
		double total = 0;
		for (Atraccion a : itinerario) {
			total += a.getTiempoRecorrido();
		}

		if(total < 0) {
			throw new Error("Tiempo de recorrido no puede ser negativo");
		}
		
		return total;
	}

	public Atraccion[] getItinerario() {
		return this.itinerario;
	}

	@Override
	public void ocuparLugar() {
		for (Atraccion a : itinerario) {
			a.ocuparLugar();
		}
	}

	public LinkedHashSet<String> getAtraccionesIncluidas() {
		for (Atraccion a : itinerario) {
			atraccionesIncluidas.add(a.getNombre());
		}
		return atraccionesIncluidas;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public boolean tieneCupo() {
		boolean valor = true;
		for (Atraccion a : this.getItinerario()) {
			if (!a.tieneCupo()) {
				valor = false;
				break;
			}
		}
		return valor;
	}
	

}
