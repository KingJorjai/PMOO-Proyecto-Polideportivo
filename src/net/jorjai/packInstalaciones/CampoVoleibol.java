package net.jorjai.packInstalaciones;

import net.jorjai.packUtil.ConsoleColors;

/**
 * Clase que simula un campo de voleibol.
 * Las dimensiones del campo de voleibol son siempre {@code 18x9} metros.
 * En un campo de voleibol, solo se puede jugar a voleibol.
 *
 * @author Jorge Arévalo Fernández
 */
public class CampoVoleibol extends Interior implements Acondicionable {
    /**
     * Indica la altura de la red en en centímetros.
     * Las alturas válidas son: {@code 243}, {@code 237}, {@code 224}, {@code 218}, {@code 210} y {@code 200}.
     */
    private int alturaRed;

    /** Indica si la red tiene varillas o no. */
    private boolean varillas;
    /** Longitud del campo de voleibol en metros. */
    public final static int LONGITUD = 18;
    /** Anchura del campo de voleibol en metros. */
    public final static int ANCHURA = 9;

    /**
     * Precio del acondicionamiento unitario del campo de voleibol.
     */
    private double precioAcondicionamientoUnitario;

    /**
     * Constructor de la clase CampoVoleibol
     *
     * @param nombre    Nombre del campo de voleibol.
     * @param alturaRed Altura de la red del campo de voleibol.
     * @param varillas  Indica si la red tiene varillas o no.
     */
    public CampoVoleibol(String nombre,int alturaRed, boolean varillas) {
        super(nombre, 18, 9);
        setAlturaRed(alturaRed);
        setVarillas(varillas);
        addDeporte("Voleibol");
        modificarDeporteActual("Voleibol");
    }

    /**
     * Constructor de la clase CampoVoleibol. Por defecto, la red tiene varillas.
     * @param nombre Nombre del campo de voleibol.
     * @param alturaRed Altura de la red del campo de voleibol.
     */
    public CampoVoleibol(String nombre, int alturaRed) {
        this(nombre, alturaRed, true);
    }

    @Override
    public String toString() {
        return "CampoVoleibol#%s#%s#%s".formatted(getNombre(), getAlturaRed(), hasVarillas());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof CampoVoleibol f)) return false;
        return super.equals(f) && f.getAlturaRed() == getAlturaRed() && f.hasVarillas() == hasVarillas();
    }

    @Override
    public String inheritancePath() {
        return super.inheritancePath() + " -> CampoVoleibol";
    }

    @Override
    public void mostrarInfoDetallada(){
        String formato = "%28s";

        super.mostrarInfoDetallada();
        System.out.println(ConsoleColors.CYAN_BOLD +
                String.format(formato, "Altura de la red: ") + ConsoleColors.RESET +
                String.format("%d,%d m", this.getAlturaRed()/100, this.getAlturaRed()%100)
        );
        System.out.println(ConsoleColors.CYAN_BOLD +
                String.format(formato, "Varillas: ") + ConsoleColors.RESET + (this.hasVarillas() ? "Sí" : "No")
        );
    }

	/**
	 * Devuelve la altura de la red del campo de voleibol.
	 * 
	 * @return Altura de la red del campo de voleibol.
	 */
    public int getAlturaRed() {
        return alturaRed;
    }

    /**
     * Establece la altura de la red del campo de voleibol.
     * @param alturaRed Altura de la red del campo de voleibol.
     * @throws IllegalArgumentException Si la altura de la red no es válida.
     */
    public void setAlturaRed(int alturaRed) {
        if (
                alturaRed == 243 || alturaRed == 237 || alturaRed == 224 ||
                alturaRed == 218 || alturaRed == 210 || alturaRed == 200
        ) {
            this.alturaRed = alturaRed;
        } else {
            throw new IllegalArgumentException("La altura de la red no es válida.");
        }
    }

    /**
     * Indica si la red tiene varillas o no.
     * @return true si la red tiene varillas, false en caso contrario.
     */
    public boolean hasVarillas() {
        return varillas;
    }

    /**
     * Establece si la red tiene varillas o no.
     * @param varillas true si la red tiene varillas, false en caso contrario.
     */
    public void setVarillas(boolean varillas) {
        this.varillas = varillas;
    }

    @Override
    public final void addDeporte (String deporte) {
        if (!deporte.equalsIgnoreCase("Voleibol")) {
            throw new IllegalArgumentException("No se puede jugar a otro deporte en un campo de voleibol.");
        }
        super.addDeporte(deporte);
    }

    @Override
    public final void removeDeporte (String deporte) {
        if (deporte.equalsIgnoreCase("Voleibol")) {
            throw new IllegalArgumentException("No se puede quitar el voleibol de un campo de voleibol.");
        }
        super.removeDeporte(deporte);
    }

    @Override
    public final String getDeporteActual() {
        return "Voleibol";
    }

    @Override
    public final void modificarDeporteActual(String deporteActual) {
        if (!deporteActual.equalsIgnoreCase("Voleibol")) {
            throw new IllegalArgumentException("No se puede jugar a otro deporte en un campo de voleibol.");
        }
        super.modificarDeporteActual(deporteActual);
    }

    @Override
    public double getPrecioAcondicionamiento() {
        return getPrecioAcondicionamientoUnitario() * (getAlturaRed() + (hasVarillas() ? 1 : 0));
    }

    @Override
    public void setPrecioAcondicionamientoUnitario(double precioAcondicionamientoUnitario) {
        this.precioAcondicionamientoUnitario = precioAcondicionamientoUnitario;
    }

    @Override
    public double getPrecioAcondicionamientoUnitario() {
        return precioAcondicionamientoUnitario;
    }

    @Override
    public void setAnchura(int anchura) {
        if (anchura != ANCHURA) {
            throw new IllegalArgumentException("No se puede cambiar la anchura de un campo de voleibol.");
        }
        super.setAnchura(anchura);
    }

    @Override
    public void setLongitud(int longitud) {
        if (longitud != LONGITUD) {
            throw new IllegalArgumentException("No se puede cambiar la longitud de un campo de voleibol.");
        }
        super.setLongitud(longitud);
    }
}
