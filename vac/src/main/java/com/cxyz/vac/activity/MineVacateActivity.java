package com.cxyz.vac.activity;

import android.widget.ListView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.vac.R;
import com.cxyz.vac.adapter.VacateAdapter;
import com.cxyz.vac.dto.VacateDto;
import com.cxyz.vac.icon.IconfontModule;
import com.cxyz.vac.ipresenter.IMineVacatePresenter;
import com.cxyz.vac.ipresenter.impl.IMineVacatePresenterImpl;
import com.cxyz.vac.iview.IMineVacateView;
import com.joanzapata.iconify.Iconify;

import java.util.List;

/**
 * Created by Administrator on 2018/12/31.
 */

@Route(path = "/vac/MineVacateActivity")
public class MineVacateActivity extends BaseActivity<IMineVacatePresenter> implements IMineVacateView {

    private TitleView tv_title;

    private ListView lv_vacates;

    private VacateAdapter adapter;

    @Override
    public int getContentViewId() {
        Iconify.with(new IconfontModule());
        return R.layout.activity_mine_vacate_layout;
    }

    @Override
    public void initView() {
        tv_title = findViewById(R.id.tv_title);
        lv_vacates = findViewById(R.id.lv_vacates);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        tv_title.setBackClickListener(v -> onBackPressed());
        lv_vacates.setEmptyView(findViewById(R.id.tv_empty));
    }

    @Override
    protected void afterInit() {
        super.afterInit();
        iPresenter.getVacates();
    }

    @Override
    protected IMineVacatePresenter createIPresenter() {
        return new IMineVacatePresenterImpl();
    }

    @Override
    public void showVacates(List<VacateDto> vacateDtos) {
        if(adapter == null)
            adapter = new VacateAdapter(getActivity(),vacateDtos,R.layout.item_vacate_layout);
        lv_vacates.setAdapter(adapter);
    }

    @Override
    public void getVacatesFail(String error) {
        ToastUtil.showShort(error);
    }
}
