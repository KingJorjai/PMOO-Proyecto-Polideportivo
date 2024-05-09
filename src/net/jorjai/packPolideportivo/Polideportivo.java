package net.jorjai.packPolideportivo;

/**
 * Clase que simula un polideportivo.
 */
public class Polideportivo {

	private static Polideportivo instance = null;
	
	private Polideportivo() {
		
	}
	
	/**
	 * Devuelve la instancia de la clase Polideportivo.
	 * 
	 * @return Instancia de la clase Polideportivo.
	 */
	public static Polideportivo getInstance() {
		if (instance == null) {
			instance = new Polideportivo();
		}
		return instance;
	}
}
