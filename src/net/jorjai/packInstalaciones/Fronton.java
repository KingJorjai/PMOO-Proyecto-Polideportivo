package net.jorjai.packInstalaciones;

/**
 * Clase que simula un frontón.
 */
public class Fronton extends Exterior implements Acondicionable {
    private boolean cubierto;
    private int metrosCuadrados;
    private double precioAcondicionamientoUnitario;

    /**
     * Constructor de la clase Fronton
     * 
     * @param nombre           Nombre del frontón.
     * @param anioConstruccion Año de construcción del frontón.
     * @param cubierto         Indica si el frontón es cubierto.
     * @param metrosCuadrados  Metros cuadrados del frontón.
     */
    public Fronton(String nombre, int anioConstruccion, boolean cubierto, int metrosCuadrados) {
        super(nombre, anioConstruccion);
        this.cubierto = cubierto;
        this.metrosCuadrados = metrosCuadrados;
    }

    @Override
    public String inheritancePath() {
        return super.inheritancePath() + "#Fronton";
    }

    @Override
    public void printInheritancePath() {
        System.out.println(inheritancePath());
    }

    @Override
    public double precioAlquiler() {
        return precioAcondicionamientoUnitario * metrosCuadrados + (cubierto ? 20 : 0);
    }

    /**
	 * Actualiza los horarios de apertura y cierre del frontón.
	 *
	 * @param horaApertura Hora de apertura del frontón.
	 * @param horaCierre   Hora de cierre del frontón.
	 */
        public void actualizarHorarios(int horaApertura, int horaCierre) {
        	            if (horaCierre > horaApertura && horaCierre <= 22) {
                setHoraApertura(horaApertura);
                setHoraCierre(horaCierre);
            } else {
                System.out.println("Error: Los horarios no son correctos.");
            }
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
	public boolean getCubierto() {
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
