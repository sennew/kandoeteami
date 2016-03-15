package com.projects.wens.kandoeteami.themes.adapter;

import com.projects.wens.kandoeteami.themes.data.Theme;

/**
 * Created by michaelkees on 04/03/16.
 */
public interface ThemeItemListener {
    void onThemeClick(Theme clickTheme);
    void onSubthemesClick(int idClickedTheme);
}
