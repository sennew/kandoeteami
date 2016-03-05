package com.projects.wens.kandoeteami.organisation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.projects.wens.kandoeteami.organisation.data.GroupItem;

import java.util.List;

/**
 * Created by senne on 04/03/2016.
 */
public class expandableListViewAdapter extends BaseExpandableListAdapter {
    private LayoutInflater inflater;
    private List<GroupItem> items;

    public expandableListViewAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    public void setDate(List<GroupItem> items){
        this.items = items;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return items.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }



    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }



    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
