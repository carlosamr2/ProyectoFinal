/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Carlos
 */
public class Interface {
    private BorderPane root2=new BorderPane();
    
    FlowPane optionPane = new FlowPane();
    FlowPane translationPane = new FlowPane();
    FlowPane uploadPane = new FlowPane(Orientation.VERTICAL);
    FlowPane frequencyPane=new FlowPane(Orientation.VERTICAL);
    Button loadTxt=new Button("Cargar txt");
    Button save=new Button("Guardar");
    RadioButton encode=new RadioButton("Codificar");
    RadioButton decode=new RadioButton("Decodificar");
    CheckBox inverseCoding= new CheckBox("Invertir arbol");
    TextArea input=new TextArea();
    TextArea output=new TextArea();
    TextArea showText=new TextArea();
    TextArea showEncode=new TextArea();
    TableView frequencyTable=new TableView();
    FrequencyTable ft;
    EncriptedData ed;
    Encryption e;
    
    public Interface() {
        BorderPane root=this.root2;
    }
    
    
    public void createOptionPanel(){
        optionPane.setHgap(25); 
        ObservableList items=optionPane.getChildren();
        Label controls=new Label("Controles: ");
        final ToggleGroup group = new ToggleGroup();
        encode.setToggleGroup(group);
        encode.setSelected(true);
        decode.setToggleGroup(group);
        items.addAll(controls,loadTxt,encode,decode,inverseCoding,save);
        root2.setTop(optionPane);
        
        loadTxt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent ev) {
                final FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(new Stage());
                FileReader txtData=new FileReader(file);
                showText.setText(txtData.texto);
                
                if(inverseCoding.isSelected()){
                    ft = new FrequencyTable(false, txtData.texto);
                }
                else{
                    ft = new FrequencyTable(true, txtData.texto);
                }
                ed = new EncriptedData(ft);
                e = new Encryption(ed);
                
                showEncode.setText(e.encode(txtData.texto));
            }
        }); 
        
        
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent ev) {
                FileChooser fileChooser = new FileChooser();
 
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);

                File file = fileChooser.showSaveDialog(new Stage());

                if (file != null) {
                    saveTextToFile(showEncode.getText(), file);
                }
            }
        });
    }
    public void createTranslator(){
        ObservableList items=translationPane.getChildren();
        items.addAll(input,output);
        root2.setCenter(translationPane);
        output.setEditable(false);
        input.setPrefWidth(250);
        output.setPrefWidth(250);
        
        input.setOnKeyReleased(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent t){
                if(encode.isSelected()){
                    output.setText(e.encode(input.getText()));        
                }
                if(decode.isSelected()){
                    output.setText(e.decode(input.getText()));        
                }
            }
        });
    }
    
    public void createUploadPanel(){
        ObservableList items=uploadPane.getChildren();
        showText.setEditable(false);
        showEncode.setEditable(false);
        showText.setPrefWidth(250);
        showEncode.setPrefWidth(250);
        items.addAll(showText,showEncode);
        root2.setLeft(uploadPane);
    }
    
    public void createDictionary(){
        Label header=new Label("Tabla de Frecuencias\n");
        ObservableList items=frequencyPane.getChildren();
        items.addAll(header,frequencyTable);
        root2.setRight(frequencyPane);
    }

    public BorderPane getRoot2() {
        return root2;
    }
        static void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
