package com.cxyz.commons.IPresenter;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.IView.IBaseView;

/**
 * Created by 夏旭晨 on 2018/9/22.
 * IPresenter的基类
 */

public abstract class IBasePresenter<M extends IBaseModel, V extends IBaseView> {

    public M mIModle;
    public V mIView;

    /**
     * 依附相应的IView
     * @param v
     */
    public void attachV(V v){
        this.mIView = v;
        if (mIModle == null) {
            mIModle = createModel();
        }
    }
    //当View被销毁掉时删除Presenter层对View层的引用,并关闭所有请求
     public void detachV(){
        if(mIModle != null)
            mIModle.ClearCalls();
        mIView = null;
    }

    /**
     * 创建model
     * @return
     */
    public abstract M createModel();


    /**
     * 生命周期方法,在Activity中重写getIBasePresenter将会在activity中相应的进行注册
     */
    public void onStart() {}

    /**
     * 生命周期方法,在Activity中重写getIBasePresenter将会在activity中相应的进行注册
     */
    public void onPause() {}

    /**
     * 生命周期方法,在Activity中重写getIBasePresenter将会在activity中相应的进行注册
     */
    public void onStop() {}

    /**
     * 生命周期方法,在Activity中重写getIBasePresenter将会在activity中相应的进行注册
     */
    public void onResume() {}

    /**
     * 生命周期方法,在Activity中重写getIBasePresenter将会在activity中相应的进行注册
     */
    public void onDestory() {}

}
