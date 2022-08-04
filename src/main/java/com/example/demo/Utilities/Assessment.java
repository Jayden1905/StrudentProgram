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
}
