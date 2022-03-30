package src.p03.c01;

/*
 * Autores: Jonás Martínez Sanllorente, Claudia Landeira Viñuela
 * Clase: ActividadSalidaPuerta
 * Versión: 1.0
 */

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActividadSalidaPuerta implements Runnable {

	// Declaración de variables
	private static final int NUMSALIDAS = 20;
	private String puerta;
	private IParque parque;

	// Cnstructor de la clase
	public ActividadSalidaPuerta(String puerta, IParque parque) {
		this.puerta = puerta;
		this.parque = parque;
	}

	// Método run
	@Override
	public void run() {
		for (int i = 0; i < NUMSALIDAS; i++) {
			try {
				// Se llama a la función salirDelParque por esa puerta
				parque.salirDelParque(puerta);
				// Se esera un tiempo random mediante un sleep
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5) * 1000);
			} catch (InterruptedException e) {
				Logger.getGlobal().log(Level.INFO, "Salida interrumpida");
				Logger.getGlobal().log(Level.INFO, e.toString());
				return;
			}
		}
	}

}
