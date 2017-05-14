package com.company;

import com.company.Entity.*;
import com.company.Entity.Element;
import org.w3c.dom.*;

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
    final static int p = 8, l = 6;

    public static void main(String[] args) {
        groups = new ArrayList<>();
        requirementses = new ArrayList<Requirements>();
        flows = new ArrayList<>();
        listClasses = new ArrayList<ListClasses>();
        classroom = new ArrayList<Classroom>();
        tempElems = new ArrayList<TempElem>();

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

        classroom.add(new Classroom(40, 302));
        classroom.add(new Classroom(40, 359));
        classroom.add(new Classroom(40, 285));
        classroom.add(new Classroom(40, 403));
        classroom.add(new Classroom(40, 466));

        classroom.add(new Classroom(24, 303));
        classroom.add(new Classroom(2, 304));
        classroom.add(new Classroom(2, 305));
        classroom.add(new Classroom(2, 306));

        SetListClasses.getListClasses(requirementses, groups, classroom, listClasses, flows);
        sortByS(listClasses);
        doSchedule();
       // printElements();

    }

    private static void doSchedule() {
        double k = 0, t = 0, R = 0;
        for (ListClasses list : listClasses) {
          //  System.out.println(list.getElement().toString() + "--------------------------------------------------------------------------------------");
            boolean equals = false;
            tempElems.clear();
            List<Element> element = new ArrayList<>();
            Timeslot key;
            for (int i = 0; i < p; i++) {
                for (int n = 0; n < l; n++) {
                    element.clear();
                    key = new Timeslot(n, i);
                   // System.out.println("Process for key " + key.toString());
                    element.addAll(getElem(key));
                   // System.out.println("Found " + element.size() + " elements");
                    /*если тип предмета практика или лаба, а нагрузка 2 раза в неделю - ставим подряд*/
                    if (!element.isEmpty()) {
                        for (Element element1 : element) {
                            if (thisIsNotThree(element1, listClasses)) {
                                if (checkEquals(element1, list.getElement())) {
                                   // System.out.println("Same class found");
                                    equals = true;
                                    tempElems.clear();
                                    tempElems.add(new TempElem(k, new Element(element1.getTeacher(), element1.getClassroom(), element1.getSubject(), element1.getGroup(), element1.getTypeSubject()), ++i, n));
                                    break;
                                }
                            }
                        }
                    }
                    if (equals) {
                        break;
                    }
                    for (int h = 0; h < classroom.size(); h++) {
                        if (!element.isEmpty()) {
                            for (Element element1 : element) {
                                k = checkOverlap(element1, list.getElement(), classroom.get(h));
                                if (k == 0 || k == 1) {
                                   // System.out.println("n:" + n + ", i:" + i + ", k:" + k + ", class1:" + element1.getClassroom().getNumberClassroom() + ", h:" + classroom.get(h).getNumberClassroom());
                                    break;}
                            }
                        } else k = 10;
                        if (k == 0) break;
                        if (k == 1) continue;

                        if (checkAud(null, classroom.get(h), list.getElement()) == 0) continue;


//                        if (!element.isEmpty()) {
//                            for (Element element1 : element) {
//                                t = checkAud(element1, classroom.get(h), list.getElement());
//                                if (t == 0) {
//                                    while (t == 0 && h < classroom.size()) {
//                                        t = checkAud(element1, classroom.get(h), list.getElement());
//                                        if (h == classroom.size() - 1 || t != 0) {
//                                                break;
//                                        } else {
//                                            h++;
//                                        }
//                                    }
//                                }
//                                if (t == 0) break;
//                            }
//                        } else {
//                            t = checkAud(null, classroom.get(h), list.getElement());
//                            if (t == 0) {
//                                while (t == 0 && h < classroom.size()) {
//                                    t = checkAud(null, classroom.get(h), list.getElement());
//                                    if (h == classroom.size() - 1 || t != 0) {
//                                        break;
//                                    } else {
//                                        h++;
//                                    }
//                                }
//                            }
//                        }
                        //if (t == 0) break;
                        k = k + t;
                        t = checkWindowForStudens(n, i, list.getElement());
                        k = k + t;
                        t = checkWindowForTeachers(n, i, list.getElement());
                        k = k + t;
                        t = countClassesForFlow(n, list.getElement().getFlow());
                        k = k + t;
                        tempElems.add(new TempElem(k, new Element(list.getElement().getTeacher(), classroom.get(h), list.getElement().getSubject(), list.getElement().getGroup(), list.getElement().getTypeSubject()), i, n));
                    }
                }
                if (equals) {
                    break;

                }

            }
            if (equals) {
                elem.put(new Timeslot(tempElems.get(0).getN(), tempElems.get(0).getI()), tempElems.get(0).getElement());
                System.out.println(tempElems.get(0).getN() + " " + tempElems.get(0).getI() + " " + tempElems.get(0).getElement().toString());
            } else {
                //здесь находим самый большой R и записываем в element занятие
                sortByR(tempElems);
                elem.put(new Timeslot(tempElems.get(0).getN(), tempElems.get(0).getI()), tempElems.get(0).getElement());
                System.out.println(tempElems.get(0).getN() + " " + tempElems.get(0).getI() + " " + tempElems.get(0).getElement().toString());

            }
        }
    }
    /*Проверка "перекрытия": если в это же время и этот же день уже заняты
            преподаватель или группа, тогда возвращаем ноль и прекращаем рассматривать эту аудиторию*/
// добавить возможность не рассматривать остальные аудитории, если вернет 0
    private static double checkOverlap(Element element, Element elementList, Classroom classroom) {
        int w = 10;
        if (element != null) {
            if (element.getTeacher().equals(elementList.getTeacher())) {
                return 0;
            } else if (checkGroup(element.getFlow(), elementList.getFlow())) { //element.getGroup().equals(elementList.getGroup())) {
                return 0;
            } else if (element.getClassroom().getNumberClassroom() == classroom.getNumberClassroom()) {
                return 1;
            } else return 1 * w;
        }
        return 1 * w;
    }
    private static boolean thisIsNotThree(Element element1, List<ListClasses> listClasses) {
        int i = 0;
        for (ListClasses listClasses1 : listClasses) {
            if (listClasses1.getElement().equals(element1)) i++;
        }
        if (i == 3) return false;
        else return true;
    }

      private static double checkAud(Element element, Classroom classroom, Element listElement) {
        int w = 10;
        double ret = 0;
        if (element!=null && element.getClassroom().getNumberClassroom() == classroom.getNumberClassroom()) ret = 0;
        else {
            if (listElement.getClassroom().getTypeClassroom() == 0) {
                if (classroom.getTypeClassroom() == 40 || classroom.getTypeClassroom() == 0) {
                    ret = 1 * w;
                }
            } else if (listElement.getClassroom().getTypeClassroom() == 2){
                if (classroom.getTypeClassroom() == 2 || classroom.getTypeClassroom() == 24) {
                    ret = 1 * w;
                }
            } else if (listElement.getClassroom().getTypeClassroom() == 40){
                if (classroom.getTypeClassroom() == 40) {
                    ret = 1 * w;
                }
            } else if (listElement.getClassroom().getTypeClassroom() == 24) {
                if (classroom.getTypeClassroom() == 24) {
                    ret = 1 * w;
                }
            }
//            if (listElement.getClassroom().getTypeClassroom() == classroom.getTypeClassroom()) { /* по типу совпадают, значит подходит.
//                                                                                (в element.classroom.typeClassroom хранится
//                                                                                 рекомендация, какого типа должна быть аудитория)*/
//                ret = 1 * w;
//
//            } else ret = 0;
        }
        return ret;
    }

    private static boolean checkEquals(Element element1, Element element) {
        if (element1.equals(element)) return true;
        else return false;
    }

    private static void printElements() {
        for (Map.Entry entry : elem.entrySet()) {
            Timeslot t = (Timeslot) entry.getKey();
            System.out.println(t.getDayOfWeek() + "  " + t.getNumberClass() + "  " + " Value: " + entry.getValue());
        }
//        for (ListClasses l : listClasses) {
//            System.out.println(l.toString());
//        }
    }

    private static List<Element> getElem(Timeslot key) {
        List<Element> e = new ArrayList<>();
        for (Map.Entry entry : elem.entrySet()) {
            if (entry.getKey().equals(key)) e.add((Element) entry.getValue());
        }
        return e;
    }



    private static double countClassesForFlow(int n, int fl) {
        int w = 4, j = 0;
        Element element = null;
        List<Group> elemFlow = new ArrayList<>();
        List<Group> elementFlow = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        Timeslot key;
        for (int i = 0; i < p; i++) {
            key = new Timeslot(n, i);
            if (elem.containsKey(key)) element = elem.get(key);
            if (element != null) {
                for (Flow flow : flows) {
                    if (flow.getNumberFlow() == fl) {
                        elemFlow.addAll(flow.getGroupList());
                        break;
                    }
                }
                if (element.getFlow() == fl) {
                    for (Flow flow : flows) {
                        if (flow.getNumberFlow() == fl) {
                            elemFlow.addAll(flow.getGroupList());
                        }
                        if (flow.getNumberFlow() == element.getFlow()) {
                            elementFlow.addAll(flow.getGroupList());
                        }
                    }
                    for (int f = 0; f < elementFlow.size(); f++) {
                        count.add(0);
                    }
                    for (int l = 0; l < elemFlow.size(); l++) {
                        for (int f = 0; f < elementFlow.size(); f++) {
                            if (elementFlow.get(f).getGroup().equals(elemFlow.get(l).getGroup())) {
                                count.set(f, count.get(f) + 1);
                            }
                        }
                    }
                }
            }
        }
        if (returnMax(count) > 3) return 0;
        else return 1 * w;
    }

    private static int returnMax(List<Integer> count) {
        int max = 0;
        if (!count.isEmpty()) {
            max = count.get(0);
            for (Integer i : count) {
                if (i > max) max = i;
            }
        }
        return max;
    }

    private static boolean checkGroup(int elementFlow, int elementListFlow) {
        List<Group> elementGroup = new ArrayList<>();
        List<Group> elementListGroup = new ArrayList<>();
        for (Flow flow : flows) {
            if (flow.getNumberFlow() == elementFlow) elementGroup.addAll(flow.getGroupList());
            if (flow.getNumberFlow() == elementListFlow) elementListGroup.addAll(flow.getGroupList());
        }
        for (Group group : elementGroup) {
            if (elementListGroup.contains(group)) return true;
        }
        return false;
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

    private static double checkWindowForTeachers(int nn, int ii, Element element) {
        int w = 2;
        double k = 0;
        Element el = null;
        Timeslot key;
        for (int i = 0; i < p; i++) {
            key = new Timeslot(nn, i);
            if (elem.containsKey(key)) el = elem.get(key);
            if (el != null) {
                if (!el.equals(element)) {
                    if (el.getTeacher().equals(element.getTeacher())) {
                        if (ii > i && ii - i > 1 || ii < i && i - ii > 1) {
                            k = 2; // окно в 2 и более пары
                        } else if (ii > i && ii - i == 1 || ii < i && i - ii == 1) {
                            k = 1; //окно в одну пару
                        } else k = 0;
                    }
                }
            }
            if (k > 1) {
                k = 0;
                break;
            }
        }
        if (k == 1) k = 0.5;
        else k = 1;
        return k * w;
    }

    private static double checkWindowForStudens(int nn, int ii, Element element) {
        int w = 2;
        double k = 0;
        Element el = null;
        List<Group> elFlow = new ArrayList<>();
        List<Group> elementFlow = new ArrayList<>();
        Timeslot key;
        for (int i = 0; i < p; i++) {
            key = new Timeslot(nn, i);
            if (elem.containsKey(key)) el = elem.get(key);
            if (el != null) {
                if (!el.equals(element)) {
                    for (Flow flow : flows) {
                        if (flow.getNumberFlow() == element.getFlow()) {
                            elFlow.addAll(flow.getGroupList());
                        }
                        if (flow.getNumberFlow() == element.getFlow()) {
                            elementFlow.addAll(flow.getGroupList());
                        }
                    }
                    //проверяем поток, если у какой-нибудь из него группы более
                    //двух пар окно - тогда возвращаем значение, как 0
                    for (int l = 0; l < elFlow.size(); l++) {
                        for (int f = 0; f < elementFlow.size(); f++) {
                            if (elementFlow.get(f).getGroup().equals(elFlow.get(l).getGroup())) {
                                if (ii > i && ii - i > 1 || ii < i && i - ii > 1) {
                                    k = 2; // окно в 2 и более пары
                                } else if (ii > i && ii - i == 1 || ii < i && i - ii == 1) {
                                    k = 1; //окно в одну пару
                                } else k = 0;
                            }
                        }
                    }
                }
            }
            if (k > 1) {
                k = 0;
                break;
            }
        }
        if (k == 1) k = 0.5;
        else k = 1;
        return k * w;
    }

    private static void sortByR(List<TempElem> tempElems) {
        for (int i = tempElems.size() - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (tempElems.get(j).getR() < tempElems.get(j + 1).getR()) {
                    TempElem t = tempElems.get(j);
                    tempElems.set(j, tempElems.get(j + 1));
                    tempElems.set(j + 1, t);
                }
            }
        }
    }
}
