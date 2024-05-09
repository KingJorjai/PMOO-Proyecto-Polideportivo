package net.jorjai.packInstalaciones;

/**
 * Clase que simula una instalación deportiva interior.
 */
public abstract class Interior extends Instalacion {

	private int longitud;
	private int anchura;

	/**
	 * Constructor de la clase Interior.
	 * 
	 * @param nombre   Nombre de la instalación.
	 * @param longitud Longitud de la instalación.
	 * @param anchura  Anchura de la instalación.
	 */
	public Interior(String nombre, int longitud, int anchura) {
		super(nombre);
		this.longitud = longitud;
		this.anchura = anchura;
	}

	/**
	 * Devuelve la longitud de la instalación.
	 * 
	 * @return Longitud de la instalación.
	 */
	public int getLongitud() {
		return longitud;
	}

	/**
	 * Devuelve la anchura de la instalación.
	 * 
	 * @return Anchura de la instalación.
	 */
	public int getAnchura() {
		return anchura;
	}

	
	@Override
	public double precioAlquiler() {
		return 40;
	}

	
	@Override
	public String inheritancePath() {
		return super.inheritancePath() + " -> Interior";
	}

	@Override
	public void printInheritancePath() {
		System.out.println(inheritancePath());
	}

}
