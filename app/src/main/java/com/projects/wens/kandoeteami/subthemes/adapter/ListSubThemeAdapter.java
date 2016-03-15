package com.projects.wens.kandoeteami.subthemes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.subthemes.data.SubTheme;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by senne on 14/03/2016.
 */
public class ListSubThemeAdapter extends RecyclerView.Adapter<ListSubThemeAdapter.ViewHolder> {
    private List<SubTheme> mSubThemes;
    private ListSubThemeListener subThemeItemListener;
    private static final String PICASSO_BASEURL = "http://wildfly-teamiip2kdgbe.rhcloud.com/";
    private Context context;

    public ListSubThemeAdapter(List<SubTheme> subThemes, ListSubThemeListener itemListener, Context context) {
        mSubThemes = subThemes;
        subThemeItemListener = itemListener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cardOrgView = inflater.inflate(R.layout.item_subtheme, parent, false);

        return new ViewHolder(cardOrgView, subThemeItemListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SubTheme subTheme = mSubThemes.get(position);
        holder.title.setText(subTheme.getThemeName());
        holder.description.setText(subTheme.getDescription());

        if (subTheme.getIconURL().charAt(0) == 'r') {
            Picasso.with(context).load(PICASSO_BASEURL + subTheme.getIconURL()).into(holder.image);
        } else {
            Picasso.with(context).load(subTheme.getIconURL()).into(holder.image);
        }
    }

    public void replaceData(List<SubTheme> subThemes) {
        setList(subThemes);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mSubThemes.size();
    }

    private void setList(List<SubTheme> subThemes) {
        mSubThemes.clear();
        for (SubTheme t : subThemes) {
            mSubThemes.add(t);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView description;
        public ImageView image;
        public TextView theme;

        private ListSubThemeListener themeItemListener;

        public ViewHolder(View itemView, ListSubThemeListener listener) {
            super(itemView);
            themeItemListener = listener;
            title = (TextView) itemView.findViewById(R.id.card__subtheme_title);
            description = (TextView) itemView.findViewById(R.id.card_subtheme_text);
            image = (ImageView) itemView.findViewById(R.id.card_subtheme_image);
            theme = (TextView) itemView.findViewById(R.id.theme_themeName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            SubTheme subTheme = mSubThemes.get(position);
            themeItemListener.onSubThemeClick(subTheme);
        }
    }
}
