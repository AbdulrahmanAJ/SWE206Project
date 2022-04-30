module com.example.swe206project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.swe206project to javafx.fxml, javafx.controls;
    exports com.example.swe206project;
}