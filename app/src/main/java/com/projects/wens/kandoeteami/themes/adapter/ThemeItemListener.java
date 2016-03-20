package com.projects.wens.kandoeteami.themes.adapter;

import com.projects.wens.kandoeteami.themes.data.Theme;

public interface ThemeItemListener {
    /**
     *
     * @param clickTheme
     */
    void onThemeClick(Theme clickTheme);

    /**
     *
     * @param idClickedTheme
     */
    void onSubthemesClick(int idClickedTheme);
}
