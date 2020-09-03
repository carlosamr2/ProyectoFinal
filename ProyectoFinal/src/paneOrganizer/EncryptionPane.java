/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneOrganizer;

import classes.EncriptedData;
import classes.Encryption;
import classes.FileReader;
import classes.FrequencyTable;
import classes.Symbol;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Carlos
 */
public class EncryptionPane {

    private final BorderPane root;
    public static Encryption encryption;

    HBox translationPane = new HBox();
    VBox uploadPane = new VBox();
    VBox frequencyPane = new VBox();
    Button loadTxt = new Button("Upload file");
    Button codify = new Button("Codify");
    RadioButton normal = new RadioButton("Normal");
    RadioButton Inverse = new RadioButton("Inverse");
    TextArea input = new TextArea();
    TextArea output = new TextArea();
    TextArea showText = new TextArea();
    TextArea showEncode = new TextArea();
    GridPane frequencyTable = new GridPane();
    boolean flagFrequence;
    String textFile;
    Button encode = new Button("Encode");
    Button decode = new Button("Decode");
    boolean encoding;
    Button choose = new Button("Choose new file");
    Button save;
    public static char caracter;
    public static int amount;
    Button change;
    ObservableList items;
    boolean changeChar;
    Button executeChange;
    boolean flagChange;

    public EncryptionPane() {
        root = new BorderPane();
        createTitle();
        createOptionPanel();
        createTranslator();
        createUploadPanel();
        createDictionary();
        flagFrequence = false;
        encoding = true;
    }

    private void setNodes() {
        flagFrequence = false;
        createDictionary();
        input.clear();
        output.clear();
        showText.clear();
        showEncode.clear();
        save.setDisable(true);
    }

    private void createTitle() {
        Label title = new Label("Huffman Coding");
        title.setFont(Font.font("Californian FB", FontPosture.ITALIC, 50));
        title.setTextFill(Color.web("White"));
        HBox cTitle = new HBox(title);
        cTitle.setAlignment(Pos.CENTER);
        cTitle.setMinHeight(70);
        cTitle.setStyle("-fx-background-color:BBD196");
        root.setTop(cTitle);
    }

    private void createOptionPanel() {
        TextField link = new TextField();
        link.setEditable(false);
        link.setPrefWidth(120);
        HBox optionPane = new HBox();
        optionPane.setSpacing(20);
        optionPane.setPrefHeight(90);
        items = optionPane.getChildren();
        optionPane.setPadding(new Insets(5, 20, 10, 20));
        optionPane.setAlignment(Pos.CENTER);
        Label controls = new Label("Controls: ");
        controls.setTextFill(Color.web("White"));
        ToggleGroup group = new ToggleGroup();
        normal.setToggleGroup(group);
        normal.setTextFill(Color.web("White"));
        normal.setSelected(true);
        Inverse.setToggleGroup(group);
        Inverse.setTextFill(Color.web("White"));
        encode.setDisable(true);
        codify.setDisable(true);
        items.addAll(controls, loadTxt, link, normal, Inverse, codify);
        optionPane.setStyle("-fx-background-color:BBD196");
        root.setBottom(optionPane);

        loadTxt.setOnAction(e -> {
            final FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Stage());
            if (file == null) {//Verifica que el archivo seleccionado sea elegido correctamente
                showAlert("No se ha seleccionado ningún archivo. \nPor favor inténtelo nuevamente.");
            } else if (!file.toString().substring(file.toString().length() - 3).equals("txt")) {
                showAlert("El formato del archivo seleccionado es incorrecto. \nPor favor inténtelo nuevamente con un archivo en formato .txt.");
            } else {
                FileReader txtData = new FileReader(file);
                textFile = txtData.getTexto();
                link.setText(file.toString());
                codify.setDisable(false);
            }
        });

        codify.setOnAction(e -> {
            loadInfoText(' ', 0, false);
        });

        choose.setOnAction(e -> {
            items.clear();
            items.addAll(controls, loadTxt, link, normal, Inverse, codify);
            input.setDisable(true);
            codify.setDisable(true);
            link.clear();
            setNodes();
        });

        encode.setOnAction(e -> {
            encode.setDisable(true);
            decode.setDisable(false);
            encoding = true;
            String copy = input.getText();
            input.setText(output.getText());
            output.setText(copy);

        });

        decode.setOnAction(e -> {
            decode.setDisable(true);
            encode.setDisable(false);
            encoding = false;
            String copy = input.getText();
            input.setText(output.getText());
            output.setText(copy);

        });
    }

    public void disableButtons() {
        save.setDisable(true);
        choose.setDisable(true);
        input.setDisable(true);
        encode.setDisable(true);
        decode.setDisable(true);
    }

    public void loadInfoText(char c, int i, boolean b) {
        encryption = createEncryption(normal.isSelected(), textFile, c, i, b); // Se crea cifrado de Huffman a base del archivo seleccionado
        showText.setText(textFile); //Muestra el contenido del arhivo
        showEncode.setText(encryption.encode(textFile)); //Muestra el contenido codificado
        input.setEditable(true); //Activa la edición del nodo input
        input.setDisable(false);
        flagFrequence = true;
        save.setDisable(false);
        createDictionary();
        change.setDisable(false);
        choose.setDisable(false);
        decode.setDisable(false);
        items.clear();
        items.addAll(choose, encode, decode);
    }

    public static Encryption createEncryption(boolean isNormal, String text, char c, int i, boolean edit) { //Se crea una instancia de tipo Encryption
        FrequencyTable ft = new FrequencyTable(isNormal, text, c, i, edit);
        EncriptedData ed = new EncriptedData(ft);
        Encryption e = new Encryption(ed);
        return e;
    }

    public static void showAlert(String text) { //Muestra una alerta dependiendo del tipo de error
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    private void createTranslator() {
        ObservableList items2 = translationPane.getChildren();
        translationPane.setSpacing(8);
        translationPane.setPadding(new Insets(5, 10, 5, 10));
        translationPane.setAlignment(Pos.TOP_CENTER);
        items2.addAll(input, output);
        translationPane.setStyle("-fx-background-color:f1efe9");
        root.setCenter(translationPane);
        input.setEditable(false);
        input.setDisable(true);
        input.setWrapText(true);
        output.setEditable(false);
        output.setWrapText(true);
        input.setPrefSize(250, 250);
        output.setPrefSize(250, 250);
        input.setOnKeyReleased(e -> {
            if (encoding == true) {
                output.setText(encryption.encode(input.getText()));
            } else {
                output.setText(encryption.decode(input.getText()));
            }
        });
    }

    private void createUploadPanel() {
        uploadPane.setSpacing(15);
        uploadPane.setPadding(new Insets(10, 5, 5, 10));
        save = new Button("Save encoded text");
        save.setDisable(true);
        showText.setEditable(false);//Desactiva la opción de editar el nodo desde la interfaz
        showText.setWrapText(true);//El texto dentro del nodo se adapta al tamaño del mismo
        showEncode.setEditable(false);
        showEncode.setWrapText(true);
        showText.setPrefSize(200, 190);
        showEncode.setPrefSize(200, 190);
        uploadPane.getChildren().addAll(showText, showEncode, save);
        uploadPane.setStyle("-fx-background-color:E0ECE4");
        uploadPane.setMaxWidth(250);
        root.setLeft(uploadPane);
        save.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text file (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(new Stage());
            if (file != null) {
                saveTextToFile(showEncode.getText(), file);
                showAlert("Archivo guardado con éxito.");
            }
        });
    }

    private void createDictionary() {
        frequencyTable.getChildren().clear();
        frequencyPane.getChildren().clear();
        frequencyPane.setPadding(new Insets(10, 5, 10, 5));
        frequencyPane.setPrefWidth(200);
        frequencyPane.setSpacing(10);
        frequencyPane.setAlignment(Pos.TOP_CENTER);
        Label header = new Label("Frequence table\n");
        header.setFont(Font.font("Californian FB", FontPosture.ITALIC, 20));
        header.setTextFill(Color.web("DarkCyan"));
        change = new Button("Change frequence");
        executeChange = new Button("Accept");
        executeChange.setOnAction(e -> {
            setNodes();
            loadInfoText(caracter, amount, true);
            showAlert("La frecuencia de ha modificado con éxito.");
        });
        change.setDisable(true);
        change.setOnAction(e -> {
            disableButtons();
            FrequencePane fp = new FrequencePane();
            frequencyPane.getChildren().remove(change);
            frequencyPane.getChildren().add(executeChange);
        });
        ScrollPane scroll = new ScrollPane(frequencyTable);
        scroll.setMinSize(200, 390);//425
        Label l1 = new Label("Symbol ");
        l1.setPrefSize(45, 25);
        l1.setAlignment(Pos.CENTER);
        l1.setStyle("-fx-background-color:Lavender");
        Label l2 = new Label("Freq");
        l2.setPrefSize(40, 15);
        l2.setAlignment(Pos.CENTER);
        l2.setStyle("-fx-background-color:Lavender");
        Label l3 = new Label("Code");
        l3.setPrefSize(100, 25);
        l3.setAlignment(Pos.CENTER);
        l3.setStyle("-fx-background-color:Lavender");
        frequencyTable.add(l1, 0, 0);
        frequencyTable.add(l2, 1, 0);
        frequencyTable.add(l3, 2, 0);
        int cont = 1;
        if (flagFrequence == true) {
            while (!encryption.getEd().getFt().getSymbols().isEmpty()) {
                Symbol s = encryption.getEd().getFt().getSymbols().poll();
                String code = encryption.getEd().getMapEnc().get(s.getSign().charAt(0));
                Label ca = new Label(s.getSign() + "   ");
                ca.setPrefSize(45, 25);
                ca.setAlignment(Pos.CENTER);
                TextField am = new TextField(String.valueOf(s.getAmount()));
                am.setPrefSize(40, 15);
                am.setEditable(false);
                Label co = new Label("    " + code);
                co.setPrefSize(100, 25);
                if (cont % 2 == 0) {
                    ca.setStyle("-fx-background-color:Lightblue");
                    am.setStyle("-fx-background-color:Lightblue");
                    co.setStyle("-fx-background-color:Lightblue");
                } else {
                    ca.setStyle("-fx-background-color:Lightcyan");
                    am.setStyle("-fx-background-color:Lightcyan");
                    co.setStyle("-fx-background-color:Lightcyan");
                }
                frequencyTable.add(ca, 0, cont);
                frequencyTable.add(am, 1, cont);
                frequencyTable.add(co, 2, cont);
                cont++;
            }
        }
        frequencyTable.setAlignment(Pos.CENTER);
        frequencyTable.setGridLinesVisible(true);
        frequencyPane.getChildren().addAll(header, scroll, change);
        frequencyPane.setStyle("-fx-background-color:E0ECE4");
        root.setRight(frequencyPane);

    }

    public BorderPane getRoot() {
        return root;
    }

    static void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
