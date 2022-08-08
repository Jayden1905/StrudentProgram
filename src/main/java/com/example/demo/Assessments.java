package com.example.demo;

import com.example.demo.Utilities.Assessment;
import com.example.demo.Utilities.Module;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Assessments implements Initializable {

    public Student studentArray = new Student();

    @FXML
    public ComboBox<String> studentNameBox, studentIDBox, moduleNameBox, moduleIDBox;

    @FXML
    public TableView<Assessment> assessmentTable;

    @FXML
    public TableColumn<Assessment, String> tableAssessmentName, tableAssessmentDescription;
    @FXML
    public TableColumn<Assessment, Double> tableMarks, tableTotalMarks, tableWeightAge;

    public ObservableList<Assessment> assessmentObservableArray = FXCollections.observableArrayList(studentArray.getAssessmentList());

    @FXML
    public TextField assessmentName, assessmentMarks, assessmentTotalMarks, weightAge;
    @FXML
    public TextArea assessmentDescription;

    public Assessment selectedAssessment;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> studentNames = new ArrayList<>();
        ArrayList<String> studentID = new ArrayList<>();

        ArrayList<String> moduleNames = new ArrayList<>();
        ArrayList<String> moduleID = new ArrayList<>();

        createModule("Jayden", "123456", "Programming Fundamental", "ITDS 004", "IT", 50);
        createModule("Alice", "3245982", "Problem Solving", "ITDS 001", "Sovling problems with python", 75);
        createModule("Jordan", "1945739", "Communication Networking", "ITDS 003", "Networking", 75);
        createModule("Nike", "3452678", "Business Stats in Python", "ITDS 002", "Maths", 75);

        for (int i = 0; i < studentArray.getStudentList().size(); i++) {
            studentNames.add(studentArray.getStudentList().get(i).getStudentName());
            studentID.add(studentArray.getStudentList().get(i).getStudentID());

            for (int j = 0; j < studentArray.getStudentList().get(i).getModuleList().size(); j++) {
                moduleNames.add(studentArray.getStudentList().get(i).getModuleList().get(j).getModuleName());
                moduleID.add(studentArray.getStudentList().get(i).getModuleList().get(j).getModuleCode());
            }
        }

        studentNameBox.getItems().addAll(studentNames);
        studentIDBox.getItems().addAll(studentID);

        moduleNameBox.getItems().addAll(moduleNames);
        moduleIDBox.getItems().addAll(moduleID);

        createAssessments("Jayden", "123456", "Programming Fundamental", "ITDS 004",
                "CA1", "Quz", 95, 100, 10);

        createAssessments("Jayden", "123456", "Programming Fundamental", "ITDS 004",
                "CA2", "Common Test", 85, 100, 40);

        createAssessments("Jayden", "123456", "Programming Fundamental", "ITDS 004",
                "CA3", "Individual Assignment", 100, 100, 40);

        createAssessments("Jayden", "123456", "Programming Fundamental", "ITDS 004",
                "CA4", "Quiz", 90, 100, 20);


        tableAssessmentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableAssessmentDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableMarks.setCellValueFactory(new PropertyValueFactory<>("marks"));
        tableTotalMarks.setCellValueFactory(new PropertyValueFactory<>("totalMarks"));
        tableWeightAge.setCellValueFactory(new PropertyValueFactory<>("weightAge"));

        for (Assessment assessment : studentArray.getAssessmentList()) {
            assessmentTable.getItems().add(assessment);
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

                        assessmentTable.refresh();

                        break;
                    }
                }
            }
        }
    }

    public void addAssessment() {

        if (studentNameBox.getValue() == null && studentIDBox.getValue() == null) {
            alertMessage("error", "ERROR", "Incorrect Student Name and ID",
                    "Please add all necessary information to add new module.");
            return;
        }

        if (moduleNameBox.getValue() == null && moduleIDBox.getValue() == null) {
            alertMessage("error", "ERROR", "Incorrect Module Name and ID",
                    "Please add all necessary information to add new assessment.");
            return;
        }

        try {
            for (int i = 0; i < studentArray.getStudentList().size(); i++) {
                Assessment tempAssessment;
                if (Objects.equals(studentArray.getStudentList().get(i).getStudentName(), studentNameBox.getValue()) &&
                        Objects.equals(studentArray.getStudentList().get(i).getStudentID(), studentIDBox.getValue())) {
                    for (int j = 0; j < studentArray.getStudentList().get(i).getModuleList().size(); j++) {
                        if (Objects.equals(studentArray.getStudentList().get(i).getModuleList().get(j).getModuleName(), moduleNameBox.getValue())
                                && Objects.equals(studentArray.getStudentList().get(i).getModuleList().get(j).getModuleCode(), moduleIDBox.getValue())) {

                            tempAssessment = new Assessment(assessmentName.getText(), assessmentDescription.getText(),
                                    Double.parseDouble(assessmentMarks.getText()), Double.parseDouble(assessmentTotalMarks.getText()),
                                    Double.parseDouble(weightAge.getText()));

                            ArrayList<Assessment> tempList = new ArrayList<>();
                            tempList.add(tempAssessment);

                            for (Assessment assessment : tempList) {
                                assessmentTable.getItems().add(assessment);
                            }

                            studentArray.getStudentList().get(i).getModuleList().get(j).setAssessment(tempAssessment);
                            studentArray.getAssessmentList().add(tempAssessment);

                            assessmentTable.refresh();
                        }
                    }
                }
            }
        } catch (NumberFormatException error) {
            alertMessage("error", "ERROR", "Wrong input!",
                    "You can only enter numbers for Marks, Total Marks and Weight Age");
            return;

        }
        resetForm();
    }

    public void editData(Assessment assessment) {
        selectedAssessment = assessment;
        assessmentName.setText(selectedAssessment.getName());
        assessmentMarks.setText(String.valueOf(selectedAssessment.getMarks()));
        assessmentDescription.setText(selectedAssessment.getDescription());
        assessmentTotalMarks.setText(String.valueOf(selectedAssessment.getTotalMarks()));
        weightAge.setText(String.valueOf(selectedAssessment.getWeightAge()));
        studentNameBox.setValue(selectedAssessment.getStudentName());
        studentIDBox.setValue(selectedAssessment.getStudentID());
        moduleNameBox.setValue(selectedAssessment.getModuleName());
        moduleIDBox.setValue(selectedAssessment.getModuleCode());
    }

    public void editAssessment() {
        if (assessmentTable.getSelectionModel().getSelectedItem() != null) {
            editData(assessmentTable.getSelectionModel().getSelectedItem());
        } else {
            alertMessage("error", "ERROR", "Selection Requires",
                    "You need to select data on the table if you want to edit them.");
        }
    }

    public Assessment updateData(Assessment assessment) {
        selectedAssessment = assessment;

        selectedAssessment.setName(assessmentName.getText());
        selectedAssessment.setDescription(assessmentDescription.getText());
        selectedAssessment.setMarks(Double.parseDouble(assessmentMarks.getText()));
        selectedAssessment.setTotalMarks(Double.parseDouble(assessmentTotalMarks.getText()));
        selectedAssessment.setWeightAge(Double.parseDouble(weightAge.getText()));
        selectedAssessment.setStudentName(studentNameBox.getValue());
        selectedAssessment.setStudentID(studentIDBox.getValue());
        selectedAssessment.setModuleName(moduleNameBox.getValue());
        selectedAssessment.setModuleCode(moduleIDBox.getValue());

        return assessment;
    }

    public void updateAssessment() {
        if (assessmentTable.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("UPDATING");
            alert.setHeaderText("Updating Assessment Data");
            alert.setContentText("Do you want to update the data?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                assessmentTable.getItems().add(assessmentTable.getSelectionModel().getSelectedIndex(),
                        updateData(assessmentTable.getSelectionModel().getSelectedItem()));
                assessmentTable.getItems().remove(assessmentTable.getSelectionModel().getSelectedIndex() - 1);
                assessmentTable.refresh();

                studentArray.getStudentList().add(updateData(assessmentTable.getSelectionModel().getSelectedItem()));
                studentArray.getStudentList().remove(assessmentTable.getSelectionModel().getSelectedIndex());

                resetForm();
            }
        } else {
            System.out.println("ERROR");
            alertMessage("error", "ERROR", "Require Data",
                    "You need to add or change data in order to update.");
        }
    }

    public void deleteAssessment() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ERROR");
        alert.setHeaderText("Deleting!");
        alert.setContentText("Do you want to delete it?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            studentArray.getStudentList().remove(assessmentTable.getSelectionModel().getSelectedItem());

            assessmentTable.getItems().remove(assessmentTable.getSelectionModel().getSelectedIndex());
            assessmentTable.refresh();

            resetForm();
        }
    }


    public void resetForm() {
        assessmentName.setText("");
        assessmentDescription.setText("");
        assessmentMarks.setText("");
        assessmentTotalMarks.setText("");
        weightAge.setText("");
        moduleNameBox.setValue("");
        moduleIDBox.setValue("");
        studentNameBox.setValue("");
        studentIDBox.setValue("");
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
