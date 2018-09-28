package com.cxyz.mine.MinePresenter.ipresenter;

import android.widget.Button;
import android.widget.Switch;

import com.cxyz.commons.IView.IBaseView;
import com.cxyz.commons.domain.College;
import com.cxyz.commons.domain.Grade;
import com.cxyz.commons.domain.User;

/**
 * Created by Administrator on 2018/9/27.
 */

public interface IMyinfoView extends IBaseView {
    void showMyInfo(User info);
    void  showMyClass(Grade grade);
    void  showMyCollege(College college);

}
