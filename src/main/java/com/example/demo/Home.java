package com.example.demo;

import com.example.demo.Utilities.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    public Student studentArray = new Student();

    public ObservableList<Student> studentData = FXCollections.observableArrayList(studentArray.getStudentList());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stuGender.getItems().addAll(genderList);
        stuGender.getSelectionModel().selectFirst();

        tableStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        tableStudentAge.setCellValueFactory(new PropertyValueFactory<>("studentAge"));
        tableStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        tableStudentGender.setCellValueFactory(new PropertyValueFactory<>("studentGender"));
        tableStudentProgram.setCellValueFactory(new PropertyValueFactory<>("studentProgram"));

        for (Student stu : studentArray.getStudentList()) {
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

                studentArray.getStudentList().add(new Student(name, age, id, gender, program));
                tempStudents.add(new Student(name, age, id, gender, program));

                for (Student stu : tempStudents) {
                    studentTable.getItems().add(stu);
                }

                alertMessage("information", "SUCCESS", "Added the student",
                        "The student is successfully added to the database!");

                resetStudentForm();
            } catch (NumberFormatException error) {
                alertMessage("error", "ERROR", "Incorrect Input",
                        "Please check your inputs again!");
                studentAge.setText("");
            }
        } else {
            alertMessage("error", "ERROR", "Input Requires",
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
            alertMessage("warning", "WARNING", "Selection requires!",
                    "You need to select the data from the table.");
        }
    }

    public void updateStudent() {
        if (studentTable.getSelectionModel().getSelectedItem() != null) {
            studentTable.getItems().add(studentTable.getSelectionModel().getSelectedIndex(),
                    updateData(studentTable.getSelectionModel().getSelectedItem()));
            studentTable.getItems().remove(studentTable.getSelectionModel().getSelectedIndex() - 1);
            studentTable.refresh();

            studentArray.getStudentList().add(updateData(studentTable.getSelectionModel().getSelectedItem()));
            studentArray.getStudentList().remove(studentTable.getSelectionModel().getSelectedIndex());

            resetStudentForm();
        } else {
            alertMessage("warning", "WARNING", "Can't find any Information to update!",
                    "You need to select and edit the data first to update them.");
        }
    }

    public void deleteStudent() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ERROR");
        alert.setHeaderText("Deleting!");
        alert.setContentText("Do you want to delete it?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            studentArray.getStudentList().remove(studentTable.getSelectionModel().getSelectedItem());

            studentTable.getItems().remove(studentTable.getSelectionModel().getSelectedIndex());
            studentTable.refresh();

            resetStudentForm();
        }
    }

    public void alertMessage(String type, String title, String headerText, String contentText) {
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
}
