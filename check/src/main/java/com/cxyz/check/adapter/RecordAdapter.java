package com.cxyz.check.adapter;

import android.content.Context;

import com.cxyz.check.R;
import com.cxyz.commons.Adapter.AdapterBase;
import com.cxyz.commons.Adapter.ViewHolder;
import com.cxyz.logiccommons.dto.ResultCustom;
import com.cxyz.logiccommons.typevalue.CheckRecordResult;

import java.util.List;

public class RecordAdapter extends AdapterBase<ResultCustom> {

    public RecordAdapter(Context mContext, List<ResultCustom> list, int... mItemLayoutId) {
        super(mContext, list, mItemLayoutId);
    }

    @Override
    public void convertView(ViewHolder holder, ResultCustom item) {
        super.convertView(holder, item);
        int pic = 0;//圆点
        String info = null;//信息
        int count = item.getCount();
        switch (item.getResultType())
        {
            case CheckRecordResult.ABSENTEEISM:
            {
                pic = R.mipmap.check_icon_red;
                info = count+"次缺勤记录";
            }break;
            case CheckRecordResult.LATE:
            {
                pic = R.mipmap.check_icon_yellow;
                info = count+"次迟到记录";
            }break;
            case CheckRecordResult.VACATE:
            {
                pic = R.mipmap.check_icon_green;
                info = count+"次请假记录";
            }break;
            case CheckRecordResult.EARLYLEAVE:
            {
                pic = R.mipmap.check_icon_blue;
                info = count+"次早退记录";
            }break;
        }
        holder.setImageResource(R.id.iv_icon,pic);
        holder.setText(R.id.tv_info,info);
    }

}