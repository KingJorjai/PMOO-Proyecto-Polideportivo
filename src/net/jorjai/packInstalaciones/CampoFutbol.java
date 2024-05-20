package net.jorjai.packInstalaciones;

import net.jorjai.packUtil.ConsoleColors;

import java.time.Year;

/**
 * Clase que simula un campo de fútbol. Almacena información sobre si tiene
 * gradas o no.
 *
 * @author Jorge Arévalo Fernández
 */
public class CampoFutbol extends Exterior {
	/**
	 * Indica si el campo de fútbol tiene gradas.
     */
	private boolean gradas;

	/**
	 * Constructor de la clase CampoFutbol
	 * 
	 * @param nombre           Nombre del campo de fútbol.
	 * @param anioConstruccion Año de construcción del campo de fútbol.
	 * @param gradas           Indica si el campo de fútbol tiene gradas.
	 */
	public CampoFutbol(String nombre, int anioConstruccion, boolean gradas) {
		super(nombre, anioConstruccion);
		setGradas(gradas);
	}

	@Override
	public String toString() {
		return "CampoFutbol#%s#%s#%s".formatted(getNombre(), getAnioConstruccion(), hasGradas());
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof CampoFutbol c)) return false;
		return super.equals(c) && c.hasGradas() == hasGradas();
	}

	@Override
	public String inheritancePath() {
		return super.inheritancePath() + " -> CampoFutbol";
	}

	@Override
	public void mostrarInfoDetallada() {
		String formato = "%28s";

		super.mostrarInfoDetallada();
		System.out.println(ConsoleColors.CYAN_BOLD +
				String.format(formato, "Gradas: ") + ConsoleColors.RESET + (this.hasGradas() ? "Sí" : "No")
		);
	}

	/**
	 * Devuelve si el campo de fútbol tiene gradas.
	 * 
	 * @return true si el campo de fútbol tiene gradas, false en caso contrario.
	 */
	public boolean hasGradas() {
		return gradas;
	}

	/**
	 * Establece si el campo de fútbol tiene gradas.
	 * 
	 * @param gradas true si el campo de fútbol tiene gradas, false en caso
	 *               contrario.
	 */
	public void setGradas(boolean gradas) {
		this.gradas = gradas;
	}

	@Override
	public double precioAlquiler() {
		return (40 - (Year.now().getValue() - getAnioConstruccion()))*3;
	}

}
