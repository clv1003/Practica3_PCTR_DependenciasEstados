package src.p03.c01;

/*
 * Autores: Jonás Martínez Sanllorente, Claudia Landeira Viñuela
 * Interfaz: IParque
 * Versión: 1.0
 */

public interface IParque {

	// Método abstracto que se usa para entrar al parque
	public abstract void entrarAlParque(String puerta);

	// Método abstracto que se usa para salir del parque
	public abstract void salirDelParque(String puerta);

}
