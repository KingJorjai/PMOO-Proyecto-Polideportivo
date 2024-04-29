package net.jorjai.packInstalaciones;

/* Clase que simula una instalación deportiva al aire libre. */
public abstract class Exterior extends Instalacion {
	private int anioConstruccion;

	/**
	 * Constructor de la clase Exterior
	 * 
	 * @param nombre           Nombre de la instalación.
	 * @param anioConstruccion Año de construcción de la instalación.
	 */
	public Exterior(String nombre, int anioConstruccion) {
		super(nombre);
		this.anioConstruccion = anioConstruccion;
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
	public String inheritancePath() {
		return "Instalacion#Exterior";
	}

	@Override
	public void printInheritancePath() {
		System.out.println(inheritancePath());
	}

	/**
     * Actualiza los horarios de apertura y cierre de la instalación.
     * 
     * @param horaApertura Hora de apertura de la instalación.
     * @param horaCierre   Hora de cierre de la instalación.
     */
	public void actualizarHorarios(int horaApertura, int horaCierre) {
		if (horaCierre > horaApertura) {
			setHoraApertura(horaApertura);
			setHoraCierre(horaCierre);
		}
	}
}
