package com.cxyz.homepage.iview;

import com.cxyz.commons.IView.IBaseView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 鱼塘主 on 2018/10/3.
 */

public interface MassageListView extends IBaseView{
     void setListItem(List<HashMap<String,String>> listItem);//将从服务器上得到的数据载入listview中
     void refreshData(ArrayList inListDataBean);//下拉刷新
}
