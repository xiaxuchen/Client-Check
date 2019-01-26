package com.cxyz.vac.activity;

import android.widget.ListView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.vac.R;
import com.cxyz.vac.adapter.AuditAdapter;
import com.cxyz.vac.dto.VacateDto;
import com.cxyz.vac.icon.IconfontModule;
import com.cxyz.vac.ipresenter.IAuditPresenter;
import com.cxyz.vac.ipresenter.impl.IAuditPresenterImpl;
import com.cxyz.vac.iview.IAuditView;
import com.joanzapata.iconify.Iconify;

import java.util.List;

/**
 * Created by Administrator on 2018/12/29.
 */

@Route(path="/vac/AuditActivity")
public class AuditActivity extends BaseActivity<IAuditPresenter> implements IAuditView {

    private TitleView tv_title;

    private ListView lv_audits;

    private AuditAdapter adapter;

    @Override
    public int getContentViewId() {
        Iconify.with(new IconfontModule());
        return R.layout.activity_audit_layout;
    }

    @Override
    public void initView() {
        tv_title = findViewById(R.id.tv_title);
        lv_audits = findViewById(R.id.lv_audits);

        lv_audits.setEmptyView(findViewById(R.id.tv_empty));
    }

    @Override
    public void initData() {
    }

    @Override
    public void setEvent() {
        tv_title.setBackClickListener(v -> onBackPressed());
    }

    @Override
    protected void afterInit() {
        super.afterInit();
        iPresenter.getVacates();
    }

    @Override
    protected IAuditPresenter createIPresenter() {
        return new IAuditPresenterImpl();
    }

    @Override
    public void getVacatesSuccess(List<VacateDto> vacateDtos) {
        adapter = new AuditAdapter(getActivity(), iPresenter, vacateDtos, R.layout.item_audit_layout);
        lv_audits.setAdapter(adapter);
    }

    @Override
    public void getVacatesError(String error) {
        ToastUtil.showShort(error);
    }

    @Override
    public void auditSuccess(int position,int state) {
        adapter.auditSuccess(position,state);
    }

    @Override
    public void auditFail(String error) {
        ToastUtil.showShort(error);
    }
}
