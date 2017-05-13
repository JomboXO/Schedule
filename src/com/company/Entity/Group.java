package com.company.Entity;

/**
 * Created by Татьяна on 13.05.2017.
 */
public class Group {
    private String group;

    public Group(String group) {
        this.group = group;
    }

    public String getGroup() {

        return group;
    }

    public void setGroup(String group) {
        this.group = group;
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
            Group tmp = (Group)obj;
            if(tmp.group.equals(this.group))
                return true;
            else
                return false;
        }
    }

    @Override
    public String toString() {
        return "Group{" +
                "group='" + group + '\'' +
                '}';
    }
}
