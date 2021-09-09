package trabajoPracticco;

import java.util.ArrayList;

public class Usuario {

	private String nombre;
	private double dineroDisponible;
	private double tiempoDisponible;
	private String tipoPreferido;

	private ArrayList<Ofertable> productosComprados;

	public Usuario(String nombre, int presupuesto, double tiempoDisponible, String tipoPreferido) {

		if (presupuesto < 0 || tiempoDisponible < 0) {
			throw new Error("Los valores ingresados no puede ser negativo");
		}

		this.nombre = nombre;
		this.dineroDisponible = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoPreferido = tipoPreferido;
		this.productosComprados = new ArrayList<Ofertable>();

	}

	public void gastarDinero(double gasto) {
		if (getDineroDisponible() < gasto) {
			throw new Error("Dinero insuficiente");
		}
		this.dineroDisponible -= gasto;
	}

	private void restarTiempo(double tiempoUsado) {

		if (getTiempoDisponible() < tiempoUsado) {
			throw new Error("Tiempo insuficiente");
		}
		this.tiempoDisponible -= tiempoUsado;
	}

	public void comprarProducto(Ofertable producto) {

		this.productosComprados.add(producto);
		this.gastarDinero(producto.getCosto());
		this.restarTiempo(producto.getTiempoRecorrido());
		producto.ocuparLugar();
	}

	public boolean atraccionIncluidaEnPromocionComprada(Ofertable atraccion) {
		boolean valor = false;
		for (Ofertable producto : productosComprados) {
			if (producto instanceof Promocion) {
				for (Ofertable prodInterno : ((Promocion) producto).getItinerario()) {
					valor = prodInterno.equals(atraccion) || valor;
				}
			}
		}
		return valor;
	}

	public boolean estaAtraccionMegusta(Ofertable producto) {
		return this.tipoPreferido.equals(producto.getTipo());
	}

	public ArrayList<Ofertable> getproductosComprados() {
		return this.productosComprados;
	}

	public double getTiempoDisponible() {

		return this.tiempoDisponible;
	}

	public String getTipoPreferido() {
		return this.tipoPreferido;
	}

	public double getDineroDisponible() {
		return this.dineroDisponible;
	}

	public String getNombre() {
		return this.nombre;
	}

	@Override
	public String toString() {
		return "Nombre de usuario:" + nombre + ", dineroDisponible:" + dineroDisponible + ", tiempoDisponible:"
				+ tiempoDisponible + ", tipoPreferido:" + tipoPreferido;
	}
}
