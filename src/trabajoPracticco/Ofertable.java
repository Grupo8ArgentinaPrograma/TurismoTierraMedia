package trabajoPracticco;

public abstract class Ofertable {

	private String nombre;
	private String tipo;
	private double costo;
	private double tiempo;

	public Ofertable(String nombre, String tipo, double costo, double tiempo) {
		this(nombre, tipo);
		this.costo = costo;
		this.tiempo = tiempo;

		if (costo < 0 || tiempo < 0) {
			throw new Error("Los valores ingresados no puede ser negativo");
		}
	}

	public Ofertable(String nombre, String tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
	}

	abstract protected void ocuparLugar();

	abstract public boolean tieneCupo();

	protected double getTiempo() {
		return this.tiempo;
	}

	protected double getCosto() {
		return this.costo;
	}

	protected double getTiempoRecorrido() {
		return this.tiempo;
	}

	protected String getTipo() {
		return this.tipo;
	}

	protected String getNombre() {
		return this.nombre;
	}

	protected void setCosto(double costo) {
		if( costo < 0  ) {
			throw new Error("El costo no puede ser negativo");
		}
		this.costo = costo;
	}

	protected void setTiempo(double tiempo) {

		this.tiempo = tiempo;
	}

	@Override
	public String toString() {
		return "\n Nombre: " + nombre + " -- " + " Tipo: " + tipo + " -- " + " Costo: $" + costo + " -- " + " Tiempo:"
				+ tiempo + " Hs" + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ofertable other = (Ofertable) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

}
