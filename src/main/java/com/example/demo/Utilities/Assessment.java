package com.example.demo.Utilities;

import java.util.ArrayList;

public class Assessment extends Module {
    private String name;
    private String description;
    private double marks;
    private double totalMarks;
    private double weightAge;
    private ArrayList<Assessment> assessmentList = new ArrayList<>();

    public Assessment(String name, String description, double marks, double totalMarks, double weightAge) {
        this();
        this.name = name;
        this.description = description;
        this.marks = marks;
        this.totalMarks = totalMarks;
        this.weightAge = weightAge;
    }

    public Assessment() {
    }

    public ArrayList<Assessment> getAssessmentList() {
        return assessmentList;
    }

    public void setAssessmentList(ArrayList<Assessment> assessmentList) {
        this.assessmentList = assessmentList;
    }

    public double getWeightMarks() {
        return this.marks / this.totalMarks * this.weightAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(double totalMarks) {
        this.totalMarks = totalMarks;
    }

    public double getWeightAge() {
        return weightAge;
    }

    public void setWeightAge(double weightAge) {
        this.weightAge = weightAge;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", marks=" + marks +
                ", totalMarks=" + totalMarks +
                ", weightAge=" + weightAge;
    }
}
