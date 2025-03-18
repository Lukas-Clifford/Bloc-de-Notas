package common.notesbloc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private Stage stage;
    private NotesHandler notesHandler;

    @FXML
    private TilePane notesButtons;

    public void setStage(Stage stage) {
        this.stage = stage;}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarBotones();}

    private void cargarBotones() {
        notesButtons.getChildren().clear();
        this.notesHandler = new NotesHandler();

        for(Note note:this.notesHandler.getNotesArray()){
            Button boton = new Button(note.getTitle());
            boton.setOnAction(e -> this.loadNote(note));
            notesButtons.getChildren().add(boton);
        }
    }

    public void newNote() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editor-view.fxml"));
        Parent root = loader.load();

        EditorController editorController = loader.getController();
        editorController.setStage(stage);

        editorController.setCurrentNote(this.notesHandler.newNote());


        stage.setScene(new Scene(root, 500, 500));
        stage.setTitle("Pantalla 1");
    }

    public void loadNote(Note loadingNote){
        try{
            System.out.println("loading note..."+loadingNote.getTitle());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editor-view.fxml"));
            Parent root = loader.load();

            EditorController editorController = loader.getController();
            editorController.setStage(stage);
            editorController.setCurrentNote(loadingNote);


            stage.setScene(new Scene(root, 500, 500));
            stage.setTitle("Pantalla 1");
        }catch (Exception e) {
            System.out.println("Could not load the note: " + loadingNote.getTitle());
        }
    }


}

