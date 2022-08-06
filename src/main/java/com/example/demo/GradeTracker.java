package com.example.demo;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class GradeTracker extends javafx.application.Application {

    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) throws IOException {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        Scene scene = new Scene(root);

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });


        String css = "CSS/application.css";
        scene.getStylesheets().add(css);

        primaryStage.setTitle("Grade Tracker");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
//        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
    }

}