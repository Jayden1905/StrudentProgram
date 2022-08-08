package com.example.demo;


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


public class modulePage implements Initializable {
    public Student studentArray = new Student();
    public Module selectedModule;
    @FXML
    public ComboBox<String> studentNameBox;
    @FXML
    public ComboBox<String> studentIDBox;
    @FXML
    public TableView<Student> moduleTable;
    @FXML
    public TableColumn<Module, String> tableModuleName, tableModuleCode, tableModuleDescription;

    @FXML
    public TableColumn<Module, Integer> tableCreditUnits;

    public ObservableList<Module> moduleData = FXCollections.observableArrayList(studentArray.getModuleList());

    @FXML
    public TextField moduleName, moduleCode, creditUnits;
    @FXML
    public TextArea moduleDescription;

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

        createModule("Jayden", "123456", "Programming Fundamental", "ITDS 004", "IT", 50);
        createModule("Alice", "3245982", "Problem Solving", "ITDS 001", "Sovling problems with python", 75);
        createModule("Jordan", "1945739", "Communication Networking", "ITDS 003", "Networking", 75);
        createModule("Nike", "3452678", "Business Stats in Python", "ITDS 002", "Maths", 75);

        tableModuleName.setCellValueFactory(new PropertyValueFactory<>("moduleName"));
        tableModuleCode.setCellValueFactory(new PropertyValueFactory<>("moduleCode"));
        tableModuleDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableCreditUnits.setCellValueFactory(new PropertyValueFactory<>("creditUnits"));

        for (int i = 0; i < studentArray.getStudentList().size(); i++) {
            for (int j = 0; j < studentArray.getStudentList().get(i).getModuleList().size(); j++) {
                moduleTable.getItems().add(studentArray.getStudentList().get(i).getModuleList().get(j));
            }
        }

        moduleTable.refresh();
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

    public void onClickAddModule() {

        if (studentNameBox.getValue() == null && studentIDBox.getValue() == null) {
            alertMessage("error", "ERROR", "Incorrect Student Name and ID",
                    "Please add all necessary information to add new module.");
            return;
        }

        if (Objects.equals(moduleName.getText(), "") || Objects.equals(moduleCode.getText(), "") ||
                Objects.equals(moduleDescription.getText(), "") || Objects.equals(creditUnits.getText(), "")) {
            alertMessage("error", "ERROR", "Requires Input",
                    "Please add all necessary information to add new module.");
            return;
        }

        try {
            for (int i = 0; i < studentArray.getStudentList().size(); i++) {
                Module tempModule;
                if (Objects.equals(studentArray.getStudentList().get(i).getStudentName(), studentNameBox.getValue()) && Objects.equals(studentArray.getStudentList().get(i).getStudentID(), studentIDBox.getValue())) {
                    tempModule = new Module(moduleName.getText(), moduleCode.getText(), moduleDescription.getText(), Integer.parseInt(creditUnits.getText()));

                    for (Module module :
                            tempModule.getModuleList()) {
                        tempModule.getModuleList().add(module);
                    }

                    ArrayList<Module> tempList = new ArrayList<>();
                    tempList.add(tempModule);

                    for (Module module : tempList) {
                        moduleTable.getItems().add(module);
                    }

                    studentArray.getStudentList().get(i).setModule(tempModule);
                    studentArray.getModuleList().add(tempModule);

                    moduleTable.refresh();
                }
            }

        } catch (NumberFormatException error) {
            alertMessage("error", "ERROR", "Wrong input!",
                    "You can only enter numbers for Credit Units");
            return;
        }
        refreshForm();
    }


    public void editData(Module module) {
        selectedModule = module;
        moduleName.setText(selectedModule.getModuleName());
        moduleCode.setText(String.valueOf(selectedModule.getModuleCode()));
        moduleDescription.setText(selectedModule.getDescription());
        creditUnits.setText(String.valueOf(selectedModule.getCreditUnits()));
        studentNameBox.setValue(selectedModule.getStudentName());
        studentIDBox.setValue(selectedModule.getStudentID());
    }

    public void editModule() {
        if (moduleTable.getSelectionModel().getSelectedItem() != null) {
            editData((Module) moduleTable.getSelectionModel().getSelectedItem());
        } else {
            alertMessage("error", "ERROR", "Selection Requires",
                    "You need to select data on the table if you want to edit them.");
        }
    }

    public Module updateData(Module module) {
        selectedModule = module;

        selectedModule.setModuleName(moduleName.getText());
        selectedModule.setModuleCode(moduleCode.getText());
        selectedModule.setDescription(moduleDescription.getText());
        selectedModule.setCreditUnits(Integer.parseInt(creditUnits.getText()));
        selectedModule.setStudentName(studentNameBox.getValue());
        selectedModule.setStudentID(studentIDBox.getValue());

        return module;
    }

    public void updateModule() {
        if (moduleTable.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("UPDATING");
            alert.setHeaderText("Updating Module Data");
            alert.setContentText("Do you want to update the data?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                moduleTable.getItems().add(moduleTable.getSelectionModel().getSelectedIndex(),
                        updateData((Module) moduleTable.getSelectionModel().getSelectedItem()));
                moduleTable.getItems().remove(moduleTable.getSelectionModel().getSelectedIndex() - 1);
                moduleTable.refresh();

                studentArray.getStudentList().add(updateData((Module) moduleTable.getSelectionModel().getSelectedItem()));
                studentArray.getStudentList().remove(moduleTable.getSelectionModel().getSelectedIndex());

                refreshForm();
            }
        } else {
            System.out.println("ERROR");
            alertMessage("error", "ERROR", "Require Data",
                    "You need to add or change data in order to update.");
        }
    }

    public void deleteModule() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ERROR");
        alert.setHeaderText("Deleting!");
        alert.setContentText("Do you want to delete it?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            studentArray.getStudentList().remove(moduleTable.getSelectionModel().getSelectedItem());

            moduleTable.getItems().remove(moduleTable.getSelectionModel().getSelectedIndex());
            moduleTable.refresh();

            refreshForm();
        }
    }


    public void refreshForm() {
        moduleName.setText("");
        moduleCode.setText("");
        moduleDescription.setText("");
        creditUnits.setText("");
        studentNameBox.getSelectionModel().clearSelection();
        studentIDBox.getSelectionModel().clearSelection();
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
