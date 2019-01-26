package com.cxyz.vac.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cxyz.commons.Adapter.AdapterBase;
import com.cxyz.commons.Adapter.ViewHolder;
import com.cxyz.commons.utils.DateUtil;
import com.cxyz.logiccommons.typevalue.AuditState;
import com.cxyz.logiccommons.typevalue.VacType;
import com.cxyz.vac.R;
import com.cxyz.vac.dto.VacateDto;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/12/31.
 */

public class VacateAdapter extends AdapterBase<VacateDto> {

    public VacateAdapter(Context mContext, List<VacateDto> list, int... mItemLayoutId) {
        super(mContext, list, mItemLayoutId);
    }

    @Override
    public void convertView(ViewHolder holder, VacateDto item) {
        super.convertView(holder, item);
        holder.setText(R.id.tv_start, DateUtil.dateToString(new Date(item.getStart().getTime()),
                DateUtil.DatePattern.ONLY_MINUTE));
        holder.setText(R.id.tv_end,DateUtil.dateToString(new Date(item.getEnd().getTime()),
                DateUtil.DatePattern.ONLY_MINUTE));
        holder.setText(R.id.tv_sponsor_time,DateUtil.dateToString(new Date(item.getSponsorTime().getTime()),
                DateUtil.DatePattern.ONLY_MINUTE));
        holder.setText(R.id.tv_len,item.getLen()+"天");
        holder.setText(R.id.tv_vac_type,item.getType()== VacType.VAC_THING?"事假":"病假");

        IconTextView tv_reason_hint = holder.getView(R.id.tv_reason_hint);
        TextView tv_reason = holder.getView(R.id.tv_reason);
        tv_reason.setText(item.getDes());
        tv_reason_hint.setOnClickListener(view ->{
            Integer visible = View.VISIBLE;
            if(tv_reason.getVisibility() == View.VISIBLE)
            {
                visible = View.GONE;
                tv_reason_hint.setText("请假事由 {vac_down}");
            }else {
                tv_reason_hint.setText("请假事由 {vac_up}");
            }
            tv_reason.setVisibility(visible);
        });
        int state = item.getState();
        Button btn_audited = holder.getView(R.id.btn_audited);
        if( state == AuditState.WAIT_AUDIT)
        {
            btn_audited.setText("待审核");
        }else if(state == AuditState.FAIL)
        {
            btn_audited.setText("已拒绝");
        }
        else if(state == AuditState.SUCCESS)
        {
            btn_audited.setText("已同意");
        }
        ListView lv_audits = holder.getView(R.id.lv_audits);
        if(item.getAudits().size() == 0)
            holder.setVisible(R.id.tv_audits_hint,View.GONE);
        else
            holder.setVisible(R.id.tv_audits_hint,View.VISIBLE);
        lv_audits.setAdapter(new AuditedAdapter(mContext,item.getAudits(),R.layout.item_vacate_audit_layout));
        holder.setOnClickListener(R.id.tv_audits_hint,view ->{
            if(lv_audits.getVisibility() == View.VISIBLE)
            {
                lv_audits.setVisibility(View.GONE);
                holder.setText(R.id.tv_audits_hint,"审核情况 {vac_down}");
            }else {
                lv_audits.setVisibility(View.VISIBLE);
                holder.setText(R.id.tv_audits_hint,"审核情况 {vac_up}");
            }
        } );

    }
}
