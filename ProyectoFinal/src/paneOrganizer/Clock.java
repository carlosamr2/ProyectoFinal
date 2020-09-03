/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneOrganizer;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import static paneOrganizer.EncryptionPane.music;

/**
 *
 * @author Carlos
 */
public class Clock extends Pane{
    private Timeline animation;
    private int tap=100;
    private String S="";
    private Stage stage;
    private BorderPane root;
    private VBox frequencyPane;
    Label label=new Label("100");
    Button boton;
    public Clock(Stage stage,Button boton,BorderPane root,VBox frequencyPane){
        label.setFont(javafx.scene.text.Font.font(100));
        label.setTextFill(Color.web("#0076a3"));
        getChildren().add(label);
        this.stage=stage;
        this.boton=boton;
        this.root=root;
        this.frequencyPane=frequencyPane;
        animation=new Timeline(new KeyFrame(Duration.seconds(1),e->timeLabel()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
    
    public void timeLabel(){
        if(tap>0){
            tap--;
        }
        S=tap+"";
        label.setText(S);
        if(tap==0){
            animation.pause();
            animation.stop();
            stage.close();
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("¡Oh no!\nNo pudiste desactivar la bomba, suerte para la próxima");
            root.setRight(frequencyPane);
            boton.setDisable(false);
            music.stop();
            this.stop();
            a.show();
        }
    }

    public int getTap() {
        return tap;
    }
    
    public void stop(){
        this.animation=new Timeline();
        tap=100;
    }
}
