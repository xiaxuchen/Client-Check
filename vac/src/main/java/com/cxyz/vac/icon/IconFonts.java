package com.cxyz.vac.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by Administrator on 2018/12/15.
 */

public enum IconFonts implements Icon {


    next('\ue61e'),
    clear('\ue664');

    private char character;

    IconFonts(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
