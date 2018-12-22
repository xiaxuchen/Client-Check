package com.cxyz.check.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by Administrator on 2018/12/15.
 */

public enum IconFonts implements Icon {


    check_forget('\ue626'),
    check_part_time('\ue623'),
    check_saved('\ue60e');

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
