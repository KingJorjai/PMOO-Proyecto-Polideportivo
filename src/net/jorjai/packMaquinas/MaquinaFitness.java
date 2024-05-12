package net.jorjai.packMaquinas;

/** Clase que simula una máquina de fitness. */
public class MaquinaFitness implements Comparable<MaquinaFitness> {

	private String nombre;
    private String tipo;
    private int anios;
    private TablaReservas tablaReservas;

    /**
     * Constructor de la clase MaquinaFitness.
     * @param nombre Nombre de la máquina.
     */
    public MaquinaFitness(String nombre) {
        this.nombre = nombre;
        this.anios = 0;
        this.tablaReservas = new TablaReservas();
    }

	/**
	 * Constructor de la clase MaquinaFitness
	 * 
	 * @param nombre Nombre de la máquina.
	 * @param anios  Años de antigüedad de la máquina.
	 */
    public MaquinaFitness(String nombre, int anios) {
        this.nombre = nombre;
        setAnios(anios);
        this.tablaReservas = new TablaReservas();
    }

	/**
	 * Dada una hora, indica si la máquina está libre a esa hora.
	 * 
	 * @param hora Hora a comprobar.
	 * @return true si la máquina está libre a esa hora.
	 */
    public boolean estaLibre(int hora) {
        return tablaReservas.estaLibre(hora);
    }

	/**
	 * Dada una hora, si la máquina está libre a dicha hora, la reserva.
	 * 
	 * @param hora Hora a reservar.
	 * @return true si se ha podido realizar la reserva correctamente.
	 */
    public boolean reservar(int hora) {
        return tablaReservas.reservar(hora);
    }

    public void setAnios(int anios) {
        if (anios >= 0 && anios <= 20) {
            this.anios = anios;
        }
    }

    /**
     * Dos máquinas son iguales si su nombre es igual.
     * @param maquina Máquina con la que comparar.
     * @return true si las máquinas son iguales.
     */
    public boolean equals(MaquinaFitness maquina) {
        return this.nombre.equals(maquina.nombre);
    }

    @Override
    public String toString() {
        return nombre + "#" + tipo + "#" + anios;
    }

	/**
	 * Devuelve el nombre de la máquina.
	 * 
	 * @return Nombre de la máquina.
	 */
    public String getNombre() {
        return nombre;
    }

	/**
	 * Establece el nombre de la máquina.
	 * 
	 * @param nombre Nombre de la máquina.
	 */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	/**
	 * Devuelve el tipo de la máquina.
	 * 
	 * @return Tipo de la máquina.
	 */
    public String getTipo() {
        return tipo;
    }

	/**
	 * Establece el tipo de la máquina.
	 * 
	 * @param tipo Tipo de la máquina.
	 */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

	/**
	 * Devuelve los años de antigüedad de la máquina.
	 * 
	 * @return Años de antigüedad de la máquina.
	 */
    public int getAnios() {
        return anios;
    }

    /**
     * Devuelve la tabla de reservas de la máquina.
     * @return Tabla de reservas de la máquina.
     */
    public TablaReservas getTablaReservas() {
        return tablaReservas;
    }

	/**
	 * Establece la tabla de reservas de la máquina.
	 * 
	 * @param tablaReservas Tabla de reservas de la máquina.
	 */
    public void setTablaReservas(TablaReservas tablaReservas) {
        this.tablaReservas = tablaReservas;
    }

    @Override
    public int compareTo(MaquinaFitness o) {
        return this.nombre.compareTo(o.nombre);
    }
}
