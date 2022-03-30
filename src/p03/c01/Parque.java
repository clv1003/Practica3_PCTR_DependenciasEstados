package src.p03.c01;

/*
 * Autores: Jonás Martínez Sanllorente, Claudia Landeira Viñuela
 * Clase: Parque
 * Versión: 1.0
 */

import java.util.Enumeration;
import java.util.Hashtable;

public class Parque implements IParque {

	// Declaración de variables
	private int contadorPersonasTotales;
	private Hashtable<String, Integer> contadoresPersonasPuerta;

	// Constructor de la clase Parque
	public Parque() {
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
	}

	// Método para entrar al parque
	@Override
	public synchronized void entrarAlParque(String puerta) {

		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null) {
			contadoresPersonasPuerta.put(puerta, 0);
		}

		// Comprueba si el parque está lleno
		comprobarAntesDeEntrar();

		// Aumentamos el contador total y el individual
		contadorPersonasTotales++;
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta) + 1);

		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Entrada");

		// Realiza todas las comprobaciones del método checkInvariante
		checkInvariante();

		// Permite que se ejecuten los hilos que están en espera
		notifyAll();
	}

	// Método para salir del parque
	@Override
	public synchronized void salirDelParque(String puerta) {

		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null) {
			contadoresPersonasPuerta.put(puerta, 0);
		}

		// Comprueba si el parque está vacío
		comprobarAntesDeSalir();

		// Aumentamos el contador total y el individual
		contadorPersonasTotales--;
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta) - 1);

		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Salida");

		// Realiza todas las comprobaciones del método checkInvariante
		checkInvariante();

		// Permite que se ejecuten los hilos que están en espera
		notifyAll();
	}

	private void imprimirInfo(String puerta, String movimiento) {
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales); // + " tiempo medio de estancia: " +
																					// tmedio);

		// Iteramos por todas las puertas e imprimimos sus entradas
		for (String p : contadoresPersonasPuerta.keySet()) {
			System.out.println("----> Por puerta " + p + " " + contadoresPersonasPuerta.get(p));
		}
		System.out.println(" ");
	}

	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
		Enumeration<Integer> iterPuertas = contadoresPersonasPuerta.elements();
		while (iterPuertas.hasMoreElements()) {
			sumaContadoresPuerta += iterPuertas.nextElement();
		}
		return sumaContadoresPuerta;
	}

	// Método que realiza una serie de comprobaciones solbre el programa
	protected void checkInvariante() {
		assert sumarContadoresPuerta() == contadorPersonasTotales
				: "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte";
		// Comprobamos que las personas que están en el parque no son un valor negativo
		assert sumarContadoresPuerta() >= 0 : "INV: La suma de contadores de las puertas debe ser un valor positivo";
		// Comprobamos que no se supera el límite de personas en el parque
		assert sumarContadoresPuerta() <= 50
				: "INV: El número de personas que hay en el parque está superando el máximo disponible";

	}

	// Método que comprueba si se puede entrar al parque
	protected void comprobarAntesDeEntrar() {
		// Si el parque está lleno tendra que esperar
		while (contadorPersonasTotales >= 50) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Método que comprueba si se puede salir del parque
	protected void comprobarAntesDeSalir() {
		// Si el parque no tiene nadie dentro tendra que esperar
		while (contadorPersonasTotales <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
