package com.company.Entity;

/**
 * Created by ������� on 14.05.2017.
 */
public class TempElem {
    private double R;
    private Element element;
    private int i;
    private int n;

    public TempElem(double r, Element element, int i, int n) {
        R = r;
        this.element = element;
        this.i = i;
        this.n = n;
    }

    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;

     /* obj ссылается на null */

        if(obj == null)
            return false;

     /* Удостоверимся, что ссылки имеют тот же самый тип */
//
//        if(!(getClass() == obj.getClass()))
//            return false;
//        else
//        {
//            Element tmp = (Element)obj;
//            if(tmp.classroom.numberClassroom == this.cla)
//                return true;
//            else
//                return false;
//        }
        return false;
    }

    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
