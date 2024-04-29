package net.jorjai.packInstalaciones;

/*
 * Programación Modular y Orientación a Objetos Proyecto
2023/2024
1
Proyecto: Polideportivo
Se desea realizar una aplicación para la gestión de un polideportivo. Esta aplicación contará con
información sobre las diferentes instalaciones del polideportivo y las reservas de sus máquinas de
entrenamiento.
Para la implementación de este proyecto se han establecido tres partes que debes realizar secuencialmente, siendo aconsejable que, antes de empezar cada una de ellas, te asegures de que las
anteriores están completas y funcionan correctamente.
Cada clase del proyecto debe contener los métodos toString y equals, que tendrás que sobrescribir cuando sea necesario. También debes implementar los métodos get y set necesarios
para los objetivos de la aplicación, aunque no se pidan explícitamente en el enunciado (¡¡sólo los
necesarios!!). Incluye la documentación general de todas las clases (la que describe las características generales de la clase: autor, nombre de la clase, entidad que representa, …) y la que se
pida específicamente para algunos métodos siguiendo, en todos los casos, el estilo Javadoc. Al
finalizar el proyecto, genera la documentación de todas sus clases.
Requisitos de la aplicación Polideportivo (primera parte)
Información básica de las instalaciones y del proceso de gestión de las reservas de las máquinas del polideportivo
Para esta parte se han diseñado las clases TablaReservas, MaquinaFitness, Instalacion
y Polideportivo.
• La clase TablaReservas (incluida en el paquete packMaquinas) recoge las reservas de una
máquina para 24 horas, utilizando una estructura Array de booleanos, que indica si la máquina está (o no) reservada para la hora correspondiente a cada posición. Constará de los siguientes métodos:
o Constructor sin parámetros: inicializa el Array de reservas de la máquina, marcando todas sus posiciones como libres (NO reservadas).
o reservar: dado un número, reserva la máquina para la hora correspondiente a dicho número. Devuelve el valor true si se ha podido realizar la reserva correctamente.
o numeroReservas: devuelve la cantidad de horas en las que la máquina está reservada.
o primeraLibre: devuelve la primera hora a la que la máquina está libre. Si está reservada
a todas las horas devolverá -1.
o estaLibre: dado un número entero, devuelve si la máquina está libre a la hora correspondiente al número dado.
o Sobrescribe el método toString para que devuelva un String con el estado de reservas de
la máquina en cada una de las 24 horas recogidas en su TablaReservas. Este String debe
tener el siguiente formato:
“[true, false, true, true, true, true, false, true, true, true, true, true, true, true, true, true,
false, true, true, true, true, true, true, true, true, true, true, true]”
o descargar: libera todas las horas de reserva.
• La clase MaquinaFitness contiene la información de una máquina de entrenamiento. Esta
clase debe crearse dentro del paquete packMaquinas. Cada máquina guarda información sobre su nombre, su tipo, los años1 que tiene (nunca más de 20, siendo 0 su valor por

1 No utilices la letra ñ en los nombres de atributos, ni de métodos, ni clases
Programación Modular y Orientación a Objetos Proyecto
2023/2024
2
defecto) y su tabla de reservas (de tipo TablaReservas). Además de estos atributos,
la MaquinaFitness contiene los métodos que se describen a continuación:
 Dos constructores:
1. Recibe como parámetro un String para el nombre de la MaquinaFitness.
2. Recibe como parámetros un String para el nombre de la MaquinaFitness que se
va a crear y un entero para los años que tiene. Usa el método setAnios para asignar
al atributo años que tiene, el número recibido como parámetro, tras comprobar
que es un valor válido (valor entre 0 y 20, ambos incluidos).
 Métodos:
o estaLibre: dada una hora, indica si la máquina está libre a esa hora.
o reservar: dada una hora, si la máquina está libre a dicha hora, la reserva.
o setAnios: dado un valor entero que representa una cantidad de años, comprueba que
dicho valor esta entre 0 y 20 (ambos incluidos). En caso afirmativo, asigna dicho
valor al atributo años que tiene de la instancia de MaquinaFitnes desde la que
se invocó el método. Si la cantidad de años recibida como parámetro no es válida, no
hace nada.
o equals: dos máquinas son iguales si su nombre es igual.
o Sobrescribe el método de toString para que devuelva un String con el formato:
nombre#tipo#años
• La clase Instalacion tiene un nombre, un código (constante, diferente para cada instancia de Instalacion, y que se genera automáticamente), horas de apertura y cierre
(sólo se guardará la hora sin minutos, por lo que ambas serán números enteros). Por defecto,
las instalaciones se abrirán a las 7 de la mañana y se cerrarán a las 10 de la noche). Además,
cada Instalacion contará con una lista de nombres de deportes que se pueden
practicar en ella, implementada como un Array. Una instalación puede admitir un máximo
de 8 deportes. También se guardará el nombre del deporte actual. Esta clase debe
crearse en el paquete packInstalaciones.
Métodos de esta clase:
o Constructor: recibe como parámetro un String que será el nombre de la instalación.
o equals: dos Instalaciones son iguales si tienen el mismo nombre.
o seAcepta: método auxiliar que, dado el nombre de un deporte, indica si dicho nombre de deporte está incluido en la lista de nombres de deportes de la instalación
actual (la utilizada para invocar el método).
o addDeporte: dado el nombre de un deporte, lo añade a la lista de nombres de
deportes de la Instalacion, si es posible (queda espacio y no está ya incluido en la
lista de nombres de deportes admitidos en la instalación actual.
o modificarDeporteActual: método que, dado un deporte, si está en la lista de nombres de deportes posibles, lo asigna como nombre del deporte actual.
• Se distinguen diferentes tipos de Instalaciones que se describen a continuación. Las distintas clases de Instalacion, deben definirse en el paquete packInstalaciones.
Las Instalaciones se dividen en dos clases principales: Exteriores e Interiores.
Las Exteriores contienen el año en el que se construyeron y pueden ser de dos
tipos: Campos de fútbol y Frontones. Los Campos de fútbol guardan si tienen gradas (true), o no (false). Cada Fronton recoge también, si es o no cubierto y cuántos
Programación Modular y Orientación a Objetos Proyecto
2023/2024
3
metros cuadrados tiene. Además, el constructor que recibe como parámetro el nombre del Fronton, debe establecer si es cubierto. Por defecto, los Frontones NO son cubiertos.
Por último, las instalaciones Interiores guardan su longitud y anchura, y NO debe poder crearse instancias de este tipo directamente. Se distinguen dos tipos de instalaciones Interiores: Piscinas y pistas de Padel. Las Piscinas guardan la cantidad de cloro
que tienen, y las pistas de Padel, el color de las paredes.
Para cualquier objeto de Instalacion, el método inheritancePath devuelve un String
con el camino de la jerarquía que va desde la clase hoja hasta la clase Instalación (String
con los nombres de las clases del camino). Además, las instalaciones contendrán, también, el
método printInheritancePath2
, que escribe en pantalla el String correspondiente a la vía
hereditaria de una Instalacion.
Las Instalaciones también pueden alquilarse, por lo que será necesario tener un método
que calcule el precio de alquiler de las distintas clases de Instalacion. El precio
de alquiler de las Interiores es siempre 40 euros. En el caso de los Campos de fútbol es (40 ─ cantidad de años desde que se construyó) * 3. En el caso de Frontones, el precio, en general, será de 2 * m2
, incrementándose en 20 euros si son cubiertos.
Las instalaciones Exteriores deben incluir otro método para actualizar sus horarios
de apertura y cierre. Este método recibe dos parámetros de tipo entero, correspondientes
a los nuevos valores para estos horarios, pero antes de hacer la actualización de los atributos,
se debe comprobar que la hora de cierre es mayor que la hora de apertura; si no
fuera así, no se asignarán dichos valores a los atributos correspondiente. En el caso de los
Frontones, además, la hora de cierre NO puede ser posterior a 22. Si los valores a
asignar no son correctos, no se modificará el valor de los atributos y se escribirá un mensaje
de error en la pantalla.
Por otro lado, hay ocasiones en las que el Polideportivo tiene que acondicionar algunas
Instalaciones, siendo necesario calcular el precio de acondicionamiento a partir
del precio de acondicionamiento unitario (precio de condicionar 1m ó de 1m2
). Se pueden
acondicionar Frontones y pistas de Padel. En el caso de los Frontones, dado el precio
unitario, el precio de acondicionamiento se obtiene multiplicando sus metros cuadrados por el
precio de acondicionamiento unitario. En el caso de las pistas de Padel será el producto del
ancho de la pista por el precio de acondicionamiento unitario.
• La clase Polideportivo (definida en packPolideportivo), debe implementarse de
forma que sea posible garantizar que sólo va a existir una instancia de esta clase. Se utiliza
para guardar y gestionar la información de las Instalaciones y de las MaquinasFitness
del Polideportivo. Para ello, guardará dos listas (implementadas como ArrayList):
lista de máquinas y lista de instalaciones. En esta clase se deben implementar
los siguientes métodos:
o registrarInstalacion: dada una Instalacion, la incorpora al Polideportivo.
o registrarMaquina: dada una MaquinaFitness, la añade a la lista de máquinas del
Polideportivo, si no estaba ya incluida y la lista no está llena.

2 getClass().getSimpleName() devuelve un String con el nombre de la clase de un objeto
Programación Modular y Orientación a Objetos Proyecto
2023/2024
4
o masReservas: dado un número, devuelve un ArrayList con los nombres de las máquinas que tengan un número de reservas mayor que el dado.
o getMaquinaLibre: dado un tipo de máquina y una hora, devuelve la primera máquina del
tipo dado, disponible a la hora indicada. Si no hay ninguna máquina del tipo pedido que
esté libre, devolverá null
o reservarMaquina: dado un tipo de máquina y una hora, reservará la primera máquina
del tipo indicado que esté libre a la hora indicada. Si no hay ninguna máquina del tipo
deseado, libre a la hora indicada mostrará un mensaje informativo de esta situación y no
hará ninguna reserva.
o mostrar en pantalla el nombre y la hora de apertura de todas las Instalaciones que estén
almacenadas.
o ordenarMáquinas: ordena ascendentemente según su orden natural (por nombre) las máquinas del Polideportivo.
o Mostrar3Frontones: muestra en pantalla el nombre y si son cubiertos (o no) de los 3
primeros Frontones del Polideportivo.
o cuantasAcondicionar: devuelve el número de Instalaciones del Polideportivo
que se pueden acondicionar.
o mostrarMáquinas: muestra la información de todas las MaquinaFitness que hay en el
Polideportivo.
o mostrarInstalaciones: muestra la información de todas las Instalaciones del Polideportivo.
o borrarMáquinas: dada un número, elimina las máquinas cuyo número de años es
igual al dado. Devuelve el número de máquinas eliminadas (debe implementarse utilizando
Iterator). 
 */
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
