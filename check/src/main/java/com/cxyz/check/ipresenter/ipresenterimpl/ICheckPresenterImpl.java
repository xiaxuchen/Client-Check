package com.cxyz.check.ipresenter.ipresenterimpl;

import com.cxyz.check.R;
import com.cxyz.check.constant.IDs;
import com.cxyz.check.ipresenter.ICheckPresenter;
import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.application.MyApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 28058 on 2018/9/29.
 */

public class ICheckPresenterImpl extends ICheckPresenter {

    /**
     * 模块对应的图片
     */
    private int[] imgs = new int[]{R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};

    /**
     * 模块对应的文字
     */
    private String[] txts = new String[]{"日常考勤","临时考勤","模块","模块","模块","模块","模块","模块","模块"};


    /**
     * 模块对应的id
     */
    private long ids[] = new long[]{IDs.DAILYID, IDs.SHORTTIMEID,2,3,4,5,6,7,8};

    public IBaseModel createModel() {
        return null;
    }

    public void getStusToShow() {

    }

    //getGridView功能是返回将会在显示在GridView内的功能名字，返回List，里面包含了功能名和功能图片
    public List getGridViewInfo() {
        int[] indexs = getIndex();
        List<Map<String,Object>> datas = new ArrayList<>();
        Map<String,Object> data = null;
        for(int i = 0;i<indexs.length;i++)
        {
            data = new HashMap<>();
            data.put("img",imgs[indexs[i]]);
            data.put("txt",txts[indexs[i]]);
            data.put("id",ids[indexs[i]]);
            datas.add(data);
        }
        return datas;
    }

    private int[] getIndex()
    {
        switch (MyApp.getUser().getPower())
        {
            case 0:
            {
                return new int[]{};
            }
            case 5:
            {
                return new int[]{0,1};
            }
            case 20:
            {
                return new int[]{};
            }
            case 50:
            {
                return new int[]{};
            }
            case 100:
            {
                return new int[]{};
            }
            default:
            {
                return null;
            }
        }
    }

}