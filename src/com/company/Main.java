package com.company;

import com.company.Entity.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Load> groups;
//    private static List<Requirements> requirementses;
    private static List<Flow> flows;
    private static List<Requirements> requirementses;
    private static List<Classroom> classroom;
    static List<ListClasses> listClasses;


    public static void main(String[] args) {
	    groups = new ArrayList<>();
        requirementses = new ArrayList<Requirements>();
        flows = new ArrayList<>();
        listClasses = new ArrayList<ListClasses>();

        XMLParser.goLoads(groups, flows);

        requirementses.add(new Requirements("�������� � �������������", 0, 13));
        requirementses.add(new Requirements("���������� � �������������� ������ MathCad", 0, 4));
        requirementses.add(new Requirements("���������� ����������", 0, 13));
        requirementses.add(new Requirements("�������� � �������������", 1, 13));
        requirementses.add(new Requirements("���������� � �������������� ������ MathCad", 1, 2));
        requirementses.add(new Requirements("���������� ����������", 1, 13));

        requirementses.add(new Requirements("�����������", 0, 4));
        requirementses.add(new Requirements("����������������", 0, 4));
        requirementses.add(new Requirements("����� � ��������� ����������������", 0, 4));
        requirementses.add(new Requirements("�����������", 1, 2));
        requirementses.add(new Requirements("����������������", 1, 2));
        requirementses.add(new Requirements("����� � ��������� ����������������", 1, 2));

        classroom.add(new Classroom(302, 1, 4));
        classroom.add(new Classroom(359, 1, 4));
        classroom.add(new Classroom(285, 1, 4));
        classroom.add(new Classroom(403, 1, 4));
        classroom.add(new Classroom(466, 1, 4));

        classroom.add(new Classroom(303, 2, 4));
        classroom.add(new Classroom(304, 2));
        classroom.add(new Classroom(305, 2));
        classroom.add(new Classroom(306, 2));

//        for (Load l : groups){
//            System.out.println(l.toString());
//        }
//        for (Flow l : flows){
//            String str = null;
//            for (Group h : l.getGroupList()){
//                str = str + h.toString() + " ";
//            }
//            System.out.println(l.getNumberFlow() + " " + str);
//        }
    }
}
