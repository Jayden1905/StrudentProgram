package com.example.demo;

import com.example.demo.Utilities.Assessment;
import com.example.demo.Utilities.Module;
import com.example.demo.Utilities.Student;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Summary implements Initializable {
    public Student studentArray = new Student();

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
                "CA4", "Quiz", 90, 100, 20);

        System.out.println(studentArray.getStudentList());
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
