package Extras;
/*
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
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
    private static Circular Output, OutputII,Input;
    private Label numEntrada1, numEntrada2, numSalida;

    public Rectangular(int width, int height, ImageView image, DragEvent e,Circular output, Circular outputII,
                       Circular input, Label numEntrada1, Label numEntrada2, Label numSalida) {
        super(width,height);
        Output = output;
        OutputII = outputII;
        Input = input;
        this.numEntrada1 = numEntrada1;
        this.numEntrada2 = numEntrada2;
        this.numSalida = numSalida;
        this.setFill(new ImagePattern(image.getImage()));
        this.setCursor(Cursor.MOVE);
        this.setX(e.getX());
        this.setY(e.getY());
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
                    orgTranslateX = ((Rectangle)(evento.getSource())).getTranslateX();
                    orgTranslateY = ((Rectangle)(evento.getSource())).getTranslateY();

                    numEntrada1.setLayoutX(evento.getSceneX() - 165);
                    numEntrada1.setLayoutY(evento.getSceneY() - 150);
                    numEntrada2.setLayoutX(evento.getSceneX() - 165);
                    numEntrada2.setLayoutY(evento.getSceneY() - 65);
                    numSalida.setLayoutX(evento.getSceneX() - 80);
                    numSalida.setLayoutY(evento.getSceneY() - 110);

                    if (OutputII!=null) {
                        Output.Pressed(evento);
                        OutputII.Pressed(evento);
                        Input.Pressed(evento);
                    }
                    else{
                        Output.Pressed(evento);
                        Input.Pressed(evento);
                    }
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
                    ((Rectangle)(evento.getSource())).setTranslateX(newTranslateX);
                    ((Rectangle)(evento.getSource())).setTranslateY(newTranslateY);

                    numEntrada1.setLayoutX(evento.getSceneX() - 165);
                    numEntrada1.setLayoutY(evento.getSceneY() - 150);
                    numEntrada2.setLayoutX(evento.getSceneX() - 165);
                    numEntrada2.setLayoutY(evento.getSceneY() - 65);
                    numSalida.setLayoutX(evento.getSceneX() - 80);
                    numSalida.setLayoutY(evento.getSceneY() - 110);

                    if (OutputII!=null) {
                        Output.Dragged(evento);
                        OutputII.Dragged(evento);
                        Input.Dragged(evento);
                    }
                    else {
                        Output.Dragged(evento);
                        Input.Dragged(evento);
                    }
                }
            };

     */


import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
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
    Label numEntrada1;
    Label numEntrada2;
    Label numSalida;

    public Rectangular(int width, int height, ImageView image, DragEvent e,Circular output, Circular outputII,
                       Circular input, Label labelEntrada1, Label labelEntrada2, Label labelSalida) {
        super(width,height);
        this.Output = output;
        this.OutputII = outputII;
        this.Input = input;
        this.numEntrada1 = labelEntrada1;
        this.numEntrada2 = labelEntrada2;
        this.numSalida = labelSalida;
        this.setFill(new ImagePattern(image.getImage()));
        this.setCursor(Cursor.MOVE);
        this.setX(e.getSceneX());
        this.setY(e.getSceneY());
        this.setOnMousePressed(RectangleOnMousePressedEventHandler);
        this.setOnMouseDragged(RectangleOnMouseDraggedEventHandler);
    }


    /**
     * Evento al presionar el rectangulo con la imagen.
     */
    private EventHandler<MouseEvent> RectangleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent evento) {
                    orgSceneX = evento.getSceneX();
                    orgSceneY = evento.getSceneY();
                    orgTranslateX = ((Rectangle)(evento.getSource())).getTranslateX();
                    orgTranslateY = ((Rectangle)(evento.getSource())).getTranslateY();

                    if (OutputII != null) {
                        numEntrada1.setLayoutX(evento.getSceneX() - 48);
                        numEntrada1.setLayoutY(evento.getSceneY() - 38);
                        numEntrada2.setLayoutX(evento.getSceneX() - 40);
                        numEntrada2.setLayoutY(evento.getSceneY() + 10);
                        numSalida.setLayoutX(evento.getSceneX() + 30);
                        numSalida.setLayoutY(evento.getSceneY() - 38);

                        Output.Pressed(evento);
                        OutputII.Pressed(evento);
                        Input.Pressed(evento);
                    }
                    else {
                        numEntrada1.setLayoutX(evento.getSceneX() - 48);
                        numEntrada1.setLayoutY(evento.getSceneY() - 38);
                        numSalida.setLayoutX(evento.getSceneX() + 30);
                        numSalida.setLayoutY(evento.getSceneY() - 38);

                        Output.Pressed(evento);
                        Input.Pressed(evento);
                    }
                }
            };

    /**
     * Evento al mover el rectangulo con la imagen.
     */
    private EventHandler<MouseEvent> RectangleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent evento) {
                    double offsetX = evento.getSceneX() - orgSceneX;
                    double offsetY = evento.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;
                    ((Rectangle)(evento.getSource())).setTranslateX(newTranslateX);
                    ((Rectangle)(evento.getSource())).setTranslateY(newTranslateY);

                    if (OutputII != null) {
                        numEntrada1.setLayoutX(evento.getSceneX() - 48);
                        numEntrada1.setLayoutY(evento.getSceneY() - 38);
                        numEntrada2.setLayoutX(evento.getSceneX() - 40);
                        numEntrada2.setLayoutY(evento.getSceneY() + 10);
                        numSalida.setLayoutX(evento.getSceneX() + 30);
                        numSalida.setLayoutY(evento.getSceneY() - 38);

                        Output.Dragged(evento);
                        OutputII.Dragged(evento);
                        Input.Dragged(evento);
                    }
                    else {
                        numEntrada1.setLayoutX(evento.getSceneX() - 48);
                        numEntrada1.setLayoutY(evento.getSceneY() - 38);
                        numSalida.setLayoutX(evento.getSceneX() + 30);
                        numSalida.setLayoutY(evento.getSceneY() - 38);

                        Output.Dragged(evento);
                        Input.Dragged(evento);
                    }
                }
            };
}