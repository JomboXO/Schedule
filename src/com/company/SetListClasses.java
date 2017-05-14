package com.company;

import com.company.Entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tatiana on 14.05.2017.
 */
public class SetListClasses {
    public static void getListClasses(List<Requirements> requirementses, List<Load> groups,
                                      List<Classroom> classroom, List<ListClasses> listClasses, List<Flow> flows) {
        int h = 0;
        for (int i = 0; i < groups.size(); i++) {
            String subject = groups.get(i).getSubject();
            String teacher = groups.get(i).getTeacher();
            int typeSubject = groups.get(i).getTypeSubject();
            int requirements = 0;
            int reqType = 0;
            int reqTypeClass = 0;
            int flow = groups.get(i).getFlow();

            for (int j = 0; j < requirementses.size(); j++) {
                if (requirementses.get(j).getSubject().equals(subject)) { //если в ограничениях есть такой предмет
                    if (requirementses.get(j).getTypeSubject() == typeSubject) { //если тип его совпадает
                        requirements = requirementses.get(j).getRequirment(); //тогда узнаем какие ограничения есть на аудиторию
                        reqType = requirementses.get(j).getTypeSubject(); //и какого типа она должна быть
                        break;
                    }

                }

            }
            if (requirements == 13) {
                if (reqType == 0) {
                    reqTypeClass = 0; //оставляем тип лекция
                }
                if (reqType == 1 || reqType == 2) {
                    reqTypeClass = 2; //сюда надо записать любой практический класс, зависит от кол-ва людей в группе практики
                }
            } else if (requirements == 4) {
                if (reqType == 0) {
                    reqTypeClass = 40; //лекционная аудитория с проектором
                }
                if (reqType == 1 || reqType == 2) {
                    reqTypeClass = 24; //комп. класс с проектором, или любой практический класс с проектором
                }
            } else if (requirements == 2) {
                reqTypeClass = 2; //компьютерный класс
            }

            Element sch;
            for (int k = 0; k < groups.get(i).getLoad(); k++) {
                sch = new Element(teacher, (new Classroom(reqTypeClass)), subject, flow, typeSubject);
                double a = getClassrooms(requirements, reqType, classroom);
                double p = getTeachersLoad(teacher, groups);
                double g = getStudientsLoad(flow, groups, flows);
                double S = getS(a, p, g);
                listClasses.add(new ListClasses(sch, S));
            }

        }

    }

    private static double getS(double a, double p, double g) {
        return a / (p * g);
    }

    private static double getStudientsLoad(int flow, List<Load> groups, List<Flow> flows) {
        double e = 0;
        List<Double> d = new ArrayList<>();
        for (Flow flow1 : flows) {
            if (flow1.getNumberFlow() == flow) {
                for (int i = 0; i < flow1.getGroupList().size(); i++) {
                    Group group = flow1.getGroupList().get(i);
                    d.add(checkGroup(group, groups, flows));
                }
            }
        }
        e = getMax(d);
        return e;
    }

    private static double getMax(List<Double> d) {
        double max = d.get(0);
        for (double dd : d) {
            if (dd > max) max = dd;
        }
        return max;
    }

    private static double checkGroup(Group group, List<Load> groups, List<Flow> flows) {
        double e = 0;
        for (int h = 0; h < groups.size(); h++) {
            for (int j = 0; j < flows.size(); j++) {
                if (flows.get(j).getNumberFlow() == groups.get(h).getFlow()) {
                    if (flows.get(j).getGroupList().contains(group)) {
                        e = e + groups.get(h).getLoad();
                    }
                }
            }
        }
        return e;
    }

    private static double getTeachersLoad(String teacher, List<Load> groups) {
        double t = 0;
        for (int i = 0; i < groups.size(); i++) {
            if (teacher.equals(groups.get(i).getTeacher())) {
                t = t + groups.get(i).getLoad();
            }
        }
        return t;
    }

    private static float getClassrooms(int requirements, int reqType, List<Classroom> classroom) {
        int k = 0;
        for (int i = 0; i < classroom.size(); i++) {
            if (requirements == 13 && reqType == 0) { //лекция без ограничений
                if (classroom.get(i).getTypeClassroom() == 40 || classroom.get(i).getTypeClassroom() == 0) { //просто лекционная, или лекционная с проектором
                    k++;
                }
            } else if ((requirements == 13 && (reqType == 1 || reqType == 2))) {
                if (classroom.get(i).getTypeClassroom() == 2 || classroom.get(i).getTypeClassroom() == 24) {
                    k++; //просто комп.класс или комп.класс с проектором
                }
            } else {
                if (requirements == 4 && reqType == 0) {
                    if (classroom.get(i).getTypeClassroom() == 40) { //лекционная с проектором
                        k++;
                    }
                } else if (requirements == 4 && (reqType == 1 || reqType == 2)) { //то ищем аудитории с проектором, в которых может проводиться практика или лаба


                } else if (requirements == 2 && reqType == 0) { //если лекция должна быть в комп. классе
                    if (classroom.get(i).getTypeClassroom() == 2 || classroom.get(i).getTypeClassroom() == 24) {
                        k++; //просто комп.класс или комп.класс с проектором
                    }
                } else if (requirements == 2 && (reqType == 1 || reqType == 2)) {
                    if (classroom.get(i).getTypeClassroom() == 2 || classroom.get(i).getTypeClassroom() == 24) {
                        k++; //просто комп.класс или комп.класс с проектором
                    }
                }
            }

        }
        return k;
    }
}
