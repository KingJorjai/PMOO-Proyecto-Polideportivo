package net.jorjai.packUtil;

/**
 * Clase que imprime tablas.
 */
public class Tablas {

    private static int maxWidth = 0;


    /**
     * Imprime una tabla con los elementos de la matriz.
     *
     * @param matrix    la matriz
     * @param decimales el número de decimales
     */
    public static void printTable (Object[][] matrix, int decimales) {

        int rows = matrix.length;
        int columns = matrix[0].length;

        System.out.print("\033[0m"); // Resetear colores

        // ----- PRIMERA FILA -----

        // ----- SEPARADOR DE FILAS -----

        separadorDeFilas(matrix,rows,columns);

        // ------------------------------

        // ------------------------

        // ----- CASO GENERAL -----

        for (int k=0; k<rows; k++) {

            // ----- RECORRER UNA FILA -----

            for (int j=0; j<columns; j++){

                // ----- CALCULAR ANCHURA MÁXIMA -----

                for (int i=0; i<rows; i++) {

                    maxWidth = Math.max(matrix[i][j].toString().length(), maxWidth);
                }

                // -----------------------------------

                // ----- ESCRIBIR ELEMENTO M[K,J] -----

                escribirElemento(matrix, k, j, decimales);

                // ------------------------------------

            }

            System.out.println("|");

            // ----- SEPARADOR DE FILAS -----

            separadorDeFilas(matrix,rows,columns);

            // ------------------------------
        }


        System.out.println();

    }

    private static void separadorDeFilas(Object[][] matrix, int rows,int columns) {

        for (int j=0; j<columns; j++){

            for (int i=0; i<rows; i++) {

                maxWidth = Math.max(matrix[i][j].toString().length(), maxWidth);
            }

            System.out.printf("+");
            for (int a=0; a<maxWidth+2; a++) {
                System.out.print("-");
            }
            maxWidth = 1;
        }

        System.out.println("+");
    }

    private static void escribirElemento(Object[][] matrix, int k, int j, int decimales) {
        Object elemento = matrix[k][j];
        // Redondear
        if (elemento instanceof Number)
            try {
                elemento = String.format("%."+decimales+"f", elemento);
            } catch (Exception ignored) {}

        // Imprimir
        System.out.print("| ");
        System.out.print(
                (k==0 ? "\033[1;97m" : "") +
                elemento.toString() +
                "\033[0m");
        for (int a=0; a<maxWidth-elemento.toString().length()+1; a++) {
            System.out.print(" ");
        }
        maxWidth = 1;
    }
}

