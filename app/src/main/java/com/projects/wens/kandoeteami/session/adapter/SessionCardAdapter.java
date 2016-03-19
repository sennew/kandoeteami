package com.projects.wens.kandoeteami.session.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.themes.data.Card;
import com.projects.wens.kandoeteami.themes.data.Theme;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by michaelkees on 16/03/16.
 */
public class SessionCardAdapter extends RecyclerView.Adapter<SessionCardAdapter.ViewHolder>{

    private List<Card> cards;
    private SessionCardItemListener itemListener;
    private static final String PICASSO_BASEURL = "http://wildfly-teamiip2kdgbe.rhcloud.com/";
    private Context context;

    public SessionCardAdapter(List<Card> cards, SessionCardItemListener itemListener, Context context) {
        this.cards = cards;
        this.itemListener = itemListener;
        this.context = context;
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


        if (card.getImageUrl().charAt(0) == 'r') {
            Picasso.with(context).load(PICASSO_BASEURL + card.getImageUrl()).into(holder.sessionCardImage);
        } else {
            Picasso.with(context).load(card.getImageUrl()).into(holder.sessionCardImage);
        }
    }

    public void replaceData(List<Card> cards) {
        setList(cards);
        notifyDataSetChanged();
    }

    private void setList(List<Card> cs) {
        this.cards.clear();
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

        public ViewHolder(View itemView, SessionCardItemListener listener) {
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
                    //TODO: BACKEND SOCKET VOTE UP / 1 MOVE;

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
