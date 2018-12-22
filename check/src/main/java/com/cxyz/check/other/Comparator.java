package com.cxyz.check.other;

import com.cxyz.check.dto.GradeStusDto;
import com.cxyz.commons.widget.sideview.CharacterParser;

/**
 * Created by Administrator on 2018/11/20.
 */

public class Comparator implements java.util.Comparator<GradeStusDto>{

    @Override
    public int compare(GradeStusDto o1, GradeStusDto o2) {
            CharacterParser parser = CharacterParser.getInstance();
            String o1_first_letter = String.valueOf(parser.getSelling(o1.getName()).charAt(0));
            String o2_first_letter = String.valueOf(parser.getSelling(o2.getName()).charAt(0));
            //这里主要是用来对ListView里面的数据根据ABCDEFG...来排序
            if (o1_first_letter.equals("#")) {
                return -1;
            } else if (o2_first_letter.equals("#")) {
                return 1;
            } else {
                return o1_first_letter.compareTo(o2_first_letter);
            }
    }
}
