package Aplicacion;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Clase molde para la creación de los conectores específicos
 */
public class Conector implements interfazConector {
    /**
     * Variable que almacena la imagen del conector
     */
    private Image Image;
    /**
     * Variable que almacena el valor de la primera entrada del conector
     */
    protected Conector Entrada1;
    /**
     * Variable que almacena el valor de la segunda entrada del conector
     */
    protected Conector Entrada2;

    private static int IDt;
    private int ID;
    protected String Name;
    protected boolean input;



    /**
     * Constructor clase Conector
     * @param image - Imagen del conector
     * @param entrada1 - Primera entrada del conector
     * @param entrada2 - Segunda entrada del conector
     */
    public Conector(Image image, Conector entrada1, Conector entrada2, String name) {
        Image = image;
        Entrada1 = entrada1;
        Entrada2 = entrada2;
        Name = name;
        ID = IDt;
        IDt++;
        input = false;
    }


    public Conector getPrimeraEntrada(){
        return Entrada1;
    }
    public Conector getSegundaEntrada(){
        return Entrada2;
    }
    public String getName() {
        return Name;
    }
    public int getID() {
        return ID;
    }
    public void setInput(boolean input) {
        this.input = input;
    }

    /**
     * Método que establece el valor de la primera entrada del conector
     * @param entrada - Valor de la entrada
     */
    @Override
    public void setPrimeraEntrada(Conector entrada) {
        this.Entrada1=entrada;
    }

    /**
     * Método que establece el valor de la segunda entrada del conector
     * @param entrada - Valor de la entrada
     */
    @Override
    public void setSegundaEntrada(Conector entrada) {
        this.Entrada2=entrada;
    }


    /**
     * Método que retorna la salida del conector
     * @return - Retorna un entero según el valor de las entradas del conector
     */
    @Override
    public int getSalida() {
        return 0;
    }

    /**
     * Metodo para obtener la imagen del conector.
     * @return - Imagen del conector.
     */
    public ImageView getImage() {
        ImageView img = new ImageView(Image);
        img.setFitWidth(70);
        img.setFitHeight(50);
        return img;
    }
}