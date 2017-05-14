package com.company.Entity;

/**
 * Created by ������� on 14.05.2017.
 */
public class Timeslot {
    private int dayOfWeek; //день недели
    private int numberClass; //номер пары

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getNumberClass() {
        return numberClass;
    }

    public void setNumberClass(int numberClass) {
        this.numberClass = numberClass;
    }

    public Timeslot(int dayOfWeek, int numberClass) {
        this.dayOfWeek = dayOfWeek;
        this.numberClass = numberClass;
    }
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        /* obj ссылается на null */

        if(obj == null)
            return false;
         /* Удостоверимся, что ссылки имеют тот же самый тип */

        if(!(getClass() == obj.getClass()))
            return false;
        else
        {
            Timeslot tmp = (Timeslot)obj;
            if(tmp.dayOfWeek == this.dayOfWeek && tmp.numberClass == this.numberClass)
                return true;
            else
                return false;
        }
    }
}
