package trabajoPracticco;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Sistema {

	private ArrayList<Usuario> visitantes;
	private ArrayList<Promocion> promociones;
	private ArrayList<Atraccion> atracciones;
	private ArrayList<Ofertable> ofertasPrioritarias;
	private ArrayList<Ofertable> ofertasSecundarias;
	private ArrayList<Ofertable> ofertasDiaria;
	
	public Sistema() {
		this.visitantes = new ArrayList<Usuario>();
		this.promociones = new ArrayList<Promocion>();
		this.atracciones = new ArrayList<Atraccion>();
		this.ofertasPrioritarias = new ArrayList<Ofertable>();
		this.ofertasSecundarias = new ArrayList<Ofertable>();
		this.ofertasDiaria = new ArrayList<Ofertable>();
	}
	

	public  ArrayList<Usuario> getVisitantes(){
		return this.visitantes;
	}
	
	public  ArrayList<Atraccion> getAtracciones(){
		return this.atracciones;
	}
	
	public  ArrayList<Promocion> getPromociones(){
		return this.promociones;
	}
	
	public  ArrayList<Ofertable> getOfertasDiaria(){
		return this.ofertasDiaria; 
	}
	
	private void agregarVisitantes(Usuario visitantes) {
		this.visitantes.add(visitantes);
	}

	private void agregarAtracciones(Atraccion atraccion) {
		this.atracciones.add(atraccion);
	}


	private void agregarPromoPorcentual(String nombre, Atraccion[] paquete, String tipo, int descuento) {
		this.promociones.add(new PromoPorcentual(nombre, paquete, tipo, descuento));
	}

	private void agregarPromoAbsoluta(String nombre, Atraccion[] paquete, String tipo, int costo) {
		this.promociones.add(new PromoAbsoluta(nombre, paquete, tipo, costo));
	}

	private void agregarPromoAxB(String nombre, Atraccion[] paquete, String tipo) {
		this.promociones.add(new PromoAxB(nombre, paquete, tipo));
	}
	
	
	private void setOfertas(Usuario visitante) {

		this.promociones.sort(new ComparaTor());
		this.atracciones.sort(new ComparaTor());

		this.ofertasPrioritarias.clear();
		this.ofertasSecundarias.clear();
		this.ofertasDiaria.clear();

		for (Promocion promocion : promociones) {
			if (visitante.estaAtraccionMegusta(promocion)) {
				this.ofertasPrioritarias.add(promocion);
			} else {
				this.ofertasSecundarias.add(promocion);
			}
		}

		for (Atraccion atraccion : atracciones) {
			if (visitante.estaAtraccionMegusta(atraccion)) {
				this.ofertasPrioritarias.add(atraccion);
			} else {
				this.ofertasSecundarias.add(atraccion);
			}
		}

		for (Ofertable producto : ofertasPrioritarias) {
			if ((!ofertasDiaria.contains(producto) && producto.tieneCupo())) {
				this.ofertasDiaria.add(producto);
			}
		}
		for (Ofertable producto : ofertasSecundarias) {
			if ((!ofertasDiaria.contains(producto) && producto.tieneCupo())) {
				this.ofertasDiaria.add(producto);
			}
		}

	}
	
	
	private int cantidadDeAOfertas(Usuario visitante) {
		int cantidad = 0;

		for (Ofertable producto : ofertasDiaria) {
			
			
			if (!(visitante.getDineroDisponible() < producto.getCosto()
					|| visitante.getTiempoDisponible() < producto.getTiempo() || (!producto.tieneCupo())
					|| visitante.getproductosComprados().contains(producto))) {

				cantidad++;
			}
		}

		for (Ofertable producto : ofertasDiaria) {
			if (visitante.atraccionIncluidaEnPromocionComprada(producto)) {
				cantidad--;
			}
		}

		return cantidad;
	}	

	
	public void hacerOfertas() {
		
		Scanner escaner = new Scanner(System.in);
		int respuesta = 0;
		
		for (Usuario visitante : visitantes) {
			this.setOfertas(visitante);
			System.out.println("                  **************************************************");
			System.out.println("                     Hola " + visitante.getNombre() + " Estas son tus sugerencias diarias ");
			System.out.println("                  **************************************************");
			
			while (cantidadDeAOfertas(visitante) > 0) {

				for (Ofertable producto : this.ofertasDiaria) {

					this.cantidadDeAOfertas(visitante);
					

					if (       visitante.getDineroDisponible() < producto.getCosto()
							|| visitante.getTiempoDisponible() < producto.getTiempo()
							|| visitante.atraccionIncluidaEnPromocionComprada(producto) || (!producto.tieneCupo())
							|| visitante.getproductosComprados().contains(producto)) {

						continue;
					}

					System.out.println("\n "+visitante.getNombre() + " queres comprar" + producto+"\n");
					System.out.print(" 1 para comprar 2 para seguir>>");
					respuesta = escaner.nextInt();

					if (respuesta == 1) {
						visitante.comprarProducto(producto);
					}
				}

			}
			System.out.println("\n" + visitante.getNombre() + " terminste de comprar gasto: " +getGastoTotal(visitante)
			+ " recorrio: " + getTiempoTotalARecorrer(visitante));
			imprimirFactura(visitante);
		}	
		escaner.close();
	}
	

/////////////////////calcular total a pagar para usuario y tiempo del recorrido//////////////////
	
	private double getGastoTotal(Usuario visitante) {
		double total = 0;

		for (Ofertable producto : visitante.getproductosComprados()) {
			total += producto.getCosto();
		}
		return total;
	}

	private double getTiempoTotalARecorrer(Usuario visitante) {
		double total = 0;

		for (Ofertable producto : visitante.getproductosComprados()) {
			total += producto.getTiempo();
		}
		return total;

	}
	
////////////////////// Crear facturas para visitantes/////////////////////////////////////////	

	private void imprimirFactura(Usuario visitante) {	

		try{
			PrintWriter writer = new PrintWriter("Factura" + visitante.getNombre() + ".txt", "UTF-8");
			
			writer.println("\n"
					+ "      *                                     *\r\n"
					+ "{o)xxx|===============-    *    -===============|xxx(o}\r\n"
					+ "      *                                     *");
			writer.println(" \n            Nombre visitante: " + visitante.getNombre());
			writer.println("            monto a pagar: $" + getGastoTotal(visitante));
			writer.println("            Duracion del reccorido: " + getTiempoTotalARecorrer(visitante) + "Hs");
			writer.println("\n"
					+ "      *                                     *\r\n"
					+ "{o)xxx|===============-    *    -===============|xxx(o}\r\n"
					+ "      *                                     *");
			
			
			
			writer.println("\n                DETALLES DE LA COMPRA\n");
			
			writer.println(visitante.getproductosComprados());	
			writer.println("\n"
					+ "      *                                     *\r\n"
					+ "{o)xxx|===============-    *    -===============|xxx(o}\r\n"
					+ "      *                                     *");
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		System.out.println("\n                ------------------------------------------------------------");
		System.out.println("                  Gracias por tu compra "+visitante.getNombre()+" Disfruta el viaje!!!");
		System.out.println("               ------------------------------------------------------------\n\n\n");

	}
	
////////////////////////////Carga de archivos //////////////////////////////////////////

	public void cargarUsuarios(String archivo) {

		Scanner sc = null;
		int dinero = 0;
		double tiempo = 0;
		String nombre = "";
		String tipo = "";

		try {
			sc = new Scanner(new File(archivo));

			while (sc.hasNext()) {

				String linea = sc.nextLine();
				String datos[] = linea.split(",");

				try {

					nombre = datos[0];
					dinero = Integer.parseInt(datos[1]);
					tiempo = Double.parseDouble(datos[2]);
					tipo = datos[3];
					this.agregarVisitantes(new Usuario(nombre, dinero, tiempo, tipo));
				} catch (NumberFormatException nfe) {
					System.err.println(
							"Falla en la carga de usuario " + "[" + datos[0] + "]" + "Uno de los argumentos no es un valor numerico");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
			System.out.println("** Visitantes cargados **");
		}

	}

	public void cargarAtracciones(String archivo) {

		Scanner sc = null;
		String nombre = "";
		double costo = 0;
		double tiempo = 0;
		int cupo = 0;
		String tipo ="";
		
		try {
			sc = new Scanner(new File(archivo));

			while (sc.hasNext()) {

				String linea = sc.nextLine();
				String datos[] = linea.split(",");
				try {
					nombre = datos[0];
					costo = Double.parseDouble(datos[1]);
					tiempo = Double.parseDouble(datos[2]);
					cupo = Integer.parseInt(datos[3]);
					tipo = datos[4];
					
					this.agregarAtracciones(new Atraccion(nombre, costo, tiempo, cupo, tipo));
					
				}catch (NumberFormatException nfe) {
					System.err.println(
							"Falla en la carga de la atraccion "+nombre+ "Uno de los argumentos no es un valor numerico");
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
			System.out.println("** Atracciones cargadas **");
		}
	}
	
	public void cargarPromociones(String archivo) {
		Scanner sc = null;
		String tipoDePromo ="";
		String nombre ="";
		int costo = 0;
		String tipo = "";
		int primeraAtraccion = 0;
		int segundaAtraccion = 0;
		int terceraAtraccion = 0;

		try {
			sc = new Scanner(new File(archivo));

			while (sc.hasNext()) {

				String linea = sc.nextLine();
				String datos[] = linea.split(",");
				try {
					tipoDePromo = datos[0];
					nombre = datos[1];
					costo = Integer.parseInt(datos[2]);
					tipo = datos[3];
					primeraAtraccion = Integer.parseInt(datos[4]);
					segundaAtraccion = Integer.parseInt(datos[5]);
					terceraAtraccion = Integer.parseInt(datos[6]);
			
					
					if (tipoDePromo.equals("porcentual")) {
						agregarPromoPorcentual(nombre,new Atraccion[]{atracciones.get(primeraAtraccion),atracciones.get(segundaAtraccion),atracciones.get(terceraAtraccion)},tipo, costo);
					}
					if (tipoDePromo.equals("absoluta")) {
						agregarPromoAbsoluta(nombre,new Atraccion[]{atracciones.get(primeraAtraccion),atracciones.get(segundaAtraccion),atracciones.get(terceraAtraccion)}, tipo, costo);
					}
					if (tipoDePromo.equals("axb")) {	
						agregarPromoAxB(nombre,new Atraccion[]{atracciones.get(primeraAtraccion),atracciones.get(segundaAtraccion),atracciones.get(terceraAtraccion)}, tipo);	
					}	
					
					
				
				}catch (NumberFormatException nfe) {
					System.err.println(
							"Falla en la carga de promocion" + "[" + datos[1] + "]" + "Uno de los argumentos no es un valor numerico");
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
			System.out.println("** Promociones cargadas **");
		}
	}
}
