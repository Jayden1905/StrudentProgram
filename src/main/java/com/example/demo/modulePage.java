package com.example.demo;


import com.example.demo.Utilities.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class modulePage implements Initializable {
    @FXML
    public ComboBox<String> studentNameBox;

    @FXML
    public ComboBox<String> studentIDBox;

    public Student studentArray = new Student();


    public void logOut(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout");
        alert.setContentText("Do you want to save before existing?");

        if (alert.showAndWait().get() == ButtonType.OK) {

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<String> studentNames = new ArrayList<>();
        ArrayList<String> studentID = new ArrayList<>();


        for (Student stu : studentArray.getStudentList()) {
            studentNames.add(stu.getStudentName());
            studentID.add(stu.getStudentID());
        }
        studentNameBox.getItems().addAll(studentNames);
        studentIDBox.getItems().addAll(studentID);
    }
}
