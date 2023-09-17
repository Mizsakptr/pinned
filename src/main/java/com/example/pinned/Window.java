package com.example.pinned;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Window extends Stage {
    public Window() {
        ArrayList<String> history = new ArrayList<>();
        AtomicInteger current = new AtomicInteger();
        current.set(0);
        AtomicBoolean backorforw = new AtomicBoolean(false);
        TextField text = new TextField();
        text.setText("https://www.google.com/");
        text.setPrefWidth(600);
        history.add(text.getText());
        Button go = new Button("Go");
        Button newtab = new Button("+");
        Button backwards = new Button("<-");
        Button forwards = new Button("->");

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        webEngine.load(text.getText());

        go.setOnAction(event -> {
            webEngine.load(text.getText());
        });

        text.setOnKeyPressed(ke -> {

            if (ke.getCode().equals(KeyCode.ENTER)) {
                
                webEngine.load(text.getText());
                
            }

        });

        webEngine.locationProperty().addListener((observable, oldURL, newURL) -> {

            System.out.println("Új URL: " + newURL);
            text.setText(newURL);
            
            if(!backorforw.get()){
                current.getAndIncrement();
                history.add(newURL);
            }

            backorforw.set(false);
            System.out.printf(history.toString());
            System.out.printf(String.valueOf(current.get()));
            
        });

        backwards.setOnAction(event ->{

            backorforw.set(true);
            current.getAndDecrement();
            webEngine.load(history.get(current.get()));
            System.out.printf(history.toString());
            System.out.printf(String.valueOf(current.get()));

        });

        forwards.setOnAction(event ->{

            backorforw.set(true);
            current.getAndIncrement();
            webEngine.load(history.get(current.get()));
            System.out.printf(history.toString());
            System.out.printf(String.valueOf(current.get()));

        });





        newtab.setOnAction(e -> App.newWindow());

        HBox inputBox = new HBox(10);
        inputBox.getChildren().addAll(backwards,forwards,text, go, newtab);

        BorderPane root = new BorderPane();
        root.setTop(inputBox);
        root.setCenter(webView);

        Scene scene = new Scene(root, 800, 600);
        setScene(scene);
    }
}
