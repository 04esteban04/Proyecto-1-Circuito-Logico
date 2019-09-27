package Extras;
/*
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
*/
//CORREGIR LÍNEAS ENTRE COMPONENTES

/**
 * Clase que simplifica el código para generar eventos
 */
/*
public class MejoraEventos {

    private static Circular entrada1, entrada2, salida;
    private static Rectangle rectangle;
    private static double orgSceneX,orgSceneY;
    private static double orgTranslateX,orgTranslateY;
    public static int input = 1;
    public static int output = 1;
    public static Line linea;
*/
    /*
    /**
     * Método que administra el drag sobre el conector del palette
     * @param evento - Evento de mouse al mover la imagen
     * @param imagenConector - Imagen del conector seleccionado
     * @param nombre - Nombre del conector seleccionado
     */
    /*
   public static final void DragDetected(MouseEvent evento, ImageView imagenConector, String nombre){
        Dragboard db = imagenConector.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(nombre);
        db.setContent(content);
        evento.consume();
    }
    */
    /*
    /**
     * Método que coloca la imagen en un rectángulo para colocarlo en el panel principal
     * @param evento - Evento de drag al mover la imagen
     * @param imagenConector - Imagen del conector.
     */
    /*
    private static void DroppedAux(DragEvent evento,ImageView imagenConector){

        Label numEntrada1 = new Label("i");
        Label numEntrada2 = new Label("i");
        Label numSalida = new Label("o");

        numEntrada1.setText("i");
        numEntrada2.setText("i");
        numSalida.setText("o");

        numEntrada1.setText(numEntrada1.getText() + input);
        numEntrada1.setLayoutX(evento.getX());
        numEntrada1.setLayoutY(evento.getY() - 15);
        input++;

        numEntrada2.setText(numEntrada2.getText() + input);
        numEntrada2.setLayoutX(evento.getX() - 20);
        numEntrada2.setLayoutY(evento.getY() + 35);
        input ++ ;

        numSalida.setText(numSalida.getText() + output);
        numSalida.setLayoutX(evento.getX() + 50);
        numSalida.setLayoutY(evento.getY() - 5);
        output ++ ;

        entrada1 = new Circular(evento.getX(), evento.getY() + 13);
        entrada2 = new Circular(evento.getX(), evento.getY() + 28);
        salida = new Circular(evento.getX() + 55,evento.getY() + 19.85);

        rectangle = new Rectangular(60, 55, imagenConector, evento, entrada1, entrada2, salida, numEntrada1, numEntrada2, numSalida);
        AplicacionMain.Group.getChildren().addAll(rectangle,entrada1, entrada2, salida, numEntrada1, numEntrada2, numSalida);
    }
    */
    /*
    /**
     * Método que introduce en una lista enlazada cada conector cuando se suelta la imagen del conector
     * @param evento - Evento de drag al mover la imagen
     */

    /*
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

    public static void Play(MouseEvent e){
        for (int x=0;x<AplicacionMain.lista.getLargo();x++){
            Conector c = AplicacionMain.lista.Obtener(x);
            System.out.println(c.getPrimeraEntrada() +""+ c.getSegundaEntrada());
        }
    }
}
*/

import Aplicacion.AplicacionMain;
import Aplicacion.Conector;
import Aplicacion.ConectorFactory;
import Conectores.*;
import Lista.ListaEnlazada;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MejoraEventos {

    /**
     * Este metodo gestiona el drag de la imagen
     * que se encuentra en la paleta.
     *
     * @param e    - Evento de mouse al mover la imagen.
     * @param Comp - Imagen del componente.
     * @param Name - Nombre del componente que se está moviendo.
     */
    public static void DragDetected(MouseEvent e, ImageView Comp, String Name) {
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
    private static void DroppedAux(DragEvent evento, ImageView i, String C, Conector c, int labelEntrada, int labelSalida) {
        Circular output;
        Circular input;
        Rectangle rectangle;
        Line linea;
        Label numEntrada1 = new Label("i");
        Label numEntrada2 = new Label("i");
        Label numSalida = new Label("o");

        if (C != "Not") {
            output = new Circular(evento.getSceneX() + 4, evento.getSceneY() + 17, c, "output");
            Circular outputII = new Circular(evento.getSceneX() + 4, evento.getSceneY() + 31, c, "output");
            input = new Circular(evento.getSceneX() + 65, evento.getSceneY() + 25, c, "input");

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

            rectangle = new Rectangular(70, 70, i, evento, output, outputII, input, numEntrada1, numEntrada2, numSalida);
            AplicacionMain.Group.getChildren().addAll(rectangle, output, outputII, input, numEntrada1, numEntrada2, numSalida);

        } else {
            output = new Circular(evento.getSceneX() + 4, evento.getSceneY() + 22, c, "output");
            input = new Circular(evento.getSceneX() + 65, evento.getSceneY() + 22, c, "input");

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

            rectangle = new Rectangular(70, 70, i, evento, output, null, input, numEntrada1, null, numSalida);
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
    public static void Dropped(DragEvent e, ImageView[] i, int labelEntrada, int labelSalida) {
        int cont = 0;
        for (String x : AplicacionMain.nombreConector) {
            if (e.getDragboard().getString().equals(x)) {
                Conector c = new ConectorFactory().crearComponente(x);
                MejoraEventos.DroppedAux(e, i[cont], x, c, labelEntrada, labelSalida);
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
        CleanLista();
        ListaEnlazada outputs = new ListaEnlazada();
        ListaEnlazada inputs = new ListaEnlazada();
        for (int x = 0; x < AplicacionMain.lista.getLargo(); x++){
            Conector c = AplicacionMain.lista.Obtener(x);

            if (c.getEntrada1() != null && c.getEntrada2() != null && !c.isInput()){
                outputs.Insertar(x, c);
            }
            else if (c.getEntrada1() == null && c.getEntrada2() == null && c.isInput()){
                inputs.Insertar(x, c);
                SetInputs(c);
            }
        }
        try {
            Asignar(AplicacionMain.lista);
        }catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Error al ejecutar la operación. Por favor revise su configuración.");
            alert.showAndWait();
            Reset();
        }
        // Se obtiene la salida de la simulación
        for (int x = 0; x < outputs.getLargo(); x++){
            for(int y = 0; y < AplicacionMain.lista.getLargo(); y++){
                Conector c1 = outputs.Obtener(x);
                Conector c2 = AplicacionMain.lista.Obtener(y);
                if (c1.getID() == c2.getID()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText(c2.getName()+" #"+c2.getID()+ "- Output"+" = "+ c2.getOutput());
                    alert.showAndWait();
                }
            }
        }
    }

    /**
     * Método que establece los entradas de los componentes iniciales del circuito
     * @param c - Componente inicial.
     */
    private static void SetInputs(Conector c) {
        if(c.getName().equals("Not")){
            List<String> choices = new ArrayList<>();
            choices.add("1");
            choices.add("0");
            ChoiceDialog<String> dialog = new ChoiceDialog<>("1", choices);
            dialog.setTitle("Choice");
            dialog.setHeaderText("Choice Value of input :"+ c.getName()+" #"+c.getID());
            dialog.setContentText("Value: ");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(number -> c.setInput1(Integer.parseInt(number)));
            result.ifPresent(number -> c.setInput2(Integer.parseInt(number)));
        }
        else if(c.getEntrada1() == null && c.getEntrada2() == null) {
            for (int x=0; x< 2; x++) {
                List<String> choices = new ArrayList<>();
                choices.add("1");
                choices.add("0");
                ChoiceDialog<String> dialog = new ChoiceDialog<>("1", choices);
                dialog.setTitle("Choice");
                dialog.setHeaderText("Choice Value of input :"+ c.getName()+" #"+c.getID());
                dialog.setContentText("Value: ");

                Optional<String> result = dialog.showAndWait();
                if(x==0) {
                    result.ifPresent(number -> c.setInput1(Integer.parseInt(number)));
                }else {
                    result.ifPresent(number -> c.setInput2(Integer.parseInt(number)));
                }
            }
        }
    }

    /**
     * Método que asigna a cada elemento de la lista un valor entero para la variable output (salida)
     * @param lista - lista enlazada diseñada.
     */
    private static void Asignar(ListaEnlazada lista){
        int componentesListos = Listos(lista);
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
                    c.setOutput(ClasiCompo(c));
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
    private static int Listos(ListaEnlazada lista){
        int componentesListos = 0;
        for (int x = 0; x < lista.getLargo(); x++){
            if (lista.Obtener(x).getInput2()<2 && lista.Obtener(x).getInput1()<2){
                lista.Obtener(x).setOutput(ClasiCompo(lista.Obtener(x)));
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
    private static int ClasiCompo(Conector conector) {
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
    private static void CleanLista(){
        for(int x = 0; x < AplicacionMain.lista.getLargo(); x++){
            AplicacionMain.lista.Obtener(x).setOutput(2);
            AplicacionMain.lista.Obtener(x).setInput1(2);
            AplicacionMain.lista.Obtener(x).setInput2(2);
        }
    }

    /**
     * Método que borra las entradas y salidas de los componentes en la lista
     */
    static void Reset(){
        AplicacionMain.lista = new ListaEnlazada();
        Conector.setIDt(1);
        AplicacionMain.Group.getChildren().clear();
        System.out.println(AplicacionMain.lista.getLargo());
    }

}



