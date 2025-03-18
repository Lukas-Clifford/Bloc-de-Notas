module common.notesbloc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens common.notesbloc to javafx.fxml;
    exports common.notesbloc;
}