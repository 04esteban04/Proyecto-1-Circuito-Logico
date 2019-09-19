package Extras;

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

/**
 * Clase que simplifica el código para generar eventos
 */
public class MejoraEventos {

    private static Circular entrada1, entrada2, salida;
    private static Rectangle rectangle;
    private static double orgSceneX,orgSceneY;
    private static double orgTranslateX,orgTranslateY;
    public static int input = 1;
    public static int output = 1;
    public static Line linea;


    /**
     * Método que administra el drag sobre el conector del palette
     * @param evento - Evento de mouse al mover la imagen
     * @param imagenConector - Imagen del conector seleccionado
     * @param nombre - Nombre del conector seleccionado
     */
   public static final void DragDetected(MouseEvent evento, ImageView imagenConector, String nombre){
        Dragboard db = imagenConector.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(nombre);
        db.setContent(content);
        evento.consume();
    }

    /**
     * Método que coloca la imagen en un rectángulo para colocarlo en el panel principal
     * @param evento - Evento de drag al mover la imagen
     * @param imagenConector - Imagen del conector.
     */
    private static void DroppedAux(DragEvent evento,ImageView imagenConector){
        /*
        Label numEntrada1 = new Label("i");
        Label numEntrada2 = new Label("i");
        Label numSalida = new Label("o");

        numEntrada1.setText("i");
        numEntrada2.setText("i");
        numSalida.setText("o");

        numEntrada1.setText(numEntrada1.getText() + input);
        numEntrada1.setLayoutX(evento.getSceneX());
        numEntrada1.setLayoutY(evento.getSceneY() - 15);
        input++;

        numEntrada2.setText(numEntrada2.getText() + input);
        numEntrada2.setLayoutX(evento.getSceneX() - 20);
        numEntrada2.setLayoutY(evento.getSceneY() + 35);
        input ++ ;

        numSalida.setText(numSalida.getText() + output);
        numSalida.setLayoutX(evento.getSceneX() + 50);
        numSalida.setLayoutY(evento.getSceneY() - 5);
        output ++ ;
        */
        entrada1 = new Circular(evento.getSceneX(), evento.getSceneY() + 13);
        entrada2 = new Circular(evento.getSceneX(), evento.getSceneY() + 28);
        salida = new Circular(evento.getSceneX() + 55,evento.getSceneY() + 19.85);

        rectangle = new Rectangular(60, 55, imagenConector, evento, entrada1, entrada2, salida);
        AplicacionMain.Group.getChildren().addAll(rectangle,entrada1, entrada2, salida);
    }

    /**
     * Método que introduce en una lista enlazada cada conector cuando se suelta la imagen del conector
     * @param evento - Evento de drag al mover la imagen
     */
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
}


