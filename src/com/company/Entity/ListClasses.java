package com.company.Entity;

/**
 * Created by Татьяна on 14.05.2017.
 */
public class ListClasses {
    private Element element;
    private float S;

    public ListClasses(Element element, float s) {
        this.element = element;
        S = s;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public float getS() {
        return S;
    }

    public void setS(float s) {
        S = s;
    }

    @Override
    public String toString(){
        return element.getTeacher() + "   " + element.getSubject() + "   " + element.getFlow().getNumberFlow() + "   " + element.getClassroom().getTypeClassroom() + "   "+ element.getTypeSubject() +"   "+ S;
    }
}
