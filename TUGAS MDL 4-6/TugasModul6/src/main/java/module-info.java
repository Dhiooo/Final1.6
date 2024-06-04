module org.example.TugasModul6 {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.TugasModul6 to javafx.fxml;
    exports org.example.TugasModul6;
}
