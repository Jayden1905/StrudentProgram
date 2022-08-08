package com.example.demo.Utilities;

import java.util.ArrayList;

public class Module extends Student {
    public ArrayList<Module> moduleList = new ArrayList<>();
    private ArrayList<Assessment> assessmentList = new ArrayList<>();
    private String name;
    private String moduleCode;
    private String description;
    private int creditUnits;
    private Assessment assessment;

    public Module(String name, String moduleCode, String description, int creditUnits) {
        this.setModuleName(name);
        this.setModuleCode(moduleCode);
        this.setDescription(description);
        this.setCreditUnits(creditUnits);
    }


    public Module(String name, String moduleCode, String description, int creditUnits, ArrayList<Assessment> assessmentList) {
        this.setModuleName(name);
        this.setModuleCode(moduleCode);
        this.setDescription(description);
        this.setCreditUnits(creditUnits);
        this.setAssessmentList(assessmentList);
    }

    public Module() {

    }


    public ArrayList<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(ArrayList<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public String getModuleName() {
        return this.name;
    }

    public void setModuleName(String name) {
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

    public ArrayList<Assessment> getAssessmentList() {
        return this.assessmentList;
    }

    public void setAssessmentList(ArrayList<Assessment> assessmentList) {
        this.assessmentList = assessmentList;
    }

    public Assessment getAssessment() {
        return this.assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.getAssessmentList().add(assessment);
    }

    @Override
    public String toString() {
        return this.getModuleName() +
                " " + this.getModuleCode() +
                " " + this.getDescription() +
                " " + this.getCreditUnits() + "\n" +
                " " + this.getAssessmentList();
    }

    public void getGradePoint() {
    }
}
