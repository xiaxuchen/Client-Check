package com.cxyz.homepage.excelutil;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.cxyz.homepage.feature_z_domain.Clazz;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;


/**
 * Created by 鱼塘主 on 2018/9/27.
 * 使用jxl需要在Android工程中导入jxl.jar包，jxl可以完成Excel的基本读写操作，其支持与不支持的情况如下：<br/>
 1、jxl只支持Excel2003格式，不支持Excel2007格式。即支持xls文件，不支持xlsx文件。
 2、jxl不支持直接修改excel文件，但可通过复制新文件覆盖原文件的方式来间接修改。
 3、jxl只能识别PNG格式的图片，不能识别其他格式的图片。
 */

public class ExcelUtil{
//数据库打开助手

    private static ExcelUtil excelUtils = null;
    private HashMap<String,List<Clazz>> clazz_table = new HashMap<String,List<Clazz>>();
    private List<Clazz> clazz_week_table;
    //构造方法
    private String src;
    /**
     * @param context  当前的上下文
     * @param src  excel的路径
     */
    private ExcelUtil(Context context,String src) {
        this.src = src;//src是excel的路径
    }

    /**
     *
     * @param context 上下文
     * @param src excel的路径
     * @return simpleExcelUtil  单例模式
     */
    public static ExcelUtil getInstance(Context context,String src) {
        if (excelUtils == null) {
            excelUtils = new ExcelUtil(context.getApplicationContext(),src);
        }
        return excelUtils;
    }    /*    * 将excel中的数据读取到数据库中    * */

    /**
     * 读取Excel表的方法
     * @param context 上下文
     * @return hashMap<Srting<Week>,List<Clazz>></>
     */
        public HashMap<String,List<Clazz>> readExcelToDB(Context context) {
            // 所以每次添加前先都要先将数据库清空。
            SharedPreferences sharedPreferences = context.getSharedPreferences("excel", Context.MODE_PRIVATE);
            Boolean noConfig = sharedPreferences.getBoolean("readExcel", true);//默认是没有保存过
            Log.e("readExcelToDB", noConfig + "-----config---------");
            if (noConfig) {//如果没有保存过而且数据库Clazz_info表的内容为空
                try {

                    // 我们把excel放在Assset目录下，通过Workbook.getWorkbook(inputStream);获取到整个excel。
                    InputStream inputStream = context.getAssets().open(src);
                    Workbook workbook = Workbook.getWorkbook(inputStream);// 获取第一张excel数据表。
                    Sheet sheet = workbook.getSheet(0);
                    int rows = sheet.getRows();//获取该表中有多少行数据。
                    int culs = sheet.getColumns();//获取该表中有多少列数据
                    Log.e("readExcelToDB", rows + "-------rows-------");
                    Clazz clazz = null;
                    String[] week = {"周一","周二","周三","周四","周五"};
                    for (int j = 2 ; j < culs ;j++) {
                        clazz_week_table = new ArrayList<Clazz>();
                        for (int i = 3; i < rows; i++) {
                            sheet.getCell(j, i);   //在这里i表示第几行数据，012346表示第几列，从2开始算。

                            String excelString = (sheet.getCell(j, i)).getContents();
                            clazz = StringClazzUtil.getClazzName(excelString, j);
                            Log.e("ExcelUtils", clazz.toString());
                            clazz_week_table.add(clazz);
                        }
                        clazz_table.put(week[j],clazz_week_table);
                    }
                    sharedPreferences.edit().putBoolean("readExcel", false).commit();//读取完毕后，把记录置为不读。

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return clazz_table;
        }

}

