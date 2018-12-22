package com.cxyz.check.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by Administrator on 2018/12/15.
 */

public class IconfontModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "check.ttf";
    }

    @Override
    public Icon[] characters() {
        return IconFonts.values();
    }
}
