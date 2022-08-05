package com.example.demo;

import com.example.demo.Utilities.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Home implements Initializable {
    private final String[] genderList = {"Select your gender", "Male", "Female", "Others"};

    @FXML
    public Button studentFormSave;
    @FXML
    public TextField studentName, studentAge, studentID, studentProgram;
    public String name, id, program, gender;
    public int age;
    public ArrayList<Student> studentList = new ArrayList<Student>();
    @FXML
    public TableView<Student> studentTable;
    @FXML
    public TableColumn<Student, String> tableStudentName;
    @FXML
    public TableColumn<Student, String> tableStudentID;
    @FXML
    public TableColumn<Student, String> tableStudentGender;
    @FXML
    public TableColumn<Student, String> tableStudentProgram;

    @FXML
    public TableColumn<Student, Integer> tableStudentAge;
    public ComboBox<String> stuGender;
    ObservableList<Student> studentData = FXCollections.observableArrayList(studentList);

    public Home() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stuGender.getItems().addAll(genderList);
        stuGender.getSelectionModel().selectFirst();

        tableStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        tableStudentAge.setCellValueFactory(new PropertyValueFactory<>("studentAge"));
        tableStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        tableStudentGender.setCellValueFactory(new PropertyValueFactory<>("studentGender"));
        tableStudentProgram.setCellValueFactory(new PropertyValueFactory<>("studentProgram"));

        studentList.add(new Student("Jayden", 19, "123456", "Male", "Diploma In Information Technology"));
        studentList.add(new Student("Alice", 22, "3245982", "Female", "Diploma In Business Management"));

        for (Student stu : studentList) {
            studentTable.getItems().add(stu);
        }
    }

    public void resetStudentForm() {
        studentName.setText("");
        studentAge.setText("");
        studentID.setText("");
        stuGender.getSelectionModel().clearSelection();
        stuGender.getSelectionModel().selectFirst();
        studentProgram.setText("");
    }

    public void addStudent() throws IOException {
        ArrayList<Student> tempStudents = new ArrayList<>();

        name = studentName.getText();
        age = Integer.parseInt(studentAge.getText());
        id = studentID.getText();
        gender = stuGender.getValue();
        program = studentProgram.getText();

        studentList.add(new Student(name, age, id, gender, program));
        tempStudents.add(new Student(name, age, id, gender, program));

        for (Student stu : tempStudents) {
            studentTable.getItems().add(stu);
        }

        resetStudentForm();
    }

    public void printStudents() {
        for (Student stu : studentList) {
            System.out.println(stu);
        }
    }
}
