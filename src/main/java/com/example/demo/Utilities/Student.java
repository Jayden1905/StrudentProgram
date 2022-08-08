package com.example.demo.Utilities;

//import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Student {
    public ArrayList<Student> studentList = new ArrayList<Student>();
    private ArrayList<Module> moduleList = new ArrayList<>();
    private ArrayList<Assessment> assessmentList = new ArrayList<>();
    private String name;
    private int age;
    private String studentID;
    private String gender;
    private String program;
    private char grade;
    private double gpa;

    public Student(String name, int age, String studentID, String gender, String program) {
        this.setStudentName(name);
        this.setStudentAge(age);
        this.setStudentID(studentID);
        this.setStudentGender(gender);
        this.setStudentProgram(program);
    }

    public Student() {
        this.getStudentList().add(new Student("Jayden", 19, "123456", "Male", "Diploma In Information Technology", moduleList));
        this.getStudentList().add(new Student("Alice", 22, "3245982", "Female", "Diploma In Business Management", moduleList));
        this.getStudentList().add(new Student("Jordan", 20, "1945739", "Male", "Diploma In Robotic Engineering", moduleList));
        this.getStudentList().add(new Student("Nike", 23, "3452678", "Female", "Diploma In Tourism", moduleList));
    }

    public Student(String name, int age, String studentID, String gender, String program, ArrayList<Module> moduleList) {
        this.setStudentName(name);
        this.setStudentAge(age);
        this.setStudentID(studentID);
        this.setStudentGender(gender);
        this.setStudentProgram(program);
        this.setModuleList(moduleList);
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public ArrayList<Assessment> getAssessmentList() {
        return this.assessmentList;
    }

    public void setAssessmentList(ArrayList<Assessment> assessmentList) {
        this.assessmentList = assessmentList;
    }

    public void setAssessment(Assessment assessment) {
        this.assessmentList.add(assessment);
    }

    public ArrayList<Module> getModuleList() {
        return this.moduleList;
    }

    public void setModuleList(ArrayList<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public void setModule(Module module) {
        this.moduleList.add(module);
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
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

    @Override
    public String toString() {
        return getStudentName() +
                " " + getStudentAge() +
                " " + getStudentID() +
                " " + getStudentGender() +
                " " + getStudentProgram() + "\n" +
                " " + getModuleList();
    }
}
