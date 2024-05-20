package net.jorjai.packPolideportivo;

import net.jorjai.packInstalaciones.Acondicionable;
import net.jorjai.packInstalaciones.Instalacion;
import net.jorjai.packMaquinas.MaquinaFitness;
import net.jorjai.packUtil.ConsoleColors;

import javax.management.InstanceNotFoundException;
import javax.swing.*;
import java.io.File;
import java.util.*;

/**
 * Clase que controla el menú del polideportivo.
 * Permite gestionar las instalaciones y las máquinas de fitness del polideportivo.
 *
 * @author Jorge Arévalo Fernández
 */
public class PolideportivoMenu {

    /**
     * Instancia del polideportivo
     */
    private final Polideportivo polideportivo;

    /**
     * Controlador de menús
     */
    private final MenuController menuController;

    /**
     * Scanner para leer de la entrada estándar
     */
    private final Scanner sc;

    /**
     * Método main
     * @param args Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        PolideportivoMenu poliMenu = new PolideportivoMenu();   // Instanciar PolideportivoMenu
        poliMenu.cargarMaquinasInstalacionesInicial();          // Carga de máquinas e instalaciones iniciales
        poliMenu.mostrarTituloGrande();                         // Muestra el título del programa
        poliMenu.menuController.menuPrincipal();                // Llamada al menú
        poliMenu.sc.close();                                    // Cierre del Scanner
    }

    /**
     * Constructor de la clase PolideportivoMenu
     * Inicializa el polideportivo, el scanner y el controlador de menús.
     * Además, carga las máquinas e instalaciones iniciales.
     *
     * @see Polideportivo
     */
    public PolideportivoMenu() {
        this.polideportivo = Polideportivo.getInstance();
        this.sc = new Scanner(System.in);
        this.menuController = new MenuController();
    }

    /**
     * Clase interna MenuController. Controla los menús del programa.
     * @author Jorge Arévalo Fernández
     */
    class MenuController {

        /**
         * Constructor de la clase MenuController
         */
        public MenuController() {}

        /**
    	 * Opciones del menú principal
    	 */
        private final String[] menuPrincipalOptions = {
                "Finalizar",
                "Gestionar instalaciones",
                "Gestionar máquinas",
                "Veo símbolos raros en la consola, ¿qué hago?"
        };
        /**
         * Opciones del menú de Instalaciones
         */
        private final String[] menuInstalacionesOptions = {
                "Volver al menú principal",
                "Mostrar todas las instalaciones",
                "Gestionar una instalación",
                "Acondicionamiento rápido de instalaciones",
                "Borrado rápido de instalaciones",
                "Ordenar instalaciones por nombre",
                "Importar instalaciones",
                "Exportar instalaciones",
                "Mostrar TODA la información de las TODAS las instalaciones (¡puede ser mucha!)"
        };
        /**
         * Opciones del menú específico de una instalación
         */
        private final String[] menuInstalacionOptions = {
                "Volver al menú de instalaciones",
                "Mostrar información detallada",
                "Modificar los horarios de apertura y cierre",
                "Admitir un nuevo deporte",
                "Eliminar un deporte admitido",
                "Establecer deporte actual",
                "Acondicionar esta instalación",
                "Modificar precio de acondicionamiento unitario",
                "Borrar esta instalación"
        };
        /**
         * Opciones del menú de Máquinas
         */
        private final String[] MenuMaquinasOptions = {
                "Volver al menú principal",
                "Mostrar todas las máquinas",
                "Gestionar una máquina",
                "Reserva rápida de máquinas",
                "Borrado rápido de máquinas",
                "Ordenar máquinas por nombre",
                "Importar máquinas",
                "Exportar máquinas",
                "Mostrar máquinas con más de X reservas",
                "Borrar máquinas con más de X años de antigüedad"

        };
        /**
         * Opciones del menú específico de una máquina
         */
        private final String[] menuMaquinaOptions = {
                "Volver al menú de máquinas",
                "Mostrar información detallada",
                "Reservar",
                "Borrar una reserva",
                "Vaciar la tabla de reservas",
                "Borrar esta máquina"
        };

        /**
         * Controla el menú principal.
         */
        public void menuPrincipal(){
            int opcion;
            do {
                escribirMenuPrincipal();
                opcion = leerEntero();
                switch (opcion) {
                    case 0:
                        System.out.println(ConsoleColors.CYAN_BOLD + "Hasta pronto." + ConsoleColors.RESET);
                        break;
                    case 1:
                        menuInstalaciones();
                        break;
                    case 2:
                        menuMaquinas();
                        break;
                    case 3:
                        helpColors();
                        break;
                    default:
                        System.out.println(ConsoleColors.RED_BRIGHT + "✘ Opción no válida." + ConsoleColors.RESET);
                        break;
                }
            } while (opcion != 0);
        }

        /**
         * Controla el menú de Instalaciones.
         */
        private void menuInstalaciones() {
            int opcion;
            do {
                escribirMenuInstalaciones();
                opcion = leerEntero();
                switch (opcion){
                    case 0:
                        System.out.println(ConsoleColors.GREEN_BRIGHT + "✔ Volviendo al menú principal."+ ConsoleColors.RESET);
                        break;
                    case 1:
                        mostrarInstalaciones();
                        break;
                    case 2:
                        try {
                            ArrayList<Instalacion> instalaciones = buscarInstalacion();
                            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "  [0] " + ConsoleColors.CYAN + "Cancelar" + ConsoleColors.RESET);
                            Instalacion instalacion = seleccionarInstalacion(instalaciones);
                            menuInstalacion(instalacion);
                        } catch (Exception e) {
                            System.out.println(ConsoleColors.RED_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
                        }
                        break;
                    case 3:
                        acondicionadoRapidoInstalacion();
                        break;
                    case 4:
                        borrarInstalacion();
                        break;
                    case 5:
                        ordenarInstalaciones();
                        break;
                    case 6:
                        cargarInstalaciones();
                        break;
                    case 7:
                        exportarInstalaciones();
                        break;
                    case 8:
                        mostrarTodaLaInfoInstalaciones();
                        break;
                    default:
                        System.out.println(ConsoleColors.RED_BRIGHT + "✘ Opción no válida." + ConsoleColors.RESET);
                        break;
                }
            } while (opcion != 0);
        }

		/**
		 * Controla el menú de una instalación específica.
		 * @param instalacion Instalación de la que se quiere mostrar el menú.
		 */
        private void menuInstalacion(Instalacion instalacion) {
            int opcion;
            do {
                escribirMenuInstalacion(instalacion);
                opcion = leerEntero();
                switch (opcion) {
                    case 0:
                        System.out.println(ConsoleColors.GREEN_BRIGHT + "✔ Volviendo al menú de instalaciones." + ConsoleColors.RESET);
                        break;
                    case 1:
                        instalacion.mostrarInfoDetallada();
                        break;
                    case 2:
                        try {
                            System.out.println("Introduce la hora de apertura:");
                            int horaApertura = leerEnteroIntermedio(0, 23);
                            System.out.println("Introduce la hora de cierre:");
                            int horaCierre = leerEnteroIntermedio(0, 23);
                            instalacion.actualizarHorarios(horaApertura, horaCierre);
                            System.out.println(ConsoleColors.GREEN_BRIGHT + "✔ Horarios actualizados correctamente." + ConsoleColors.RESET);
                        } catch (Exception e) {
                            System.out.println(ConsoleColors.RED_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
                        }
                        break;
                    case 3:
                        addDeporteToInstalacion(instalacion);
                        break;
                    case 4:
                        eliminarDeporteDeInstalacion(instalacion);
                        break;
                    case 5:
                        modificarDeporteActivoInstalacion(instalacion);
                        break;
                    case 6:
                        if (instalacion instanceof Acondicionable) {
                            try {
                                acondicionarInstalacion(((Acondicionable) instalacion));
                            } catch (InterruptedException e) {
                                System.out.println(ConsoleColors.RED_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
                            }
                        } else {
                            System.out.println(ConsoleColors.RED_BRIGHT + "✘ Esta instalación no se puede acondicionar." + ConsoleColors.RESET);
                        }
                        break;
                    case 7:
                        if (instalacion instanceof Acondicionable) {
                            try {
                                modificarAcondicionamientoUnitario(((Acondicionable) instalacion));
                            } catch (InterruptedException e) {
                                System.out.println(ConsoleColors.RED_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
                            }
                        } else {
                            System.out.println(ConsoleColors.RED_BRIGHT + "✘ Esta instalación no se puede acondicionar." + ConsoleColors.RESET);
                        }
                        break;
                    case 8:
                        borrarInstalacion();
                        break;
                    default:
                        System.out.println(ConsoleColors.RED_BRIGHT + "✘ Opción no válida." + ConsoleColors.RESET);
                        break;
                }
            } while (opcion != 0 && opcion != menuInstalacionOptions.length-1); // Salir si se pulsa 0 o la última opción
        }

        /**
         * Controla el menú de Maquinas.
         */
        private void menuMaquinas() {
            int opcion;
            do {
                escribirMenuMaquinas();
                opcion = leerEntero();
                switch (opcion) {
                    case 0:
                        System.out.println(ConsoleColors.GREEN_BRIGHT + "✔ Volviendo al menú principal." + ConsoleColors.RESET);
                        break;
                    case 1:
                        mostrarMaquinas(polideportivo.getListaMaquinas());
                        break;
                    case 2:
                        try {
                            ArrayList<MaquinaFitness> maquinas = buscarMaquina();
                            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "  [0] " + ConsoleColors.CYAN + "Cancelar" + ConsoleColors.RESET);
                            MaquinaFitness maquina = seleccionarMaquina(maquinas);
                            menuMaquina(maquina);
                        } catch (Exception e) {
                            System.out.println(ConsoleColors.RED_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
                        }
                        break;
                    case 3:
                        reservaRapidaMaquina();
                        break;
                    case 4:
                        borradoRapidoMaquina();
                        break;
                    case 5:
                        ordenarMaquinas();
                        break;
                    case 6:
                        cargarMaquinas();
                        break;
                    case 7:
                        exportarMaquinas();
                        break;
                    case 8:
                        masReservas();
                        break;
                    case 9:
                        borrarMaquinasConMasDeXAnios();
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } while (opcion != 0);
        }

		/**
		 * Controla el menú de una máquina.
		 *
		 * @param maquina Máquina de la que se quiere mostrar el menú.
		 */
        private void menuMaquina(MaquinaFitness maquina) {
            int opcion;
            do {
                escribirMenuMaquina(maquina);
                opcion = leerEntero();
                switch (opcion) {
                    case 0:
                        System.out.println(ConsoleColors.GREEN_BRIGHT + "✔ Volviendo al menú de máquinas." + ConsoleColors.RESET);
                        break;
                    case 1:
                        maquina.mostrarInfoDetallada();
                        break;
                    case 2:
                        reservarMaquina(maquina);
                        break;
                    case 3:
                        borrarReserva(maquina);
                        break;
                    case 4:
                        maquina.getTablaReservas().descargar();
                        System.out.println(ConsoleColors.GREEN_BRIGHT + "✔ Tabla de reservas vaciada correctamente." + ConsoleColors.RESET);
                        break;
                    case 5:
                        borrarMaquina(maquina);
                        break;
                    default:
                        System.out.println(ConsoleColors.RED_BRIGHT + "✘ Opción no válida." + ConsoleColors.RESET);
                        break;
                }
            } while (opcion != 0 && opcion != menuMaquinaOptions.length-1); // Salir si se pulsa 0 o la última opción
        }

        /**
         * Escribe las opciones del menú principal.
         */
        private void escribirMenuPrincipal(){
            System.out.println();
            System.out.printf(
                    "%sMENÚ POLIDEPORTIVO%s:\n%s",
                    ConsoleColors.WHITE_BRIGHT,
                    polideportivo.getNombre() != null ? " "+polideportivo.getNombre().toUpperCase() : "" ,
                    ConsoleColors.RESET
            );
            for (int i = 0; i < menuPrincipalOptions.length; i++) {
                System.out.printf("%s%d. - %s%s\n", ConsoleColors.WHITE_BRIGHT, i, ConsoleColors.CYAN, menuPrincipalOptions[i]);
            }
            System.out.print(ConsoleColors.RESET);
        }

        /**
         * Escribe las opciones del menú de Instalaciones.
         */
        private void escribirMenuInstalaciones() {
            System.out.println();
            System.out.printf(
                    "%sMENÚ INSTALACIONES:\n%s",
                    ConsoleColors.CYAN_BACKGROUND+ ConsoleColors.WHITE_BRIGHT, ConsoleColors.RESET
            );
            for (int i = 0; i < menuInstalacionesOptions.length; i++) {
                System.out.printf("%s%d. - %s%s\n", ConsoleColors.WHITE_BRIGHT, i, ConsoleColors.CYAN, menuInstalacionesOptions[i]);
            }
            System.out.print(ConsoleColors.RESET);
        }

		/**
		 * Muestra las opciones del menú específico de una instalación.
		 * 
		 * @param instalacion Instalación de la que se quiere mostrar el menú.
		 */
        private void escribirMenuInstalacion(Instalacion instalacion) {
            System.out.println();
            System.out.printf(
                    "%sMENÚ INSTALACIÓN%s:\n%s",
                    ConsoleColors.WHITE_BRIGHT,
                    instalacion.getNombre() != null ? " "+instalacion.getNombre().toUpperCase() : "" ,
                    ConsoleColors.RESET
            );
            for (int i = 0; i < menuInstalacionOptions.length; i++) {
                System.out.printf("%s%d. - %s%s\n", ConsoleColors.WHITE_BRIGHT, i, ConsoleColors.CYAN, menuInstalacionOptions[i]);
            }
            System.out.print(ConsoleColors.RESET);
        }

        /**
         * Escribe las opciones del menú de Máquinas.
         */
        private void escribirMenuMaquinas() {
            System.out.println();
            System.out.printf(
                    "%sMENÚ MÁQUINAS:\n%s",
                    ConsoleColors.WHITE_BRIGHT, ConsoleColors.RESET
            );
            for (int i = 0; i < MenuMaquinasOptions.length; i++) {
                System.out.printf("%s%d. - %s%s\n", ConsoleColors.WHITE_BRIGHT, i, ConsoleColors.CYAN, MenuMaquinasOptions[i]);
            }
            System.out.print(ConsoleColors.RESET);
        }

        /**
         * Muestra las opciones del menú específico de una máquina.
         * @param maquina Máquina de la que se quiere mostrar el menú.
         */
        private void escribirMenuMaquina(MaquinaFitness maquina) {
            System.out.println();
            System.out.printf(
                    "%sMENÚ MÁQUINA%s:\n%s",
                    ConsoleColors.WHITE_BRIGHT,
                    maquina.getNombre() != null ? " "+maquina.getNombre().toUpperCase() : "" ,
                    ConsoleColors.RESET
            );
            for (int i = 0; i < menuMaquinaOptions.length; i++) {
                System.out.printf("%s%d. - %s%s\n", ConsoleColors.WHITE_BRIGHT, i, ConsoleColors.CYAN, menuMaquinaOptions[i]);
            }
            System.out.print(ConsoleColors.RESET);
        }
    }

	/**
	 * Pide un deporte y modifica el deporte actual de una instalación.
	 * @param instalacion Instalación a la que se le quiere modificar el deporte actual.
	 */
    private void modificarDeporteActivoInstalacion(Instalacion instalacion) {
        try {
            System.out.println("Introduce el deporte que quieres establecer como actual:");
            String deporte = leerString();
            instalacion.modificarDeporteActual(deporte);
            System.out.println(ConsoleColors.GREEN_BRIGHT + "✔ Deporte actual establecido correctamente." + ConsoleColors.RESET);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
        }
    }

    /**
     * Pregunta al usuario si está seguro de que quiere mostrar
     * toda la información de todas las instalaciones. En caso afirmativo,
     * muestra toda la información de todas las instalaciones.
     */
    private void mostrarTodaLaInfoInstalaciones() {
        // Diálogo de confirmación
        JFrame jf = new JFrame();
        jf.setAlwaysOnTop(true);
        int confirm = JOptionPane.showConfirmDialog(
                jf, "¿Estás seguro de que quieres mostrar TODA la información de TODAS las instalaciones?\n" +
                        "Puede ser mucha.", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            polideportivo.mostrarTodaLaInfoInstalaciones();
        }
    }

	/**
	 * Pide un deporte e intenta eliminarlo de la lista de deportes de una instalación.
	 * @param instalacion Instalación a la que se quiere eliminar el deporte.
	 */
    private void eliminarDeporteDeInstalacion(Instalacion instalacion) {
        try {
            System.out.println("Introduce el deporte que quieres eliminar:");
            String deporte = leerString();
            instalacion.removeDeporte(deporte);
            System.out.println(ConsoleColors.GREEN_BRIGHT + "✔ Deporte eliminado correctamente." + ConsoleColors.RESET);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
        }
    }

    /**
     * Pide al usuario un deporte y lo añade a la instalación si no está ya en la lista y si
     * la lista de deportes no está llena.
     * @param instalacion Instalación a la que se quiere añadir el deporte.
     */
    private void addDeporteToInstalacion(Instalacion instalacion) {
        try {
            System.out.println("Introduce el deporte que quieres añadir:");
            String deporte = leerString();
            instalacion.addDeporte(deporte);
            System.out.println(ConsoleColors.GREEN_BRIGHT + "✔ Deporte añadido correctamente." + ConsoleColors.RESET);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
        }
    }

    /**
     * Muestra un diálogo de confirmación para borrar una máquina.
     * @param maquina Máquina a borrar
     */
    private void borrarMaquina(MaquinaFitness maquina) {
        try {
            polideportivo.borrarMaquina(maquina);
            System.out.println(ConsoleColors.GREEN_BRIGHT + "✔ Máquina borrada correctamente." + ConsoleColors.RESET);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
        }
    }

    /**
     * Pide al usuario la hora de la reserva y la realiza.
     * @param maquina Máquina en la que se quiere realizar la reserva.
     */
    private void reservarMaquina(MaquinaFitness maquina) {
        System.out.println("Introduce la hora de la reserva:");
        int hora = leerEnteroIntermedio(0, 23);
        try {
            maquina.reservar(hora);
            System.out.println(ConsoleColors.GREEN_BRIGHT + "✔ Reserva realizada correctamente." + ConsoleColors.RESET);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
        }
    }

    /**
     * Pide al usuario la hora de la reserva y elimina la reserva.
     * @param maquina Máquina de la que se quiere borrar la reserva.
     */
    private void borrarReserva(MaquinaFitness maquina) {
        System.out.println("Introduce la hora de la reserva:");
        int hora = leerEnteroIntermedio(0, 23);
        try {
            maquina.borrarReserva(hora);
            System.out.println(ConsoleColors.GREEN_BRIGHT + "✔ Reserva borrada correctamente." + ConsoleColors.RESET);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
        }
    }

    /**
     * Muestra las instalaciones del polideportivo.
     */
    private void mostrarInstalaciones() {
        ArrayList<Instalacion> instalaciones = polideportivo.getListaInstalaciones();
        if (instalaciones.isEmpty()) {
            System.out.println(ConsoleColors.RED_BRIGHT + "✘ No hay instalaciones." + ConsoleColors.RESET);
        } else {
            System.out.println(
                    ConsoleColors.GREEN_BRIGHT + "✔ Mostrando " + instalaciones.size() + " instalaciones (* = Acondicionables):" + ConsoleColors.RESET);
            polideportivo.mostrarInstalaciones();
        }
    }

	/**
	 * Muestra cuanto cuesta acondicionar una instalación.
	 */
    private void acondicionadoRapidoInstalacion() {

        ArrayList<Instalacion> instalaciones = polideportivo.getListaInstalaciones();
        instalaciones.removeIf(instalacion -> !(instalacion instanceof Acondicionable));
        try {
            instalaciones = buscarInstalacion(instalaciones);
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "  [0] " + ConsoleColors.CYAN + "Cancelar" + ConsoleColors.RESET);
            Acondicionable instalacion = (Acondicionable) seleccionarInstalacion(instalaciones);
            acondicionarInstalacion(instalacion);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
        }
    }

    /**
     * Muestra el precio del acondicionamiento de una instalación si tiene un precio de acondicionamiento
     * unitario asignado. Si no lo tiene, pide al usuario que lo introduzca.
     * @param instalacion Instalación a acondicionar.
     * @throws InterruptedException Si se cancela la operación.
     */
    private void acondicionarInstalacion(Acondicionable instalacion) throws InterruptedException {
        if (instalacion.getPrecioAcondicionamiento() > 0){
            double precio = instalacion.getPrecioAcondicionamiento();
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "Precio de acondicionamiento: " + ConsoleColors.RESET + precio + "€.");
        } else {
            System.out.println(ConsoleColors.RED_BRIGHT + "✘ Esta instalación no tiene precio de acondicionamiento unitario asignado." + ConsoleColors.RESET);
            modificarAcondicionamientoUnitario(instalacion);
        }
    }

    /**
     * Pide al usuario el precio de acondicionamiento unitario y lo establece.
     * @param instalacion Instalación a la que se le quiere establecer el precio de acondicionamiento unitario.
     * @throws InterruptedException Si se cancela la operación.
     */
    private void modificarAcondicionamientoUnitario(Acondicionable instalacion) throws InterruptedException {
        System.out.println("Introduce el precio de acondicionamiento unitario que quieres establecer o '0' para cancelar:");
        double precio = leerDouble();
        if (precio > 0) {
            instalacion.setPrecioAcondicionamientoUnitario(precio);
            System.out.println(ConsoleColors.GREEN_BRIGHT + "✔ Precio de acondicionamiento unitario establecido correctamente." + ConsoleColors.RESET);
        } else {
            throw new InterruptedException("Operación cancelada.");
        }
    }

    /**
     * Exporta las instalaciones a un archivo de texto.
     */
    private void exportarInstalaciones() {
        try {
            polideportivo.guardarInstalaciones();
            System.out.println(
                    ConsoleColors.GREEN_BRIGHT + "✔ " +
                            "Instalaciones exportadas correctamente." + ConsoleColors.RESET
            );
        } catch (Exception e) {
            System.out.println(
                    ConsoleColors.RED_BRIGHT + "✘ " +
                            e.getMessage() + ConsoleColors.RESET
            );
        }
    }

    /**
     * Importa instalaciones desde un archivo de texto.
     */
    private void cargarInstalaciones() {
        try {
            polideportivo.leerInstalacionesDeArchivo();
            System.out.println(
                    ConsoleColors.GREEN_BRIGHT + "✔ " +
                            "Instalaciones cargadas correctamente." + ConsoleColors.RESET
            );
        } catch (Exception e) {
            System.out.println(
                    ConsoleColors.RED_BRIGHT + "✘ " +
                            e.getMessage() + ConsoleColors.RESET
            );
        }
    }

    /**
     * Ordena las instalaciones por nombre.
     */
    private void ordenarInstalaciones() {
        System.out.println("Ordenando instalaciones por nombre...");
        polideportivo.ordenarInstalaciones();
        System.out.println(
                ConsoleColors.GREEN_BRIGHT +"✔ " +
                        "Instalaciones ordenadas por nombre." + ConsoleColors.RESET
        );
    }

    /**
     * Pide al usuario el nombre y tipo de la instalación y muestra todas las instalaciones que coincidan.
     * Pregunta cuál de ellas quiere borrar y la borra. Se puede cancelar la operación.
     */
    private void borrarInstalacion() {
        try {
            ArrayList<Instalacion> encontradas = buscarInstalacion();
            // Mostrar 0 como opción para cancelar
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "  [0] " + ConsoleColors.CYAN + "Cancelar" + ConsoleColors.RESET);
            System.out.println("Introduce el número de la instalación que quieres borrar:");
            int numero = leerEnteroIntermedio(0, encontradas.size());
            try {
                if (numero != 0) {
                    polideportivo.borrarInstalacion(encontradas.get(numero - 1));
                    System.out.println(ConsoleColors.GREEN_BRIGHT + "✔ Instalación borrada correctamente." + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.GREEN_BRIGHT + "✔ Operación cancelada." + ConsoleColors.RESET);
                }
            } catch (InterruptedException e) {
                System.out.println(ConsoleColors.RED_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
            }
        } catch (InstanceNotFoundException e) {
            System.out.println(ConsoleColors.RED_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
        }
    }

    /**
     * Pide al usuario el nombre y tipo de la máquina y muestra todas las máquinas que coincidan.
     * Pregunta cuál de ellas quiere borrar y la borra. Se puede cancelar la operación.
     */
    private void borradoRapidoMaquina() {
        ArrayList<MaquinaFitness> encontradas;
        try {
            encontradas = buscarMaquina();

            // Mostrar 0 como opción para cancelar
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "  [0] " + ConsoleColors.CYAN + "Cancelar" + ConsoleColors.RESET);
            System.out.println("Introduce el número de la máquina que quieres borrar:");
            int numero = leerEnteroIntermedio(0, encontradas.size());
            try {
                if (numero != 0) {
                    polideportivo.borrarMaquina(encontradas.get(numero - 1));
                    System.out.println(ConsoleColors.GREEN_BRIGHT + "✔ Máquina borrada correctamente." + ConsoleColors.RESET);
                } else {
                    throw new InterruptedException("Operación cancelada.");
                }
            } catch (InterruptedException e) {
                System.out.println(ConsoleColors.RED_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
            }

        } catch (InstanceNotFoundException e) {
            System.out.println(ConsoleColors.RED_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
        }
    }

    /**
     * Busca una instalación por nombre o tipo. Muestra las máquinas que contengan
     * el texto introducido como subcadena en su nombre o tipo.
     *
     * @param instalaciones Lista de instalaciones en las que buscar.
     * @return Lista de instalaciones encontradas.
     * @throws InstanceNotFoundException Si no se encuentra ninguna instalación.
     */
    private ArrayList<Instalacion> buscarInstalacion(ArrayList<Instalacion> instalaciones) throws InstanceNotFoundException {
        ArrayList<Instalacion> encontradas = new ArrayList<>();

        // Pedir el nombre o tipo de la instalación
        System.out.println("Introduce el nombre o el tipo de la instalación:");
        String nombre_o_tipo = leerString();

        // Buscar instalaciones que contengan el texto introducido, en mayúsculas o minúsculas
        for (Instalacion i : instalaciones) {
            if (i.getNombre().toLowerCase().contains(nombre_o_tipo.toLowerCase()) ||
                    i.getClass().getSimpleName().toLowerCase().contains(nombre_o_tipo.toLowerCase()) ||
                    (String.valueOf(i.getCodigo()).contains(nombre_o_tipo.toLowerCase()))) {
                encontradas.add(i);
            }
        }

        // Mostrar si se han encontrado instalaciones o no
        if (encontradas.isEmpty()) {
            throw new InstanceNotFoundException("No se ha encontrado ninguna instalación con ese nombre o tipo.");
        } else {
            if (encontradas.size() == 1) System.out.println(
                    ConsoleColors.GREEN_BRIGHT + "✔ Se ha encontrado 1 instalación:" + ConsoleColors.RESET);
            else System.out.println(
                    ConsoleColors.GREEN_BRIGHT + "✔ Se han encontrado " + encontradas.size() + " instalaciones:" + ConsoleColors.RESET);

            // Mostrar las instalaciones encontradas
            String txt,nombre,tipo, strCodigo;
            int j = 1;
            for (Instalacion i : encontradas) {
                // Resaltar el texto que coincide con el nombre o tipo
                nombre = i.getNombre().replaceAll("(?i)"+nombre_o_tipo,
                        ConsoleColors.YELLOW_BRIGHT + "$0" + ConsoleColors.RESET);
                tipo = i.getClass().getSimpleName().replaceAll("(?i)"+nombre_o_tipo,
                        ConsoleColors.YELLOW_BRIGHT + "$0" + ConsoleColors.RESET);
                strCodigo = String.valueOf(i.getCodigo()).replaceAll("(?i)"+nombre_o_tipo,
                        ConsoleColors.YELLOW_BRIGHT + "$0" + ConsoleColors.RESET);
                // Formatear nombre y tipo
                txt = String.format(
                        ConsoleColors.CYAN_BOLD_BRIGHT + String.format("%6s","[" + j++ + "] ") +
                                ConsoleColors.RESET + "%s (%s" +
                                (i instanceof Acondicionable ? ConsoleColors.CYAN_BRIGHT + "*" + ConsoleColors.RESET : "") +
                                ", #%s)", nombre, tipo, strCodigo);

                System.out.println(txt);
            }
        }
        return encontradas;

    }

    /**
     * Busca una instalación por nombre o tipo en el polideportivo.
     * Muestra las instalaciones que contengan el texto introducido como subcadena en su nombre o tipo.
     *
     * @return Lista de instalaciones encontradas.
     * @throws InstanceNotFoundException Si no se encuentra ninguna instalación.
     */
    private ArrayList<Instalacion> buscarInstalacion() throws InstanceNotFoundException {
        return buscarInstalacion(polideportivo.getListaInstalaciones());
    }

    /**
     * Busca una máquina por nombre o tipo en un ArrayList dado.
     * Muestra las máquinas que contengan el texto introducido como subcadena en su nombre o tipo.
     *
     * @param maquinas Lista de máquinas en las que buscar.
     * @return Lista de máquinas encontradas.
     * @throws InstanceNotFoundException Si no se encuentra ninguna máquina.
     */
    private ArrayList<MaquinaFitness> buscarMaquina(ArrayList<MaquinaFitness> maquinas) throws InstanceNotFoundException {
        ArrayList<MaquinaFitness> encontradas = new ArrayList<>();

        // Pedir el nombre o tipo de la máquina
        System.out.println("Introduce el nombre o el tipo de la máquina:");
        String nombre_o_tipo = leerString();

        // Buscar máquinas que contengan el texto introducido, en mayúsculas o minúsculas
        for (MaquinaFitness m : maquinas) {
            if (m.getNombre().toLowerCase().contains(nombre_o_tipo.toLowerCase()) ||
                    m.getTipo().toLowerCase().contains(nombre_o_tipo.toLowerCase())) {
                encontradas.add(m);
            }
        }

        // Mostrar si se han encontrado máquinas o no
        if (encontradas.isEmpty()) {
            throw new InstanceNotFoundException("No se ha encontrado ninguna máquina con ese nombre o tipo.");
        } else {
            if (encontradas.size() ==1) System.out.println(
                    ConsoleColors.GREEN_BRIGHT + "✔ Se ha encontrado 1 máquina:" + ConsoleColors.RESET);
            else System.out.println(
                    ConsoleColors.GREEN_BRIGHT + "✔ Se han encontrado " + encontradas.size() + " máquinas:" + ConsoleColors.RESET);

            // Mostrar las máquinas encontradas
            String txt,nombre,tipo;
            int i = 1;
            for (MaquinaFitness m : encontradas) {
                // Resaltar el texto que coincide con el nombre o tipo
                nombre = m.getNombre().replaceAll("(?i)"+nombre_o_tipo,
                        ConsoleColors.YELLOW_BRIGHT + "$0" + ConsoleColors.RESET);
                tipo = m.getTipo().replaceAll("(?i)"+nombre_o_tipo,
                        ConsoleColors.YELLOW_BRIGHT + "$0" + ConsoleColors.RESET);
                // Formatear nombre y tipo
                txt = String.format(
                        ConsoleColors.CYAN_BOLD_BRIGHT + String.format("%6s","[" + i++ + "] ") + ConsoleColors.RESET + "%s (%s",
                        nombre, tipo);
                // Añadir los años de antigüedad
                txt += ", " + m.getAnios() + " años)";

                System.out.println(txt);
            }
        }
        return encontradas;
    }

    /**
     * Busca una máquina por nombre o tipo en el polideportivo.
     * Muestra las máquinas que contengan el texto introducido como subcadena en su nombre o tipo.
     *
     * @return Lista de máquinas encontradas.
     * @throws InstanceNotFoundException Si no se encuentra ninguna máquina.
     */
    private ArrayList<MaquinaFitness> buscarMaquina() throws InstanceNotFoundException {
        return buscarMaquina(polideportivo.getListaMaquinas());
    }

    /**
     * Realiza una búsqueda de instalaciones por nombre o tipo en el polideportivo y muestra el resultado.
     * Pide al usuario que seleccione una instalación de la lista, pudiendo este
     * cancelar la operación.
     * @param instalaciones Lista de instalaciones en las que buscar.
     * @return La instalación seleccionada o null si se cancela la operación.
     * @throws InterruptedException Si se cancela la operación.
     * @throws IllegalArgumentException Si no hay instalaciones.
     */
    private Instalacion seleccionarInstalacion(ArrayList<Instalacion> instalaciones) throws InterruptedException, IllegalArgumentException {
        if (!instalaciones.isEmpty()) {
            System.out.println("Introduce el número de la instalación que quieras gestionar o '0' para cancelar:");
            int numero = leerEnteroIntermedio(0, instalaciones.size());
            if (numero == 0) {
                throw new InterruptedException("Operación cancelada.");
            } else {
                return instalaciones.get(numero - 1);
            }
        } else {
            throw new IllegalArgumentException("No hay instalaciones.");
        }
    }

    /**
     * Realiza una búsqueda de máquinas por nombre o tipo y muestra el resultado.
     * Pide al usuario que seleccione una máquina de la lista, pudiendo este
     * cancelar la operación.
     * @param maquinas Lista de máquinas en las que buscar.
     * @return La máquina seleccionada o null si se cancela la operación.
     * @throws InterruptedException Si se cancela la operación.
     * @throws IllegalArgumentException Si no hay máquinas.
     */
    private MaquinaFitness seleccionarMaquina(ArrayList<MaquinaFitness> maquinas) throws InterruptedException {
        if (!maquinas.isEmpty()) {
            System.out.println("Introduce el número de la máquina que quieras gestionar o '0' para cancelar:");
            int numero = leerEnteroIntermedio(0, maquinas.size());
            if (numero == 0) {
                throw new InterruptedException("Operación cancelada.");
            } else {
                return maquinas.get(numero - 1);
            }
        } else {
            throw new IllegalArgumentException("No hay máquinas.");
        }
    }

    /**
     * Ordena las instalaciones por nombre.
     */
    private void ordenarMaquinas() {
        System.out.println("Ordenando máquinas por nombre...");
        polideportivo.ordenarMaquinas();
        System.out.println(
                ConsoleColors.GREEN_BRIGHT +"✔ " +
                "Máquinas ordenadas por nombre." + ConsoleColors.RESET
        );
    }

    /**
     * Exporta las máquinas a un archivo de texto.
     */
    private void exportarMaquinas() {
        try {
            polideportivo.guardarMaquinas();
            System.out.println(
                    ConsoleColors.GREEN_BRIGHT + "✔ " +
                            "Máquinas exportadas correctamente." + ConsoleColors.RESET
            );
        } catch (Exception e) {
            System.out.println(
                    ConsoleColors.RED_BRIGHT + "✘ " +
                            e.getMessage() + ConsoleColors.RESET
            );
        }

    }

    /**
     * Muestra las máquinas de fitness del polideportivo.
     * @param maquinas Lista de máquinas de fitness.
     */
    private void mostrarMaquinas(ArrayList<MaquinaFitness> maquinas) {
        if (maquinas.isEmpty()) {
            System.out.println(ConsoleColors.RED_BRIGHT + "✘ No hay máquinas." + ConsoleColors.RESET);
        } else {
            System.out.println(
                    ConsoleColors.GREEN_BRIGHT + "✔ Mostrando " + maquinas.size() + " máquinas:" + ConsoleColors.RESET);
            polideportivo.mostrarMaquinas();
        }
    }

    /**
     * Pide al usuario el número de años positivo y borra las máquinas con más
     * de ese número de años.
     */
    private void borrarMaquinasConMasDeXAnios() {
        int anios, borradas, totalBorradas=0;

        do {
            System.out.println("Introduce el número de años:");
            anios = leerEntero();
        } while (anios < 0);

        for (int i = anios+1; i <= 20; i++) {
            borradas = polideportivo.borrarMaquinas(i);
            totalBorradas += borradas;
        }
        if (totalBorradas == 0) {
            System.out.println( ConsoleColors.RED_BRIGHT +
                    "✘ " + "No se han encontrado máquinas con más de " + anios + " años." + ConsoleColors.RESET);
        } else if (totalBorradas == 1) {
            System.out.println( ConsoleColors.GREEN_BRIGHT +
                    "✔ " + "Se ha borrado 1 máquina con más de " + anios + " años." + ConsoleColors.RESET);
        } else {
            System.out.println( ConsoleColors.GREEN_BRIGHT +
                    "✔ " + "Se han borrado " + totalBorradas + " máquinas con más de " + anios + " años." + ConsoleColors.RESET);
        }
    }

    /**
     * Carga las máquinas desde un archivo de texto.
     */
    private void cargarMaquinas() {
        try {
            polideportivo.leerMaquinasDeArchivo();
            System.out.println(
                    ConsoleColors.GREEN_BRIGHT + "✔ " +
                            "Máquinas cargadas correctamente." + ConsoleColors.RESET
            );
        } catch (Exception e) {
            System.out.println(
                    ConsoleColors.RED_BRIGHT + "✘ " +
                            e.getMessage() + ConsoleColors.RESET
            );
        }
    }

    /**
     * Pide al usuario un número y muestra las máquinas con más de ese número de reservas.
     */
    private void masReservas() {
        System.out.println("Introduce el número de reservas:");
        int numero = leerEntero();

        ArrayList<String> lista = polideportivo.masReservas(numero);
        if (lista.isEmpty()) {
            System.out.println(
                    ConsoleColors.RED_BRIGHT + "✘ No hay máquinas con más de " + numero + " reservas." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.GREEN_BRIGHT + "✔ " +
                    "Máquinas con más de " + numero + " reservas:" + ConsoleColors.RESET);
            for (String maquina : lista) {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "- " + ConsoleColors.RESET + maquina);
            }
        }
    }

    /**
     * Realiza una búsqueda de máquinas por nombre o tipo y muestra el resultado.
     * El usuario puede seleccionar una máquina de la lista para reservarla.
     *
     */
    private void reservaRapidaMaquina() {
        ArrayList<MaquinaFitness> encontradas;
        try {
            encontradas = buscarMaquina();

            // Mostrar 0 como opción para cancelar
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "  [0] " + ConsoleColors.CYAN + "Cancelar" + ConsoleColors.RESET);
            System.out.println("Introduce el número de la máquina que quieres reservar:");
            int numero = leerEnteroIntermedio(0, encontradas.size());
            try {
                if (numero != 0) {
                    reservarMaquina(encontradas.get(numero - 1));
                } else {
                    throw new InterruptedException("Operación cancelada.");
                }
            } catch (InterruptedException e) {
                System.out.println(ConsoleColors.RED_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
            }

        } catch (InstanceNotFoundException e) {
            System.out.println(ConsoleColors.RED_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
        }
    }

    /**
     * Carga las máquinas e instalaciones iniciales del programa. Si no existen los ficheros
     * necesarios, los crea. Si ocurre un error inesperado, muestra un mensaje de error.
     */
    private void cargarMaquinasInstalacionesInicial() {
        // Declaración de variables
        boolean creadoMaquinas = false, creadoInstalaciones = false;
        File fichero;
        String ficherosGenerados = ConsoleColors.GREEN_BRIGHT +
                "✔ Se han creado los archivos necesarios para el funcionamiento del programa." + ConsoleColors.RESET;
        String errorInesperado = ConsoleColors.RED_BRIGHT +
                "✘ Ha ocurrido un error inesperado durante la carga inicial del programa.\n" +
                "%s\n" +
                "Es posible que el programa no funcione como se espera.\n" + ConsoleColors.RESET;

        try {
            // Comprobar que existe el directorio default o crearlo si no
            File directorio = new File("data/default");
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // Carga de máquinas de fitness
            fichero = new File("data/default/maquinasFitness.txt");
            creadoMaquinas = fichero.createNewFile();
            polideportivo.cargarMaquinas(fichero);

            // Carga de instalaciones
            fichero = new File("data/default/instalaciones.txt");
            creadoInstalaciones = fichero.createNewFile();
            polideportivo.cargarInstalaciones(fichero);

        } catch (Exception e) {
            System.out.printf(errorInesperado, e.getMessage());
        }

        if (creadoMaquinas || creadoInstalaciones) {
            System.out.println(ficherosGenerados);
        }
    }

    /**
     * Lee un número entero de la entrada estándar.
     * @return El número entero leído.
     */
    private int leerEntero(){
        Integer numero = null;
        do {
            System.out.print(" > ");
            try {
                String texto = sc.nextLine();
                if (texto.isEmpty())
                    throw new NoSuchElementException();
                numero = Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColors.RED_BRIGHT + "✘ Debes introducir un número entero." + ConsoleColors.RESET);
            } catch (NoSuchElementException e) {
                System.out.println(ConsoleColors.RED_BRIGHT + "✘ No se ha introducido ningún número." + ConsoleColors.RESET);
            }
        } while (numero == null);
        return numero;
    }

    /**
     * Lee un número decimal de la entrada estándar.
     * @return El número decimal leído.
     */
    private double leerDouble() {
        Double numero = null;
        do {
            System.out.print(" > ");
            try {
                String texto = sc.nextLine();
                if (texto.isEmpty())
                    throw new NoSuchElementException();
                numero = Double.parseDouble(texto);
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColors.RED_BRIGHT + "✘ Debes introducir un número decimal." + ConsoleColors.RESET);
            } catch (NoSuchElementException e) {
                System.out.println(ConsoleColors.RED_BRIGHT + "✘ No se ha introducido ningún número." + ConsoleColors.RESET);
            }
        } while (numero == null);
        return numero;
    }

    /**
     * Lee un número entero de la entrada estándar y comprueba que esté en el rango [inf, sup].
     * @param inf Número mínimo.
     * @param sup Número máximo.
     * @return El número entero leído.
     */
    private int leerEnteroIntermedio(int inf, int sup){
        Integer numero = null;
        do {
            try {
                numero = leerEntero();
                if (numero < inf || numero > sup) {
                    numero = null;
                    throw new IllegalArgumentException("El número debe estar entre " + inf + " y " + sup + ".");
                }
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED_BRIGHT + "✘ " + e.getMessage() + ConsoleColors.RESET);
            }
        } while (numero == null);
        return numero;
    }

    /**
     * Lee un texto de la entrada estándar.
     * @return El texto leído.
     */
    private String leerString(){
        String texto = "";
        do {
            System.out.print(" > ");
            try {
                texto = sc.nextLine();
                if (texto.isEmpty())
                    throw new NoSuchElementException();
            } catch (NoSuchElementException e) {
                System.out.println(ConsoleColors.RED_BRIGHT + "✘ No se ha introducido ningún texto." + ConsoleColors.RESET);
            }
        } while (texto.isEmpty());
        return texto;
    }

    /**
     * Muestra el título del programa.
     */
    private void mostrarTituloGrande(){
        System.out.println( ConsoleColors.CYAN_BOLD +
                "  _____        _  _      _                            _    _                ______  _    _  _    _ \n" +
                " |  __ \\      | |(_)    | |                          | |  (_)              |  ____|| |  | || |  | |\n" +
                " | |__) |___  | | _   __| |  ___  _ __    ___   _ __ | |_  _ __   __ ___   | |__   | |__| || |  | |\n" +
                " |  ___// _ \\ | || | / _` | / _ \\| '_ \\  / _ \\ | '__|| __|| |\\ \\ / // _ \\  |  __|  |  __  || |  | |\n" +
                " | |   | (_) || || || (_| ||  __/| |_) || (_) || |   | |_ | | \\ V /| (_) | | |____ | |  | || |__| |\n" +
                " |_|    \\___/ |_||_| \\__,_| \\___|| .__/  \\___/ |_|    \\__||_|  \\_/  \\___/  |______||_|  |_| \\____/ \n" +
                "                                 | |                                                               \n" +
                "                                 |_|   Creador: Jorge Arévalo Fernández                            " +
                ConsoleColors.RESET
        );
    }

    /**
     * Muestra un mensaje de ayuda para habilitar los colores ANSI en la consola.
     */
    private void helpColors(){
        String url = "https://ss64.com/nt/syntax-ansi.html#:~:text=To%20use%20ANSI%20colours%20in,the%20terminal%20and%20in%20ConPTY.&text=Alternatively%20it%20can%20be%20enabled,API%20with%20the%20ENABLE_VIRTUAL_TERMINAL_PROCESSING%20flag.";

        System.out.println("A continuación se muestran algunos de los problemas más frecuentes:");
        System.out.println(" 1. Si en los mensajes de error y de operación completada ves (?) significa que tu terminal no soporta emoticonos.");
        System.out.println(" 2. Si ves símbolos raros en la consola, es posible que tu terminal no soporte los colores ANSI.");
        System.out.println("    Puedes habilitarlos siguiendo las instrucciones de este enlace: " + url);
    }
}
