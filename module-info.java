// Credit to this article:
// https://edencoding.com/runtime-components-error/

module com.example._218scenebuilder {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;

    opens com.example._218scenebuilder to javafx.graphics;

    exports com.example._218scenebuilder;
}