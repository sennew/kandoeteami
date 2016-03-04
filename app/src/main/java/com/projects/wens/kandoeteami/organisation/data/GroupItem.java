package com.projects.wens.kandoeteami.organisation.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by senne on 04/03/2016.
 */
public class GroupItem {
    public String title;
    public List<ChildItem> children;

    public GroupItem(String title) {
        this.title = title;
        children = new ArrayList<ChildItem>();
    }

    public String getTitle() {
        return title;
    }

    public List<ChildItem> getChildren() {
        return children;
    }

    public void addChildren(ChildItem item){
        children.add(item);
    }
}
