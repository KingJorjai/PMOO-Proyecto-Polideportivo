package net.jorjai.packPolideportivo;

import net.jorjai.packInfo.ReservaException;
import net.jorjai.packInstalaciones.Acondicionable;
import net.jorjai.packInstalaciones.Fronton;
import net.jorjai.packInstalaciones.Instalacion;
import net.jorjai.packMaquinas.MaquinaFitness;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Clase que simula un polideportivo.
 */
public class Polideportivo {

	private static Polideportivo instance = null;
	private ArrayList<Instalacion> listaInstalaciones;
	private ArrayList<MaquinaFitness> listaMaquinas;
	
	private Polideportivo() {
		listaInstalaciones = new ArrayList<Instalacion>();
		listaMaquinas = new ArrayList<MaquinaFitness>();
	}
	
	/**
	 * Devuelve la instancia de la clase Polideportivo.
	 * 
	 * @return Instancia de la clase Polideportivo.
	 */
	public static Polideportivo getInstance() {
		if (instance == null) instance = new Polideportivo();
		return instance;
	}

	/**
	 * Registra una instalación en el polideportivo.
	 *
	 * @param instalacion Instalación a registrar.
	 */
	public void registrarInstalacion(Instalacion instalacion) {
		listaInstalaciones.add(instalacion);
	}

	/**
	 * Registra una máquina en el polideportivo.
	 *
	 * @param maquina Máquina a registrar.
	 */
	public void registrarMaquina(MaquinaFitness maquina) {
		if (!listaMaquinas.contains(maquina)) listaMaquinas.add(maquina);
	}

	/**
	 * Dado un número, devuelve un ArrayList con los nombres de las máquinas que tengan un número de reservas mayor que el dado.
	 *
	 * @param numero Número de reservas a superar.
	 * @return ArrayList con los nombres de las máquinas que tengan un número de reservas mayor que el dado.
	 */
	public ArrayList<String> masReservas(int numero) {
		ArrayList<String> resultado = new ArrayList<String>();
		for (MaquinaFitness maquina : listaMaquinas) {
			if (maquina.getTablaReservas().numeroReservas() > numero) {
				resultado.add(maquina.getNombre());
			}
		}
		return resultado;
	}

	/**
	 * Dado un tipo de máquina y una hora, devuelve la primera máquina del tipo dado, disponible a la hora indicada.
	 * Si no hay ninguna máquina del tipo pedido que esté libre a la hora indicada, devolverá null.
	 *
	 * @param tipo Tipo de máquina.
	 * @param hora Hora a la que debe estar libre la máquina.
	 * @return Primera máquina del tipo dado, disponible a la hora indicada o null si no hay ninguna.
	 * @throws IllegalArgumentException Si la hora no está comprendida entre 0 y 23, ambas incluidas.
	 * @throws ReservaException Si la máquina ya está reservada a la hora indicada.
	 */
	public MaquinaFitness getMaquinaLibre(String tipo, int hora) throws IllegalArgumentException, ReservaException {

		for (MaquinaFitness maquina : listaMaquinas) {
			if (maquina.getTipo().equals(tipo) && maquina.estaLibre(hora)) {
				return maquina;
			}
		}
		throw new ReservaException("No hay máquinas de tipo " + tipo + " libres a las " + hora + ":00 horas.");
	}

	/**
	 * Dado un tipo de máquina y una hora, reserva la primera máquina del tipo indicado que esté libre a la hora indicada.
	 * Si no hay ninguna máquina del tipo deseado libre a la hora indicada, muestra un mensaje informativo de esta situación y no hace ninguna reserva.
	 *
	 * @param tipo Tipo de máquina.
	 * @param hora Hora a la que reservar la máquina.
	 * @return true si se ha podido realizar la reserva correctamente.
	 * @throws IllegalArgumentException Si la hora no está comprendida entre 0 y 23, ambas incluidas.
	 * @throws ReservaException Si la máquina ya está reservada a la hora indicada.
	 */
	public boolean reservarMaquina(String tipo, int hora) throws IllegalArgumentException, ReservaException {
		MaquinaFitness maquina = getMaquinaLibre(tipo, hora);
		if (maquina == null) {
			System.out.println("No hay máquinas de tipo " + tipo + " libres a la hora " + hora);
			return false;
		} else {
			return maquina.reservar(hora);
		}
	}

	/**
	 * Muestra en pantalla el nombre y la hora de apertura de todas las Instalaciones que estén almacenadas.
	 */
	public void mostrarInstalaciones() {
		for (Instalacion instalacion : listaInstalaciones) {
			System.out.println(instalacion.getNombre() + " - Abre a las " + instalacion.getHoraApertura());
		}
	}

	/**
	 * Ordena ascendentemente según su orden natural (por nombre) las máquinas del Polideportivo.
	 */
	public void ordenarMaquinas() {
		listaMaquinas.sort(null);
	}

	/**
	 * Muestra en pantalla el nombre y si son cubiertos (o no) de los 3 primeros Frontones del Polideportivo.
	 */
	public void mostrar3Frontones() {
		int contador = 0;
		Iterator<Instalacion> iterator = listaInstalaciones.iterator();

		while (iterator.hasNext() && contador < 3) {
			Instalacion instalacion = iterator.next();
			if (instalacion instanceof Fronton fronton) {
                System.out.println(fronton.getNombre() + " - " + (fronton.isCubierto() ? "Cubierto" : "Descubierto"));
				contador++;
			}
		}
	}

	/**
	 * Devuelve el número de Instalaciones del Polideportivo que se pueden acondicionar.
	 *
	 * @return Número de Instalaciones del Polideportivo que se pueden acondicionar.
	 */
	public int cuantasAcondicionar() {
		int contador = 0;
		for (Instalacion instalacion : listaInstalaciones) {
			if (instalacion instanceof Acondicionable) {
				contador++;
			}
		}
		return contador;
	}

	/**
	 * Muestra la información de todas las MaquinaFitness que hay en el Polideportivo.
	 */
	public void mostrarMaquinas() {
		for (MaquinaFitness maquina : listaMaquinas) {
			System.out.println(maquina);
		}
	}

	/**
	 * Dada un número, elimina las máquinas cuyo número de años es igual al dado.
	 * Devuelve el número de máquinas eliminadas.
	 *
	 * @param anios Número de años a comparar.
	 * @return Número de máquinas eliminadas.
	 */
	public int borrarMaquinas(int anios) {
		Iterator<MaquinaFitness> iterator = listaMaquinas.iterator();
		int contador = 0;
		while (iterator.hasNext()) {
			MaquinaFitness maquina = iterator.next();
			if (maquina.getAnios() == anios) {
				iterator.remove();
				contador++;
			}
		}
		return contador;
	}

	/**
	 * Devuelve la lista de Instalaciones del Polideportivo.
	 * @return Lista de Instalaciones del Polideportivo.
	 */
	public ArrayList<Instalacion> getListaInstalaciones() {
		return listaInstalaciones;
	}

	/**
	 * Establece la lista de Instalaciones del Polideportivo.
	 * @param listaInstalaciones Lista de Instalaciones del Polideportivo.
	 */
	public void setListaInstalaciones(ArrayList<Instalacion> listaInstalaciones) {
		this.listaInstalaciones = listaInstalaciones;
	}

	/**
	 * Devuelve la lista de Máquinas del Polideportivo.
	 * @return Lista de Máquinas del Polideportivo.
	 */
	public ArrayList<MaquinaFitness> getListaMaquinas() {
		return listaMaquinas;
	}

	/**
	 * Establece la lista de Máquinas del Polideportivo.
	 * @param listaMaquinas Lista de Máquinas del Polideportivo.
	 */
	public void setListaMaquinas(ArrayList<MaquinaFitness> listaMaquinas) {
		this.listaMaquinas = listaMaquinas;
	}

}
