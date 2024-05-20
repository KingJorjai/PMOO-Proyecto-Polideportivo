package net.jorjai.packMaquinas;

import net.jorjai.packInfo.ReservaException;
import net.jorjai.packUtil.ConsoleColors;

import java.util.Arrays;

/**
 * Clase que simula una tabla de reservas de una máquina.
 * Almacena información sobre las horas a las que la máquina está reservada.
 *
 * @author Jorge Arévalo Fernández
 */
public class TablaReservas {

	/**
	 * Array de booleanos que indica si la máquina está reservada a una hora
	 * determinada. Cada posición del array corresponde a una hora del día.
	 * Si reservas[i] == true, la máquina está reservada a la hora i.
	 */
	private boolean[] reservas;

	/**
	 * Constructor sin parámetros: inicializa el Array de reservas de la máquina,
	 * marcando todas sus posiciones como libres (NO reservadas).
	 */
	public TablaReservas() {
		this.reservas = new boolean[24];
        Arrays.fill(reservas, false);
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
        for (boolean reserva : reservas) {
            if (reserva) {
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
       Arrays.fill(reservas, false);
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

	/**
	 * Dado un número entero, borra la reserva de la tabla de reservas a la hora correspondiente
	 * @param hora Número de la hora a borrar.
	 * @throws IllegalArgumentException Si la hora no está comprendida entre 0 y 23, ambas incluidas.
	 * @throws ReservaException Si no hay ninguna reserva a la hora indicada.
	 */
    public void borrarReserva(int hora) throws IllegalArgumentException, ReservaException {
        if (hora < 0 || hora >= 24) {
            throw new IllegalArgumentException("La hora debe estar comprendida entre 0 y 23, ambas incluidas.");
        } else {
			if (estaLibre(hora)) {
				throw new ReservaException("No hay ninguna reserva a las " + String.format("%02d",hora) + ":00 horas.");
			} else {
				reservas[hora] = false;
			}
		}
    }

    /**
     * Devuelve el array de reservas de la máquina.
     * @return Array de reservas de la máquina.
     */
	public boolean[] getReservas() {
		return reservas;
	}

	/**
	 * Muestra, separadas en tres filas, las horas de la tabla de reservas de la máquina y si están
	 * reservadas o no. Las horas se muestran en formato de 24 horas. Si la máquina está reservada
	 * a una hora, se mostrará un Libre verde si no está reservada y un Ocupado rojo si lo está.
	 * 
	 * @param nFilas Número de filas en las que se mostrará la tabla.
	 */
	public void mostrarTabla(int nFilas) {
		int iPorFila = 24/nFilas;

		System.out.println(" ┌" + "─────────┬".repeat(iPorFila-1) + "─────────┐ ");
		for (int i = 0; i < nFilas; i++) {
			System.out.print(" │ ");
			for (int j = i * iPorFila; j < iPorFila + iPorFila * i; j++) {
				System.out.printf(" %02d:00 ", j);
				System.out.print(" │ ");
			}
			System.out.println();

			System.out.print(" │ ");
			for (int j = i * iPorFila; j < iPorFila + iPorFila * i; j++) {
				String estadoReserva = reservas[j] ? "Ocupado" : " Libre ";
				estadoReserva = estadoReserva.replaceAll(
						"Libre|Ocupado", (reservas[j] ? ConsoleColors.RED : ConsoleColors.GREEN) + "$0" + ConsoleColors.RESET);
				System.out.print(estadoReserva);
				System.out.print(" │ ");
			}
			System.out.println();
			if (i != nFilas-1) {
				System.out.println(" ├" + "─────────┼".repeat(iPorFila-1) + "─────────┤ ");
			}
		}
		System.out.println(" └" + "─────────┴".repeat(iPorFila-1) + "─────────┘ ");
	}
}
