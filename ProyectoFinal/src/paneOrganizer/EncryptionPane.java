/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneOrganizer;

import classes.EncriptedData;
import classes.Encryption;
import classes.FileReader;
import classes.FileWritter;
import classes.FrequencyTable;
import classes.Symbol;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    FlowPane optionPane = new FlowPane();
    FlowPane translationPane = new FlowPane(Orientation.HORIZONTAL);
    FlowPane uploadPane = new FlowPane(Orientation.VERTICAL);
    FlowPane frequencyPane = new FlowPane(Orientation.VERTICAL);
    Button loadTxt = new Button("Upload file");
    Button codify = new Button("Codify");
    RadioButton normal = new RadioButton("Normal");
    RadioButton Inverse = new RadioButton("Inverse");
    TextArea input = new TextArea();
    TextArea output = new TextArea();
    TextArea showText = new TextArea();
    TextArea showEncode = new TextArea();
    GridPane frequencyTable = new GridPane();
    boolean flag;
    String textFile;
    Button encode = new Button("Encode");
    Button decode = new Button("Decode");
    boolean encoding;
    Button choose = new Button("Choose new file");
    Button save;
    public static char caracter2;
    public static int amount2;
    Button change;
//    boolean flagAmount;

    public EncryptionPane() {
        root = new BorderPane();
        createTitle();
        createOptionPanel();
        createTranslator();
        createUploadPanel();
        createDictionary();
        flag = false;
        encoding = true;
    }

    private void createTitle() {
        Label title = new Label("Huffman Coding");
        HBox cTitle = new HBox(title);
        title.setFont(Font.font("Blue", FontPosture.REGULAR, 40));
        cTitle.setAlignment(Pos.CENTER);
        cTitle.setMinHeight(70);
        root.setTop(cTitle);
    }

    private void createOptionPanel() {
        TextField link = new TextField();
        link.setEditable(false);
        link.setPrefWidth(120);
        optionPane.setHgap(20);
        ObservableList items = optionPane.getChildren();
        optionPane.setPadding(new Insets(0, 20, 0, 20));
        optionPane.setAlignment(Pos.TOP_CENTER);
        Label controls = new Label("Controls: ");
        ToggleGroup group = new ToggleGroup();
        normal.setToggleGroup(group);
        normal.setSelected(true);
        Inverse.setToggleGroup(group);
        encode.setDisable(true);
        codify.setDisable(true);
        items.addAll(controls, loadTxt, link, normal, Inverse, codify);
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
            encryption = createEncryption(normal.isSelected(), textFile,' ',0,false); // Se crea cifrado de Huffman a base del archivo seleccionado
            showText.setText(textFile); //Muestra el contenido del arhivo
            showEncode.setText(encryption.encode(textFile)); //Muestra el contenido codificado
            input.setEditable(true); //Activa la edición del nodo input
            input.setDisable(false);
            flag = true;
            save.setDisable(false);
            createDictionary();
            change.setDisable(false);
            items.clear();
            items.addAll(choose, encode, decode);

        });

        choose.setOnAction(e -> {
            items.clear();
            items.addAll(controls, loadTxt, link, normal, Inverse, codify);
            codify.setDisable(true);
            link.clear();
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

    public static Encryption createEncryption(boolean isNormal, String text, char c, int i, boolean edit) { //Se crea una instancia de tipo Encryption
        FrequencyTable ft = new FrequencyTable(isNormal, text);
        if (edit) {
            ft.getFrequency().put(c, i);
        }
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
        ObservableList items = translationPane.getChildren();
        translationPane.setHgap(8);
        translationPane.setPadding(new Insets(20, 5, 0, 10));
        translationPane.setAlignment(Pos.TOP_CENTER);
        items.addAll(input, output);
        root.setCenter(translationPane);
        input.setEditable(false);
        input.setDisable(true);
        input.setWrapText(true);
        output.setEditable(false);
        output.setWrapText(true);
        input.setPrefSize(300, 450);//450
        output.setPrefSize(300, 450);//450
        input.setOnKeyReleased(e -> {
            if (encoding == true) {
                output.setText(encryption.encode(input.getText()));
            } else {
                output.setText(encryption.decode(input.getText()));
            }
        });
    }

    private void createUploadPanel() {
        uploadPane.setVgap(15);
        uploadPane.setPadding(new Insets(0, 0, 0, 10));
        save = new Button("Save encoded text");
        save.setDisable(true);
        showText.setEditable(false);//Desactiva la opción de editar el nodo desde la interfaz
        showText.setWrapText(true);//El texto dentro del nodo se adapta al tamaño del mismo
        showEncode.setEditable(false);
        showEncode.setWrapText(true);
        showText.setPrefSize(200, 190);//225
        showEncode.setPrefSize(200, 190);//225
        uploadPane.getChildren().addAll(showText, showEncode, save);
//        items.addAll(showText, showEncode);
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
        frequencyPane.getChildren().clear();
        frequencyPane.setPadding(new Insets(0, 5, 0, 0));
        frequencyPane.setPrefWidth(250);
        frequencyPane.setVgap(10);
        frequencyPane.setAlignment(Pos.TOP_CENTER);
        Label header = new Label("Frequence table\n");
        change = new Button("Change frequence");
        change.setDisable(true);
        change.setOnAction(e -> {
            FrequencePane fp = new FrequencePane(textFile,normal.isSelected());
        });
        ScrollPane scroll = new ScrollPane(frequencyTable);
        scroll.setPrefSize(160, 325);//425
        Label l1 = new Label("Symbol ");
        l1.setPrefSize(45, 25);
        l1.setAlignment(Pos.CENTER);
        l1.setStyle("-fx-background-color:Lavender");
        Label l2 = new Label("Freq");
        l2.setPrefSize(40, 15);
        l2.setAlignment(Pos.CENTER);
        l2.setStyle("-fx-background-color:Lavender");
        Label l3 = new Label("Code");
        l3.setPrefSize(70, 25);
        l3.setAlignment(Pos.CENTER);
        l3.setStyle("-fx-background-color:Lavender");
        frequencyTable.add(l1, 0, 0);
        frequencyTable.add(l2, 1, 0);
        frequencyTable.add(l3, 2, 0);
        int cont = 1;
        if (flag == true) {
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
            ex.printStackTrace();
        }
    }


    
    
}
