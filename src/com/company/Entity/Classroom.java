package com.company.Entity;

/**
 * Created by ������� on 14.05.2017.
 */
public class Classroom {
    private int numberClassroom;
    private int numberHull; // 0 - главный корпус, кронверский, 1 - ломоносова, 2 - гривцова, 3 - биржевая
    private int typeClassroom; // 0 - lecture; 2 - computer class; 3 - practice class; 24 - компьютерный класс с проектором
  // 40 - лекционный с проектором

    /*устанавливаем сюда номер типа, чтобы потом по типу аудитории определить подходящюю*/
    public Classroom(int typeClassroom,int numberHull) {
        this.typeClassroom = typeClassroom;
        this.numberHull = numberHull;
    }

    public Classroom(int typeClassroom,int numberClassroom, int numberHull) {
        this.typeClassroom = typeClassroom;
        this.numberClassroom = numberClassroom;
        this.numberHull = numberHull;
    }
    public int getNumberHull() {
        return numberHull;
    }

    public void setNumberHull(int numberHull) {
        this.numberHull = numberHull;
    }

    public int getNumberClassroom() {
        return numberClassroom;
    }

    public void setNumberClassroom(int numberClassroom) {
        this.numberClassroom = numberClassroom;
    }

    public int getTypeClassroom() {
        return typeClassroom;
    }

    public void setTypeClassroom(int typeClassroom) {
        this.typeClassroom = typeClassroom;
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
            Classroom tmp = (Classroom) obj;
            if (tmp.numberClassroom == this.numberClassroom && tmp.numberHull == this.numberHull)
                return true;
            else
                return false;
        }
    }
}
