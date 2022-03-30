package src.p03.c01;

/*
 * Autores: Jon�s Mart�nez Sanllorente, Claudia Landeira Vi�uela
 * Interfaz: IParque
 * Versi�n: 1.0
 */

public interface IParque {

	// M�todo abstracto que se usa para entrar al parque
	public abstract void entrarAlParque(String puerta);

	// M�todo abstracto que se usa para salir del parque
	public abstract void salirDelParque(String puerta);

}
