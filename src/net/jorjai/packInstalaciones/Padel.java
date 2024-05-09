package net.jorjai.packInstalaciones;

/**
 * Clase que simula una pista de Padel.
 */
public class Padel extends Interior implements Acondicionable {
	private String colorParedes;

	/**
	 * Constructor de la clase Padel.
	 * 
	 * @param nombre       Nombre de la pista de Padel.
	 * @param longitud     Longitud de la pista de Padel.
	 * @param anchura      Anchura de la pista de Padel.
	 * @param colorParedes Color de las paredes de la pista de Padel.
	 */
	public Padel(String nombre, int longitud, int anchura, String colorParedes) {
		super(nombre, longitud, anchura);
		this.colorParedes = colorParedes;
	}

	/**
	 * Devuelve el color de las paredes de la pista de Padel.
	 * 
	 * @return Color de las paredes de la pista de Padel.
	 */
	public String getColorParedes() {
		return colorParedes;
	}

	public double getPrecioAcondicionamiento() {
		return getAnchura() * 10;
	}

	@Override
	public double precioAlquiler() {
		return 40;
	}

	@Override
	public String inheritancePath() {
		return super.inheritancePath() + " -> Padel";
	}

	@Override
	public void printInheritancePath() {
		System.out.println(inheritancePath());
	}

}
