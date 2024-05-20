package net.jorjai.packInstalaciones;

import net.jorjai.packUtil.ConsoleColors;

/**
 * Clase que simula un frontón. Almacena información sobre si es cubierto o no.
 * Además, almacena los metros cuadrados del frontón.
 *
 * @author Jorge Arévalo Fernández
 */
public class Fronton extends Exterior implements Acondicionable {
	/**
	 * Indica si el frontón es cubierto.
	 */
    private boolean cubierto;
    
	/**
	 * Metros cuadrados del frontón.
	 */
    private int metrosCuadrados;
    
    /**
     * Precio del acondicionamiento unitario de la pista de Padel.
     */
    private double precioAcondicionamientoUnitario;

    /**
     * Constructor de la clase Fronton.
     * 
     * @param nombre           Nombre del frontón.
     * @param anioConstruccion Año de construcción del frontón.
     * @param cubierto         Indica si el frontón es cubierto.
     * @param metrosCuadrados  Metros cuadrados del frontón.
     */
    public Fronton(String nombre, int anioConstruccion, boolean cubierto, int metrosCuadrados) {
        super(nombre, anioConstruccion);
        setCubierto(cubierto);
        setMetrosCuadrados(metrosCuadrados);
    }

	/**
	 * Constructor de la clase Fronton. Por defecto, el frontón no está cubierto.
	 * 
	 * @param nombre           Nombre del frontón.
	 * @param anioConstruccion Año de construcción del frontón.
	 * @param metrosCuadrados  Metros cuadrados del frontón.
	 */
	public Fronton(String nombre, int anioConstruccion, int metrosCuadrados) {
		this(nombre, anioConstruccion, false, metrosCuadrados);
	}

	@Override
	public String toString() {
		return "Fronton#%s#%s#%s#%s".formatted(
				getNombre(), getAnioConstruccion(), isCubierto(), getMetrosCuadrados());
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Fronton f)) return false;
        return super.equals(f) && f.isCubierto() == isCubierto() && f.getMetrosCuadrados() == getMetrosCuadrados();
	}

    @Override
    public String inheritancePath() {
        return super.inheritancePath() + " -> Fronton";
    }

    @Override
    public double precioAlquiler() {
        return 2 * metrosCuadrados + (cubierto ? 20 : 0);
    }

    @Override
	public void actualizarHorarios(int horaApertura, int horaCierre) throws IllegalArgumentException {
		if (horaCierre > 22) {
			throw new IllegalArgumentException(
					"Los frontones no pueden cerrar más tarde de las 22:00 horas.");
		} else {
			super.actualizarHorarios(horaApertura, horaCierre);
		}
	}

	@Override
	public void mostrarInfoDetallada() {
		String formato = "%28s";

		super.mostrarInfoDetallada();
		System.out.println(ConsoleColors.CYAN_BOLD +
				String.format(formato, "Cubierto: ") + ConsoleColors.RESET + (this.isCubierto() ? "Sí" : "No")
		);
		System.out.println(ConsoleColors.CYAN_BOLD +
				String.format(formato, "Metros cuadrados: ") + ConsoleColors.RESET + (this.getMetrosCuadrados())
		);
	}

	@Override
	public double getPrecioAcondicionamiento() {
		
		return metrosCuadrados * precioAcondicionamientoUnitario;
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
	 * Devuelve los metros cuadrados del frontón.
	 * 
	 * @return Metros cuadrados del frontón.
	 */
	public int getMetrosCuadrados() {
	    return metrosCuadrados;
	}

	/**
	 * Establece los metros cuadrados del frontón.
	 * 
	 * @param metrosCuadrados Metros cuadrados del frontón.
	 */
	public void setMetrosCuadrados(int metrosCuadrados) {
	    this.metrosCuadrados = metrosCuadrados;
	}

	/**
	 * Devuelve si el frontón es cubierto.
	 * 
	 * @return true si el frontón es cubierto, false en caso contrario.
	 */
	public boolean isCubierto() {
	    return cubierto;
	}

	/**
	 * Establece si el frontón es cubierto.
	 * 
	 * @param cubierto true si el frontón es cubierto, false en caso contrario.
	 */
	public void setCubierto(boolean cubierto) {
	    this.cubierto = cubierto;
	}
}
