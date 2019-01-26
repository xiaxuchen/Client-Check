package com.cxyz.vac.icon;

import com.cxyz.commons.utils.LogUtil;
import com.joanzapata.iconify.Icon;

/**
 * Created by Administrator on 2018/12/15.
 */

public enum IconFonts implements Icon {


    next('\ue61e'),
    clear('\ue664'),
    time('\ue73c'),
    down('\ue607'),
    up('\ue665');


    private char character;

    IconFonts(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        LogUtil.e("vac_".concat(name().replace('_', '-')));
        return "vac_".concat(name().replace('_', '-'));
    }

    @Override
    public char character() {
        return character;
    }
}
