package com.cxyz.logiccommons.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by Administrator on 2018/12/15.
 */

public enum IconFonts implements Icon {
    icon_people('\ue68b'),
    icon_task('\ue68c'),
    icon_time('\ue68d'),
    icon_home('\ue68e'),
    icon_videos('\ue68f'),
    icon_scan('\ue690'),
    icon_search('\ue691'),
    icon_satisic('\ue692'),
    icon_add('\ue693'),
    icon_text('\ue694'),
    icon_file('\ue695'),
    icon_down('\ue696'),
    icon_visible('\ue697'),
    icon_photo('\ue698'),
    icon_bankcard('\ue699'),
    icon_print('\ue69a'),
    icon_write('\ue69b'),
    icon_volice('\ue69c'),
    icon_sign('\ue69d'),
    icon_label('\ue69e'),
    icon_move('\ue69f'),
    icon_order('\ue6a0'),
    icon_good('\ue6a1'),
    icon_message('\ue6a2'),
    icon_sort('\ue6a3'),
    icon_locate('\ue6a4'),
    icon_exit('\ue6a5'),
    icon_share('\ue6a6'),
    icon_buycar('\ue6a7'),
    icon_buybag('\ue6a8'),
    icon_return('\ue6a9'),
    icon_service('\ue6aa'),
    icon_rubbish('\ue6ab'),
    icon_earphone('\ue6ac'),
    icon_rubbishs('\ue6ad'),
    icon_wallet('\ue6ae'),
    icon_video('\ue6af'),
    icon_kind('\ue6a3'),
    icon_ring('\ue6b0'),
    icon_coin('\ue6b1'),
    icon_wallets('\ue6b2'),;


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
