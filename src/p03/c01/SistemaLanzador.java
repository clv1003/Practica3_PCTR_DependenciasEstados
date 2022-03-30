package src.p03.c01;

/*
 * Autores: Jon�s Mart�nez Sanllorente, Claudia Landeira Vi�uela
 * Clase: SistemaLanzador
 * Versi�n: 1.0
 */

public class SistemaLanzador {
	public static void main(String[] args) {

		// Creaci�n del parque
		IParque parque = new Parque();
		char letra_puerta = 'A';

		System.out.println("�Parque abierto!");

		for (int i = 0; i < Integer.parseInt(args[0]); i++) {

			String puerta = "" + ((char) (letra_puerta++));

			// Creacion de hilos de entrada
			ActividadEntradaPuerta entradas = new ActividadEntradaPuerta(puerta, parque);
			new Thread(entradas).start();

			// Creacion de hilos de salida
			ActividadSalidaPuerta salidas = new ActividadSalidaPuerta(puerta, parque);
			new Thread(salidas).start();

		}
	}
}