//package Extras;

/*
import Aplicacion.AplicacionMain;
import Extras.Circular;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import static Extras.MejoraEventos.linea;

*/


/*
public class Rectangular extends Rectangle {
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    public static Circular Output, OutputII, Input;
    private Label numEntrada1, numEntrada2, numSalida;

    public Rectangular(int width, int height, ImageView image, DragEvent e, Circular output, Circular outputII,
                       Circular input) {

        super(width, height);

        this.Output = output;
        this.OutputII = outputII;
        this.Input = input;

        //this.numEntrada1 = numEntrada1;
        //this.numEntrada2 = numEntrada2;
        //this.numSalida = numSalida;
        this.setFill(new ImagePattern(image.getImage()));
        this.setCursor(Cursor.MOVE);
        this.setX(e.getSceneX());
        this.setY(e.getSceneY());
        this.setOnMousePressed(RectangleOnMousePressedEventHandler);
        this.setOnMouseDragged(RectangleOnMouseDraggedEventHandler);
    }
    */

    /**
     * Evento al precionar el rectangulo con la imagen.
     */
    /*
    private EventHandler<MouseEvent> RectangleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent evento) {
            orgSceneX = evento.getSceneX();
            orgSceneY = evento.getSceneY();
            orgTranslateX = ((Rectangle) (evento.getSource())).getTranslateX();
            orgTranslateY = ((Rectangle) (evento.getSource())).getTranslateY();
            Output.Pressed(evento);
            OutputII.Pressed(evento);
            Input.Pressed(evento);

            numEntrada1.setLayoutX(evento.getSceneX() - 165);
            numEntrada1.setLayoutY(evento.getSceneY() - 150);
            numEntrada2.setLayoutX(evento.getSceneX() - 165);
            numEntrada2.setLayoutY(evento.getSceneY() - 65);
            numSalida.setLayoutX(evento.getSceneX() - 80);
            numSalida.setLayoutY(evento.getSceneY() - 110);

        }
    };
    */


    /**
     * Evento al mover el rectangulo con la imagen.
     */
    /*
    private EventHandler<MouseEvent> RectangleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent evento) {
            double offsetX = evento.getSceneX() - orgSceneX;
            double offsetY = evento.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            ((Rectangle) (evento.getSource())).setTranslateX(newTranslateX);
            ((Rectangle) (evento.getSource())).setTranslateY(newTranslateY);
            Output.Dragged(evento);
            OutputII.Dragged(evento);
            Input.Dragged(evento);

            numEntrada1.setLayoutX(evento.getSceneX() - 165);
            numEntrada1.setLayoutY(evento.getSceneY() - 150);
            numEntrada2.setLayoutX(evento.getSceneX() - 165);
            numEntrada2.setLayoutY(evento.getSceneY() - 65);
            numSalida.setLayoutX(evento.getSceneX() - 80);
            numSalida.setLayoutY(evento.getSceneY() - 110);

        }
    };
    */




package Extras;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Rectangular extends Rectangle {
    private double orgSceneX,orgSceneY;
    private double orgTranslateX,orgTranslateY;
    private Circular Output, OutputII,Input;

    public Rectangular(int width, int height, ImageView image, DragEvent e,Circular output, Circular outputII, Circular input) {
        super(width,height);
        this.Output=output;
        this.OutputII=outputII;
        this.Input=input;
        this.setFill(new ImagePattern(image.getImage()));
        this.setCursor(Cursor.MOVE);
        this.setX(e.getSceneX());
        this.setY(e.getSceneY());
        this.setOnMousePressed(RectangleOnMousePressedEventHandler);
        this.setOnMouseDragged(RectangleOnMouseDraggedEventHandler);
    }


    /**
     * Evento al precionar el rectangulo con la imagen.
     */
    private EventHandler<MouseEvent> RectangleOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((Rectangle)(t.getSource())).getTranslateX();
                    orgTranslateY = ((Rectangle)(t.getSource())).getTranslateY();
                    if (OutputII!=null) {
                        Output.Pressed(t);
                        OutputII.Pressed(t);
                        Input.Pressed(t);
                    }
                    else{
                        Output.Pressed(t);
                        Input.Pressed(t);
                    }
                }
            };
    /**
     * Evento al mover el rectangulo con la imagen.
     */
    private EventHandler<MouseEvent> RectangleOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;
                    ((Rectangle)(t.getSource())).setTranslateX(newTranslateX);
                    ((Rectangle)(t.getSource())).setTranslateY(newTranslateY);
                    if (OutputII!=null) {
                        Output.Dragged(t);
                        OutputII.Dragged(t);
                        Input.Dragged(t);
                    }
                    else {
                        Output.Dragged(t);
                        Input.Dragged(t);
                    }
                }
            };
}
