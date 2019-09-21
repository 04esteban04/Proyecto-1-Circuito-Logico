package Extras;
/*
import Aplicacion.*;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import static Extras.Rectangular.*;
*/
//CORREGIR LÍNEAS ENTRE COMPONENTES

/**
 * Clase que simplifica el código para generar eventos
 */
/*
public class MejoraEventos {

    private static Circular entrada1, entrada2, salida;
    private static Rectangle rectangle;
    private static double orgSceneX,orgSceneY;
    private static double orgTranslateX,orgTranslateY;
    public static int input = 1;
    public static int output = 1;
    public static Line linea;
*/
    /*
    /**
     * Método que administra el drag sobre el conector del palette
     * @param evento - Evento de mouse al mover la imagen
     * @param imagenConector - Imagen del conector seleccionado
     * @param nombre - Nombre del conector seleccionado
     */
    /*
   public static final void DragDetected(MouseEvent evento, ImageView imagenConector, String nombre){
        Dragboard db = imagenConector.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(nombre);
        db.setContent(content);
        evento.consume();
    }
    */
    /*
    /**
     * Método que coloca la imagen en un rectángulo para colocarlo en el panel principal
     * @param evento - Evento de drag al mover la imagen
     * @param imagenConector - Imagen del conector.
     */
    /*
    private static void DroppedAux(DragEvent evento,ImageView imagenConector){

        Label numEntrada1 = new Label("i");
        Label numEntrada2 = new Label("i");
        Label numSalida = new Label("o");

        numEntrada1.setText("i");
        numEntrada2.setText("i");
        numSalida.setText("o");

        numEntrada1.setText(numEntrada1.getText() + input);
        numEntrada1.setLayoutX(evento.getX());
        numEntrada1.setLayoutY(evento.getY() - 15);
        input++;

        numEntrada2.setText(numEntrada2.getText() + input);
        numEntrada2.setLayoutX(evento.getX() - 20);
        numEntrada2.setLayoutY(evento.getY() + 35);
        input ++ ;

        numSalida.setText(numSalida.getText() + output);
        numSalida.setLayoutX(evento.getX() + 50);
        numSalida.setLayoutY(evento.getY() - 5);
        output ++ ;

        entrada1 = new Circular(evento.getX(), evento.getY() + 13);
        entrada2 = new Circular(evento.getX(), evento.getY() + 28);
        salida = new Circular(evento.getX() + 55,evento.getY() + 19.85);

        rectangle = new Rectangular(60, 55, imagenConector, evento, entrada1, entrada2, salida, numEntrada1, numEntrada2, numSalida);
        AplicacionMain.Group.getChildren().addAll(rectangle,entrada1, entrada2, salida, numEntrada1, numEntrada2, numSalida);
    }
    */
    /*
    /**
     * Método que introduce en una lista enlazada cada conector cuando se suelta la imagen del conector
     * @param evento - Evento de drag al mover la imagen
     */

    /*
    public static final void Dropped(DragEvent evento, ImageView[] i){
        int cont = 0;
        for (String x : AplicacionMain.nombreConector){
            if (evento.getDragboard().getString().equals(x)){
                MejoraEventos.DroppedAux(evento, i[cont]);
                if (AplicacionMain.lista.getLargo() == 0){
                    AplicacionMain.lista.InsertarInicio(new ConectorFactory().crearComponente(x)); }
                else{
                    AplicacionMain.lista.InsertarFinal(new ConectorFactory().crearComponente(x)); }
            }
            cont++;
        }
    }

    public static void Play(MouseEvent e){
        for (int x=0;x<AplicacionMain.lista.getLargo();x++){
            Conector c = AplicacionMain.lista.Obtener(x);
            System.out.println(c.getPrimeraEntrada() +""+ c.getSegundaEntrada());
        }
    }
}
*/

import Aplicacion.*;
import Conectores.*;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;


public class MejoraEventos {

    /**
     * Este metodo gestiona el drag de la imagen
     * que se encuentra en la paleta.
     *
     * @param e    - Evento de mouse al mover la imagen.
     * @param Comp - Imagen del componente.
     * @param Name - Nombre del componente que se está moviendo.
     */
    public static void DragDetected(MouseEvent e, ImageView Comp, String Name) {
        Dragboard db = Comp.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(Name);
        db.setContent(content);
        e.consume();
    }

    /**
     * Este metodo es un auxiliar del evento drop de la imagen.
     * En este se coloca la imagen en un rectangulo para la
     * implementacion en el panel principal.
     *
     * @param evento - Evento tipo drag.
     * @param i - Imagen del componente.
     * @param C - String para identificar el componente.
     */
    private static void DroppedAux(DragEvent evento, ImageView i, String C, Conector c) {
        Circular output;
        Circular input;
        Rectangle rectangle;
        int labelEntrada = 1;
        int labelSalida = 1;
        Line linea;
        if (!C.equals("NOT")) {
            output = new Circular(evento.getSceneX(), evento.getSceneY() + 10, c, "output");
            Circular outputII = new Circular(evento.getSceneX(), evento.getSceneY() + 30, c, "output");
            input = new Circular(evento.getSceneX() + 50, evento.getSceneY() + 20, c, "input");

            Label numEntrada1 = new Label("i");
            Label numEntrada2 = new Label("i");
            Label numSalida = new Label("o");

            //numEntrada1.setText("i");
            //numEntrada2.setText("i");
            //numSalida.setText("o");

            numEntrada1.setText(numEntrada1.getText() + labelEntrada);
            numEntrada1.setLayoutX(evento.getX());
            numEntrada1.setLayoutY(evento.getY() - 15);
            labelEntrada++;

            numEntrada2.setText(numEntrada2.getText() + labelEntrada);
            numEntrada2.setLayoutX(evento.getX() - 20);
            numEntrada2.setLayoutY(evento.getY() + 35);
            labelEntrada ++ ;

            numSalida.setText(numSalida.getText() + labelSalida);
            numSalida.setLayoutX(evento.getX() + 50);
            numSalida.setLayoutY(evento.getY() - 5);
            labelSalida++;

            rectangle = new Rectangular(50, 40, i, evento, output, outputII, input, numEntrada1, numEntrada2, numSalida);
            AplicacionMain.Group.getChildren().addAll(rectangle, output, outputII, input, numEntrada1, numEntrada2, numSalida);

        } else {
            output = new Circular(evento.getSceneX(), evento.getSceneY() + 20, c, "output");
            input = new Circular(evento.getSceneX() + 50, evento.getSceneY() + 20, c, "input");

            Label numEntrada1 = new Label("i");
            Label numEntrada2 = new Label("i");
            Label numSalida = new Label("o");

            //numEntrada1.setText("i");
            //numEntrada2.setText("i");
            //numSalida.setText("o");

            numEntrada1.setText(numEntrada1.getText() + input);
            numEntrada1.setLayoutX(evento.getX());
            numEntrada1.setLayoutY(evento.getY() - 15);
            labelEntrada++;

            numEntrada2.setText(numEntrada2.getText() + input);
            numEntrada2.setLayoutX(evento.getX() - 20);
            numEntrada2.setLayoutY(evento.getY() + 35);
            labelEntrada ++ ;

            numSalida.setText(numSalida.getText() + output);
            numSalida.setLayoutX(evento.getX() + 50);
            numSalida.setLayoutY(evento.getY() - 5);
            labelSalida++;

            rectangle = new Rectangular(50, 40, i, evento, output, null, input, numEntrada1, numEntrada2, numSalida);
            AplicacionMain.Group.getChildren().addAll(rectangle, output, input, numEntrada1, numEntrada2, numSalida);
        }
    }

    /**
     * Este metodo gestiona el drop de la imagen del componente.
     * Identifica que componente se está moviendo, imprime en un TextArea
     * e introduce en una lista enlazada cada componente.
     *
     * @param e - Evento tipo DragEvent.
     */
    public static void Dropped(DragEvent e, ImageView[] i) {
        int cont = 0;
        for (String x : AplicacionMain.nombreConector) {
            if (e.getDragboard().getString().equals(x)) {
                Conector c = new ConectorFactory().crearComponente(x);
                MejoraEventos.DroppedAux(e, i[cont], x, c);
                //AplicacionMain.AreaText.appendText("Agrega componente "+ x +"\n");
                if (AplicacionMain.lista.getLargo() == 0) {
                    AplicacionMain.lista.InsertarInicio(c);
                } else {
                    AplicacionMain.lista.InsertarFinal(c);
                }
            }
            cont++;
        }
    }

    public static void Play(MouseEvent e) {
        for (int x = 0; x < AplicacionMain.lista.getLargo(); x++) {
            Conector c = AplicacionMain.lista.Obtener(x);
            System.out.println(c.getPrimeraEntrada() + "" + c.getSegundaEntrada());
        }
    }
}



