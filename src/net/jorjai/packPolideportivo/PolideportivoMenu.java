package net.jorjai.packPolideportivo;

import java.io.IOException;
import java.util.Scanner;

public class PolideportivoMenu {

    private Polideportivo polideportivo;
    private Scanner sc;

    public static void main(String[] args) {
        PolideportivoMenu poliMenu = new PolideportivoMenu();
        poliMenu.cargarMaquinasInstalacionesInicial();
        poliMenu.menu();
    }

    public PolideportivoMenu() {
        this.polideportivo = Polideportivo.getInstance();
        this.sc = new Scanner(System.in);
    }

    public void cargarMaquinasInstalacionesInicial() {
        // Carga de máquinas de fitness
        polideportivo.cargarMaquinas(new File("data/maquinasFitness.txt"));
        // Carga de instalaciones
        polideportivo.cargarInstalaciones(new File("data/instalaciones.txt"));
    }

    public void menu() {
        int opcion;
        do {
            escribirMenu();
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer
            switch (opcion) {
                case 1:
                    reservarMaquina();
                    break;
                case 2:
                    masReservas();
                    break;
                case 3:
                    cargarMaquinas();
                    break;
                case 4:
                    guardarMaquinas();
                    break;
                case 5:
                    mostrarMaquinas();
                    break;
                case 6:
                    ordenarMaquinas();
                    break;
                case 7:
                    eliminarMaquinas();
                    break;
                case 8:
                    // Otra opción
                    break;
                case 0:
                    System.out.println("Hasta pronto.");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 0);
    }

    private void reservarMaquina() {
        System.out.println("Introduce el tipo de máquina:");
        String tipo = sc.nextLine();
        System.out.println("Introduce la hora de la reserva:");
        int hora = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        try {
            boolean reservaHecha = polideportivo.reservarMaquina(tipo, hora);
            if (reservaHecha) {
                System.out.println("Reserva realizada.");
            } else {
                System.out.println("No se ha podido realizar la reserva.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void escribirMenu() {
        System.out.println("0. Finalizar");
        System.out.println("1. Reservar máquina");
        System.out.println("2. Más reservas");
        System.out.println("3. Cargar máquinas desde fichero");
        System.out.println("4. Guardar información de las máquinas en fichero");
        System.out.println("5. Mostrar información de las máquinas");
        System.out.println("6. Ordenar las máquinas por nombre");
        System.out.println("7. Eliminar máquinas por años");
        System.out.println("8. Otra opción");
    }
}
