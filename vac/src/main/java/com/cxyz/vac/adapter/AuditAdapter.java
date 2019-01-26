package com.cxyz.vac.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cxyz.commons.Adapter.AdapterBase;
import com.cxyz.commons.Adapter.ViewHolder;
import com.cxyz.commons.utils.DateUtil;
import com.cxyz.logiccommons.domain.Audit;
import com.cxyz.logiccommons.manager.UserManager;
import com.cxyz.logiccommons.typevalue.AuditState;
import com.cxyz.logiccommons.typevalue.VacType;
import com.cxyz.vac.R;
import com.cxyz.vac.dto.VacateDto;
import com.cxyz.vac.ipresenter.IAuditPresenter;
import com.joanzapata.iconify.widget.IconTextView;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/12/31.
 */

public class AuditAdapter extends AdapterBase<VacateDto>{

    private IAuditPresenter ipresenter;

    public AuditAdapter(Context mContext,IAuditPresenter iAuditPresenter, List<VacateDto> list, int... mItemLayoutId) {
        super(mContext, list, mItemLayoutId);
        ipresenter = iAuditPresenter;
    }

    @Override
    public void convertView(ViewHolder holder, VacateDto item,int position) {
        holder.setText(R.id.tv_auditer,item.getSponsor().getName()+"提交的请假");
        holder.setText(R.id.tv_start,DateUtil.dateToString(new Date(item.getStart().getTime()),
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
        Button btn_accept = holder.getView(R.id.btn_accept);
        Button btn_reject = holder.getView(R.id.btn_reject);
        if( state == AuditState.WAIT_AUDIT)
        {
            btn_audited.setVisibility(View.INVISIBLE);
            btn_accept.setOnClickListener(view ->
            {
                showDialog(position, AuditState.SUCCESS,item);
            });
            btn_reject.setOnClickListener(view ->
            {
                showDialog(position, AuditState.FAIL,item);
            });
        }else
        {
            if(state == AuditState.FAIL)
                btn_audited.setText("已拒绝");
            else if(state == AuditState.SUCCESS)
            {
                btn_audited.setText("已同意");
            }
            btn_audited.setVisibility(View.VISIBLE);
            btn_accept.setVisibility(View.INVISIBLE);
            btn_reject.setVisibility(View.INVISIBLE);
        }

        super.convertView(holder, item);

    }

    /**
     * 显示审核对话框
     * @param position 位置
     * @param state 状态
     * @param item
     */
    private void showDialog(int position,int state,VacateDto item)
    {
        QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(mContext);
        builder.setTitle("同意请假");
        builder.setPlaceholder("请输入回馈信息(如:请假时间地点)");
        builder.setCancelable(false);
        builder.addAction("取消",(dialog, index) -> dialog.dismiss());
        builder.addAction("确定",(dialog, index) -> {
            String info = builder.getEditText().getText().toString();
            Integer id = null;
            for(Audit audit:item.getAudits())
            {
                if(audit.getChecker().getId().equals(UserManager.getInstance().getUser().getId()))
                {
                    id = audit.getId();
                    break;
                }
            }
            ipresenter.auditVac(position,id,state,info);
            dialog.dismiss();
        });
        QMUIDialog dialog = builder.create();
        dialog.show();
    }

    public void auditSuccess(int position,int state)
    {
        VacateDto item = getItem(position);
        item.setState(state);
        notifyDataSetChanged();
    }
}
