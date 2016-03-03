package com.projects.wens.kandoeteami.organisation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.user.data.User;

import java.util.List;

/**
 * Created by michaelkees on 03/03/16.
 */
public class OrganisationMemberAdapter extends ArrayAdapter<User> {

        public OrganisationMemberAdapter(Context context,  List<User> members) {
            super(context, 0, members);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            User user = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
            }

            TextView tvUsername = (TextView) convertView.findViewById(R.id.item_user_username);
            TextView tvEmail = (TextView) convertView.findViewById(R.id.item_user_email);
            ImageView imgProfilepic = (ImageView) convertView.findViewById(R.id.item_user_profilepic);
            ImageView imgOrganiser = (ImageView) convertView.findViewById(R.id.item_user_organiser_icon);

            tvUsername.setText(user.getUsername());
            tvEmail.setText(user.getEmail());
            //imgProfilepic.setImageBitmap();
            imgOrganiser.setVisibility(View.INVISIBLE);
            return convertView;
        }

}
