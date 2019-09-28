package Extras;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * Clase rectangular que sirve para crear contenedores de las imagenes de los componentes
 */
public class Rectangulo extends Rectangle {
    private double orgSceneX,orgSceneY;
    private double orgTranslateX,orgTranslateY;
    private Circulo Output, OutputII,Input;
    Label numEntrada1;
    Label numEntrada2;
    Label numSalida;

    /**
     * Constructor de la clase que define los atributos y algunos eventos.
     * @param width - ancho
     * @param height - largo
     * @param image - imagen
     * @param e - evento
     * @param output - input
     * @param outputII - input 2
     * @param input - output
     * @param labelEntrada1 - label para mostrar la numeración de la entrada
     * @param labelEntrada2 - label para mostrar la numeración de la entrada
     * @param labelSalida - label para mostrar la numeración de la salida
     */
    public Rectangulo(int width, int height, ImageView image, DragEvent e, Circulo output, Circulo outputII,
                      Circulo input, Label labelEntrada1, Label labelEntrada2, Label labelSalida) {
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
     * Evento que se ejecuta al presionar el rectángulo con la imagen.
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

                        Output.presionado(evento);
                        OutputII.presionado(evento);
                        Input.presionado(evento);
                    }
                    else {
                        numEntrada1.setLayoutX(evento.getSceneX() - 48);
                        numEntrada1.setLayoutY(evento.getSceneY() - 38);
                        numSalida.setLayoutX(evento.getSceneX() + 30);
                        numSalida.setLayoutY(evento.getSceneY() - 38);

                        Output.presionado(evento);
                        Input.presionado(evento);
                    }
                }
            };

    /**
     * Evento que se ejecuta al mover el rectángulo con la imagen.
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

                        Output.arrastrado(evento);
                        OutputII.arrastrado(evento);
                        Input.arrastrado(evento);
                    }
                    else {
                        numEntrada1.setLayoutX(evento.getSceneX() - 48);
                        numEntrada1.setLayoutY(evento.getSceneY() - 38);
                        numSalida.setLayoutX(evento.getSceneX() + 30);
                        numSalida.setLayoutY(evento.getSceneY() - 38);

                        Output.arrastrado(evento);
                        Input.arrastrado(evento);
                    }
                }
            };
}