package com.cxyz.check.model;

import android.app.AlertDialog;
import android.media.Image;
import android.widget.GridView;
import android.widget.ListView;

import com.cxyz.check.checkTools.StuInfo_Check;
import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.application.MyApp;
import com.cxyz.commons.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 28058 on 2018/9/26.
 */

public interface IListViewModel extends IBaseModel {



    StuInfo_Check getListViewInfo(User user);



}