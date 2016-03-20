package com.projects.wens.kandoeteami.session.adapter;

import com.projects.wens.kandoeteami.themes.data.Card;
import com.projects.wens.kandoeteami.themes.data.Theme;


public interface SessionCardItemListener {
    /**
     * on card click action
     * @param clickCard
     */
    void onCardClick(Card clickCard);
}
