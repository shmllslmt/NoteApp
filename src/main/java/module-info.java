module com.shamilla.noteapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.shamilla.noteapp to javafx.fxml;
    exports com.shamilla.noteapp;
}