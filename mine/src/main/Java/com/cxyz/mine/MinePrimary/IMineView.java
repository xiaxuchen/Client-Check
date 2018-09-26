package com.cxyz.mine.MinePrimary;

import com.cxyz.commons.IView.IBaseView;
import com.cxyz.commons.domain.College;
import com.cxyz.commons.domain.Grade;
import com.cxyz.commons.domain.User;

/**
 * Created by Administrator on 2018/9/25.
 */

public interface IMineView extends IBaseView{
    void showMineInfo(User info);
    void  showMineClass(Grade grade);
    void  showMineCollege(College college);

}
