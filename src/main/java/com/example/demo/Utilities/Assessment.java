package com.example.demo.Utilities;

public class Assessment {
    private String name;
    private String description;
    private Module module;
    private double marks;
    private double totalMarks;
    private double weightAge;

    public double getWeightMarks() {
        return marks / totalMarks * weightAge;
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

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public double getWeightAge() {
        return weightAge;
    }

    public void setWeightAge(double weightAge) {
        this.weightAge = weightAge;
    }
}
