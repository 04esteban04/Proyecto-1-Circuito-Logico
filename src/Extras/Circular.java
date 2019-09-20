package Extras;

import Aplicacion.AplicacionMain;
import Aplicacion.Conector;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;

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


public class Circular extends Circle{
    private static boolean Draw;
    private static double StartX, StartY;
    private String Type;
    private Line line;
    private boolean HaveLine;
    private Conector Component;
    private Circular Enlazado;
    private static Conector temp;
    private static Circular Anterior;
    //Variables para mover el circulo
    private double orgSceneX,orgSceneY;
    private double orgTranslateX,orgTranslateY;

    public Circular(double X,double Y, Conector component,String type) {
        super(X, Y, 5, Color.BLACK);
        Type=type;
        Component = component;
        Draw=false;
        HaveLine=false;
        line=null;

        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Click(e);
            }
        });
    }

    public void Pressed (MouseEvent t){

        orgSceneX = t.getSceneX();
        orgSceneY = t.getSceneY();
        orgTranslateX = ((Shape)(t.getSource())).getTranslateX();
        orgTranslateY = ((Shape)(t.getSource())).getTranslateY();
    }
    public void Dragged(MouseEvent t){
        double offsetX = t.getSceneX() - orgSceneX;
        double offsetY = t.getSceneY() - orgSceneY;
        double newTranslateX = orgTranslateX + offsetX;
        double newTranslateY = orgTranslateY + offsetY;

        this.setTranslateX(newTranslateX);
        this.setTranslateY(newTranslateY);

    }

    public void Click(MouseEvent e){
        if(!HaveLine){
            if (Draw) {
                if (Component.getID()!=Anterior.getComponente().getID()) {
                    if (!this.Type.equals(Anterior.Type)) {
                        DrawLine(e);
                        Enlazar();
                        StopDraw("not");
                    }
                }else{
                    StopDraw("all");
                }
            }else{
                StartDraw(e);
            }
        }
    }

    private void DrawLine(MouseEvent e){
        line = new Line(StartX, StartY, e.getSceneX(), e.getSceneY());
        this.setFill(Color.GREEN);
        //AplicacionMain.AreaText.appendText(Component.getName() + "..." + temp.getName());
        AplicacionMain.Group.getChildren().add(line);
    }
    private void StartDraw(MouseEvent e){
        StartX = e.getSceneX();
        StartY = e.getSceneY();
        temp=this.Component;
        Anterior=this;
        //AplicacionMain.AreaText.appendText(Component.getName());
        Draw = true;
        HaveLine=true;
        this.setFill(Color.GREEN);
    }
    private void StopDraw(String i){
        if(i.equals("all")){
            Anterior.setFill(Color.BLACK);
            Anterior.HaveLine=false;
            Anterior=null;
            temp=null;
            Draw=false;
            HaveLine=false;
        }else{
            Enlazado = Anterior;
            Anterior.Enlazado = this;
            Anterior = null;
            temp = null;
            Draw = false;
            HaveLine = true;
        }
    }

    private void Enlazar(){
        if (this.Type.equals("input")) {
            this.getComponente().setInput(true);
            if (Anterior.getComponente().getPrimeraEntrada() != null) {
                Anterior.getComponente().setSegundaEntrada(this.Component);
            } else {
                Anterior.getComponente().setPrimeraEntrada(this.Component);
            }
        } else {
            Anterior.getComponente().setInput(true);
            if (this.getComponente().getPrimeraEntrada() != null) {
                this.getComponente().setSegundaEntrada(Anterior.Component);
            } else {
                this.getComponente().setPrimeraEntrada(Anterior.Component);
            }
        }
    }


    public Line getLine() {
        return line;
    }

    public Conector getComponente() {
        return Component;
    }
}
