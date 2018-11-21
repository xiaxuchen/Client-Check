package com.cxyz.homepage.iview;

import com.cxyz.commons.IView.IBaseView;
import com.cxyz.homepage.feature_z_domain.MySubject;

import java.util.List;

/**
 * Created by 鱼塘主 on 2018/11/20.
 */

public interface SubjectsView extends IBaseView{
    public void setSubjects(List<MySubject> mySubjects);
}
