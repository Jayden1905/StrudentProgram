package com.example.demo;

import com.example.demo.Utilities.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Home implements Initializable {
    private final String[] genderList = {"Select your gender", "Male", "Female", "Others"};
    @FXML
    public ComboBox<String> stuGender;

    @FXML
    public Button studentFormSave;

    @FXML
    public TextField studentName, studentAge, studentID, studentProgram;

    public String name, id, program, gender;
    public int age;

    public ArrayList<Student> studentList = new ArrayList<Student>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stuGender.getItems().addAll(genderList);
        stuGender.getSelectionModel().selectFirst();
        stuGender.setOnAction(this::comboBox);
    }

    public void comboBox(ActionEvent event) {
    }

    public void resetStudentForm() {
        studentName.setText("");
        studentAge.setText("");
        studentID.setText("");
        stuGender.getSelectionModel().clearSelection();
        stuGender.getSelectionModel().selectFirst();
        studentProgram.setText("");
    }


    public void addStudent() {
        this.name = studentName.getText();
        this.age = Integer.parseInt(studentAge.getText());
        this.id = studentID.getText();
        this.gender = stuGender.getValue();
        this.program = studentProgram.getText();

        studentList.add(new Student(name, age, id, gender, program));
        resetStudentForm();
    }

    public void printStudents() {
        for (Student stu : studentList) {
            System.out.println(stu);
        }
    }
}
