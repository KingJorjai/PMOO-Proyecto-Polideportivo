package net.jorjai.packInstalaciones;

/**
 * Interfaz que define el comportamiento de las instalaciones que pueden ser
 * acondicionadas.
 *
 * @author Jorge Arévalo Fernández
 */
public interface Acondicionable {

	/**
	 * Devuelve el precio del acondicionamiento de la instalación.
	 * 
	 * @return Precio del acondicionamiento de la instalación.
	 */
    double getPrecioAcondicionamiento();
	
	/**
	 * Establece el precio del acondicionamiento unitario de la instalacion.
	 * 
	 * @param precioAcondicionamientoUnitario Precio del acondicionamiento unitario
	 *                                        de la instalacion.
	 */
    void setPrecioAcondicionamientoUnitario(double precioAcondicionamientoUnitario);

	/**
	 * Devuelve el precio del acondicionamiento unitario de la instalacion.
	 *
	 * @return Precio del acondicionamiento unitario de la instalacion.
	 */
    double getPrecioAcondicionamientoUnitario();
}
