package com.cxyz.check.ipresenter.ipresenterimpl;

import com.cxyz.check.R;
import com.cxyz.check.constant.IDs;
import com.cxyz.check.ipresenter.ICheckPresenter;
import com.cxyz.check.model.ICheckModel;
import com.cxyz.check.model.imodelimpl.ICheckModelImpl;
import com.cxyz.logiccommons.domain.TaskInfo;
import com.cxyz.logiccommons.domain.User;
import com.cxyz.logiccommons.manager.UserManager;

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
    private int[] imgs = new int[]{R.mipmap.dailychecks,R.mipmap.tempchecks,
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

    public ICheckModel createModel() {
        return new ICheckModelImpl();
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

    @Override
    public void checkTask() {
        User u = UserManager.getInstance().getUser();
        mIView.showLoadingView();
        mIModle.checkComp(u.get_id(), u.getType(), new ICheckModel.CheckListener() {
            @Override
            public void onSuccess(TaskInfo taskInfo) {
                //请求成功显示成功逻辑
                mIView.hideLoadingView();
                mIView.showTask(taskInfo);
            }

            @Override
            public void onFail(String error) {
                //请求失败或数据错误显示失败逻辑
                mIView.hideLoadingView();
                mIView.showNoTask(error);
            }
        });
    }

    private int[] getIndex()
    {
        switch (UserManager.getInstance().getUser().getPower())
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