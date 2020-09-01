/*
 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneOrganizer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Carlos
 */
public class MainPane extends Application {

    @Override
    public void start(Stage primaryStage) {

        EncryptionPane gui = new EncryptionPane();
        Scene scene = new Scene(gui.getRoot(), 1000, 550);

        primaryStage.setTitle("Huffman Compression");
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
