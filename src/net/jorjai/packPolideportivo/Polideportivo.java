package net.jorjai.packPolideportivo;

import net.jorjai.packUtil.ConsoleColors;
import net.jorjai.packUtil.Util;
import net.jorjai.packInfo.ReservaException;
import net.jorjai.packInstalaciones.*;
import net.jorjai.packMaquinas.MaquinaFitness;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


/**
 * Clase que simula un polideportivo.
 *
 * @author Jorge Arévalo Fernández
 */
public class Polideportivo {

	/**
	 * Instancia única de la clase Polideportivo.
	 */
	private static Polideportivo instance = null;

	/**
	 * Nombre del Polideportivo.
	 */
	private String nombre;

	/**
	 * Lista de Instalaciones del Polideportivo.
	 */
	private ArrayList<Instalacion> listaInstalaciones;

	/**
	 * Lista de Máquinas del Polideportivo.
	 */
	private ArrayList<MaquinaFitness> listaMaquinas;

	/**
	 * Constructor privado de la clase Polideportivo.
	 */
	private Polideportivo() {
		listaInstalaciones = new ArrayList<>();
		listaMaquinas = new ArrayList<>();
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
	 * @throws IllegalArgumentException Si la máquina ya está registrada.
	 */
	public void registrarMaquina(MaquinaFitness maquina) throws IllegalArgumentException {
		if (!listaMaquinas.contains(maquina)) listaMaquinas.add(maquina);
		else throw new IllegalArgumentException("La máquina" + maquina + "ya está registrada.");
	}

	/**
	 * Dado un número, devuelve un ArrayList con los nombres de las máquinas que tengan un número de reservas mayor que el dado.
	 *
	 * @param numero Número de reservas a superar.
	 * @return ArrayList con los nombres de las máquinas que tengan un número de reservas mayor que el dado.
	 */
	public ArrayList<String> masReservas(int numero) {
		ArrayList<String> resultado = new ArrayList<>();
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
		boolean maquinaEncontrada=false;
		for (MaquinaFitness maquina : listaMaquinas) {
			if (maquina.getTipo().equalsIgnoreCase(tipo)) {
				maquinaEncontrada=true;
				if (maquina.estaLibre(hora)) {
					return maquina;
				}
			}
		}
		if (!maquinaEncontrada) {
			throw new ReservaException("No existe ninguna máquina de tipo " + tipo.toUpperCase() + ".");
		} else {
			throw new ReservaException("No hay ninguna máquina de tipo " + tipo.toUpperCase() +
					" libre a las " + String.format("%02d",hora) + ":00 horas.");
		}
	}

	/**
	 * Dado un tipo de máquina y una hora, reserva la primera máquina del tipo indicado que esté libre a la hora indicada.
	 * Si no hay ninguna máquina del tipo deseado libre a la hora indicada, muestra un mensaje informativo de esta situación y no hace ninguna reserva.
	 *
	 * @param tipo Tipo de máquina.
	 * @param hora Hora a la que reservar la máquina.
	 * @throws IllegalArgumentException Si la hora no está comprendida entre 0 y 23, ambas incluidas.
	 * @throws ReservaException Si no se ha podido realizar la reserva.
	 */
	public void reservarMaquina(String tipo, int hora) throws RuntimeException, ReservaException{
		try{
			MaquinaFitness maquina = getMaquinaLibre(tipo, hora);
			maquina.reservar(hora);
		} catch (ReservaException e) {
			throw new ReservaException(e.getMessage());
		}
	}

	/**
	 * Muestra en pantalla el nombre y la hora de apertura de todas las Instalaciones que estén almacenadas.
	 */
	public void mostrarInstalaciones() {
		for (Instalacion instalacion : listaInstalaciones) {
			System.out.println(
					ConsoleColors.CYAN_BOLD_BRIGHT + " - " + ConsoleColors.RESET +
					instalacion.getNombre() +
					" (" + instalacion.getClass().getSimpleName() +
					(instalacion instanceof Acondicionable ? ConsoleColors.CYAN_BRIGHT + "*" + ConsoleColors.RESET : "") + ", " +
					String.format("%02d",instalacion.getHoraApertura()) + ":00-" +
					String.format("%02d",instalacion.getHoraCierre()) + ":00)"
			);
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
			System.out.println(
					ConsoleColors.CYAN_BOLD_BRIGHT + " - " + ConsoleColors.RESET +
					maquina.getNombre() + " (" + maquina.getTipo() + ", " + maquina.getAnios() + " años)"
			);
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
	 * Lee las instalaciones de un archivo y las añade al Polideportivo.
	 * @throws FileNotFoundException Si no se encuentra el archivo.
	 * @throws RuntimeException Si hay algún error en el formato del archivo.
	 */
	public void leerMaquinasDeArchivo() throws FileNotFoundException {
		JFrame jf=new JFrame();
		jf.setAlwaysOnTop(true);
		String rutaArchivo = JOptionPane.showInputDialog
				(jf,"Introduce la ruta del archivo de máquinas:","data/default/maquinasFitness.txt");
		if (rutaArchivo == null) {
			throw new RuntimeException("No se ha introducido ninguna ruta.");
        }
		File archivo = new File(rutaArchivo);
		try {
			cargarMaquinas(archivo);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
    }

	/**
	 * Lee las instalaciones de un archivo y las añade al Polideportivo.
	 * @throws FileNotFoundException Si no se encuentra el archivo.
	 * @throws RuntimeException Si hay algún error en el formato del archivo.
	 */
	public void leerInstalacionesDeArchivo() throws FileNotFoundException {
		JFrame jf=new JFrame();
		jf.setAlwaysOnTop(true);
		String rutaArchivo = JOptionPane.showInputDialog
				(jf,"Introduce la ruta del archivo de instalaciones:","data/default/instalaciones.txt");
		if (rutaArchivo == null) {
			throw new RuntimeException("No se ha introducido ninguna ruta.");
		}
		File archivo = new File(rutaArchivo);
		try {
			cargarInstalaciones(archivo);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Carga las máquinas de un archivo y las añade al Polideportivo.
	 * @param archivo Archivo con las máquinas a cargar.
	 * @throws RuntimeException Si hay algún error en el formato del archivo.
	 * @throws FileNotFoundException Si no se encuentra el archivo.
	 */
	public void cargarMaquinas(File archivo) throws RuntimeException, FileNotFoundException {
		try {
			Scanner scanner = new Scanner(archivo);
			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				if (!linea.startsWith("//") && !linea.isBlank()) {	// Ignorar líneas en blanco o comentarios
					String[] args = linea.split("#");
					MaquinaFitness maquina = instanciarMaquina(args);
					registrarMaquina(maquina);
				}
			}
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("No se ha encontrado el archivo.");
		}
	}

	/**
	 * Instancia una máquina a partir de un array de Strings.
	 * @param args Array de Strings con los datos de la máquina.
	 * @return Máquina instanciada.
	 * @throws RuntimeException Si hay algún error en el formato del archivo.
	 */
	private static MaquinaFitness instanciarMaquina(String[] args) throws RuntimeException {
		MaquinaFitness maquina;	// Nombre y años de antigüedad
		try {
			maquina = new MaquinaFitness(args[0], Integer.parseInt(args[2]));
			maquina.setTipo(args[1]);	// Tipo de máquina
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new RuntimeException("Error en el formato del archivo.");
		} catch (NumberFormatException e) {
			throw new RuntimeException("Error en el formato de los años de antigüedad.");
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e.getMessage());
		}
		return maquina;
	}

	/**
	 * Lee las instalaciones de un archivo y las añade al Polideportivo.
	 *
	 * @param archivo Archivo con las instalaciones a cargar.
	 * @throws RuntimeException      Si hay algún error en el formato del archivo.
	 * @throws FileNotFoundException Si no se encuentra el archivo.
	 */
	public void cargarInstalaciones(File archivo) throws RuntimeException, FileNotFoundException {
        Scanner scanner;
		try {
			scanner = new Scanner(archivo);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("No se ha encontrado el archivo.");
		}
		Instalacion instalacion;
		while (scanner.hasNextLine()) {
			String linea = scanner.nextLine();
			if (!linea.startsWith("//") && !linea.isBlank()) {    // Ignorar líneas en blanco o comentarios
				String[] args = linea.split("#");
                try {
                    instalacion = (Instalacion) Util.instanciarClase(
                            args, "net.jorjai.packInstalaciones");
					registrarInstalacion(instalacion);
				} catch (Exception e) {
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
                }

            }
		}
	}

	/**
	 * Guarda los elementos de una lista en un archivo.
	 * El archivo se guardará en la carpeta {@code data/yyyy-mm-dd} con el nombre introducido por el usuario.
	 * El nombre por defecto será el nombre de la clase de los elementos de la lista seguido de la hora actual.
	 * Si se introduce un nombre no válido, se guardará con el nombre por defecto.
	 * @throws RuntimeException Si el archivo no se guarda por cualquier motivo.
	 * @param list Lista de elementos a guardar.
	 * @param <E> Tipo de elementos de la lista.
	 * @param prefix Prefijo del nombre del archivo.
	 */
	public <E> void guardarArchivo(ArrayList<E> list, String prefix) throws RuntimeException {
		final String defaultName = prefix + " " +
				LocalTime.now().format(DateTimeFormatter.ofPattern("HH.mm")) + ".txt";
		// Pedir datos
		JFrame jf=new JFrame();
		jf.setAlwaysOnTop(true);
		String archivo = JOptionPane.showInputDialog
				(jf,"Introduce el nombre del archivo a guardar:",
				 defaultName);

		// Comprobación de la ruta introducida
		if (archivo == null) {
			throw new RuntimeException("No se ha introducido ninguna ruta.");
		} else {
			// Eliminar todos los carácteres no admitidos para nombres de archivos
			archivo = archivo.replaceAll("[~\"#%&*:<>?/\\\\{|}]+", "");
			// Añadir extensión .txt si no la tiene
			if (archivo.isEmpty()){
				archivo = defaultName;
			}
			if (!archivo.endsWith(".txt")) {
				archivo = archivo + ".txt";
			}
		}

		// Crear directorio
		LocalDate fecha = LocalDate.now();
		File ruta = new File("data/" + fecha.format(DateTimeFormatter.ISO_LOCAL_DATE));
		boolean creado = ruta.mkdir();

		// Crear archivo
		ruta = new File(ruta +"/"+archivo);
		try {
			boolean yaExiste = !ruta.createNewFile();
			if (yaExiste) {
				int opcion = JOptionPane.showOptionDialog(
						jf, "El archivo ya existe. ¿Quieres sobreescribirlo?", "Atención",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
				if (opcion == JOptionPane.YES_OPTION) {
					if (ruta.delete()) {
						writeArrayList(list, ruta);
					} else {
						throw new RuntimeException("Error al sobreescribir el archivo.");
					}
				} else {
					throw new RuntimeException("No se ha guardado el archivo.");
				}
            } else {
				writeArrayList(list, ruta);
			}
		} catch (IOException e) {
			throw new RuntimeException("Ocurrió un error inesperado.");
        }
    }

	/**
	 * Escribe un ArrayList en un archivo.
	 * @param list Lista a escribir.
	 * @param archivo Archivo en el que escribir.
	 * @param <E> Tipo de elementos de la lista.
	 * @throws RuntimeException Si hay algún error al escribir en el archivo.
	 */
	private <E> void writeArrayList(ArrayList<E> list, File archivo) {
		try (FileWriter fw = new FileWriter(archivo, false);
			 BufferedWriter bw = new BufferedWriter(fw);
			 PrintWriter out = new PrintWriter(bw))
		{
			for (E elemento : list) {
				out.println(elemento);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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

	/**
	 * Devuelve el nombre del Polideportivo.
	 * @return Nombre del Polideportivo.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del Polideportivo.
	 * @param nombre Nombre del Polideportivo.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Borra una instalación de la lista de instalaciones.
	 * @param instalacion Instalación a borrar.
	 * @throws InterruptedException Si se cancela el borrado de la instalación.
	 */
	public void borrarInstalacion(Instalacion instalacion) throws InterruptedException {
		// Diálogo de confirmación
		JFrame jf=new JFrame();
		jf.setAlwaysOnTop(true);
		int opcion = JOptionPane.showOptionDialog(
				jf, "¿Estás seguro de que quieres borrar la instalación " + instalacion.getNombre() + "?",
				"Confirmación de borrado", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, JOptionPane.NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			listaInstalaciones.remove(instalacion);	// Borrar instalación
		} else
			throw new InterruptedException("Se ha cancelado el borrado de la instalación.");
	}

	/**
	 * Borra una máquina de la lista de máquinas.
	 * @param maquinaFitness Máquina a borrar.
	 * @throws InterruptedException Si se cancela el borrado de la máquina.
	 */
	public void borrarMaquina(MaquinaFitness maquinaFitness) throws InterruptedException {
		// Diálogo de confirmación
		JFrame jf=new JFrame();
		jf.setAlwaysOnTop(true);
		int opcion = JOptionPane.showOptionDialog(
				jf, "¿Estás seguro de que quieres borrar la máquina " + maquinaFitness.getNombre() + "?",
				"Confirmación de borrado", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, JOptionPane.NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			listaMaquinas.remove(maquinaFitness);	// Borrar máquina
		} else {
			throw new InterruptedException("Se ha cancelado el borrado de la máquina.");
		}
	}

	/**
	 * Ordena ascendentemente según su orden natural (por nombre) las instalaciones
	 * del Polideportivo.
	 */
	public void ordenarInstalaciones() {
		listaInstalaciones.sort(null);
	}

	/**
	 * Guarda las instalaciones en un archivo.
	 * @throws RuntimeException Si hay algún error al guardar las instalaciones.
	 */
	public void guardarInstalaciones() throws RuntimeException {
		guardarArchivo(getListaInstalaciones(), "Instalaciones");
	}

	/**
	 * Guarda las máquinas en un archivo.
	 * @throws RuntimeException Si hay algún error al guardar las máquinas.
	 */
	public void guardarMaquinas() throws RuntimeException {
		guardarArchivo(getListaMaquinas(),"MaquinasFitness");
	}

	/**
	 * Muestra toda la información de todas las instalaciones del Polideportivo.
	 */
	public void mostrarTodaLaInfoInstalaciones() {
		for (Instalacion instalacion : listaInstalaciones) {
			System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + instalacion.getNombre() +
					(instalacion instanceof Acondicionable ? ConsoleColors.YELLOW_BOLD_BRIGHT + "*" + ConsoleColors.CYAN_BOLD_BRIGHT : "") + ":" + ConsoleColors.RESET);
			instalacion.mostrarInfoDetallada();
			System.out.println();
		}
	}
}
