package com.company;

import com.company.Entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static List<Load> groups;
//    private static List<Requirements> requirementses;
    private static List<Flow> flows;
    private static List<Requirements> requirementses;
    private static List<Classroom> classroom;
    static List<ListClasses> listClasses;
    static Map<Timeslot, Element> elem = new HashMap<Timeslot, Element>();
    static List<TempElem> tempElems;


    public static void main(String[] args) {
	    groups = new ArrayList<>();
        requirementses = new ArrayList<Requirements>();
        flows = new ArrayList<>();
        listClasses = new ArrayList<ListClasses>();
        classroom = new ArrayList<Classroom>();

        XMLParser.goLoads(groups, flows);

        requirementses.add(new Requirements("Введение в специальность", 0, 13));
        requirementses.add(new Requirements("Вычисление с использованием пакета MathCad", 0, 4));
        requirementses.add(new Requirements("Дискретная математика", 0, 13));
        requirementses.add(new Requirements("Введение в специальность", 1, 13));
        requirementses.add(new Requirements("Вычисление с использованием пакета MathCad", 2, 2));
        requirementses.add(new Requirements("Дискретная математика", 1, 13));

        requirementses.add(new Requirements("Информатика", 0, 4));
        requirementses.add(new Requirements("Программирование", 0, 4));
        requirementses.add(new Requirements("Языки и парадигмы программирования", 0, 4));
        requirementses.add(new Requirements("Информатика", 1, 2));
        requirementses.add(new Requirements("Программирование", 2, 2));
        requirementses.add(new Requirements("Языки и парадигмы программирования", 2, 2));

        classroom.add(new Classroom(302, 0, 4));
        classroom.add(new Classroom(359, 0, 4));
        classroom.add(new Classroom(285, 0, 4));
        classroom.add(new Classroom(403, 0, 4));
        classroom.add(new Classroom(466, 0, 4));

        classroom.add(new Classroom(303, 2, 4));
        classroom.add(new Classroom(304, 2, 0));
        classroom.add(new Classroom(305, 2, 0));
        classroom.add(new Classroom(306, 2, 0));

        SetListClasses.getListClasses(requirementses, groups, classroom, listClasses, flows);
        sortByS(listClasses);
        doSchedule();
        printElements();

    }
    private static void doSchedule() {

    }
    private static void printElements(){
        for (Map.Entry entry : elem.entrySet()) {
            Timeslot t = (Timeslot) entry.getKey();
            System.out.println(t.getDayOfWeek()+"  "+ t.getNumberClass()+"  " + " Value: " + entry.getValue());
        }
    }
    private static List<Element> getElem(Timeslot key) {
        List<Element> e = new ArrayList<>();
        for (Map.Entry entry : elem.entrySet()) {
            if (entry.getKey().equals(key)) e.add((Element)entry.getValue());
        }
        return e;
    }

    private static void sortByS(List<ListClasses> listClasses) {
        for (int i = listClasses.size() - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (listClasses.get(j).getS() > listClasses.get(j + 1).getS()) {
                    ListClasses t = listClasses.get(j);
                    listClasses.set(j, listClasses.get(j + 1));
                    listClasses.set(j + 1, t);
                }
            }
        }
    }
}
