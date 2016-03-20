package com.projects.wens.kandoeteami.themes.adapter;

import com.projects.wens.kandoeteami.themes.data.Theme;

public interface ThemeItemListener {
    /**
     * on theme click action open activity
     * @param clickTheme
     */
    void onThemeClick(Theme clickTheme);

    /**
     * on subtheme click action open activity
     * @param idClickedTheme
     */
    void onSubthemeClick(int idClickedTheme);
}
