package Aplicacion;

import Conectores.*;
import Extras.MejoraEventos;
import Lista.ListaEnlazada;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;

import static java.lang.Thread.sleep;

/**
 * Clase para ejecutar la ventana principal de la aplicación.
 */
public class AplicacionMain extends Application {

    /**
     * Método que ejecuta la aplicacion.
     * @param args
     */
    public static void main(String [] args){
        launch(args);
    }

    /**
     * Variables necesarias para obtener el nombre del conetor
     */
    public static String[] nombreConector = {And.nombre,
                                            Nand.nombre,
                                            Or.nombre,
                                            Nor.nombre,
                                            Not.nombre,
                                            Xor.nombre,
                                            Xnor.nombre};

    /**
     * Variables necesarias para obtener la imagen del conetor
     */
    private ImageView [] Imagenes = {imagenAnd = and.getImage(),
                                     imagenNand = nand.getImage(),
                                     imagenOr = or.getImage(),
                                     imagenNor = nor.getImage(),
                                     imagenNot = not.getImage(),
                                     imagenXor = xor.getImage(),
                                     imagenXnor = xnor.getImage()};

    /**
     * Lista para almacenar los conectores utilizados
     */
    public static ListaEnlazada lista = new ListaEnlazada();
    /**
     * Grupo para almacenar los diferentes contenedores
     */
    public static final Group Group = new Group();
    /**
     * Panel donde se muestran los conectores escogidos
     */
    public static final Pane ventanaDiseño = new Pane(Group);

    /**
     * Botón para ejecutar la simulación del circuito
     */
    private Button Play = new Button("Play");

    /**
     * Contenedor de los conectores iniciales
     */
    private VBox componentesInicio;

    /**
     * Variable para obtener la imagen del conector And
     */
    public static ImageView imagenAnd;
    /**
     * Variable para obtener la imagen del conector Nand
     */
    public static  ImageView imagenNand;
    /**
     * Variable para obtener la imagen del conector Or
     */
    public static  ImageView imagenOr;
    /**
     * Variable para obtener la imagen del conector Nor
     */
    public static  ImageView imagenNor;
    /**
     * Variable para obtener la imagen del conector Not
     */
    public static  ImageView imagenNot;
    /**
     * Variable para obtener la imagen del conector Xor
     */
    public static ImageView imagenXor;
    /**
     * Variable para obtener la imagen del conector Xnor
     */
    public static ImageView imagenXnor;

    /**
     * Variable para incorporar el conectores en el palette
     */
    public static final And and = (And)new ConectorFactory().crearComponente(And.nombre);
    /**
     * Variable para incorporar el conectores en el palette
     */
    public static final Nand nand = (Nand) new ConectorFactory().crearComponente(Nand.nombre);
    /**
     * Variable para incorporar el conectores en el palette
     */
    public static final Or or = (Or) new ConectorFactory().crearComponente(Or.nombre);
    /**
     * Variable para incorporar el conectores en el palette
     */
    public static final Nor nor = (Nor)new ConectorFactory().crearComponente(Nor.nombre);
    /**
     * Variable para incorporar el conectores en el palette
     */
    public static final Not not =(Not) new ConectorFactory().crearComponente(Not.nombre);
    /**
     * Variable para incorporar el conectores en el palette
     */
    public static final Xor xor = (Xor)new ConectorFactory().crearComponente(Xor.nombre);
    /**
     * Variable para incorporar el conectores en el palette
     */
    public static final Xnor xnor = (Xnor)new ConectorFactory().crearComponente(Xnor.nombre);

    /**
     * Variable para la numeración de las entradas
     */
    public static int labelEntrada = 0;
    /**
     * Variable para la numeración de las salidas
     */
    public static int labelSalida = 0;

    /*
    static int cont = 0;
    static boolean tipo = true;
    public void nuevaVentana() throws InterruptedException {
        System.out.println("timer: " + cont);
        Stage stage1 = new Stage();
        stage1.setWidth(500);
        stage1.setHeight(350);

        BorderPane pane = new BorderPane();
        pane.setCenter(new Label(" Prueba Ventana Inicio "));

        Scene scene = new Scene(pane);
        stage1.setScene(scene);
        stage1.setTitle("Nueva Ventana");
        stage1.show();

        while(cont < 5) {
            cont++;
            tipo = false;
            Thread.sleep(1000);
        }

        //Thread.sleep(4000);
        stage1.close();

    }
    */


    /**
     * Método que contiene todos los componentes gráficos de la aplicación.
     * @param primaryStage - Ventana principal, donde se colocará la escena y los contenedores.
     */
    @Override
    public void start(Stage primaryStage) throws  Exception{

        //nuevaVentana();
        //Thread.sleep(4000);
        //stage.initStyle(StageStyle.UNDECORATED);

        /**
         * Se asigna a cada imagen el evento correspondiente
         */
        int a = 0;
        for (ImageView x : Imagenes){
            int finalA = a;
            x.setFitWidth(70);
            x.setFitHeight(70);
            x.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent evento) {
                    MejoraEventos.DragDetected(evento , x, nombreConector[finalA]);
                }});
            a++;
        }

        /**
         * Evento para manejar DragOver de conectores en la ventana principal
         */
        ventanaDiseño .setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent evento) {
                evento.acceptTransferModes(TransferMode.COPY_OR_MOVE); }
        });
        /**
         * Evento para conectores soltados en la ventana principal
         */
        ventanaDiseño .setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent evento) {
                MejoraEventos.Dropped(evento, Imagenes, labelEntrada, labelSalida);
            }
        });

        /**
         * Evento para simular el circuito
         */
        Play.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MejoraEventos.Play(event);
            }
        });


        /**
         * Label del Palette
         */
        /*
        Label labelPalette = new Label("PALETTE");
        labelPalette.setFont(Font.font("Banschriff Sans Seriff", FontWeight.BOLD, 22));
        AnchorPane anclar = new AnchorPane();
        anclar.getChildren().add(labelPalette);
        AnchorPane.setBottomAnchor(labelPalette, 15.0);
        */

        /**
         * Contenedor para conectores iniciales
         */
        componentesInicio = new VBox(imagenAnd, imagenNand, imagenOr, imagenNor, imagenNot, imagenXor, imagenXnor);
        /**
         * Contenedor para conectores dentro del contenedor Vbox
         */
        ScrollPane scrollpane = new ScrollPane();
        //scrollpane.setMaxSize(88, 485);
        //scrollpane.setPrefWidth(88);
        scrollpane.setCursor(Cursor.HAND);
        scrollpane.setContent(componentesInicio);
        scrollpane.setBackground(Background.EMPTY);
        scrollpane.setStyle("-fx-border-color: black");
        ventanaDiseño .setStyle("-fx-background-color: white");

        /**
         * Contenedor del scrollbar
         */
        BorderPane borderPane = new BorderPane();
        //borderPane.setLayoutX(40);
        //borderPane.setLayoutY(50);
        borderPane.setBackground(Background.EMPTY);
        //borderPane.setTop(anclar);
        borderPane.setRight(scrollpane);

        borderPane.setCenter(ventanaDiseño);

        //Creación de la escena
        Scene scene = new Scene(borderPane, 1200, 600);
        primaryStage.setTitle(("                                                                                         " +
                               "                                             Simulador de Circuitos Lógicos ").toUpperCase());
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();
    }
}