package com.company.Entity;

/**
 * Created by ������� on 14.05.2017.
 */
public class ListClasses {
    private Element element;
    private double S;

    public ListClasses(Element element, double s) {
        this.element = element;
        S = s;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public double getS() {
        return S;
    }

    public void setS(double s) {
        S = s;
    }

    @Override
    public String toString(){
        return element.getTeacher() + "   " + element.getSubject() + "   " + element.getFlow() + "   " + element.getClassroom().getTypeClassroom() + "   "+ element.getTypeSubject() +"   "+ S;
    }
}
