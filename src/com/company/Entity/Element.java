package com.company.Entity;

/**
 * Created by Татьяна on 14.05.2017.
 */
public class Element {
    private String teacher;
    private Classroom classroom;
    private String subject;
    private Flow flow;
    private int typeSubject;

    public Element(String teacher, Classroom classroom, String subject, Flow flow, int typeSubject) {
        this.teacher = teacher;
        this.classroom = classroom;
        this.subject = subject;
        this.flow = flow;
        this.typeSubject = typeSubject;
    }


    public int getTypeSubject() {
        return typeSubject;
    }

    public void setTypeSubject(int typeSubject) {
        this.typeSubject = typeSubject;
    }


    public Flow getGroup() {
        return flow;
    }

    public void setGroup(Flow flow) {
        this.flow = flow;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return teacher + "   " + subject + "   " + flow.getNumberFlow() + "   " + classroom.getNumberClassroom() + "   " + typeSubject;
    }

    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        /* obj ссылается на null */

        if (obj == null)
            return false;
         /* Удостоверимся, что ссылки имеют тот же самый тип */

        if (!(getClass() == obj.getClass()))
            return false;
        else {
            Element tmp = (Element) obj;
            if (tmp.typeSubject == this.typeSubject && tmp.teacher.equals(this.teacher)
                    && tmp.subject.equals(this.subject) && tmp.flow.equals(this.flow))
                return true;
            else
                return false;
        }
    }

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }
}
