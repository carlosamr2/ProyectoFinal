/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneOrganizer;

import java.util.Map;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static paneOrganizer.EncryptionPane.showAlert;
import static paneOrganizer.EncryptionPane.encryption;

/**
 *
 * @author Bryan
 */
public class FrequencePane {

    private final Stage stage;
    private final Scene scene;
    private final Map<Character, Integer> freq;
//    public static char caracter;
//    public static int amount;
    private char caracter;
    private int amount;
    private final String text;
    private final boolean b;

    public FrequencePane(String text,boolean b) {
        this.text = text;
        this.b = b;
        this.freq = encryption.getEd().getFt().getFrequency();
        VBox root = createRoot();
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(30);
        scene = new Scene(root, 350, 200);
        stage = createStage();
    }

    private Stage createStage() {
        Stage s = new Stage();
        s.setTitle("Change Frequence");
        s.setScene(this.scene);
        s.show();
        return s;
    }

    private VBox createRoot() {
        Label car = new Label("Seleccione el caracter: ");
        Set<Character> setChars = freq.keySet();
        ObservableList<Character> chars = FXCollections.observableArrayList(setChars);
        ComboBox<Character> cb = new ComboBox<>(chars);
        HBox hCaracter = new HBox(car, cb);
        hCaracter.setAlignment(Pos.CENTER);
        hCaracter.setSpacing(40);
        Label oAmount = new Label();
        Label lam = new Label("New frequence:  ");
        TextField tam = new TextField();
        Button accept = new Button("Accept");
        accept.setOnAction(e -> {
            if (isInteger(tam.getText())) {
                EncryptionPane.caracter2 = cb.getValue();
                EncryptionPane.amount2 = Integer.parseInt(tam.getText());
                stage.close();
            } else {
                showAlert("El número ingresado no es válido. Inténtelo nuevamente.");
            }
        });
        HBox nAmount = new HBox(lam, tam);
        nAmount.setAlignment(Pos.CENTER);
        nAmount.setSpacing(40);
        VBox v = new VBox(hCaracter,oAmount,nAmount,accept);
        tam.setDisable(true);
        accept.setDisable(true);
        cb.setOnAction(e -> {
            tam.setDisable(false);
            accept.setDisable(false);
            oAmount.setText("Current frequence:   " + String.valueOf(freq.get(cb.getValue())));
        });
        return v;
    }

    private boolean isInteger(String numero) {
        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Scene getScene() {
        return scene;
    }

    public Map<Character, Integer> getFreq() {
        return freq;
    }

    public char getCaracter() {
        return caracter;
    }

    public int getAmount() {
        return amount;
    }

}
