package net.jorjai.packInfo;

/**
 * Excepción que se lanza cuando se produce un error en la reserva de una máquina.
 */
public class ReservaException extends RuntimeException{

    /**
     * Construye una excepción de reserva con mensaje de error por defecto {@code null}.
     */
    public ReservaException(){
        super();
    }

    /**
     * Construye una excepción de reserva con un mensaje de error.
     * @param message Mensaje de error.
     */
    public ReservaException(String message){
        super(message);
    }
}
