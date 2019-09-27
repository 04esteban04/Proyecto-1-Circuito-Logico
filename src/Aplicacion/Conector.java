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

    private static int IDt = -6;
    private int ID;
    protected String Name;
    protected boolean input;
    protected int Output;
    protected int input1, input2;

    /**
     * Constructor clase Conector
     * @param image - Imagen del conector
     * @param entrada1 - Primera entrada del conector
     * @param entrada2 - Segunda entrada del conector
     * @param name - Nombre del conector
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

    /**
     * Método que establece el valor de la primera entrada del conector
     * @param entrada - Valor de la entrada
     */
    @Override
    public void setPrimeraEntrada(Conector entrada) {
        if(Name.equals("Not")){
            Entrada1=entrada;
            Entrada2=entrada;
        }else {
            this.Entrada1 = entrada;
        }
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
     * Método para obtener la imagen del conector.
     * @return - Imagen del conector.
     */
    public ImageView getImage() {
        ImageView img = new ImageView(Image);
        img.setFitWidth(70);
        img.setFitHeight(70);
        return img;
    }

    /**
     * Método que retorna el valor del input1.
     * @return - entero representativo de input 1.
     */
    public int getInput1() {
        return input1;
    }

    /**
     * Método que retorna el valor del input2.
     * @return - entero representativo de input 2.
     */
    public int getInput2() {
        return input2;
    }

    /**
     * Método que le da un valor al input1.
     * @param input1- Valor para reemplazar.
     */
    public void setInput1(int input1) {
        this.input1 = input1;
    }

    /**
     * Método que le da un valor al input2.
     * @param input2- Valor para reemplazar.
     */
    public void setInput2(int input2) {
        this.input2 = input2;
    }

    /**
     * Método para obtener el componente enlazado en el input 2.
     * @return - Componente enlazado.
     */
    public Conector getEntrada2() {
        return Entrada2;
    }

    /**
     * Método para obtener el componente enlazado en el input 1.
     * @return - Componente enlazado.
     */
    public Conector getEntrada1() {
        return Entrada1;
    }

    /**
     * Método que retorna el atributo input.
     * @return - boolean.
     */
    public boolean isInput() {
        return input;
    }

    /**
     * Método que sobreescribe el valor del booleano input.
     * @param input - Valor a reemplazar.
     */
    public void setInput(boolean input) {
        this.input = input;
    }

    /**
     * Se retorna el string que representa el tipo de componente
     * @return - Atributo Name.
     */
    public String getName() {
        return Name;
    }

    /**
     * Retorna el atributo ID.
     * @return - Entero representativo de ID.
     */
    public int getID() {
        return ID;
    }

    /**
     * Método para obtener el valor del output
     * @return - entero.
     */
    public int getOutput() {
        return Output;
    }

    /**
     * Método para establecer un valor al output.
     * @param output - entero para reemplazar.
     */
    public void setOutput(int output) {
        Output = output;
    }

    /**
     * Método para establecer un valor de IDt.
     * @param IDt - valor para reemplazar.
     */
    public static void setIDt(int IDt) {
        Conector.IDt = IDt;
    }
}