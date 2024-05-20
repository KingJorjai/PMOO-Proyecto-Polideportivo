package net.jorjai.packInstalaciones;

import net.jorjai.packUtil.ConsoleColors;

/**
 * Clase que simula una sala de artes marciales.
 * <p>  Por defecto, se puede practicar {@code Karate}, {@code Taekwondo}, {@code Judo} y {@code Kung Fu}.
 *      Si la sala de artes marciales tiene ring, también se puede practicar {@code Boxeo}.
 *
 * @author Jorge Arévalo Fernández
 */
public class ArtesMarciales extends Interior {
    /**
     * Número de tatamis de la sala de artes marciales.
     * Cada tatami tiene unas dimensiones de 10x10 m.
     */
    private int tatamis;

    /**
     * Indica si la sala de artes marciales tiene ring.
     */
    private boolean ring;

    /**
     * Constructor de la clase ArtesMarciales.
     *
     * @param nombre   Nombre de la sala de artes marciales.
     * @param longitud Longitud de la sala de artes marciales.
     * @param anchura  Anchura de la sala de artes marciales.
     * @param tatamis  Número de tatamis de la sala de artes marciales.
     * @param ring     Indica si la sala de artes marciales tiene ring.
     */
    public ArtesMarciales(String nombre, int longitud, int anchura, int tatamis, boolean ring) {
        super(nombre, longitud, anchura);
        setTatamis(tatamis);
        setRing(ring);
        addDeporte("Karate");
        addDeporte("Taekwondo");
        addDeporte("Judo");
        addDeporte("Kung Fu");
        if (hasRing()) {
            addDeporte("Boxeo");
            addDeporte("Kickboxing");
            addDeporte("Muay Thai");
        }
    }

    /**
     * Constructor de la clase ArtesMarciales.
     * Por defecto, la sala de artes marciales no tiene {@code ring}.
     *
     * @param nombre   Nombre de la sala de artes marciales.
     * @param longitud Longitud de la sala de artes marciales.
     * @param anchura  Anchura de la sala de artes marciales.
     * @param tatamis  Número de tatamis de la sala de artes marciales.
     */
    public ArtesMarciales(String nombre, int longitud, int anchura, int tatamis) {
        this(nombre, longitud, anchura, tatamis, false);
    }

    /**
     * Devuelve si entran tantos tatamis en la sala de artes marciales.
     * <p> Para ello, se comprueban las dimensiones de todas las distribuciones posibles
     * de tatamis en la sala, teniendo en cuenta que entre ellos no hay separación.
     *
     * @param tatamis Número de tatamis.
     * @return true si entran tantos tatamis en la sala de artes marciales, false en caso contrario.
     */
    public boolean entranTatamis(int tatamis) {
        int ladoTatami = 10, largoSala = getLongitud(), anchoSala = getAnchura(), numTatamisLargo, numTatamisAncho;

        // Si el área de tatamis es mayor que el de la sala, no caben.
        if (tatamis*100 > getLongitud()*getAnchura()) {
            return false;
        }

        // Comprobar todas las distribuciones posibles de tatamis en la sala.
        for (numTatamisLargo = 1; numTatamisLargo <= tatamis; numTatamisLargo++) {
            numTatamisAncho = (int) Math.ceil(((double) tatamis) / (double) numTatamisLargo);

            // Comprobar si la distribución actual es válida.
            if (numTatamisLargo * ladoTatami <= largoSala && numTatamisAncho * ladoTatami <= anchoSala ||
                    numTatamisLargo * ladoTatami <= anchoSala && numTatamisAncho * ladoTatami <= largoSala) {
                return true;
            }

        }
        return false; // No hay ninguna distribución válida.
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof ArtesMarciales a)) return false;
        return super.equals(a) && a.getTatamis() == getTatamis() && a.hasRing() == hasRing();
    }

    @Override
    public String toString() {
        return "ArtesMarciales#%s#%s#%s#%s#%s".formatted(getNombre(), getLongitud(), getAnchura(), getTatamis(), hasRing());
    }

    @Override
    public String inheritancePath() {
        return super.inheritancePath() + " -> ArtesMarciales";
    }

    @Override
    public void mostrarInfoDetallada() {
        String formato = "%28s";

        super.mostrarInfoDetallada();
        System.out.println(ConsoleColors.CYAN_BOLD +
                String.format(formato, "Tatamis: ") + ConsoleColors.RESET + this.getTatamis()
        );
        System.out.println(ConsoleColors.CYAN_BOLD +
                String.format(formato, "Ring: ") + ConsoleColors.RESET + (this.hasRing() ? "Sí" : "No")
        );
    }

    /**
     * Devuelve si la sala de artes marciales tiene ring.
     * 
     * @return true si la sala de artes marciales tiene ring, false en caso contrario.
     */
    public boolean hasRing() {
        return ring;
    }

	/**
	 * Establece si la sala de artes marciales tiene ring.
	 * 
	 * @param ring true si la sala de artes marciales tiene ring, false en caso
	 *        contrario.
	 */
    public void setRing(boolean ring) {
        this.ring = ring;
    }

	/**
	 * Devuelve el número de tatamis de la sala de artes marciales.
	 *
	 * @return Número de tatamis de la sala de artes marciales.
	 */
    public int getTatamis() {
        return tatamis;
    }

    /**
     * Establece el número de tatamis de la sala de artes marciales.
     * @param tatamis Número de tatamis de la sala de artes marciales.
     * @throws IllegalArgumentException Si el número de tatamis no es válido.
     * @see #entranTatamis(int)
     */
    public void setTatamis(int tatamis) {
        if (tatamis < 0) {
            throw new IllegalArgumentException("El número de tatamis no puede ser negativo.");
        } else if (!entranTatamis(tatamis)) {
            throw new IllegalArgumentException("No caben tantos tatamis en la sala.");
        }  else {
            this.tatamis = tatamis;
        }
    }
}
