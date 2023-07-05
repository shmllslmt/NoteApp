package com.shamilla.noteapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class NoteApp extends Application {
    private ListView<String> lvNotes;
    private ObservableList<String> notes;
    private ComboBox<String> category;
    private TextArea txtNotes;
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane layout = new BorderPane();

        category = new ComboBox<>();
        category.getItems().addAll("Personal", "Study", "Work", "Miscellaneous");

        Button btnAdd = new Button("Add");
        btnAdd.setPrefWidth(150);
        btnAdd.setOnAction(e -> addNote());

        Button btnDelete = new Button("Delete");
        btnDelete.setPrefWidth(150);
        btnDelete.setOnAction(e -> deleteNote());

        HBox topbar = new HBox(category, btnAdd, btnDelete);
        topbar.setPadding(new Insets(10));
        topbar.setSpacing(10);

        layout.setTop(topbar);

        notes = FXCollections.observableArrayList();
        lvNotes = new ListView<>();
        lvNotes.setItems(notes);

        layout.setLeft(lvNotes);

        txtNotes = new TextArea();
        txtNotes.setWrapText(true);
        layout.setCenter(txtNotes);

        Scene scene = new Scene(layout);
        stage.setTitle("Note App");
        stage.setScene(scene);
        stage.show();
    }

    public void addNote() {
        String note = txtNotes.getText();
        String cat = category.getValue();

        if(note.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Notes");
            alert.setContentText("The note is empty. Type in something there maybe?");
            alert.showAndWait();
            return;
        }

        notes.add(note + " ("+cat+")");
        txtNotes.clear();
    }

    public void deleteNote() {
        int selectedIndex = lvNotes.getSelectionModel().getSelectedIndex();

        if(selectedIndex >= 0) {
            notes.remove(selectedIndex);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}