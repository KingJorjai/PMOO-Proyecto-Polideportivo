package net.jorjai.packInstalaciones;

import net.jorjai.packUtil.ConsoleColors;

/**
 * Clase que simula una pista de Padel. Tiene como atributo el color de las
 * paredes de la pista. Por defecto, permite practicar los deportes de {@code Padel
 * dobles}, {@code Padel individual} y {@code Padel cruzado}.
 *
 * @author Jorge Arévalo Fernández
 */
public class Padel extends Interior implements Acondicionable {
	/** Color de las paredes de la pista de Padel. */
	private String colorParedes;
	/** Precio del acondicionamiento unitario de la pista de Padel. */
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
		setColorParedes(colorParedes);
		addDeporte("Padel dobles");
		addDeporte("Padel individual");
		addDeporte("Padel cruzado");
		
	}

	public double getPrecioAcondicionamiento() {
		return getAnchura() * precioAcondicionamientoUnitario;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Padel p)) return false;
		return super.equals(p) && p.getColorParedes().equals(getColorParedes());
	}

	@Override
	public String toString() {
		return "Padel#%s#%s#%s#%s".formatted(getNombre(), getLongitud(), getAnchura(), getColorParedes());
	}

    @Override
	public String inheritancePath() {
		return super.inheritancePath() + " -> Padel";
	}

	@Override
	public void mostrarInfoDetallada() {
		String formato = "%28s";

		super.mostrarInfoDetallada();
		System.out.println(ConsoleColors.CYAN_BOLD +
				String.format(formato, "Color de las paredes: ") + ConsoleColors.RESET + this.getColorParedes()
		);
	}

	/**
	 * Devuelve el precio del acondicionamiento unitario de la pista de Padel.
	 * @return	Precio del acondicionamiento unitario de la pista de Padel.
	 */
	public double getPrecioAcondicionamientoUnitario() {
		return precioAcondicionamientoUnitario;
	}

	@Override
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
