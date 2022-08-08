package com.example.demo;

import com.example.demo.Utilities.Assessment;
import com.example.demo.Utilities.Module;
import com.example.demo.Utilities.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Summary implements Initializable {
    public Student studentArray = new Student();

    @FXML
    public ComboBox<String> studentBox;

    @FXML
    public Label studentName, studentID, grade, gpa;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        createModule("Jayden", "123456", "Programming Fundamental", "ITDS 004", "IT", 50);
        createModule("Alice", "3245982", "Problem Solving", "ITDS 001", "Sovling problems with python", 75);
        createModule("Jordan", "1945739", "Communication Networking", "ITDS 003", "Networking", 75);
        createModule("Nike", "3452678", "Business Stats in Python", "ITDS 002", "Maths", 75);

        createAssessments("Jayden", "123456", "Programming Fundamental", "ITDS 004",
                "CA1", "Quz", 95, 100, 10);

        createAssessments("Jayden", "123456", "Programming Fundamental", "ITDS 004",
                "CA2", "Common Test", 85, 100, 40);

        createAssessments("Jayden", "123456", "Programming Fundamental", "ITDS 004",
                "CA3", "Individual Assignment", 100, 100, 40);

        createAssessments("Jayden", "123456", "Programming Fundamental", "ITDS 004",
                "CA4", "Quiz", 90, 100, 10);

        createAssessments("Alice", "3245982", "Problem Solving", "ITDS 001",
                "CA1", "Quz", 85, 100, 10);

        createAssessments("Alice", "3245982", "Problem Solving", "ITDS 001",
                "CA2", "Common Test", 95, 100, 40);

        createAssessments("Alice", "3245982", "Problem Solving", "ITDS 001",
                "CA3", "Individual Assignment", 100, 100, 40);

        createAssessments("Alice", "3245982", "Problem Solving", "ITDS 001",
                "CA4", "Quiz", 96, 100, 10);

        createAssessments("Jordan", "1945739", "Communication Networking", "ITDS 003",
                "CA1", "Quz", 65, 100, 10);

        createAssessments("Jordan", "1945739", "Communication Networking", "ITDS 003",
                "CA2", "Common Test", 75, 100, 40);

        createAssessments("Jordan", "1945739", "Communication Networking", "ITDS 003",
                "CA3", "Individual Assignment", 66, 100, 40);

        createAssessments("Jordan", "1945739", "Communication Networking", "ITDS 003",
                "CA4", "Quiz", 71, 100, 10);

        createAssessments("Nike", "3452678", "Business Stats in Python", "ITDS 002",
                "CA1", "Quz", 55, 100, 10);

        createAssessments("Nike", "3452678", "Business Stats in Python", "ITDS 002",
                "CA2", "Common Test", 65, 100, 40);
        createAssessments("Nike", "3452678", "Business Stats in Python", "ITDS 002",
                "CA3", "Individual Assignment", 46, 100, 40);

        createAssessments("Nike", "3452678", "Business Stats in Python", "ITDS 002",
                "CA4", "Quiz", 51, 100, 10);

        ArrayList<String> studentNames = new ArrayList<>();

        for (Student stu : studentArray.getStudentList()) {
            studentNames.add(stu.getStudentName());
        }

        studentBox.getItems().addAll(studentNames);
    }

    public void generateSummary() {
        double totalWeightMarks = 0;
        if (studentBox.getValue() != null) {
            for (int i = 0; i < studentArray.getStudentList().size(); i++) {
                if (Objects.equals(studentBox.getValue(), studentArray.getStudentList().get(i).getStudentName())) {
                    studentName.setText("Student Name: " + studentArray.getStudentList().get(i).getStudentName());
                    studentID.setText("Student ID: " + studentArray.getStudentList().get(i).getStudentID());
                    for (int j = 0; j < studentArray.getStudentList().get(i).getModuleList().size(); j++) {
                        for (Assessment assessment : studentArray.getStudentList().get(i).getModuleList().get(j).getAssessmentList()) {
                            totalWeightMarks += assessment.getWeightMarks();
                        }
                    }
                    grade.setText("Grade: " + showGrade(totalWeightMarks));
                    gpa.setText("GPA: " + showGpa(totalWeightMarks));
                }
            }
        } else {
            alertMessage("error", "ERROR", "Selection requires!", "Please a select a student to generate summary of that student!");
        }
    }

    public void createModule(String studentName, String studentID, String moduleName, String moduleCode, String moduleDescription, int creditUnits) {
        for (int i = 0; i < studentArray.getStudentList().size(); i++) {
            Module tempModule;
            if (Objects.equals(studentArray.getStudentList().get(i).getStudentName(), studentName) && Objects.equals(studentArray.getStudentList().get(i).getStudentID(), studentID)) {
                tempModule = new Module(moduleName, moduleCode, moduleDescription, creditUnits);
                for (Module module :
                        tempModule.getModuleList()) {
                    tempModule.getModuleList().add(module);
                }
                ArrayList<Module> tempList = new ArrayList<>();
                tempList.add(tempModule);

                studentArray.getStudentList().get(i).setModuleList(tempList);
                studentArray.getModuleList().add(tempModule);

                break;
            }
        }
    }

    public void createAssessments(String studentName, String studentID,
                                  String moduleName, String moduleCode,
                                  String assessmentName, String assessmentDescription,
                                  double marks, double totalMarks, double weightAge) {
        Assessment tempAssessment;
        for (int i = 0; i < studentArray.getStudentList().size(); i++) {
            if (Objects.equals(studentArray.getStudentList().get(i).getStudentName(), studentName) &&
                    Objects.equals(studentArray.getStudentList().get(i).getStudentID(), studentID)) {

                for (int j = 0; j < studentArray.getStudentList().get(i).getModuleList().size(); j++) {
                    if (Objects.equals(studentArray.getStudentList().get(i).getModuleList().get(j).getModuleName(), moduleName)
                            && Objects.equals(studentArray.getStudentList().get(i).getModuleList().get(j).getModuleCode(), moduleCode)) {
                        tempAssessment = new Assessment(assessmentName, assessmentDescription, marks, totalMarks, weightAge);

                        studentArray.getStudentList().get(i).getModuleList().get(j).setAssessment(tempAssessment);
                        studentArray.getAssessmentList().add(tempAssessment);

                        break;
                    }
                }
            }
        }
    }

    public char showGrade(double marks) {

        char grade = ' ';
        if (marks >= 80) {
            grade = 'A';
        } else if (marks >= 70) {
            grade = 'B';
        } else if (marks >= 60) {
            grade = 'C';
        } else if (marks >= 50) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        return grade;
    }

    public double showGpa(double marks) {
        double gpa = 0;

        if (marks >= 90) {
            gpa = 4.0;
        } else if (marks >= 80) {
            gpa = 4.0;
        } else if (marks >= 75) {
            gpa = 3.5;
        } else if (marks >= 70) {
            gpa = 3.0;
        } else if (marks >= 65) {
            gpa = 2.5;
        } else if (marks >= 60) {
            gpa = 2.0;
        } else if (marks >= 55) {
            gpa = 1.5;
        } else if (marks >= 50) {
            gpa = 1.0;
        } else {
            gpa = 0;
        }

        return gpa;
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
}
