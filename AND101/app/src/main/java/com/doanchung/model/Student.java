package com.doanchung.model;

public class Student {
    private String studentId;
    private String studentName;
    private float avgPoint;

    public Student(String studentId, String studentName, float avgPoint) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.avgPoint = avgPoint;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public float getAvgPoint() {
        return avgPoint;
    }

    public void setAvgPoint(float avgPoint) {
        this.avgPoint = avgPoint;
    }
}
