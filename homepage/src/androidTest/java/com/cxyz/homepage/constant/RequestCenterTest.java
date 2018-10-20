package com.cxyz.homepage.constant;

import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.logiccommons.domain.CheckRecord;
import com.cxyz.logiccommons.domain.RecordDetail;

import org.junit.Test;


/**
 * Created by 夏旭晨 on 2018/10/18.
 */
public class RequestCenterTest {
    @Test
    public void getRecords() throws Exception {
        RequestCenter.getRecords("17478093", CheckRecord.LATE, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                System.out.print(GsonUtil.GsonToList(responseObj.toString(), RecordDetail.class));
            }

            @Override
            public void onFailure(Object error) {

            }
        });
    }

    @Test
    public void getTaskInfos() throws Exception {
    }

}