package com.zwy.system;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class Student implements Serializable {//实现serializable接口，使对象序列化
    private String name;
    private String sex;
    private String classInfo;
    private String studentId;
    private LinkedHashMap<String,SemesterScore> semesters;

    public String toString() {
        return "学生姓名：" + getName() +
                " 学生性别：" + getSex() +
                " 学生学号：" + getStudentId() +
                " 学生班级信息：" + getClassInfo() +
                " 学生每学期成绩：" + getSemesters();
    }
    public void addSemester(String semesterName, SemesterScore semester) {
        if (semesters == null) {
            semesters = new LinkedHashMap<>();
        }
        semesters.put(semesterName, semester);
    }
    public Student() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getClassInfo() {
        return classInfo;
    }
    public void setClassInfo(String classInfo) {
        this.classInfo = classInfo;
    }
    public LinkedHashMap<String, SemesterScore> getSemesters() {
        return semesters;
    }
    public void setSemesters(LinkedHashMap<String, SemesterScore> semesters) {
        this.semesters = semesters;
    }
}
