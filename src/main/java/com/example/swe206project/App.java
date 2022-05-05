package com.example.swe206project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("App");
        stage.setScene(scene);
        stage.show();
        System.out.println("java version: " +   System.getProperty("java.version"  ));
        System.out.println("javafx.version: " + System.getProperty("javafx.version"));
    }

    public static void main(String[] args) {
        launch();
    }
}