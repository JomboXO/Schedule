package com.company.Entity;

/**
 * Created by ������� on 14.05.2017.
 */
public class Classroom {
    private int numberClassroom;
    private int typeClassroom; // 1 - lecture; 2 - computer class; 3 - practice class
    private int requirment; //4 - ���� ��������

    public Classroom(int numberClassroom, int typeClassroom) {
        this.numberClassroom = numberClassroom;
        this.typeClassroom = typeClassroom;
    }

    /*������������� ���� ����� ����, ����� ����� �� ���� ��������� ���������� ����������*/
    public Classroom(int typeClassroom) {
        this.typeClassroom = typeClassroom;
    }

    public Classroom(int requirment, int typeClassroom, int numberClassroom) {
        this.requirment = requirment;
        this.typeClassroom = typeClassroom;
        this.numberClassroom = numberClassroom;
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

    public int getRequirment() {
        return requirment;
    }

    public void setRequirment(int requirment) {
        this.requirment = requirment;
    }

    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        /* obj ��������� �� null */

        if (obj == null)
            return false;
         /* �������������, ��� ������ ����� ��� �� ����� ��� */

        if (!(getClass() == obj.getClass()))
            return false;
        else {
            Classroom tmp = (Classroom) obj;
            if (tmp.numberClassroom == this.numberClassroom)
                return true;
            else
                return false;
        }
    }
}
