module com.example.swe206project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.swe206project to javafx.fxml, javafx.controls;
    exports com.example.swe206project;
//    exports com.example.swe206project.models;
//    opens com.example.swe206project.models to javafx.controls, javafx.fxml;
//    exports com.example.swe206project.controllers;
//    opens com.example.swe206project.controllers to javafx.controls, javafx.fxml;
}