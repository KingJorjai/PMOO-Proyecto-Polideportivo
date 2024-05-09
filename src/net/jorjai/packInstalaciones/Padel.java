package net.jorjai.packInstalaciones;

/**
 * Clase que simula una pista de Padel.
 */
public class Padel extends Interior implements Acondicionable {
	private String colorParedes;
	private double precioAcondicionamientoUnitario;

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

	public double getPrecioAcondicionamiento() {
		return getAnchura() * precioAcondicionamientoUnitario;
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

	/**
	 * Devuelve el precio del acondicionamiento unitario de la pista de Padel.
	 * @return	Precio del acondicionamiento unitario de la pista de Padel.
	 */
	public double getPrecioAcondicionamientoUnitario() {
		return precioAcondicionamientoUnitario;
	}

	/**
	 * Establece el precio del acondicionamiento unitario de la pista de Padel.
	 * 
	 * @param precioAcondicionamientoUnitario Precio del acondicionamiento unitario
	 *                                        de la pista de Padel.
	 */
	public void setPrecioAcondicionamientoUnitario(double precioAcondicionamientoUnitario) {
		this.precioAcondicionamientoUnitario = precioAcondicionamientoUnitario;
	}

	/**
	 * Establece el color de las paredes de la pista de Padel.
	 * 
	 * @param colorParedes Color de las paredes de la pista de Padel.
	 */
	public void setColorParedes(String colorParedes) {
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

}
