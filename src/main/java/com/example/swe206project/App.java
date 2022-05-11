package com.example.swe206project;

import com.example.swe206project.models.Interviewer;
import com.example.swe206project.models.Unit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    // set the database
    public static Database database;

    @Override
    public void start(Stage stage) throws IOException {
        database = new Database(1);

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        database.saveData();
    }
}