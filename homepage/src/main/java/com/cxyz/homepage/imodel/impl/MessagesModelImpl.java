package com.cxyz.homepage.imodel.impl;

import com.cxyz.commons.date.Date;
import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.homepage.constant.RequestCenter;
import com.cxyz.homepage.imodel.MessagesModel;
import com.cxyz.homepage.myAdapter.cell.MessageCell;
import com.cxyz.logiccommons.domain.RecordDetail;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 鱼塘主 on 2018/11/18.
 */

public class MessagesModelImpl extends MessagesModel {
    @Override
    public void getMessagesJson(String _id, Date time, final getMessagesInfo listtener) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "getRecordDetails");
        map.put("id", _id+"");
        map.put("Date", time + "");
        RequestParams requestParams = new RequestParams(map);
        RequestCenter.getRecords(requestParams, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                List<RecordDetail> recordDetails = null;
                try {
                    recordDetails = (List<RecordDetail>) GsonUtil.fromJson(responseObj.toString(), RecordDetail.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                List<MessageCell> userClazzsCells = new ArrayList<>();
                for (int j = 0; j < recordDetails.size(); j++) {
                    userClazzsCells.add(new MessageCell(recordDetails.get(j)));
                }
                listtener.getInfoSuccess(userClazzsCells);
                LogUtil.e(recordDetails.toString());
            }

            @Override
            public void onFailure(Object error) {
                listtener.getInfFail(error);
            }
        });

    }
}
