package src.p03.c01;

/*
 * Autores: Jon�s Mart�nez Sanllorente, Claudia Landeira Vi�uela
 * Clase: ActividadSalidaPuerta
 * Versi�n: 1.0
 */

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActividadSalidaPuerta implements Runnable {

	// Declaraci�n de variables
	private static final int NUMSALIDAS = 20;
	private String puerta;
	private IParque parque;

	// Cnstructor de la clase
	public ActividadSalidaPuerta(String puerta, IParque parque) {
		this.puerta = puerta;
		this.parque = parque;
	}

	// M�todo run
	@Override
	public void run() {
		for (int i = 0; i < NUMSALIDAS; i++) {
			try {
				// Se llama a la funci�n salirDelParque por esa puerta
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
