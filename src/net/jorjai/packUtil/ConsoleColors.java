package net.jorjai.packUtil;

/**
 * Clase que contiene códigos de colores para la consola.
 * Esta clase proporciona constantes de colores que se pueden usar para cambiar el color del texto en la consola.
 * Los colores se definen utilizando códigos ANSI, que son compatibles con la mayoría de las terminales.
 * Cada color se proporciona en varias variantes: regular, negrita, subrayado, fondo, alta intensidad,
 * negrita de alta intensidad y fondos de alta intensidad.
 *
 * @author Jorge Arévalo Fernández
 */
public class ConsoleColors {
		/** Constructor privado para evitar instanciación. */
		private ConsoleColors() {}
	
        /**
         * Código ANSI para resetear el color del texto.
         */
        public static final String RESET = "\033[0m";

        /**
         * Código ANSI para el color negro.
         */
        public static final String BLACK = "\033[0;30m";

        /**
         * Código ANSI para el color rojo.
         */
        public static final String RED = "\033[0;31m";

        /**
         * Código ANSI para el color verde.
         */
        public static final String GREEN = "\033[0;32m";

        /**
         * Código ANSI para el color amarillo.
         */
        public static final String YELLOW = "\033[0;33m";

        /**
         * Código ANSI para el color azul.
         */
        public static final String BLUE = "\033[0;34m";

        /**
         * Código ANSI para el color púrpura.
         */
        public static final String PURPLE = "\033[0;35m";

        /**
         * Código ANSI para el color cian.
         */
        public static final String CYAN = "\033[0;36m";

        /**
         * Código ANSI para el color blanco.
         */
        public static final String WHITE = "\033[0;37m";

        /**
         * Código ANSI para el color negro en negrita.
         */
        public static final String BLACK_BOLD = "\033[1;30m";

        /**
         * Código ANSI para el color rojo en negrita.
         */
        public static final String RED_BOLD = "\033[1;31m";

        /**
         * Código ANSI para el color verde en negrita.
         */
        public static final String GREEN_BOLD = "\033[1;32m";

        /**
         * Código ANSI para el color amarillo en negrita.
         */
        public static final String YELLOW_BOLD = "\033[1;33m";

        /**
         * Código ANSI para el color azul en negrita.
         */
        public static final String BLUE_BOLD = "\033[1;34m";

        /**
         * Código ANSI para el color púrpura en negrita.
         */
        public static final String PURPLE_BOLD = "\033[1;35m";

        /**
         * Código ANSI para el color cian en negrita.
         */
        public static final String CYAN_BOLD = "\033[1;36m";

        /**
         * Código ANSI para el color blanco en negrita.
         */
        public static final String WHITE_BOLD = "\033[1;37m";

        /**
         * Código ANSI para el color negro subrayado.
         */
        public static final String BLACK_UNDERLINED = "\033[4;30m";

        /**
         * Código ANSI para el color rojo subrayado.
         */
        public static final String RED_UNDERLINED = "\033[4;31m";

        /**
         * Código ANSI para el color verde subrayado.
         */
        public static final String GREEN_UNDERLINED = "\033[4;32m";

        /**
         * Código ANSI para el color amarillo subrayado.
         */
        public static final String YELLOW_UNDERLINED = "\033[4;33m";

        /**
         * Código ANSI para el color azul subrayado.
         */
        public static final String BLUE_UNDERLINED = "\033[4;34m";

        /**
         * Código ANSI para el color púrpura subrayado.
         */
        public static final String PURPLE_UNDERLINED = "\033[4;35m";

        /**
         * Código ANSI para el color cian subrayado.
         */
        public static final String CYAN_UNDERLINED = "\033[4;36m";

        /**
         * Código ANSI para el color blanco subrayado.
         */
        public static final String WHITE_UNDERLINED = "\033[4;37m";

        /**
         * Código ANSI para el color negro de fondo.
         */
        public static final String BLACK_BACKGROUND = "\033[40m";

        /**
         * Código ANSI para el color rojo de fondo.
         */
        public static final String RED_BACKGROUND = "\033[41m";

        /**
         * Código ANSI para el color verde de fondo.
         */
        public static final String GREEN_BACKGROUND = "\033[42m";

        /**
         * Código ANSI para el color amarillo de fondo.
         */
        public static final String YELLOW_BACKGROUND = "\033[43m";

        /**
         * Código ANSI para el color azul de fondo.
         */
        public static final String BLUE_BACKGROUND = "\033[44m";

        /**
         * Código ANSI para el color púrpura de fondo.
         */
        public static final String PURPLE_BACKGROUND = "\033[45m";

        /**
         * Código ANSI para el color cian de fondo.
         */
        public static final String CYAN_BACKGROUND = "\033[46m";

        /**
         * Código ANSI para el color blanco de fondo.
         */
        public static final String WHITE_BACKGROUND = "\033[47m";

        /**
         * Código ANSI para el color negro de alta intensidad.
         */
        public static final String BLACK_BRIGHT = "\033[0;90m";

        /**
         * Código ANSI para el color rojo de alta intensidad.
         */
        public static final String RED_BRIGHT = "\033[0;91m";

        /**
         * Código ANSI para el color verde de alta intensidad.
         */
        public static final String GREEN_BRIGHT = "\033[0;92m";

        /**
         * Código ANSI para el color amarillo de alta intensidad.
         */
        public static final String YELLOW_BRIGHT = "\033[0;93m";

        /**
         * Código ANSI para el color azul de alta intensidad.
         */
        public static final String BLUE_BRIGHT = "\033[0;94m";

        /**
         * Código ANSI para el color púrpura de alta intensidad.
         */
        public static final String PURPLE_BRIGHT = "\033[0;95m";

        /**
         * Código ANSI para el color cian de alta intensidad.
         */
        public static final String CYAN_BRIGHT = "\033[0;96m";

        /**
         * Código ANSI para el color blanco de alta intensidad.
         */
        public static final String WHITE_BRIGHT = "\033[0;97m";

        /**
         * Código ANSI para el color negro en negrita de alta intensidad.
         */
        public static final String BLACK_BOLD_BRIGHT = "\033[1;90m";

        /**
         * Código ANSI para el color rojo en negrita de alta intensidad.
         */
        public static final String RED_BOLD_BRIGHT = "\033[1;91m";

        /**
         * Código ANSI para el color verde en negrita de alta intensidad.
         */
        public static final String GREEN_BOLD_BRIGHT = "\033[1;92m";

        /**
         * Código ANSI para el color amarillo en negrita de alta intensidad.
         */
        public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";

        /**
         * Código ANSI para el color azul en negrita de alta intensidad.
         */
        public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";

        /**
         * Código ANSI para el color púrpura en negrita de alta intensidad.
         */
        public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";

        /**
         * Código ANSI para el color cian en negrita de alta intensidad.
         */
        public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";

        /**
         * Código ANSI para el color blanco en negrita de alta intensidad.
         */
        public static final String WHITE_BOLD_BRIGHT = "\033[1;97m";

        /**
         * Código ANSI para el color negro de fondo de alta intensidad.
         */
        public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";

        /**
         * Código ANSI para el color rojo de fondo de alta intensidad.
         */
        public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";

        /**
         * Código ANSI para el color verde de fondo de alta intensidad.
         */
        public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";

        /**
         * Código ANSI para el color amarillo de fondo de alta intensidad.
         */
        public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";

        /**
         * Código ANSI para el color azul de fondo de alta intensidad.
         */
        public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";

        /**
         * Código ANSI para el color púrpura de fondo de alta intensidad.
         */
        public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m";

        /**
         * Código ANSI para el color cian de fondo de alta intensidad.
         */
        public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";

        /**
         * Código ANSI para el color blanco de fondo de alta intensidad.
         */
        public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";
}