package com.example._218scenebuilder;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;

import javafx.geometry.Orientation;


// Credit to: https://stackoverflow.com/questions/40231858/what-is-rule-to-use-scrollbar-in-javafx

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        //ScrollBar scroll = new ScrollBar();
        //scroll.setOrientation(Orientation.VERTICAL);
        //scroll.setMin(0);
        //scroll.setMax(400);
        //scroll.setValue(50); // Where it starts
        //scroll.setLayoutX(180);
        //scroll.setLayoutY(75);

        // Group root = new Group(fxmlLoader.load());

        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Hello!");


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
