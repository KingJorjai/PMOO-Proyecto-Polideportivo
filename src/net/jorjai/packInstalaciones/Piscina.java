package net.jorjai.packInstalaciones;

import net.jorjai.packUtil.ConsoleColors;

/**
 * Clase que simula una piscina. Tiene como atributos la cantidad de {@code  cloro} de la
 * piscina. Además, por defecto permite practicar los deportes de {@code  natación}, {@code natación
 * sincronizada}, {@code waterpolo} y {@code saltos de trampolín}.
 *
 * @author Jorge Arévalo Fernández
 */
public class Piscina extends Interior {
	/** Cantidad de cloro de la piscina. */
	private double cloro;

	/**
	 * Constructor de la clase Piscina.
	 * 
	 * @param nombre   Nombre de la piscina.
	 * @param longitud Longitud de la piscina.
	 * @param anchura  Anchura de la piscina.
	 * @param cloro    Cantidad de cloro de la piscina.
	 */
	public Piscina(String nombre, int longitud, int anchura, double cloro) {
		super(nombre, longitud, anchura);
		setCloro(cloro);
		addDeporte("Natación");
		addDeporte("Natación sincronizada");
		addDeporte("Waterpolo");
		addDeporte("Saltos de trampolín");

	}

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Piscina p)) return false;
		return super.equals(p) && p.getCloro() == getCloro();
	}

	@Override
	public String toString() {
		return "Piscina#%s#%s#%s#%s".formatted(getNombre(), getLongitud(), getAnchura(), getCloro());
	}

	@Override
	public String inheritancePath() {
		return super.inheritancePath() + " -> Piscina";
	}

	@Override
	public void mostrarInfoDetallada() {
		String formato = "%28s";

		super.mostrarInfoDetallada();
		System.out.println(ConsoleColors.CYAN_BOLD +
				String.format(formato, "Cloración: ") + ConsoleColors.RESET + this.getCloro()
		);
	}

	/**
	 * Devuelve la cantidad de cloro de la piscina.
	 * 
	 * @return Cantidad de cloro de la piscina.
	 */
	public double getCloro() {
		return cloro;
	}

	/**
	 * Establece la cantidad de cloro de la piscina.
	 * @param cloro	Cantidad de cloro de la piscina.
	 */
	public void setCloro(double cloro) {
		this.cloro = cloro;
	}

}
