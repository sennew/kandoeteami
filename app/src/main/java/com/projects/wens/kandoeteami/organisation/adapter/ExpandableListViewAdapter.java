package com.projects.wens.kandoeteami.organisation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.organisation.data.ChildHolder;
import com.projects.wens.kandoeteami.organisation.data.ChildItem;
import com.projects.wens.kandoeteami.organisation.data.GroupHolder;
import com.projects.wens.kandoeteami.organisation.data.GroupItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by senne on 04/03/2016.
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private static final String PICASSO_BASEURL = "http://wildfly-teamiip2kdgbe.rhcloud.com/";
    private LayoutInflater inflater;
    private List<GroupItem> items;

    public ExpandableListViewAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<GroupItem> items){
        this.items = items;
    }

    @Override
    public ChildItem getChild(int groupPosition, int childPosition) {
        return items.get(groupPosition).children.get(childPosition);
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        ChildItem item = getChild(groupPosition, childPosition);
        if (convertView == null){
            holder = new ChildHolder();
            convertView = inflater.inflate(R.layout.expandable_list_item, parent, false);
            holder.firstName = (TextView) convertView.findViewById(R.id.textVoornaam);
            holder.lastName = (TextView) convertView.findViewById(R.id.textAchternaam);
            holder.role = (TextView) convertView.findViewById(R.id.textMemberOrganisator);
            holder.profilePicture = (CircleImageView) convertView.findViewById(R.id.user_img_expandable);
            convertView.setTag(holder);
        } else{
            holder = (ChildHolder) convertView.getTag();
        }

        holder.firstName.setText(item.firstName);
        holder.lastName.setText(item.lastName);
        holder.role.setText(item.role);
        if (item.profilePictureUrl.charAt(0) == 'h'){
            Picasso.with(convertView.getContext()).load(item.profilePictureUrl).into(holder.profilePicture);
        } else{
            Picasso.with(convertView.getContext()).load(PICASSO_BASEURL + item.profilePictureUrl).into(holder.profilePicture);
        }
        return convertView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        GroupItem item = getGroup(groupPosition);
        if (convertView == null){
            holder = new GroupHolder();
            convertView = inflater.inflate(R.layout.expandable_list_group, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.expandable_title);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        holder.title.setText(item.getTitle());
        return convertView;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return items.get(groupPosition).getChildren().size();
    }

    @Override
    public GroupItem getGroup(int groupPosition) {
        return items.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }



    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
