package net.jorjai.packUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Clase con métodos útiles para instanciar clases y obtener atributos de objetos.
 *
 * @author Jorge Arévalo Fernández
 */
public final class Util {
	
	/** Constructor privado para evitar instanciación. */
	private Util() {}
	
    /**
    * Dado clase#atributo1#atributo2#...#atributoN, devuelve una clase del tipo
    * y con atributos correspondientes.
    *
    * @param txtArgs     Array de Strings con el nombre y los atributos de la clase.
    *                    Los atributos solo pueden ser tipos primitivos o String.
    * @param rutaPaquete Ruta del paquete donde se encuentra la clase.
    * @return            Objeto instanciado.
    * @throws IllegalArgumentException Si no se encuentra la clase, el constructor o
    *                               si ocurre un error al instanciar la clase.
    * @throws ClassNotFoundException Si no se encuentra la clase en la ruta dada.
    */
    public static Object instanciarClase(String[] txtArgs, String rutaPaquete) throws ClassNotFoundException {
        Class<?> clase;
        Constructor<?> constructor = null;
        Object[] args = null;
        Object instancia;
        ArrayList<Object> argsList = new ArrayList<>();

        // Encuentra la clase
        try {
            clase = Class.forName(rutaPaquete+"."+txtArgs[0]);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("No se encontró la clase " + txtArgs[0] + " en el paquete " + rutaPaquete + ".");
        }

        // Establecer el constructor
        for (Constructor<?> c : clase.getConstructors()) {
            if(constructor != null) break;
            if (c.getParameterCount() == txtArgs.length - 1) {
                for (int i=1; i<c.getAnnotatedParameterTypes().length+1; i++) {
                    try {
                        String tipo = c.getAnnotatedParameterTypes()[i - 1].getType().getTypeName();
                        String argumento = txtArgs[i];
                        switch (tipo) {
                            case "java.lang.String":
                                argsList.add(argumento);
                                break;
                            case "double":
                                argsList.add(Double.parseDouble(argumento));
                                break;
                            case "int":
                                argsList.add(Integer.parseInt(argumento));
                                break;
                            case "boolean":
                                if (argumento.equalsIgnoreCase("true") ||
                                    argumento.equalsIgnoreCase("false")) {
                                    argsList.add(Boolean.parseBoolean(argumento));
                                } else {
                                    throw new InputMismatchException(
                                            "El argumento " + argumento + " no es válido para un boolean."
                                    );
                                }
                                break;
                            default:
                                throw new Exception("Ha ocurrido un error inesperado.");
                        }
                    } catch (Exception e) {
                        argsList.clear();
                    }
                    if (argsList.size() == c.getParameterCount()) {
                            args = argsList.toArray();
                            constructor = c;
                            break;
                    }
                }
            }
        }
        if (constructor == null) {
            throw new IllegalArgumentException("No se ha encontrado ningún un constructor de la clase " +
                    clase.getSimpleName() + " que coincida con los argumentos dados.");
        }

        // Instanciar
        try {
            instancia = constructor.newInstance(args);
        } catch (InstantiationException e) {
            throw new IllegalArgumentException("La clase " + clase.getSimpleName() + " no se puede instanciar.");
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("No se puede acceder al constructor de la clase " + clase.getSimpleName() + ".");
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException("Ha ocurrido un error al instanciar la clase " +
                    clase.getSimpleName() + ": " + e.getCause().getMessage());
        }

        return instancia;
    }
}
