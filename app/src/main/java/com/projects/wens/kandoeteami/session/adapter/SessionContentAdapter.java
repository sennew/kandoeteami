package com.projects.wens.kandoeteami.session.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.style.LocaleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.session.data.SessionDTO;
import com.projects.wens.kandoeteami.session.data.SessionState;
import com.projects.wens.kandoeteami.user.data.User;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        String format = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        //2016-03-19T07:20:02.295
        //yyyy-MM-dd'T'HH:mm:ssZ

        Date start;
        Date end;
        Date now = new Date();
        try {
            start = sdf.parse(sessionDTO.getStartTime());
            Log.i("DATE", start.toString());
            end = sdf.parse(sessionDTO.getEndTime());

            if(start.getTime() > now.getTime()) {
                long different = start.getTime() - now.getTime();
                long secondsInMilli = 1000;
                long minutesInMilli = secondsInMilli * 60;
                long hoursInMilli = minutesInMilli * 60;
                long daysInMilli = hoursInMilli * 24;

                long elapsedDays = different / daysInMilli;
                different = different % daysInMilli;

                long elapsedHours = different / hoursInMilli;
                different = different % hoursInMilli;

                long elapsedMinutes = different / minutesInMilli;
                different = different % minutesInMilli;

                long elapsedSeconds = different / secondsInMilli;

                String time = String.format("%d days, %d hours, %d minutes", elapsedDays, elapsedHours, elapsedMinutes);
                Log.i("TIME", time);
                holder.startTime.setText(time);
            } else if (now.getTime() > start.getTime()) {
                if(sessionDTO.getState() == SessionState.IN_PROGRESS) {
                    holder.startTime.setText("Session in progress");
                } else  if(sessionDTO.getState() == SessionState.FINISHED){
                    holder.startTime.setText("Session finished");
                }else  if(sessionDTO.getState() == SessionState.CREATED){
                    holder.startTime.setText("Session created");
                }
                Log.i("TIME", "progress");
            } else if(now.getTime() == start.getTime()){
                holder.startTime.setText("Session in progress");
            }

        } catch (ParseException e) {
            Log.i("DATE", e.getMessage());
        }


        List<User> users = new ArrayList<>();
        User currentUser = new User();
        users = sessionDTO.getUsers();
        for(User u : users){
            if(u.getPosition() == 1) {
                currentUser = u;
            }
        }
        holder.sessionCurrentUser.setText(currentUser.getUsername());
        //de usersession met position 0

        if(sessionDTO.getState() == SessionState.CREATED) {
            holder.sessionState.setBackgroundResource(R.color.darkblue);
            holder.sessionState.setText("PENDING");
        } else if (sessionDTO.getState() == SessionState.IN_PROGRESS){
            holder.sessionState.setBackgroundResource(R.color.green);
            holder.sessionState.setText("ACTIVE");
        } else if (sessionDTO.getState() == SessionState.FINISHED) {
            holder.sessionState.setBackgroundResource(R.color.orange);
            holder.sessionState.setText("FINISHED");
        }

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
        public TextView sessionState;
        public ImageView themeImage;

        public Button detailButton;

        private SessionItemListener mItemListener;

        public ViewHolder(View itemView, SessionItemListener listener) {
            super(itemView);
            mItemListener = listener;
            sessionName = (TextView) itemView.findViewById(R.id.session_name);
            themeImage = (ImageView) itemView.findViewById(R.id.session_theme_image);
            startTime = (TextView) itemView.findViewById(R.id.session_starttime);
            sessionState = (TextView) itemView.findViewById(R.id.session_state);
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

