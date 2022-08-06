package com.example.demo.Utilities;

//import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Student {
    public ArrayList<Student> studentList = new ArrayList<Student>();
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

    public Student() {
        this.getStudentList().add(new Student("Jayden", 19, "123456", "Male", "Diploma In Information Technology"));
        this.getStudentList().add(new Student("Alice", 22, "3245982", "Female", "Diploma In Business Management"));
        this.getStudentList().add(new Student("Jordan", 20, "1945739", "Male", "Diploma In Robotic Engineering"));
        this.getStudentList().add(new Student("Nike", 23, "3452678", "Female", "Diploma In Tourism"));
    }

    public Student(String name, int age, String studentID, String gender, String program, ArrayList<Module> moduleList) {
        this.setStudentName(name);
        this.setStudentAge(age);
        this.setStudentID(studentID);
        this.setStudentGender(gender);
        this.setStudentProgram(program);
        this.setModules(moduleList);
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
