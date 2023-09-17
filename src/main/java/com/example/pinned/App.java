package com.example.pinned;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    static int i = 0;
    @Override
    public void start(Stage stage) {

        newWindow();

    }

    public static void newWindow(){

        i++;
        Stage newStage = new Window();
        newStage.setTitle(i + ". ablak");
        newStage.setAlwaysOnTop(true);
        newStage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
