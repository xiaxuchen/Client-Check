package com.cxyz.network.listener;

/**
 * Created by 夏旭晨 on 2018/9/23.
 */

public interface DisposeDataListener {
        public void onSuccess(Object responseObj);
        public void onFailure(Object error);
}
