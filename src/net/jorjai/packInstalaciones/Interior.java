package net.jorjai.packInstalaciones;

import net.jorjai.packUtil.ConsoleColors;

/**
 * Clase que simula una instalación deportiva interior.
 * Almacena información sobre la longitud y la anchura de la instalación.
 * <p> Por defecto, el precio de alquiler de una instalación interior siempre es de 40€.
 *
 * @author Jorge Arévalo Fernández
 */
public abstract class Interior extends Instalacion {

	/**
	 * La longitud de la instalación.
	 */
	private int longitud;

	/**
	 * La anchura de la instalación.
	 */
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
		setLongitud(longitud);
		setAnchura(anchura);
	}


	@Override
	final public double precioAlquiler() {
		return 40;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Interior i)) return false;
		return super.equals(i) && i.getLongitud() == getLongitud() && i.getAnchura() == getAnchura();
	}

	@Override
	public String inheritancePath() {
		return super.inheritancePath() + " -> Interior";
	}

	@Override
	public void mostrarInfoDetallada() {
		String formato = "%28s";

		super.mostrarInfoDetallada();
		System.out.println(ConsoleColors.CYAN_BOLD +
				String.format(formato, "Dimensiones: ") + ConsoleColors.RESET + this.getLongitud() + "x" + this.getAnchura()
		);
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
	 * Establece la longitud de la instalación.
	 *
	 * @param longitud Longitud de la instalación.
	 */
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	/**
	 * Devuelve la anchura de la instalación.
	 *
	 * @return Anchura de la instalación.
	 */
	public int getAnchura() {
		return anchura;
	}

	/**
	 * Establece la anchura de la instalación.
	 * @param anchura Anchura de la instalación.
	 */
	public void setAnchura(int anchura) {
		this.anchura = anchura;
	}

}
