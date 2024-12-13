module unit13 {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens unit13 to javafx.fxml;
    exports unit13;
}
