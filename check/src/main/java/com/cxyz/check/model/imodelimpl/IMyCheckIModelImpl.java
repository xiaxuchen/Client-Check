package com.cxyz.check.model.imodelimpl;

import com.cxyz.check.adapter.RecordAdapter;
import com.cxyz.check.constant.RequestCenter;
import com.cxyz.check.model.IMyCheckModel;
import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.exception.OKHttpException;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.logiccommons.domain.CheckRecord;
import com.cxyz.logiccommons.domain.RecordDetail;
import com.cxyz.logiccommons.domain.Statistic;
import com.cxyz.logiccommons.manager.UserManager;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 夏旭晨 on 2018/10/20.
 */

public class IMyCheckIModelImpl implements IMyCheckModel {

    @Override
    public void getRds(final String id, final getRdsListener listener) {
        RequestCenter.getRecords(id, CheckRecord.ALL, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                if(listener!=null) {
                    final List<RecordDetail> recordDetails = GsonUtil.GsonToList(responseObj.toString(), RecordDetail.class);
                    Integer grade_id = UserManager.getInstance().getUser().getGrade().get_id();
                    LogUtil.e(grade_id+"");
                    //获取记录成功，获取考勤统计结果
                    RequestCenter.getStatistic(id,grade_id ,new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            //将json字符串转化为对象
                            Statistic statistic = null;
                            try {
                                statistic = GsonUtil.GsonToBean(responseObj.toString(), Statistic.class);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                listener.onFail("服务器异常");
                            }
                            //计算异常次数
                            int checkerror = statistic.getLate()+statistic.getEarly_leave()+statistic
                                    .getTruant()+statistic.get_leave();
                            //计算出勤率
                            int progress = (statistic.getTimes()-checkerror)*100/statistic.getTimes();
                            listener.onSuccess(handleData(recordDetails),statistic.getTimes()
                            ,checkerror,statistic.getLate()+statistic.getEarly_leave(),
                                    statistic.getTruant(),progress);
                        }

                        @Override
                        public void onFailure(Object error) {
                            if(error instanceof String)
                            listener.onFail(error.toString());
                            else if (error instanceof OKHttpException)
                                listener.onFail(((OKHttpException) error).getMessage());
                            else
                                listener.onFail("未知错误");
                        }
                    });
                }

            }

            @Override
            public void onFailure(Object error) {
                if(error instanceof String)
                    listener.onFail(error.toString());
                else if (error instanceof OKHttpException)
                    listener.onFail(((OKHttpException) error).getMessage());
                else
                    listener.onFail("未知错误");
            }
        });
    }

    private List<Map<String,Object>> handleData(List<RecordDetail> rds)
    {
        List<Map<String,Object>> data = new ArrayList<>();
        List<RecordDetail> rdss[] = new List[4];
        for(int i = 0;i<4;i++)
        {
            rdss[i] = new ArrayList<>();
        }
        for(RecordDetail rd:rds)
        {
            switch (rd.getResult())
            {
                case CheckRecord.ABSENTEEISM:
                {
                    rdss[0].add(rd);
                }break;
                case CheckRecord.EARLYLEAVE:
                {
                    rdss[1].add(rd);
                }break;
                case CheckRecord.LATE:
                {
                    rdss[2].add(rd);
                }break;
                case CheckRecord.VACATE:
                {
                    rdss[3].add(rd);
                }break;
            }
        }
        int ranks[] = new int[]{CheckRecord.ABSENTEEISM,CheckRecord.EARLYLEAVE,CheckRecord.LATE,CheckRecord.VACATE};
        Map<String,Object> map = null;
        for(int i = 0;i<rdss.length;i++)
        {
            if(rdss[i].isEmpty())
                continue;
            map = new HashMap<>();
            map.put("parent",new RecordAdapter.ParentInfo
                    (ranks[i],rdss[i].size()));
            map.put("child",rdss[i]);
            data.add(map);
        }
        return data;
    }
}
