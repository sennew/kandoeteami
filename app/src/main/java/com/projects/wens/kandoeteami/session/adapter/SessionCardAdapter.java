package com.projects.wens.kandoeteami.session.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.session.SessionGameContract;
import com.projects.wens.kandoeteami.themes.data.Card;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by michaelkees on 16/03/16.
 */
public class SessionCardAdapter extends RecyclerView.Adapter<SessionCardAdapter.ViewHolder> {
    private static final String PREFS_NAME = "MyPrefs";
    private List<Card> cards;
    private SessionCardItemListener itemListener;
    private static final String PICASSO_BASEURL = "http://wildfly-teamiip2kdgbe.rhcloud.com/";
    private Context context;
    private int sessionId;
    private SessionGameContract.UserActionListener actionListener;

    public SessionCardAdapter(List<Card> cards, int sessionId, SessionCardItemListener itemListener, Context context, SessionGameContract.UserActionListener actionListener) {
        this.cards = cards;
        this.itemListener = itemListener;
        this.context = context;
        this.actionListener = actionListener;
        this.sessionId = sessionId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cardView = inflater.inflate(R.layout.item_card, parent, false);

        return new ViewHolder(cardView, itemListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Card card = cards.get(position);
        holder.cardDescription.setText(card.getDescription());
        //holder.sessionCardPosition.setText(card.getPosition());
        Log.i("CARD:", "" + card.getPosition());
        holder.txtSessionCardVotes.setText(String.valueOf(card.getPosition()));
        if (card.getImageUrl().charAt(0) == 'r') {
            Picasso.with(context).load(PICASSO_BASEURL + card.getImageUrl()).resize(200,200).centerCrop().into(holder.sessionCardImage);
        } else {
            Picasso.with(context).load(card.getImageUrl()).resize(200,200).centerCrop().into(holder.sessionCardImage);
        }
    }

    public void replaceData(List<Card> cards) {
        setList(cards);
        notifyDataSetChanged();
    }

    private void setList(List<Card> cs) {
        this.cards.clear();
        Collections.sort(cs, new Comparator<Card>() {
            @Override
            public int compare(Card lhs, Card rhs) {
                return rhs.getPosition() - lhs.getPosition();
            }
        });
        for (Card c : cs) {
            this.cards.add(c);
        }
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView sessionCardPosition;
        public ImageView sessionCardImage;
        public ImageView imgVoteUp;
        public TextView cardDescription;
        public TextView txtSessionCardVotes;

        private SessionCardItemListener itemListener;

        public ViewHolder(final View itemView, SessionCardItemListener listener) {
            super(itemView);
            itemListener = listener;
            sessionCardPosition = (TextView) itemView.findViewById(R.id.session_card_position);
            cardDescription = (TextView) itemView.findViewById(R.id.card_description);
            txtSessionCardVotes = (TextView) itemView.findViewById(R.id.txt_session_card_votes);
            sessionCardImage = (ImageView) itemView.findViewById(R.id.card_session_img);
            imgVoteUp = (ImageView) itemView.findViewById(R.id.img_vote_up);

            imgVoteUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    SharedPreferences settings = context.getApplicationContext().getSharedPreferences(PREFS_NAME, 0);
                    String token = settings.getString("token", null);
                    Card card = cards.get(position);
                    actionListener.canMakeCardMove(token, card.getCardId());

                    card.setPosition(card.getPosition() + 1);
                    txtSessionCardVotes.setText(String.valueOf(card.getPosition()));


                }
            });
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Card card = cards.get(position);
            itemListener.onCardClick(card);
        }
    }
}
