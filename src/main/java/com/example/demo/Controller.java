package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public Button homeBtn, ulBtn, imageBtn, gearBtn;
    @FXML
    public AnchorPane content;

    public void navigation(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        if (Objects.equals(button.getId(), "homeBtn")) {
            changeContentScreen("Home.fxml");

            removeActiveClass();
            homeBtn.getGraphic().getStyleClass().setAll("sidebar_active", "sidebar_opt");
        }

        if (Objects.equals(button.getId(), "ulBtn")) {
            changeContentScreen("Listing.fxml");

            removeActiveClass();
            homeBtn.getGraphic().getStyleClass().add("sidebar_opt");
            ulBtn.getGraphic().getStyleClass().setAll("sidebar_active", "sidebar_opt");
        }

        if (Objects.equals(button.getId(), "imageBtn")) {
            changeContentScreen("image.fxml");

            removeActiveClass();
            homeBtn.getGraphic().getStyleClass().add("sidebar_opt");
            imageBtn.getGraphic().getStyleClass().setAll("sidebar_active", "sidebar_opt");
        }

        if (Objects.equals(button.getId(), "gearBtn")) {
            changeContentScreen("Gear.fxml");

            removeActiveClass();
            homeBtn.getGraphic().getStyleClass().add("sidebar_opt");
            gearBtn.getGraphic().getStyleClass().setAll("sidebar_active", "sidebar_opt");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            changeContentScreen("Home.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeActiveClass() {
        homeBtn.getGraphic().getStyleClass().remove("sidebar_active");
        ulBtn.getGraphic().getStyleClass().remove("sidebar_active");
        imageBtn.getGraphic().getStyleClass().remove("sidebar_active");
        gearBtn.getGraphic().getStyleClass().remove("sidebar_active");
    }

    public void changeContentScreen(String fxml) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        content.getChildren().removeAll();
        content.getChildren().setAll(root);
    }
}

class Module {

}

class Assessment {

}

class Student {

}
