/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Carlos
 */
public class ProyectoFinal extends Application {
    Interface gui=new Interface();
    
    @Override
    public void start(Stage primaryStage) {
        
        String text = "El veloz murcielago hindu comia feliz cardillo y kiwi mientras la cigueña tocaba saxofon detras del palenque de paja";

        FrequencyTable ft = new FrequencyTable(true, text);

        EncriptedData ed = new EncriptedData(ft);
        
        //-----------------------------------------------------------------------------------------------
        ed.getMapEnc().keySet().forEach((s) -> { //Muestra cada caracter con su respectivo código
            System.out.println(s + ":   " + ed.getMapEnc().get(s));
        });
        //-----------------------------------------------------------------------------------------------
      
        Encryption e = new Encryption(ed);

        String original = "puedo escribir los versos mas tristes esta noche escribir por ejemplo la noche esta estrellada y tritan azules los astros a lo lejos";

        String enc = e.encode(original);

        String dec = e.decode(enc);

        System.out.println("Texto original: " + original);

        System.out.println("Texto codificado: " + enc);

        System.out.println("Texto decodificado: " + dec);
        
//*****************************************************************************************************************************************
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        File file = new File("text.txt");
        FileReader reader = new FileReader(file);
        FileWritter escribir = new FileWritter("demo.txt");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println(reader.texto);
                escribir.create();
                escribir.write("hola mundo");
                
            }
        });
        gui.createOptionPanel();
        gui.createTranslator();
        gui.createUploadPanel();
        gui.createDictionary();
        Scene scene = new Scene(gui.getRoot2(), 1440, 900);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
