package com.company;

import com.company.Entity.*;

import java.util.List;

/**
 * Created by Татьяна on 14.05.2017.
 */
public class SetListClasses {
    public static void getListClasses(List<Requirements> requirementses, List<Load> groups,
                                      List<Classroom> classroom, List<ListClasses> listClasses) {
        int h = 0;
        for (int i = 0; i < groups.size(); i++) {
            String subject = groups.get(i).getSubject();
            String teacher = groups.get(i).getTeacher();
            int typeSubject = groups.get(i).getTypeSubject();
            int requirements = 0;
            int flow = groups.get(i).getFlow();
        }
    }
}
