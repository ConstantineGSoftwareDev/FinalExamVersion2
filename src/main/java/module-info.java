module com.example.finalexam {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.example.finalexam.Model to com.google.gson, javafx.fxml,javafx.base ;
    opens com.example.finalexam to javafx.fxml;
    exports com.example.finalexam;
}