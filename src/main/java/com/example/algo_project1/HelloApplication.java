package com.example.algo_project1;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        MainInterFace(primaryStage);
    }
    private static void MainInterFace(Stage stage) {
        BorderPane borderPane = new BorderPane();
        Label l_Top = new Label("Welcome to HuffMan");
        l_Top.setFont(new Font("Arial", 22));
        Label l_Stm1 = new Label("To compress any file, press the red button");
        Label l_Stm2 = new Label("To decompress any file, press the green button");
        Label l_res = new Label("__");


        VBox v = new VBox();
        v.setSpacing(20);
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(l_Top, l_Stm1, l_Stm2,l_res);
        borderPane.setTop(v);
        BorderPane.setAlignment(v, Pos.CENTER);

        TextArea ta = new TextArea();
        ta.setPrefSize(440, 300);
        ta.setFont(new Font(15));
        borderPane.setBottom(ta);
        BorderPane.setAlignment(ta, Pos.BASELINE_CENTER);

        Button ptnCompress = new Button("Compress");
        ptnCompress.setStyle("fx-background-color:rgba(55,88,52,0.6);-fx-background-radius:20;fx-text-fill:yellow ");
        ptnCompress.setPrefSize(200, 60);
        ptnCompress.setFont(new Font("Arial", 18));

        ptnCompress.setOnAction(e->{
            FileChooser fc = new FileChooser();
            File file = fc.showOpenDialog(stage);
            try {
                Huffman.compress(file);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }



            l_res.setText("Compress Done");



            ta.appendText("File path: " + file.getPath() + "\nCompressed file path: " + Huffman.outFileName
                    + "\n\nASCII\tCharacter\t\tFrequency\tHuffCode\n");
            for (int k = 0; k < Huffman.huffCodeArray.length; k++) {
                if((int)Huffman.huffCodeArray[k].character==10 || (int)Huffman.huffCodeArray[k].character==9)
                    continue;
                ta.appendText(String.valueOf((int) Huffman.huffCodeArray[k].character) + "\t\t  "
                        + Huffman.huffCodeArray[k].character + "\t\t\t"
                        + String.valueOf(Huffman.huffCodeArray[k].counter) + "\t\t\t"
                        + Huffman.huffCodeArray[k].huffCode+"\n");
            }

        });



        Button ptnDecompress = new Button("Decompress");
        ptnDecompress.setStyle("fx-background-color:rgba(55,88,52,0.6);-fx-background-radius:20;fx-text-fill:yellow ");
        ptnDecompress.setPrefSize(200, 60);
        ptnDecompress.setFont(new Font("Arial", 18));
        ptnDecompress.setOnAction(e->{
            FileChooser fc = new FileChooser();
            File file = fc.showOpenDialog(stage);
            Huffman.deCompress(file);
            l_res.setText("Decompress Done");
        });
        HBox h = new HBox();
        h.setSpacing(20);
        h.setAlignment(Pos.CENTER);
        h.getChildren().addAll(ptnDecompress, ptnCompress);
        borderPane.setCenter(h);
        BorderPane.setAlignment(h, Pos.CENTER);

        Scene sene = new Scene(borderPane, 700, 600);
        stage.setScene(sene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}