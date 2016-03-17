package com.projects.wens.kandoeteami.themes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.themes.data.Theme;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {
    private List<Theme> mThemes;
    private ThemeItemListener themeItemListener;
    private static final String PICASSO_BASEURL = "http://wildfly-teamiip2kdgbe.rhcloud.com/";
    private Context context;

    public ThemeAdapter(List<Theme> themes, ThemeItemListener itemListener, Context context) {
        mThemes = themes;
        themeItemListener = itemListener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cardOrgView = inflater.inflate(R.layout.item_theme, parent, false);

        return new ViewHolder(cardOrgView, themeItemListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Theme theme = mThemes.get(position);
        holder.title.setText(theme.getThemeName());
        holder.description.setText(theme.getDescription());

        if (theme.getIconURL().charAt(0) == 'r') {
            Picasso.with(context).load(PICASSO_BASEURL + theme.getIconURL()).into(holder.image);
        } else {
            Picasso.with(context).load(theme.getIconURL()).into(holder.image);
        }
        int count = theme.getCountSubthemes();
        if (count == 0){
            holder.countSubthemes.setText("none");
        } else {
            holder.countSubthemes.setText(""+count);
        }

    }

    public void replaceData(List<Theme> themes) {
        setList(themes);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mThemes.size();
    }

    private void setList(List<Theme> themes) {
        mThemes.clear();
        for (Theme t : themes) {
            mThemes.add(t);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView description;
        public ImageView image;
        public TextView countSubthemes;

        private ThemeItemListener themeItemListener;

        public ViewHolder(View itemView, ThemeItemListener listener) {
            super(itemView);
            themeItemListener = listener;
            title = (TextView) itemView.findViewById(R.id.card__theme_title);
            description = (TextView) itemView.findViewById(R.id.card_theme_text);
            image = (ImageView) itemView.findViewById(R.id.card_theme_image);
            countSubthemes = (TextView) itemView.findViewById(R.id.theme_subthemes);
            countSubthemes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: ONCLICKLISTENER OM NAAR SUBTHEMAS TE GAAN
                    int position = getAdapterPosition();
                    Theme theme = mThemes.get(position);
                    themeItemListener.onSubthemesClick(theme.getThemeId());
                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Theme theme = mThemes.get(position);
            themeItemListener.onThemeClick(theme);
        }
    }
}