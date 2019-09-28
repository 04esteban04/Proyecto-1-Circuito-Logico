package Extras;

import Aplicacion.AplicacionMain;
import Aplicacion.Conector;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;

import java.util.Random;

/*
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
            line = new Line(StartX, StartY, e.getSceneX(), e.getSceneY());
            this.setFill(Color.GREEN);
            AplicacionMain.Group.getChildren().add(line);
            Draw = false;
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

 */

/**
 * Clase Circular para crear los enlaces entre componentes
 */
public class Circular extends Circle{
    private static boolean Draw;
    private static double StartX, StartY;
    private String Type;
    private Line line;
    private int finLine = 0;
    private boolean HaveLine;
    private Conector Component;
    private static Circular Anterior;
    //Variables para mover el circulo
    private double orgSceneX,orgSceneY;
    private double orgTranslateX,orgTranslateY;

    /**
     * Contructor clase circular.
     * @param X- Distancia x del círculo
     * @param Y- Distancia y del círculo.
     * @param component - Componente respectivo del círculo.
     * @param type - Típo de circulo que es (entrada ó salida).
     */
    public Circular(double X,double Y, Conector component,String type) {
        super(X, Y, 5, Color.BLACK);
        Type = type;
        Component = component;
        Draw = false;
        HaveLine = false;
        line = null;

        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Click(e);
            }
        });
    }

    /**
     * Evento para asignar la posición inicial del círculo.
     * @param evento - evento del mouse.
     */
    public void Pressed (MouseEvent evento){
        orgSceneX = evento.getSceneX();
        orgSceneY = evento.getSceneY();
        orgTranslateX = ((Shape)(evento.getSource())).getTranslateX();
        orgTranslateY = ((Shape)(evento.getSource())).getTranslateY();
    }

    /**
     * Evento para transladar el círculo conforme se mueve el mouse.
     * @param evento - Evento del mouse.
     */
    public void Dragged(MouseEvent evento){
        double offsetX = evento.getSceneX() - orgSceneX;
        double offsetY = evento.getSceneY() - orgSceneY;
        double newTranslateX = orgTranslateX + offsetX;
        double newTranslateY = orgTranslateY + offsetY;

        this.setTranslateX(newTranslateX);
        this.setTranslateY(newTranslateY);

        if (finLine == 1){
            line.setEndX(newTranslateX+getCenterX());
            line.setEndY(newTranslateY+getCenterY());
        }else if(finLine == 2){
            line.setStartX(newTranslateX+getCenterX());
            line.setStartY(newTranslateY+getCenterY());
        }

    }

    /**
     * Evento al clickear un círculo y que comience o termine el dibujo de la línea
     * @param evento - evento del mouse.
     */
    public void Click(MouseEvent evento){
        if(!HaveLine){
            if (Draw) {
                if (Component.getID() != Anterior.getComponente().getID()) {
                    if (!this.Type.equals(Anterior.Type)) {
                        DrawLine(evento);
                        Enlazar();
                        Anterior.finLine = 2;
                        Anterior.line = line;
                        finLine = 1;
                        StopDraw("not");
                    }
                }else{
                    StopDraw("all");
                }
            }else{
                StartDraw(evento);
            }
        }
    }

    /**
     * Evento para dibujar la línea que conecta los componentes.
     * @param evento - evento de mouse.
     */
    private void DrawLine(MouseEvent evento){
        line = new Line(StartX, StartY, evento.getSceneX(), evento.getSceneY());
        line.setStrokeWidth(3);
        line.setStroke(randomColor());
        line.setStrokeLineCap(StrokeLineCap.ROUND);
        line.getStrokeDashArray().setAll(4.0, 4.0);
        this.setFill(Color.GREEN);
        AplicacionMain.Group.getChildren().add(line);
    }

    /**
     * Método que posiciona el inicio del dibujo de la línea.
     * @param evento - evento de mouse.
     */
    private void StartDraw(MouseEvent evento){
        StartX = evento.getSceneX();
        StartY = evento.getSceneY();
        Anterior = this;
        Draw = true;
        HaveLine = true;
        this.setFill(Color.GREEN);
    }

    /**
     * Evento para terminar el dibujo de línea.
     * @param parar - String que define cuando terminar el dibujo de la línea
     */
    private void StopDraw(String parar){
        if(parar.equals("all")){
            Anterior.setFill(Color.BLACK);
            Anterior.HaveLine = false;
            Anterior = null;
            Draw = false;
            HaveLine = false;
        }else{
            Anterior = null;
            Draw = false;
            HaveLine = true;
        }
    }

    /**
     * Evento que une los círculos mediante la referencia de sus respectivos componentes.
     */
    private void Enlazar(){
        if (this.Type.equals("input")) {
            this.getComponente().setInput(true);
            if (Anterior.getComponente().getEntrada1() != null) {
                Anterior.getComponente().setSegundaEntrada(this.Component);
            } else {
                Anterior.getComponente().setPrimeraEntrada(this.Component);
            }
        } else {
            Anterior.getComponente().setInput(true);
            if (this.getComponente().getEntrada1() != null) {
                this.getComponente().setSegundaEntrada(Anterior.Component);
            } else {
                this.getComponente().setPrimeraEntrada(Anterior.Component);
            }
        }
    }

    /**
     * Método que genera colores aleatorioa
     * @return - Color
     */
    public static Paint randomColor() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return Color.rgb(r, g, b);
    }

    /**
     * Método para obtener el componente respectivo
     * @return - Componente
     */
    public Conector getComponente() {
        return Component;
    }
}
