package Extras;

import Aplicacion.AplicacionMain;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;


public class Circular extends Circle {

    private static double orgSceneX, orgSceneY;
    private static double orgTranslateX, orgTranslateY;
    private Line linea = new Line();
    private static boolean Draw;
    private static double StartX, StartY;
    private Line line;

    public Circular(double X, double Y) {
        super(X, Y, 6, Color.BLACK);
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Click(event);
            }
        });
    }


    public void Pressed(MouseEvent evento) {
        orgSceneX = evento.getSceneX();
        orgSceneY = evento.getSceneY();
        orgTranslateX = ((Shape) (evento.getSource())).getTranslateX();
        orgTranslateY = ((Shape) (evento.getSource())).getTranslateY();
    }

    public void Dragged(MouseEvent evento) {
        double offsetX = evento.getSceneX() - orgSceneX;
        double offsetY = evento.getSceneY() - orgSceneY;
        double newTranslateX = orgTranslateX + offsetX;
        double newTranslateY = orgTranslateY + offsetY;

        this.setTranslateX(newTranslateX);
        this.setTranslateY(newTranslateY);
    }

    public void Click(MouseEvent e) {
        if (Draw) {
            line = new Line(StartX, StartY, e.getX(), e.getY());
            this.setFill(Color.GREEN);
            AplicacionMain.Group.getChildren().add(line);
        } else {
            StartX = e.getSceneX();
            StartY = e.getSceneY();
            Draw = true;
            this.setFill(Color.RED);
        }
    }
    
    public static Line connect(Circular c1, Circular c2) {
        Line line = new Line();

        line.startXProperty().bind(c1.centerXProperty());
        line.startYProperty().bind(c1.centerYProperty());

        line.endXProperty().bind(c2.centerXProperty());
        line.endYProperty().bind(c2.centerYProperty());

        line.setStrokeWidth(1);
        line.setStrokeLineCap(StrokeLineCap.BUTT);
        line.getStrokeDashArray().setAll(1.0, 4.0);

        return line;
    }

    public static void drawLine(Line linea){
        linea = new Line(orgSceneX, orgSceneY, orgTranslateX, orgTranslateY);
        AplicacionMain.Group.getChildren().add(linea);
    }

}