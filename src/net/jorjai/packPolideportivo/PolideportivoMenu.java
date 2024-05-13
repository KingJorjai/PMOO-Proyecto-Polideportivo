package net.jorjai.packPolideportivo;public class PolideportivoMenu {

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
        try {
            polideportivo.cargarMaquinas("maquinasFitness.txt");
        } catch (IOException e) {
            System.out.println("Error al cargar las máquinas de fitness: " + e.getMessage());
        }
        // Carga de instalaciones
        try {
            polideportivo.cargarInstalaciones("instalaciones.txt");
        } catch (IOException e) {
            System.out.println("Error al cargar las instalaciones: " + e.getMessage());
        }
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
}
