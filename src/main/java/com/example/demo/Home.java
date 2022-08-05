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
import java.util.Objects;
import java.util.ResourceBundle;

public class Home implements Initializable {
    private final String[] genderList = {"Select your gender", "Male", "Female", "Others"};

    public Student selectedStudent;

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
    @FXML
    public Button StudentFormUpdate;

    ObservableList<Student> studentData = FXCollections.observableArrayList(studentList);

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
        studentList.add(new Student("Jordan", 20, "1945739", "Male", "Diploma In Robotic Engineering"));
        studentList.add(new Student("Nike", 23, "3452678", "Female", "Diploma In Tourism"));

        studentTable.setItems(studentData);

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

        if (!Objects.equals(studentName.getText(), "") && !Objects.equals(studentAge.getText(), "") &&
                !Objects.equals(stuGender.getValue(), "") &&
                !Objects.equals(studentID.getText(), "") || !Objects.equals(studentProgram.getText(), "")) {
            try {
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

                alertMessae("information", "SUCCESS", "Added the student",
                        "The student is successfully added to the database!");

                resetStudentForm();
            } catch (NumberFormatException error) {
                alertMessae("error", "ERROR", "Incorrect Input",
                        "Please check your inputs again!");
                studentAge.setText("");
            }
        } else {
            alertMessae("error", "ERROR", "Input Requires",
                    "You need to enter all requested information.");
        }
    }

    public void editData(Student stu) {
        selectedStudent = stu;
        studentName.setText(selectedStudent.getStudentName());
        studentAge.setText(String.valueOf(selectedStudent.getStudentAge()));
        stuGender.setValue(selectedStudent.getStudentGender());
        studentID.setText(selectedStudent.getStudentID());
        studentProgram.setText(selectedStudent.getStudentProgram());
    }

    public Student updateData(Student stu) {

        selectedStudent = stu;

        selectedStudent.setStudentName(studentName.getText());
        selectedStudent.setStudentAge(Integer.parseInt(studentAge.getText()));
        selectedStudent.setStudentGender(stuGender.getValue());
        selectedStudent.setStudentID(studentID.getText());
        selectedStudent.setStudentProgram(studentProgram.getText());

        return stu;
    }

    public void insertStudent() {
        if (studentTable.getSelectionModel().getSelectedItem() != null) {
            editData(studentTable.getSelectionModel().getSelectedItem());
        } else {
            alertMessae("warning", "WARNING", "Selection requires!",
                    "You need to select the data from the table.");
        }
    }

    public void updateStudent() {
        if (studentTable.getSelectionModel().getSelectedItem() != null) {
            studentTable.getItems().add(studentTable.getSelectionModel().getSelectedIndex(),
                    updateData(studentTable.getSelectionModel().getSelectedItem()));
            studentTable.getItems().remove(studentTable.getSelectionModel().getSelectedIndex() - 1);
            studentTable.refresh();

            studentList.add(updateData(studentTable.getSelectionModel().getSelectedItem()));
            studentList.remove(studentTable.getSelectionModel().getSelectedIndex());

            resetStudentForm();
        } else {
            alertMessae("warning", "WARNING", "Can't find any Information to update!",
                    "You need to select and edit the data first to update them.");
        }
    }

    public void deleteStudent() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ERROR");
        alert.setHeaderText("Deleting!");
        alert.setContentText("Do you want to delete it?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            studentList.remove(studentTable.getSelectionModel().getSelectedItem());

            studentTable.getItems().remove(studentTable.getSelectionModel().getSelectedIndex());
            studentTable.refresh();

            resetStudentForm();
        }
    }

    public void alertMessae(String type, String title, String headerText, String contentText) {
        Alert alert = null;

        if (type.equalsIgnoreCase("error")) {
            alert = new Alert(Alert.AlertType.ERROR);
        } else if (type.equalsIgnoreCase("warning")) {
            alert = new Alert(Alert.AlertType.WARNING);
        } else if (type.equalsIgnoreCase("information")) {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }

        assert alert != null;
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }
}
