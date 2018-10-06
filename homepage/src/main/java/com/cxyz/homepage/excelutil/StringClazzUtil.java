package com.cxyz.homepage.excelutil;

/**
 * Created by 鱼塘主 on 2018/9/28.
 */

import com.cxyz.homepage.feature_z_domain.Clazz;

/**
 * 解析Excel课表中的字符串解析类
 *
 */
public class StringClazzUtil {
    Clazz clazz;
    private StringClazzUtil() {
    }

    /**
     *  // String s = " 离散数学(学科教育必修课)◇1-17(1,2节)◇3316◇朱哲◇(*54人)";
     //        s = s.replaceAll("[//s+)]","").trim();
     //        System.out.println(s);
     //        String[] put = s.split(replace);
         0::::离散数学@
         1::::学科教育必修课@
         2::::1-17@
         3::::1,2节@
         4::::3316@
         5::::朱哲@
     * @param excalString 读取到的字符串
     * @param cul 读取的列数
     * @return 一个课程对象
     */
    public static  Clazz getClazzName(String excalString,int cul){
        if(excalString.equals(" ")||excalString.equals("")){
            return new Clazz("","","","","","","","","","","");
        }
        Clazz clazz;
        String replace = "[(◇]";
        String dan = "单",shuang = "双",status;
        String s = excalString.replaceAll("[//s+)]]","").trim();
        String[] s1 = excalString.split(replace);
        String startWeek = s1[2].split("[-]")[0];
        String endWeek = s1[2].split("[-]")[1];
        int boo = endWeek.length();
        if (endWeek.indexOf(dan)==-1&&endWeek.indexOf(shuang)==-1){
                status = "单双都要上";
        }else{
                status = endWeek.substring(boo-1,boo);
        }
        clazz =  new Clazz(s1[0],s1[1],"12345",s1[5],s1[4],String.valueOf(cul),s1[3].substring(0,1),s1[3].substring(2,3),startWeek,endWeek,status);
        return clazz;
    }
}
