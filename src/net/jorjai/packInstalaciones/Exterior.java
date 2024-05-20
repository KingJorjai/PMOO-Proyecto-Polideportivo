package net.jorjai.packInstalaciones;

import net.jorjai.packUtil.ConsoleColors;

/**
 * Clase que simula una instalación deportiva al aire libre.
 * Almacena información sobre el año de construcción de la instalación.
 *
 * @author Jorge Arévalo Fernández
 */
public abstract class Exterior extends Instalacion {
	/** Año de construcción de la instalación. */
	private int anioConstruccion;

	/**
	 * Constructor de la clase Exterior
	 * 
	 * @param nombre           Nombre de la instalación.
	 * @param anioConstruccion Año de construcción de la instalación.
	 */
	public Exterior(String nombre, int anioConstruccion) {
		super(nombre);
		setAnioConstruccion(anioConstruccion);
	}

	/**
	 * Devuelve el año de construcción de la instalación.
	 * 
	 * @return Año de construcción de la instalación.
	 */
	public int getAnioConstruccion() {
		return anioConstruccion;
	}

	/**
	 * Establece el año de construcción de la instalación.
	 * 
	 * @param anioConstruccion Año de construcción de la instalación.
	 */
	public void setAnioConstruccion(int anioConstruccion) {
		this.anioConstruccion = anioConstruccion;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Exterior e)) return false;
		return super.equals(e) && e.getAnioConstruccion() == getAnioConstruccion();
	}

	@Override
	public String toString() {
		return "Exterior#%s#%s".formatted(getNombre(), getAnioConstruccion());
	}

	@Override
	public String inheritancePath() {
		return super.inheritancePath()+ " -> Exterior";
	}

	@Override
	public void mostrarInfoDetallada(){
		String formato = "%28s";
		super.mostrarInfoDetallada();
		System.out.println(ConsoleColors.CYAN_BOLD +
				String.format(formato, "Año construcción: ") + ConsoleColors.RESET + this.getAnioConstruccion()
		);
	}
}
