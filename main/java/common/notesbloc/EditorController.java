

package common.notesbloc;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditorController{

    private Stage stage;
    private Note currentNote;

    @FXML private TextField noteTitle;
    @FXML private TextArea textArea;


    public void setStage(Stage stage) {
        this.stage = stage;}


    public void setCurrentNote(Note note){
        this.currentNote = note;
        this.noteTitle.setText(this.currentNote.getTitle());
        this.textArea.setText(this.currentNote.getContent());
    }

    public void saveCurrentNote() {
        this.currentNote.setTitle(this.noteTitle.getText());
        this.currentNote.setContent(this.textArea.getText());

        this.currentNote.save();
    }

    public void deleteCurrentNote() {
        this.currentNote.delete();
        this.currentNote = null;
        this.goToMenu();
    }

    public void goToMenu() {
        try{
            if (this.currentNote != null) this.saveCurrentNote();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Parent root = loader.load();

            MenuController menuController = loader.getController();
            menuController.setStage(stage);

            stage.setScene(new Scene(root, 500, 500));
            stage.setTitle("Menú Principal");
        }catch (Exception e){
            System.out.println(e);
        }
    }



}

