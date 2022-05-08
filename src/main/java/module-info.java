module com.example.swe206project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.swe206project to javafx.fxml, javafx.controls;
    exports com.example.swe206project;
    exports com.example.swe206project.controllers;
    opens com.example.swe206project.controllers to javafx.controls, javafx.fxml;
    exports com.example.swe206project.controllers.jobBands;
    opens com.example.swe206project.controllers.jobBands to javafx.controls, javafx.fxml;
    exports com.example.swe206project.controllers.candidates;
    opens com.example.swe206project.controllers.candidates to javafx.controls, javafx.fxml;
    exports com.example.swe206project.controllers.hierarchy;
    opens com.example.swe206project.controllers.hierarchy to javafx.controls, javafx.fxml;
    exports com.example.swe206project.controllers.interviewers;
    opens com.example.swe206project.controllers.interviewers to javafx.controls, javafx.fxml;
//    exports com.example.swe206project.models;
//    opens com.example.swe206project.models to javafx.controls, javafx.fxml;
//    exports com.example.swe206project.controllers;
//    opens com.example.swe206project.controllers to javafx.controls, javafx.fxml;
}