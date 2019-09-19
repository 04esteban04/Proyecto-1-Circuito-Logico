package Aplicacion;

import Conectores.*;
import javafx.scene.image.Image;

/**
 * Clase que se utiliza como molde para crear las instancias de los conectores
 */
public class ConectorFactory {

    /**
     * Método que instancia cada clase hija de conector según el nombre ue recibe
     * @param nombre - Tipo de conector a instanciar
     * @return - Instancia de la clase conector
     */
    public static Conector crearComponente(String nombre){
        if (nombre.equals(And.nombre)){
            return new And(new Image("ImagenesComponentes/and-gate.png"),0,0);
        }
        else if (nombre.equals(Nand.nombre)){
            return new Nand(new Image("ImagenesComponentes/nand-gate.png"),0,0);
        }
        else if (nombre.equals(Or.nombre)){
            return new Or(new Image("ImagenesComponentes/or-gate.png"),0,0);
        }
        else if (nombre.equals(Nor.nombre)){
            return new Nor(new Image("ImagenesComponentes/nor-gate.png"),0,0);
        }
        else if(nombre.equals(Not.nombre)){
            return new Not(new Image("ImagenesComponentes/not-gate.png"),0,0);
        }
        else if (nombre.equals(Xor.nombre)){
            return new Xor(new Image("ImagenesComponentes/xor-gate.png"),0,0);
        }
        else if (nombre.equals(Xnor.nombre)){
            return new Xnor(new Image("ImagenesComponentes/xnor-gate.png"),0,0);
        }
        else{
            return new Conector(null,0,0);
        }
    }
}