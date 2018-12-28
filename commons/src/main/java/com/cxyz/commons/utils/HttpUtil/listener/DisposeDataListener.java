package com.cxyz.commons.utils.HttpUtil.listener;

import org.json.JSONException;

/**
 * Created by 夏旭晨 on 2018/9/23.
 */

public interface DisposeDataListener {
        public void onSuccess(Object responseObj) throws JSONException;
        public void onFailure(Object error);
}
