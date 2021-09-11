package trabajoPracticco;


import java.util.Scanner;

public class AppTierraMedia {

	public static void main(String[] args) {

		Sistema sistema = new Sistema();
		Scanner escaner = new Scanner(System.in);
		String nombreArchivo;

		System.out.println("" 
				+ "\n"
				+ "                                 /\\\r\n"
				+ "                  _/\\           /  \\\r\n"
				+ "              _  /   \\         /    \\/\\\r\n"
				+ "             / \\/   _ \\     /\\/\\  _  _/\\\r\n"
				+ "            /   \\_ / \\/\\_/\\/_/  \\/ \\/   \\\r\n"
				+ "           /\\/\\   \\_   /   \\/            \\\r\n"
				+ "          /    \\___/\\ /     \\             \\\r\n"
				+ "                     \\       \\             \\\r\n"
				+ "                     .-\"---.  \\             \\\r\n"
				+ "          __..---.. /       \\  \\             \\\r\n"
				+ "                   /\\___.-'./\\''--..____..--''\r\n"
				+ "          `-.      \\/ O) (O \\/ ''--.._\r\n"
				+ "              __    |  (_)  |         _.-'-._\r\n"
				+ "             / /  __/\\\\___//\\__ ..--''-._\r\n"
				+ "             | (_/\\ \\/`---'\\/ /\\         `-._\r\n"
				+ "          _.-\\ \\/  \\  \\   /  /  \\.-'-._\r\n"
				+ "             /\\|   /  -| |-  \\   \\     `-._\r\n"
				+ "            | ||  /\\  -| |-  /\\   \\        `-.\r\n"
				+ "             \\/|_/ |  -|_\\-  |/   /\r\n"
				+ "             \\ \\   /  /B_B\\  \\\\  /\r\n"
				+ "             / (   \\_/  _  \\_/ \\/\r\n"
				+ "          .__\\ \\   /    |    \\_/\r\n"
				+ "             ) /''-| __ | __ |\r\n"
				+ "             |(    \\    |    /---...___\r\n"
				+ "             /|    /____|____\\         '-._\r\n"
				+ "             ||     |   ||   |\r\n"
				+ "             \\\\     ///\\\\//\\\\\\\r\n"
				+ "              \\|   oOO(_)(_)OOo"
				+ "");

		System.out.println("   ******************************************************");
		System.out.println("      “Es peligroso, Frodo, cruzar tu puerta.\n"
				+ "     Pones un pie en el camino, y si no cuidas tus pasos,\n"
				+ "     nunca sabes a dónde te puede llevar.”" );	

		System.out.println("   ******************************************************");
		System.out.println("\nBienvenido a la Tierra Media!!!");
		System.out.println("\nPara comenzar por favor realice los siguientes pasos:");

		System.out.print("1) Introduzca el nombre del archivo que contiene las atracciones >>>");
		nombreArchivo=escaner.next();
	    sistema.cargarAtracciones(nombreArchivo);
	//	sistema.cargarAtracciones("atracciones.txt");
		System.out.println();
		System.out.print("2) Introduzca el nombre del archivo que contiene las promociones del dia >>>");
		nombreArchivo=escaner.next();
	//	sistema.cargarPromociones("promociones.txt");
	    sistema.cargarPromociones(nombreArchivo);
		System.out.println();
		
		

		System.out.print("3) Introduzca el nombre del archivo que contiene los usuarios del días >>>");
		nombreArchivo=escaner.next();
	  sistema.cargarUsuarios( nombreArchivo);
	//	sistema.cargarUsuarios("visitantes.txt");
		System.out.println();
		System.out.println();
		
		System.err.println("\n");
		System.out.println("                          ************************************");
		System.out.println("                           Comenzamos con las sugerencias!!!");
		System.out.println("                          ************************************");
		System.out.println("\n\n");
		sistema.hacerOfertas();	

		System.out.println("                     ******************************************");
		System.out.println("                       No hay mas visitantes por el dia de hoy");
		System.out.println("                     ******************************************");
		escaner.close();
	}

}




