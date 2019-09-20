package Conectores;

import Aplicacion.Conector;
import Aplicacion.interfazConector;
import javafx.scene.image.Image;

/**
 * Clase para crear el conector Xnor
 */
public class Xnor extends Conector implements interfazConector {
    /*
     * Variable para obtener el nombre del conector
     */
    public static String nombre ="Xnor";

    /**
     * Constructor clase Xnor
     * @param image - Imagen del conector
     * @param entrada1 - Primera entrada del conector
     * @param entrada2 - Segunda entrada del conector
     */
    public Xnor(Image image, Conector entrada1, Conector entrada2) {
        super(image, entrada1, entrada2, nombre);
    }

    /**
     * Método que establece el valor de la primera entrada del conector
     * @param entrada - Valor de la entrada
     */
    @Override
    public void setPrimeraEntrada(Conector entrada) {
        super.setPrimeraEntrada(entrada);
    }

    /**
     * Método que establece el valor de la segunda entrada del conector
     * @param entrada - Valor de la entrada
     */
    @Override
    public void setSegundaEntrada(Conector entrada) {
        super.setSegundaEntrada(entrada);
    }

    /**
     * Método que retorna la salida del conector
     * @return - Retorna un entero según el valor de las entradas del conector
     */
    public int getSalida() {
        if (Entrada1.getSalida() == Entrada2.getSalida()){
            return 1;
        }
        else{
            return 0;
        }
    }

}
