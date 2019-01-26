package com.cxyz.vac.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.cxyz.commons.Adapter.AdapterBase;
import com.cxyz.commons.Adapter.ViewHolder;
import com.cxyz.commons.utils.DateUtil;
import com.cxyz.logiccommons.domain.Audit;
import com.cxyz.logiccommons.typevalue.AuditState;
import com.cxyz.vac.R;

import java.util.List;

/**
 * Created by Administrator on 2018/12/31.
 */

public class AuditedAdapter extends AdapterBase<Audit> {

    public AuditedAdapter(Context mContext, List<Audit> list, int... mItemLayoutId) {
        super(mContext, list, mItemLayoutId);
    }

    @Override
    public void convertView(ViewHolder holder, Audit item) {
        super.convertView(holder, item);
        holder.setText(R.id.tv_auditer,item.getChecker().getName());
        holder.setText(R.id.tv_audit_time, DateUtil.dateToString(new java.util.Date(item.getUpdateTime()
                .getTime()), DateUtil.DatePattern.ONLY_MINUTE));
        String state="";
        switch (item.getState())
        {
            case AuditState.FAIL:state="拒绝";break;
            case AuditState.SUCCESS:state="同意";break;
        }
        holder.setText(R.id.tv_audit_state,state);
        TextView tv_audit_des = holder.getView(R.id.tv_audit_des);
        tv_audit_des.setText(item.getInfo());
        holder.setOnClickListener(R.id.tv_audit_des_hint,view -> {
            if(tv_audit_des.getVisibility() == View.VISIBLE)
            {
                tv_audit_des.setVisibility(View.GONE);
                holder.setText(R.id.tv_audit_des_hint,"审核意见 {vac_down}");
            }else {
                tv_audit_des.setVisibility(View.VISIBLE);
                holder.setText(R.id.tv_audit_des_hint,"审核意见 {vac_up}");
            }

        });
    }
}
