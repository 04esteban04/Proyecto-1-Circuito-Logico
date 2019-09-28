package Extras;

import Aplicacion.AplicacionMain;
import Aplicacion.Conector;
import Aplicacion.ConectorFactory;
import Conectores.*;
import Lista.ListaEnlazada;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Clase que simplifica el código para generar eventos
 */
public class MejoraEventos {

    /**
     * Este metodo gestiona el drag de la imagen
     * que se encuentra en la paleta.
     *
     * @param e    - Evento de mouse al mover la imagen.
     * @param Comp - Imagen del componente.
     * @param Name - Nombre del componente que se está moviendo.
     */
    public static void movimientoDetectado(MouseEvent e, ImageView Comp, String Name) {
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
    private static void soltarAux(DragEvent evento, ImageView i, String C, Conector c, int labelEntrada, int labelSalida) {
        Circulo output;
        Circulo input;
        Rectangle rectangle;
        Line linea;
        Label numEntrada1 = new Label("i");
        Label numEntrada2 = new Label("i");
        Label numSalida = new Label("o");

        if (C != "Not") {
            output = new Circulo(evento.getSceneX() + 4, evento.getSceneY() + 17, c, "output");
            Circulo outputII = new Circulo(evento.getSceneX() + 4, evento.getSceneY() + 31, c, "output");
            input = new Circulo(evento.getSceneX() + 65, evento.getSceneY() + 25, c, "input");

            numEntrada1.setText("i");
            numEntrada2.setText("i");
            numSalida.setText("o");

            labelEntrada++;
            AplicacionMain.labelEntrada = labelEntrada;
            numEntrada1.setText(numEntrada1.getText() + labelEntrada);
            numEntrada1.setLayoutX(evento.getX());
            numEntrada1.setLayoutY(evento.getY() - 15);

            labelEntrada++;
            AplicacionMain.labelEntrada = labelEntrada;
            numEntrada2.setText(numEntrada2.getText() + labelEntrada);
            numEntrada2.setLayoutX(evento.getX() - 20);
            numEntrada2.setLayoutY(evento.getY() + 35);

            labelSalida++;
            AplicacionMain.labelSalida = labelSalida;
            numSalida.setText(numSalida.getText() + labelSalida);
            numSalida.setLayoutX(evento.getX() + 50);
            numSalida.setLayoutY(evento.getY() - 5);

            rectangle = new Rectangulo(70, 70, i, evento, output, outputII, input, numEntrada1, numEntrada2, numSalida);
            AplicacionMain.Group.getChildren().addAll(rectangle, output, outputII, input, numEntrada1, numEntrada2, numSalida);

        } else {
            output = new Circulo(evento.getSceneX() + 4, evento.getSceneY() + 22, c, "output");
            input = new Circulo(evento.getSceneX() + 65, evento.getSceneY() + 22, c, "input");

            numEntrada1.setText("i");
            numSalida.setText("o");

            labelEntrada++;
            AplicacionMain.labelEntrada = labelEntrada;
            numEntrada1.setText(numEntrada1.getText() + labelEntrada);
            numEntrada1.setLayoutX(evento.getX());
            numEntrada1.setLayoutY(evento.getY() - 15);

            labelSalida++;
            AplicacionMain.labelSalida = labelSalida;
            numSalida.setText(numSalida.getText() + labelSalida);
            numSalida.setLayoutX(evento.getX() + 50);
            numSalida.setLayoutY(evento.getY() - 5);

            rectangle = new Rectangulo(70, 70, i, evento, output, null, input, numEntrada1, null, numSalida);
            AplicacionMain.Group.getChildren().addAll(rectangle, output, input, numEntrada1, numSalida);
        }
    }

    /**
     * Este metodo gestiona el drop de la imagen del componente.
     * Identifica que componente se está moviendo, imprime en un TextArea
     * e introduce en una lista enlazada cada componente.
     *
     * @param e - Evento tipo DragEvent.
     */
    public static void soltar(DragEvent e, ImageView[] i, int labelEntrada, int labelSalida) {
        int cont = 0;
        for (String x : AplicacionMain.nombreConector) {
            if (e.getDragboard().getString().equals(x)) {
                Conector c = new ConectorFactory().crearComponente(x);
                MejoraEventos.soltarAux(e, i[cont], x, c, labelEntrada, labelSalida);
                if (AplicacionMain.lista.getLargo() == 0) {
                    AplicacionMain.lista.InsertarInicio(c);
                } else {
                    AplicacionMain.lista.InsertarFinal(c);
                }
            }
            cont++;
        }
    }

    /**
     * Este metodo es llamado por el botón para iniciar
     * las operaciones y obtener la salida del circuito.
     */
    public static void simularCircuito(MouseEvent e) {
        limpiarLista();
        ListaEnlazada outputs = new ListaEnlazada();
        ListaEnlazada inputs = new ListaEnlazada();
        for (int x = 0; x < AplicacionMain.lista.getLargo(); x++){
            Conector c = AplicacionMain.lista.Obtener(x);

            if (c.getEntrada1() != null && c.getEntrada2() != null && !c.isInput()){
                outputs.Insertar(x, c);
            }
            else if (c.getEntrada1() == null && c.getEntrada2() == null && c.isInput()){
                inputs.Insertar(x, c);
                establecerEntradas(c);
            }
        }
        try {
            asignar(AplicacionMain.lista);
        }catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(" Error ");
            alert.setHeaderText(null);
            alert.setContentText(" Error al simular el circuito ");
            alert.showAndWait();
            borrar();
        }
        // Se obtiene la salida de la simulación
        for (int x = 0; x < outputs.getLargo(); x++){
            for(int y = 0; y < AplicacionMain.lista.getLargo(); y++){
                Conector c1 = outputs.Obtener(x);
                Conector c2 = AplicacionMain.lista.Obtener(y);
                if (c1.getID() == c2.getID()){

                    GridPane tablaVerdad = new GridPane();
                    tablaVerdad.setPrefSize(400, 150);
                    tablaVerdad.setMaxSize(500, 150);
                    tablaVerdad.setBackground(new Background(new BackgroundFill(Color.TOMATO, CornerRadii.EMPTY, Insets.EMPTY)));
                    tablaVerdad.setGridLinesVisible(true);
                    tablaVerdad.setLayoutX(40);
                    tablaVerdad.setLayoutY(40);
                    tablaVerdad.setBackground(Background.EMPTY);

                    Label labelComponente = new Label("   Componente   ");
                    labelComponente.setFont(new Font("Helvetica", 15));;
                    Label labelEntrada1 = new Label("   Entrada #1   ");
                    labelEntrada1.setFont(new Font("Helvetica", 15));;
                    Label labelEntrada2 = new Label("   Entrada #2   ");
                    labelEntrada2.setFont(new Font("Helvetica", 15));;
                    Label labelSalida = new Label("   Salida   ");
                    labelSalida.setFont(new Font("Helvetica", 15));;
                    tablaVerdad.add(labelComponente, 0, 0);
                    tablaVerdad.add(labelEntrada1, 1, 0);
                    tablaVerdad.add(labelEntrada2, 2, 0);
                    tablaVerdad.add(labelSalida, 3, 0);

                    int fila = 1;
                    int columna = 0;
                    for (int i = 0; i < AplicacionMain.lista.getLargo(); i++ ){
                        Label nombreComponente = new Label(AplicacionMain.lista.Obtener(i).getName());
                        nombreComponente.setFont(new Font("Helvetica", 15));;
                        Label entrada1 = new Label("          " + Integer.toString(AplicacionMain.lista.Obtener(i).getInput1()) + "   ");
                        entrada1.setFont(new Font("Helvetica", 15));;
                        Label entrada2 = new Label("          " + Integer.toString(AplicacionMain.lista.Obtener(i).getInput2()) + "   ");
                        entrada2.setFont(new Font("Helvetica", 15));;
                        Label salida = new Label("     " + Integer.toString(AplicacionMain.lista.Obtener(i).getSalida()) + "   ");
                        salida.setFont(new Font("Helvetica", 15));;
                        Label entrada2Not = new Label("    -    ");

                        if (nombreComponente.equals("Not")){
                            tablaVerdad.add(nombreComponente, columna, fila);
                            columna++;
                            tablaVerdad.add(entrada1, columna, fila);
                            columna++;
                            tablaVerdad.add(entrada2Not, columna, fila);
                            columna++;
                            tablaVerdad.add(salida, columna, fila);
                            fila++;
                            columna = 0;
                        }
                        else {
                            tablaVerdad.add(nombreComponente, columna, fila);
                            columna++;
                            tablaVerdad.add(entrada1, columna, fila);
                            columna++;
                            tablaVerdad.add(entrada2, columna, fila);
                            columna++;
                            tablaVerdad.add(salida, columna, fila);
                            fila++;
                            columna = 0;
                        }
                    }

                    Scene scene = new Scene(tablaVerdad, 500,200);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setResizable(true);
                    stage.setTitle(" Tabla de verdad del circuito creado ");
                    stage.show();
                }
            }
        }
    }

    /**
     * Método que establece los entradas de los componentes iniciales del circuito
     * @param c - Componente inicial.
     */
    private static void establecerEntradas(Conector c) {
        if(c.getName().equals("Not")){
            TextInputDialog dialog = new TextInputDialog(" Ingrese un 1 ó un 0 ");

            dialog.setTitle(" Entrada de componente ");
            dialog.setHeaderText(null);
            dialog.setContentText(" Valor de la entrada de componente: " + c.getName());

            Optional<String> result = dialog.showAndWait();
            try {
                result.ifPresent(number -> c.setInput1(Integer.parseInt(number)));
                result.ifPresent(number -> c.setInput2(Integer.parseInt(number)));
            } catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(" Error ");
                alert.setHeaderText(null);
                alert.setContentText(" Error al ingresar el valor de la entrada #1 del componente " + c.getName());
                alert.showAndWait();
            }
        }
        else if(c.getEntrada1() == null && c.getEntrada2() == null) {
            for (int x = 0; x < 2; x++) {
                TextInputDialog dialog = new TextInputDialog(" Ingrese un 1 ó un 0 ");

                dialog.setTitle(" Entrada de componente ");
                dialog.setHeaderText(null);
                dialog.setContentText(" Valor de la entrada de componente: " + c.getName());

                Optional<String> result = dialog.showAndWait();
                if(x == 0) {
                    try {
                        result.ifPresent(number -> c.setInput1(Integer.parseInt(number)));
                    } catch (Exception e){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(" Error ");
                        alert.setHeaderText(null);
                        alert.setContentText(" Error al ingresar el valor de la entrada #1 del componente " + c.getName());
                        alert.showAndWait();
                    }
                }else {
                    try {
                        result.ifPresent(number -> c.setInput2(Integer.parseInt(number)));
                    } catch (Exception e){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(" Error ");
                        alert.setHeaderText(null);
                        alert.setContentText(" Error al ingresar el valor de la entrada #2 del componente " + c.getName());
                        alert.showAndWait();
                    }
                }
            }
        }
    }

    /**
     * Método que asigna a cada elemento de la lista un valor entero para la variable output (salida)
     * @param lista - lista enlazada diseñada.
     */
    private static void asignar(ListaEnlazada lista){
        int componentesListos = componentesListos(lista);
        int largo = lista.getLargo();
        int temp = 0;
        while (componentesListos < largo) {
            Conector c = lista.Obtener(temp);
            if (c.getEntrada1() != null && c.getEntrada1() != null) {
                Conector i1 = c.getEntrada1();
                Conector i2 = c.getEntrada2();
                if (i1.getOutput() < 2 && i2.getOutput() < 2 && c.getOutput() == 2) {
                    c.setInput1(i1.getOutput());
                    c.setInput2(i2.getOutput());
                    c.setOutput(clasificarCompo(c));
                    temp = 0;
                    componentesListos++;
                } else {
                    temp++;
                }
            } else {
                temp++;
            }
        }
    }

    /**
     * Se obtiene la cantidad de componentes que poseen una salida establecida
     * @param lista- lista enlazada.
     * @return - cantidad de componentes listos.
     */
    private static int componentesListos(ListaEnlazada lista){
        int componentesListos = 0;
        for (int x = 0; x < lista.getLargo(); x++){
            if (lista.Obtener(x).getInput2()<2 && lista.Obtener(x).getInput1()<2){
                lista.Obtener(x).setOutput(clasificarCompo(lista.Obtener(x)));
                componentesListos++;
            }
        }
        return componentesListos;
    }

    /**
     * Método que clasifica el componente y retorna su salida lógica
     * correspondiente.
     * @param conector - Componente.
     * @return - entero correspondiente a la salida del componente
     */
    private static int clasificarCompo(Conector conector) {
        if (conector.getName().equals("And")) {
            And v = (And) conector;
            return v.getSalida();
        }
        if (conector.getName().equals("Or")) {
            Or v = (Or) conector;
            return v.getSalida();
        }
        if (conector.getName().equals("Nand")) {
            Nand v = (Nand) conector;
            return v.getSalida();
        }
        if (conector.getName().equals("Xor")) {
            Xor v = (Xor) conector;
            return v.getSalida();
        }
        if (conector.getName().equals("Xnor")) {
            Xnor v = (Xnor) conector;
            return v.getSalida();
        }
        if (conector.getName().equals("Not")) {
            Not v = (Not) conector;
            return v.getSalida();
        }else{
            Nor v = (Nor) conector;
            return v.getSalida();
        }
    }

    /**
     * Método que establece las entradas y salidas de los componentes de la lista a 2.
     */
    private static void limpiarLista(){
        for(int x = 0; x < AplicacionMain.lista.getLargo(); x++){
            AplicacionMain.lista.Obtener(x).setOutput(2);
            AplicacionMain.lista.Obtener(x).setInput1(2);
            AplicacionMain.lista.Obtener(x).setInput2(2);
        }
    }

    /**
     * Método que borra las entradas y salidas de los componentes en la lista
     */
    public static void borrar(){
        AplicacionMain.lista = new ListaEnlazada();
        Conector.setIDt(1);
        AplicacionMain.Group.getChildren().clear();
    }

}



