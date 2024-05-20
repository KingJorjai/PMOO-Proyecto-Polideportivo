package net.jorjai.packMaquinas;

import net.jorjai.packInfo.ReservaException;
import net.jorjai.packUtil.ConsoleColors;

/**
 * Clase que simula una máquina de fitness.
 * <p> Almacena información sobre el nombre, el tipo y los años de antigüedad de la máquina.
 * Además, almacena una tabla de reservas de la máquina.
 *
 * @author Jorge Arévalo Fernández
 */
public class MaquinaFitness implements Comparable<MaquinaFitness> {

    /** Nombre de la máquina. */
	private String nombre;
    /** Tipo de la máquina. */
    private String tipo;
    /** Años de antigüedad de la máquina. */
    private int anios;
    /** Tabla de reservas de la máquina. */
    private TablaReservas tablaReservas;

    /**
     * Constructor de la clase MaquinaFitness.
     * Por defecto, la máquina tiene {@code 0} años de antigüedad.
     * @param nombre Nombre de la máquina.
     */
    public MaquinaFitness(String nombre) {
        this(nombre, 0);
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
     * @throws IllegalArgumentException Si la hora no está comprendida entre 0 y 23, ambas incluidas.
	 */
    public boolean estaLibre(int hora) throws IllegalArgumentException {
        return tablaReservas.estaLibre(hora);
    }

	/**
	 * Dada una hora, si la máquina está libre a dicha hora, la reserva.
	 *
	 * @param hora Hora a reservar.
	 * @return true si se ha podido realizar la reserva correctamente.
     * @throws IllegalArgumentException Si la hora no está comprendida entre 0 y 23, ambas incluidas.
     * @throws ReservaException Si la máquina ya está reservada a la hora indicada.
	 */
    public boolean reservar(int hora) throws IllegalArgumentException, ReservaException {
        return tablaReservas.reservar(hora);
    }

    /**
     * Establece los años de antigüedad de la máquina.
     * @param anios Años de antigüedad de la máquina.
     * @throws IllegalArgumentException Si los años de antigüedad no están entre 0 y 20, ambos incluidos.
     */
    public void setAnios(int anios) throws IllegalArgumentException {
        if (anios >= 0 && anios <= 20) {
            this.anios = anios;
        }
        else {
            throw new IllegalArgumentException("Los años de antigüedad de la máquina deben estar entre 0 y 20.");
        }
    }

    /**
     * Dos máquinas son iguales si su nombre es igual.
     * @param maquina Máquina con la que comparar.
     * @return true si las máquinas son iguales.
     */
    public boolean equals(MaquinaFitness maquina) {
        if (maquina == null)
            return false;
        if (this == maquina)
            return true;
        return this.nombre.equals(maquina.nombre);
    }

    @Override
    public String toString() {
        return nombre + "#" + tipo + "#" + anios;
    }

    /**
     * Muestra toda la información disponible de la máquina.
     */
    public void mostrarInfoDetallada(){
        System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + String.format("%10s","Nombre: ") + ConsoleColors.RESET + nombre);
        System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + String.format("%10s","Tipo: ") + ConsoleColors.RESET + tipo);
        System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + String.format("%10s","Años: ") + ConsoleColors.RESET + anios);
        System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + String.format("%10s","Reservas: ") + ConsoleColors.RESET);
        mostrarTablaReservas();
    }

	/**
	 * Muestra la tabla de reservas de la máquina.
	 */
    public void mostrarTablaReservas(){
        tablaReservas.mostrarTabla(3);
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

    /**
     * Borra una reserva de la máquina.
     * @param hora Número de la hora a borrar.
     * @throws IllegalArgumentException Si la hora no está comprendida entre 0 y 23, ambas incluidas.
     * @throws ReservaException Si no hay ninguna reserva a la hora indicada.
     */
    public void borrarReserva(int hora) throws IllegalArgumentException, ReservaException{
        try {
            tablaReservas.borrarReserva(hora);
        } catch (ReservaException e) {
            throw new ReservaException("La máquina " + nombre + " no estaba reservada a las " +
                    String.format("%02d",hora) + ":00.");
        }
    }
}
