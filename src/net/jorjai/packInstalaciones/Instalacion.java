package net.jorjai.packInstalaciones;

/** Clase que simula una instalación deportiva. */
public class Instalacion {
    private String nombre;
    private int codigo;
    private int horaApertura;
    private int horaCierre;
    private String[] deportes;
    private String deporteActual;
    private static int contador = 1;
    private static final int HORA_APERTURA = 7;
    private static final int HORA_CIERRE = 22;

	/**
	 * Constructor de la clase Instalacion
	 * 
	 * @param nombre Nombre de la instalación.
	 */
    public Instalacion(String nombre) {
        this.nombre = nombre;
        this.codigo = contador++;
        this.horaApertura = HORA_APERTURA;
        this.horaCierre = HORA_CIERRE;
        this.deportes = new String[8];
        this.deporteActual = "";
    }

	/**
	 * Dos instalaciones son iguales si su nombre es igual.
	 * 
	 * @param instalacion Instalación con la que comparar.
	 * @return true si las instalaciones son iguales.
	 */
    public boolean equals(Instalacion instalacion) {
        return this.nombre.equals(instalacion.nombre);
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
            if (d != null && d.equals(deporte)) {
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
	 */
    public void addDeporte(String deporte) {
        for (int i = 0; i < deportes.length; i++) {
            if (deportes[i] == null) {
                deportes[i] = deporte;
                break;
            }
        }
    }

    /**
     * Método que, dado un deporte, si está en la lista de nombres de deportes.
     * @param deporte Nombre del deporte a asignar.
     */
    public void modificarDeporteActual(String deporte) {
        if (seAcepta(deporte)) {
            this.deporteActual = deporte;
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
     * Actualiza los horarios de aperturae la instalación.
     * @param horaApertura Nueva hora de apertura.
     */
    public void setHoraApertura(int horaApertura) {
        if (horaApertura < horaCierre) {
            this.horaApertura = horaApertura;
        }
    }

	/**
	 * Actualiza los horarios de cierre de la instalación.
	 * 
	 * @param horaCierre Nueva hora de cierre.
	 */
    public void setHoraCierre(int horaCierre) {
        if (horaCierre > horaApertura) {
            this.horaCierre = horaCierre;
        }
    }
    
    

}
