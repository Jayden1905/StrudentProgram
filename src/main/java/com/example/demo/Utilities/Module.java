package com.example.demo.Utilities;

import java.util.ArrayList;

public class Module extends Student {
    //    public String studentName = getStudentName();
//    public String studentID = getStudentID();
    public ArrayList<Module> moduleList = new ArrayList<>();
    private String name;
    private String moduleCode;
    private String description;
    private int creditUnits;
    private ArrayList<Assessment> assessments = new ArrayList<>();

    public Module(String name, String moduleCode, String description, int creditUnits) {
        this.setName(name);
        this.setModuleCode(moduleCode);
        this.setDescription(description);
        this.setCreditUnits(creditUnits);
    }

    public Module(Module module) {
    }

    public Module(String name, String moduleCode, String description, int creditUnits, ArrayList<Assessment> assessments) {
        this.setName(name);
        this.setModuleCode(moduleCode);
        this.setDescription(description);
        this.setCreditUnits(creditUnits);
        this.setAssessments(assessments);
    }


    public ArrayList<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(ArrayList<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModuleCode() {
        return this.moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreditUnits() {
        return this.creditUnits;
    }

    public void setCreditUnits(int creditUnits) {
        this.creditUnits = creditUnits;
    }

    public ArrayList<Assessment> getAssessments() {
        return this.assessments;
    }

    public void setAssessments(ArrayList<Assessment> assessments) {
        this.assessments = assessments;
    }

    @Override
    public String toString() {
        return this.getName() +
                " " + this.getModuleCode() +
                " " + this.getDescription() +
                " " + this.getCreditUnits() +
                " " + this.getAssessments();
    }

}
