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

/**
 * Clase Circulo para crear los enlaces entre componentes
 */
public class Circulo extends Circle{
    private static boolean Draw;
    private static double StartX, StartY;
    private String Type;
    private Line line;
    private int finLine = 0;
    private boolean HaveLine;
    private Conector Component;
    private static Circulo Anterior;
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
    public Circulo(double X, double Y, Conector component, String type) {
        super(X, Y, 5, Color.BLACK);
        Type = type;
        Component = component;
        Draw = false;
        HaveLine = false;
        line = null;

        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                clickCirculo(e);
            }
        });
    }

    /**
     * Evento para asignar la posición inicial del círculo.
     * @param evento - evento del mouse.
     */
    public void presionado(MouseEvent evento){
        orgSceneX = evento.getSceneX();
        orgSceneY = evento.getSceneY();
        orgTranslateX = ((Shape)(evento.getSource())).getTranslateX();
        orgTranslateY = ((Shape)(evento.getSource())).getTranslateY();
    }

    /**
     * Evento para transladar el círculo conforme se mueve el mouse.
     * @param evento - Evento del mouse.
     */
    public void arrastrado(MouseEvent evento){
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
    public void clickCirculo(MouseEvent evento){
        if(!HaveLine){
            if (Draw) {
                if (Component.getID() != Anterior.getComponente().getID()) {
                    if (!this.Type.equals(Anterior.Type)) {
                        dibujarLinea(evento);
                        enlazarCirculo();
                        Anterior.finLine = 2;
                        Anterior.line = line;
                        finLine = 1;
                        terminarDibujo("not");
                    }
                }else{
                    terminarDibujo("all");
                }
            }else{
                empezarDibujo(evento);
            }
        }
    }

    /**
     * Evento para dibujar la línea que conecta los componentes.
     * @param evento - evento de mouse.
     */
    private void dibujarLinea(MouseEvent evento){
        line = new Line(StartX, StartY, evento.getSceneX(), evento.getSceneY());
        line.setStrokeWidth(3);
        line.setStroke(colorAleatorio());
        line.setStrokeLineCap(StrokeLineCap.ROUND);
        line.getStrokeDashArray().setAll(4.0, 4.0);
        this.setFill(Color.GREEN);
        AplicacionMain.Group.getChildren().add(line);
    }

    /**
     * Método que posiciona el inicio del dibujo de la línea.
     * @param evento - evento de mouse.
     */
    private void empezarDibujo(MouseEvent evento){
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
    private void terminarDibujo(String parar){
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
    private void enlazarCirculo(){
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
    public static Paint colorAleatorio() {
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
