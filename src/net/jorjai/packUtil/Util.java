package net.jorjai.packUtil;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class Util {
    /**
    * Dado clase#atributo1#atributo2#...#atributoN, devuelve una clase del tipo
    * y con atributos correspondientes.
    *
    * @param txtArgs     Array de Strings con el nombre y los atributos de la clase.
    *                    Los atributos solo pueden ser tipos primitivos o String.
    * @param rutaPaquete Ruta del paquete donde se encuentra la clase.
    */
    public static Object instanciarClase(String[] txtArgs, String rutaPaquete) {
        Class<?> clase = null;
        Constructor<?> argsConstructor = null;
        Object[] args = null;
        Object instancia = null;
        ArrayList<Object> argsList = new ArrayList<Object>();

        try{
            // Encuentra la clase
            clase = Class.forName(rutaPaquete+txtArgs[0]);

            // Establecer el constructor
            argsConstructor = clase.getConstructors()[0];

            // Preparar argumentos del constructor
            for (int i=1; i<argsConstructor.getAnnotatedParameterTypes().length+1; i++) {
                String tipo = argsConstructor.getAnnotatedParameterTypes()[i - 1].getType().getTypeName();
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
                        argsList.add(Boolean.parseBoolean(argumento));
                        break;
                    default:
                        throw new Exception("Ocurrió un error inesperado. "
                                + "Contacte con el administrador o el desarrollador.");
                }
            }

            args = argsList.toArray();

            // Instanciar
            instancia = argsConstructor.newInstance(args);
        } catch (Exception e) {
            System.out.println("Error al instanciar la clase: " + e.getMessage());
        }

        return instancia;
    }

    /**
    * Dado un objeto, devuelve un array de Strings con el nombre de la clase y los atributos del objeto.
    *
    * @param obj Objeto del que se quieren obtener los atributos.
    * @return Array de Strings con los atributos del objeto.
    */
    public String[] obtenerAtributos(Object obj) {
        return obj.toString().split("#");
    }
}
