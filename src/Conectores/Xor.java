package Conectores;

import Aplicacion.Conector;
import Aplicacion.interfazConector;
import javafx.scene.image.Image;

/**
 * Clase para crear el conector And
 */
public class Xor extends Conector implements interfazConector {
    /*
     * Variable para obtener el nombre del conector
     */
    public static String nombre ="Xor";

    /**
     * Constructor clase Xor
     * @param image - Imagen del conector
     * @param entrada1 - Primera entrada del conector
     * @param entrada2 - Segunda entrada del conector
     */
    public Xor(Image image, int entrada1, int entrada2) {
        super(image, entrada1, entrada2);
    }

    /**
     * Método que establece el valor de la primera entrada del conector
     * @param entrada - Valor de la entrada
     */
    @Override
    public void setPrimeraEntrada(int entrada) {
        super.setPrimeraEntrada(entrada);
    }

    /**
     * Método que establece el valor de la segunda entrada del conector
     * @param entrada - Valor de la entrada
     */
    @Override
    public void setSegundaEntrada(int entrada) {
        super.setSegundaEntrada(entrada);
    }

    /**
     * Método que retorna la salida del conector
     * @return - Retorna un entero según el valor de las entradas del conector
     */
    public int getSalida() {
        if (Entrada1 == Entrada2){
            return 0;
        }
        else{
            return 1;
        }
    }

}