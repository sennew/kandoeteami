package com.projects.wens.kandoeteami.session.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.session.data.SessionDTO;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by michaelkees on 18/03/16.
 */
public class SessionContentAdapter extends RecyclerView.Adapter<SessionContentAdapter.ViewHolder> {
    private static final String PICASSO_BASEURL = "http://wildfly-teamiip2kdgbe.rhcloud.com/";
    private List<SessionDTO> mSessions;
    private SessionItemListener itemListener;
    private Context context;

    public SessionContentAdapter(List<SessionDTO> mSessions, SessionItemListener itemListener, Context context) {
        this.mSessions = mSessions;
        this.itemListener = itemListener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //TODO: ITEM XML
        View cardSessionView = inflater.inflate(R.layout.item_session, parent, false);

        return new ViewHolder(cardSessionView, itemListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SessionDTO sessionDTO = mSessions.get(position);
        holder.sessionName.setText(sessionDTO.getSessionName());
        holder.startTime.setText(sessionDTO.getStartTime());
        //TODO: current user??
        holder.sessionCurrentUser.setText("Michael Kees");

        if(sessionDTO.getTheme()!=null){
            if (sessionDTO.getTheme().getIconURL().charAt(0) == 'r') {
                Picasso.with(context).load(PICASSO_BASEURL + sessionDTO.getTheme().getIconURL()).into(holder.themeImage);
            } else {
                Picasso.with(context).load(sessionDTO.getTheme().getIconURL()).into(holder.themeImage);
            }
        } else if (sessionDTO.getSubTheme()!=null){
            if (sessionDTO.getSubTheme().getIconURL().charAt(0) == 'r') {
                Picasso.with(context).load(PICASSO_BASEURL + sessionDTO.getSubTheme().getIconURL()).into(holder.themeImage);
            } else {
                Picasso.with(context).load(sessionDTO.getSubTheme().getIconURL()).into(holder.themeImage);
            }
        }


    }

    public void replaceData(List<SessionDTO> sessionDTOs) {
        setList(sessionDTOs);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mSessions.size();
    }

    private void setList(List<SessionDTO> sessionDTOs) {
        mSessions.clear();
        for (SessionDTO s : sessionDTOs) {
            mSessions.add(s);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView sessionName;
        public TextView sessionCurrentUser;
        public TextView startTime;
        public ImageView themeImage;

        public Button detailButton;

        private SessionItemListener mItemListener;

        public ViewHolder(View itemView, SessionItemListener listener) {
            super(itemView);
            mItemListener = listener;
            sessionName = (TextView) itemView.findViewById(R.id.session_name);
            themeImage = (ImageView) itemView.findViewById(R.id.session_theme_image);
            startTime = (TextView) itemView.findViewById(R.id.session_starttime);
            sessionCurrentUser = (TextView) itemView.findViewById(R.id.session_currentuser);
            detailButton = (Button) itemView.findViewById(R.id.button_session_detail);
            detailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    SessionDTO sessionDTO = mSessions.get(position);
                    mItemListener.onSessionDetailClick(sessionDTO);
                }
            });
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            SessionDTO sessionDTO = mSessions.get(position);
            mItemListener.onSessionClick(sessionDTO);
        }
    }
}

