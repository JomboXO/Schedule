package com.company.Entity;

/**
 * Created by ������� on 13.05.2017.
 */
public class Requirements {
    private String subject;
//    private String teacher;
    private int typeSubject; // 0 - lecture; 1 - practice 2- lab
    private int requirment;  // 1 - lecture class; 2 computer class; 3 practice class; 4 - проектор; 13 - no requirment

    public Requirements(String subject, /*String teacher,*/ int typeSubject, int requirment) {
        this.subject = subject;
//        this.teacher = teacher;
        this.typeSubject = typeSubject;
        this.requirment = requirment;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

//    public String getTeacher() {
//        return teacher;
//    }
//
//    public void setTeacher(String teacher) {
//        this.teacher = teacher;
//    }

    public int getTypeSubject() {
        return typeSubject;
    }

    public void setTypeSubject(int typeSubject) {
        this.typeSubject = typeSubject;
    }

    public int getRequirment() {
        return requirment;
    }

    public void setRequirment(int requirment) {
        this.requirment = requirment;
    }
}
