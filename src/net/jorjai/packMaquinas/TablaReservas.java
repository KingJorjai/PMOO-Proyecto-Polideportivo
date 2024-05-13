package net.jorjai.packMaquinas;

import net.jorjai.packInfo.ReservaException;

/** Clase que simula una tabla de reservas de una máquina. */
public class TablaReservas {

	private boolean[] reservas;

	/**
	 * Constructor sin parámetros: inicializa el Array de reservas de la máquina,
	 * marcando todas sus posiciones como libres (NO reservadas).
	 */
	public TablaReservas() {
		this.reservas = new boolean[24];
		for (int i = 0; i < reservas.length; i++) {
			reservas[i] = false;
		}
	}

	/**
	 * Dado un número, reserva la máquina para la hora correspondiente a dicho
	 * número. Devuelve el valor true si se ha podido realizar la reserva
	 * correctamente.
	 * 
	 * @param hora Número de la hora a reservar.
	 * @return true si se ha podido realizar la reserva correctamente.
	 * @throws IllegalArgumentException Si la hora no está comprendida entre 0 y 23, ambas incluidas.
	 * @throws ReservaException Si la máquina ya está reservada a la hora indicada.
	 */
	public boolean reservar (int hora) throws IllegalArgumentException, ReservaException {
        if (estaLibre(hora)) {
            reservas[hora] = true;
            return true;
        }
        else {
            throw new ReservaException("La máquina ya está reservada a las " + hora + ":00 horas.");
        }
    }

	/**
	 * Devuelve la cantidad de horas en las que la máquina está reservada.
	 * 
	 * @return Cantidad de horas en las que la máquina está reservada.
	 */
	public int numeroReservas() {
		int contador = 0;
		for (int i = 0; i < reservas.length; i++) {
			if (reservas[i]) {
				contador++;
			}
		}
		return contador;
	}

	/**
	 * Devuelve la primera hora a la que la máquina está libre. Si está reservada a
	 * todas las horas devolverá -1.
	 * 
	 * @return Primera hora a la que la máquina está libre o -1 si no hay ninguna.
	 */
	public int primeraLibre() {
		for (int i = 0; i < reservas.length; i++) {
			if (!reservas[i]) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Dado un número entero, devuelve si la máquina está libre a la hora
	 * correspondiente al número dado.
	 * 
	 * @param hora Número de la hora a comprobar.
	 * @return true si la máquina está libre a la hora correspondiente al número
	 *         dado.
	 * @throws IllegalArgumentException Si la hora no está comprendida entre 0 y 23, ambas incluidas.
	 */
	public boolean estaLibre(int hora) throws IllegalArgumentException {
		if (hora >= 0 && hora < 24) {
			return !reservas[hora];
		}
		else {
			throw new IllegalArgumentException("La hora debe estar comprendida entre 0 y 23, ambas incluidas.");
		}
	}

	/**
	 * Libera todas las horas de reserva.
	 */
	public void descargar() {
		for (int i = 0; i < reservas.length; i++) {
			reservas[i] = false;
		}
	}

	@Override
	public String toString() {
		String resultado = "[";
		for (int i = 0; i < reservas.length - 1; i++) {
			resultado += reservas[i] + ", ";
		}
		resultado += reservas[reservas.length - 1] + "]";
		return resultado;
	}
}
