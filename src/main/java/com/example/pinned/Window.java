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

public class Window extends Stage {
    public Window() {
        TextField text = new TextField();
        text.setPrefWidth(700);
        Button go = new Button("Go");
        Button newtab = new Button("+");

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        webEngine.load("https://www.google.com/");

        go.setOnAction(event -> {

            webEngine.load(text.getText());

        });

        text.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                webEngine.load(text.getText());
            }
        });

        newtab.setOnAction(e -> App.newWindow());

        HBox inputBox = new HBox(10);
        inputBox.getChildren().addAll(text, go, newtab);

        BorderPane root = new BorderPane();
        root.setTop(inputBox);
        root.setCenter(webView);

        Scene scene = new Scene(root, 800, 600);
        setScene(scene);
    }
}
