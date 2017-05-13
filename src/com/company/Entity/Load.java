package com.company.Entity;

/**
 * Created by ������� on 13.05.2017.
 */
public class Load {
    private String subject;
    private int flow; // ����� ������
    private double load; //���������� ������� � ������
    private int typeSubject; // 0 - ������ ; 1 - ��������, 2 - lab
    private String teacher;
   // private Requirements requirment; //��������, ���������� ���������, ������������ �����, �����������...


    public Load(String subject, int flow, double load, int typeSubject, String teacher) {
        this.subject = subject;
        this.flow = flow;
        this.load = load;
        this.typeSubject = typeSubject;
        this.teacher = teacher;
        //this.requirment = requirment;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public double getLoad() {
        return load;
    }

    public void setLoad(double load) {
        this.load = load;
    }

    public int getTypeSubject() {
        return typeSubject;
    }

    public void setTypeSubject(int typeSubject) {
        this.typeSubject = typeSubject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Load{" +
                "subject='" + subject + '\'' +
                ", flow=" + flow +
                ", load=" + load +
                ", typeSubject=" + typeSubject +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}
