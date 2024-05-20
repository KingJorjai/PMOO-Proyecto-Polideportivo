package net.jorjai.packInstalaciones;

import net.jorjai.packUtil.ConsoleColors;

import java.util.NoSuchElementException;

/**
 * Clase que simula una instalación deportiva.
 * Almacena información sobre el nombre, el código, el horario de apertura y cierre,
 * los deportes que se pueden practicar en la instalación y el deporte actual.
 * <p> La instalación tiene un código único que se asigna automáticamente al crear una nueva instancia.
 * Por defecto, la hora de apertura es a las 7:00 y la hora de cierre a las 22:00.
 *
 * @author Jorge Arévalo Fernández
 */
public abstract class Instalacion implements Comparable<Instalacion> {
    /** Nombre de la instalación. */
    private String nombre;
    /** Código de la instalación. */
    private int codigo;
    /** Hora de apertura de la instalación. */
    private int horaApertura;
    /** Hora de cierre de la instalación. */
    private int horaCierre;
    /** Lista de deportes de la instalación. */
    private String[] deportes;
    /** Deporte actual de la instalación. */
    private String deporteActual;
    /**
     * Contador de instalaciones. Se aumenta en uno tras crear una instancia nueva.
     * Se utiliza para asignar un código único a cada instalación.
     */
    private static int contador = 1;
    /** Hora de apertura por defecto de las instalaciones. */
    private static final int HORA_APERTURA = 7;
    /** Hora de cierre por defecto de las instalaciones. */
    private static final int HORA_CIERRE = 22;

    /**
     * Constructor de la clase Instalacion
     *
     * @param nombre Nombre de la instalación.
     */
    public Instalacion(String nombre) {
        setNombre(nombre);
        this.codigo = contador++;
        setHoraCierre(HORA_CIERRE);
        setHoraApertura(HORA_APERTURA);
        setDeportes(new String[8]);
        setDeporteActual("");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Instalacion i)) return false;
        return i.getNombre().equals(getNombre()) && i.getCodigo() == getCodigo();
    }

    /**
     * Método auxiliar que, dado el nombre de un deporte, indica si dicho nombre de
     * deporte está incluido en la lista de nombres de deportes de la instalación
     * actual (la utilizada para invocar el método).
     *
     * @param deporte Nombre del deporte a comprobar.
     * @return true si el deporte está incluido en la lista de deportes de la
     *         instalación.
     */
    public boolean seAcepta(String deporte) {
        for (String d : deportes) {
            if (d != null && d.equalsIgnoreCase(deporte)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Dado el nombre de un deporte, lo añade a la lista de nombres de deportes de
     * la Instalacion, si es posible (queda espacio y no está ya incluido en la
     * lista de nombres de deportes admitidos en la instalación actual.
     *
     * @param deporte Nombre del deporte a añadir.
     * @throws ArrayIndexOutOfBoundsException si no hay espacio para añadir más deportes.
     * @throws IllegalArgumentException si el deporte ya está añadido a la instalación.
     */
    public void addDeporte(String deporte) throws ArrayIndexOutOfBoundsException, IllegalArgumentException{
        for (int i = 0; i < deportes.length; i++) {
            if (deportes[i] != null && deportes[i].equalsIgnoreCase(deporte)) {
                throw new IllegalArgumentException("El deporte ya está añadido a la instalación.");
            }
            if (deportes[i] == null) {
                deportes[i] = deporte;
                return;
            }
        }
        throw new ArrayIndexOutOfBoundsException("No se pueden añadir más deportes a la instalación.");
    }

    /**
     * Método que, dado un deporte, si está en la lista de nombres de deportes.
     * @param deporte Nombre del deporte a asignar.
     * @throws IllegalArgumentException si el deporte no está admitido en la instalación.
     */
    public void modificarDeporteActual(String deporte) {
        if (seAcepta(deporte)) {
            this.deporteActual = deporte.toLowerCase();
        } else {
            throw new IllegalArgumentException("El deporte no está admitido en la instalación.");
        }
    }

    /**
     * Devuelve el precio de alquiler de la instalación.
     *
     * @return Precio de alquiler de la instalación.
     */
    public double precioAlquiler() {
        return 0;
    }

    /**
     * Devuelve el camino de la jerarquía que va desde la clase hoja hasta la clase
     * Instalación.
     *
     * @return String con los nombres de las clases del camino.
     */
    public String inheritancePath() {
        return "Instalacion";
    }

    /**
     * Escribe en pantalla el String correspondiente a la vía hereditaria de una
     * Instalacion.
     */
    public void printInheritancePath() {
        System.out.println(inheritancePath());
    }

    /**
     * Devuelve el nombre de la instalación.
     * @return Nombre de la instalación.
     */
    public String getNombre() {
        return nombre;
    }

    public int compareTo(Instalacion i) {
        return this.nombre.compareTo(i.nombre);
    }

    /**
     * Devuelve el código de la instalación.
     *
     * @return Código de la instalación.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Devuelve la hora de apertura de la instalación.
     *
     * @return Hora de apertura de la instalación.
     */
    public int getHoraApertura() {
        return horaApertura;
    }

    /**
     * Devuelve la hora de cierre de la instalación.
     *
     * @return Hora de cierre de la instalación.
     */
    public int getHoraCierre() {
        return horaCierre;
    }

    /**
     * Devuelve la lista de deportes de la instalación.
     *
     * @return Lista de deportes de la instalación.
     */
    public String[] getDeportes() {
        return deportes;
    }

    /**
     * Devuelve el deporte actual de la instalación.
     *
     * @return Deporte actual de la instalación.
     */
    public String getDeporteActual() {
        return deporteActual;
    }

    /**
     * Actualiza el deporte actual de la instalación.
     * Solo se utiliza en el constructor de la clase y no se puede invocar desde fuera.
     * @param deporteActual Nuevo deporte actual.
     */
    private void setDeporteActual(String deporteActual) {
        this.deporteActual = deporteActual;
    }

    /**
     * Actualiza el horario de apertura de la instalación.
     * @param horaApertura Nueva hora de apertura.
     * @throws IllegalArgumentException si la hora de apertura no es un número entre 0 y 23.
     */
    private void setHoraApertura(int horaApertura) throws IllegalArgumentException {
        if (horaApertura > 23 || horaApertura < 0) {
            throw new IllegalArgumentException("La hora de apertura debe ser un número entre 0 y 23.");
        } else {
            this.horaApertura = horaApertura;
        }
    }

    /**
     * Actualiza el horario de cierre de la instalación.
     *
     * @param horaCierre Nueva hora de cierre.
     * @throws IllegalArgumentException si la hora de cierre no es un número entre 0 y 23.
     */
    private void setHoraCierre(int horaCierre) throws IllegalArgumentException {
        if (horaCierre > 23 || horaCierre < 0) {
            throw new IllegalArgumentException("La hora de cierre debe ser un número entre 0 y 23.");
        } else {
            this.horaCierre = horaCierre;
        }
    }

    /**
     * Actualiza los horarios de apertura y cierre de la instalación.
     *
     * @param horaApertura Hora de apertura de la instalación.
     * @param horaCierre   Hora de cierre de la instalación.
     * @throws IllegalArgumentException Si la hora de cierre es anterior a la hora de apertura o si
     *                                  cualquiera de las dos horas no está en el rango [0, 23].
     */
    public void actualizarHorarios(int horaApertura, int horaCierre) throws IllegalArgumentException {
        if (horaCierre <= horaApertura) {
            throw new IllegalArgumentException("La hora de cierre debe ser posterior a la hora de apertura.");
        } else {
            setHoraApertura(horaApertura);
            setHoraCierre(horaCierre);
        }
    }

    /**
     * Establece el nombre de la instalación.
     * @param nombre Nombre de la instalación.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el código de la instalación.
     * @param codigo Código de la instalación.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Establece la lista de deportes de la instalación.
     * @param deportes Lista de deportes de la instalación.
     */
    public void setDeportes(String[] deportes) {
        this.deportes = deportes;
    }

    /**
     * Devuelve el contador de instalaciones.
     * @return Contador de instalaciones.
     */
    public static int getContador() {
        return contador;
    }


    /**
     * Muestra la información detallada de la instalación.
     */
    public void mostrarInfoDetallada() {
        String formato = "%28s";

        System.out.println(ConsoleColors.CYAN_BOLD +
                String.format(formato, "Código: ") + ConsoleColors.RESET + codigo
        );
        System.out.println(ConsoleColors.CYAN_BOLD +
                String.format(formato, "Jerarquía: ") + ConsoleColors.RESET + inheritancePath()
        );
        System.out.println(ConsoleColors.CYAN_BOLD +
                String.format(formato, "Nombre: ") + ConsoleColors.RESET + nombre
        );
        System.out.print(ConsoleColors.CYAN_BOLD +
                String.format(formato, "Deportes: ") + ConsoleColors.RESET + "["
        );
        for (int i = 0; i < deportes.length; i++) {
            if (deportes[i] != null) {
                System.out.print(deportes[i]);
                if (i < deportes.length - 1 && deportes[i + 1] != null) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println("]");
        System.out.println(ConsoleColors.CYAN_BOLD +
                String.format(formato, "Deporte actual: ") + ConsoleColors.RESET + deporteActual
        );
        System.out.println(ConsoleColors.CYAN_BOLD +
                String.format(formato, "Precio alquiler: ") + ConsoleColors.RESET + precioAlquiler() + " €"
        );
        System.out.println(ConsoleColors.CYAN_BOLD +
                String.format(formato, "Hora apertura: ") + ConsoleColors.RESET + String.format("%02d:00", horaApertura)
        );
        System.out.println(ConsoleColors.CYAN_BOLD +
                String.format(formato, "Hora cierre: ") + ConsoleColors.RESET + String.format("%02d:00", horaCierre)
        );

        if(this instanceof Acondicionable){
            System.out.println(ConsoleColors.CYAN_BOLD +
                    String.format(formato, "Acondicionamiento unitario: ") + ConsoleColors.RESET + ((Acondicionable) this).getPrecioAcondicionamientoUnitario()
            );
            System.out.println(ConsoleColors.CYAN_BOLD +
                    String.format(formato, "Acondicionamiento: ") + ConsoleColors.RESET + ((Acondicionable) this).getPrecioAcondicionamiento()
            );
        }
    }

    /**
     * Elimina una deporte de la lista de deportes de la instalación.
     * @param deporte Deporte a eliminar.
     * @throws NoSuchElementException si el deporte no está en la lista de deportes de la instalación.
     */
    public void removeDeporte(String deporte) {
        for (int i = 0; i < deportes.length; i++) {
            if (deportes[i] != null && deportes[i].equalsIgnoreCase(deporte)) {
                deportes[i] = null;
                return;
            }
        }
        throw new NoSuchElementException("El deporte no está en la lista de deportes de la instalación.");
    }
}

