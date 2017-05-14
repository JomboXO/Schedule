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
            int flow = groups.get(i).getFlow();

            for (int j = 0; j < requirementses.size(); j++) {
                if (requirementses.get(j).getSubject().equals(subject)){
                    if (requirementses.get(j).getTypeSubject() == typeSubject) {
                        requirements = requirementses.get(j).getRequirment();
                        reqType = requirementses.get(j).getTypeSubject();
                    }
                }
                break;
            }

        Element sch;
        for (int k = 0; k < groups.get(i).getLoad(); k++) {
            sch = new Element(teacher, (new Classroom(requirements, reqType)), subject, flow, typeSubject);
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
            if (requirements == 13) {
                k++;
            } else {
                if (requirements == 4){
                    if (classroom.get(i).getRequirment() == requirements) k++;
                }else {
                    if (classroom.get(i).getTypeClassroom() == requirements) k++;
                }
            }

        }
        return k;
    }
}
