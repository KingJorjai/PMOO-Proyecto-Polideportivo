package net.jorjai.packPolideportivo;public class PolideportivoMenu {
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
}
