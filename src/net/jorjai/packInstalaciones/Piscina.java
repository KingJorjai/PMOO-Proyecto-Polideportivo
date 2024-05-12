package net.jorjai.packInstalaciones;

/**
 * Clase que simula una piscina.
 */
public class Piscina extends Interior {
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
		this.cloro = cloro;
	}

	@Override
	public String inheritancePath() {
		return super.inheritancePath() + " -> Piscina";
	}

	@Override
	public void printInheritancePath() {
		System.out.println(inheritancePath());
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
