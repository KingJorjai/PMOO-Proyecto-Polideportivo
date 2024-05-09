package net.jorjai.packInstalaciones;

/**
 * Clase que simula un campo de fútbol.
 */
public class CampoFutbol extends Exterior {
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
		this.gradas = gradas;
	}

	@Override
	public String inheritancePath() {
		return super.inheritancePath() + "#CampoFutbol";
	}

	@Override
	public void printInheritancePath() {
		System.out.println(inheritancePath());
	}

	/**
	 * Devuelve si el campo de fútbol tiene gradas.
	 * 
	 * @return true si el campo de fútbol tiene gradas, false en caso contrario.
	 */
	public boolean getGradas() {
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

}
