package com.example.demo.Utilities;

//import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Student {
    private ArrayList<Module> modules = new ArrayList<>();
    private String name;
    private int age;
    private String studentID;
    private String gender;
    private String program;

    public Student(String name, int age, String studentID, String gender, String program) {
        this.setStudentName(name);
        this.setStudentAge(age);
        this.setStudentID(studentID);
        this.setStudentGender(gender);
        this.setStudentProgram(program);
    }

    public Student(String name, int age, String studentID, String gender, String program, ArrayList<Module> moduleList) {
        this.setStudentName(name);
        this.setStudentAge(age);
        this.setStudentID(studentID);
        this.setStudentGender(gender);
        this.setStudentProgram(program);
        this.setModules(moduleList);
    }

    public String getStudentName() {
        return name;
    }

    public void setStudentName(String name) {
        this.name = name;
    }

    public int getStudentAge() {
        return age;
    }

    public void setStudentAge(int age) {
        this.age = age;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentGender() {
        return gender;
    }

    public void setStudentGender(String gender) {
        this.gender = gender;
    }

    public String getStudentProgram() {
        return program;
    }

    public void setStudentProgram(String program) {
        this.program = program;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return getStudentName() +
                " " + getStudentAge() +
                " " + getStudentID() +
                " " + getStudentGender() +
                " " + getStudentProgram() +
                " " + getModules();
    }
}
