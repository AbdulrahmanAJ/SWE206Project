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
        // If it's your first time running the app on your PC, please put 1 and the run the app.
        // After you run the app, close it and delete the 1 and run it again.
                             // |
                             // |
                             // V
        database = new Database();

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