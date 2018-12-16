package com.cxyz.mine.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.IPresenter.presenter.IChangePwdPresenter;
import com.cxyz.mine.IPresenter.presenter.IChangePwdPresenterImpl;
import com.cxyz.mine.R;
import com.cxyz.mine.iview.IChangePwdView;


/**
 * Created by ${喻济生} on 2018/11/12.
 */

public class ChangePwdACtivity extends BaseActivity <IChangePwdPresenter>implements IChangePwdView{

    private TitleView tv_changepwd_title;
    private EditText et_changepwd_oldpwd;//旧密码
    private EditText et_changepwd_newpwd;//新密码
    private EditText et_changepwd_confirm;//新2密码
    private Button bt_changepwd_finsh;//确认按钮
    @Override
    public int getContentViewId() {
        return R.layout.activity_changepwd_layout;
    }

    @Override
    public void initView() {
        tv_changepwd_title=findViewById(R.id.tv_changepwd_title);
        et_changepwd_oldpwd=findViewById(R.id.et_changepwd_oldpwd);
        et_changepwd_newpwd=findViewById(R.id.et_changepwd_newpwd);
        et_changepwd_confirm=findViewById(R.id.et_changepwd_confirm);
        bt_changepwd_finsh=findViewById(R.id.bt_changepwd_finsh);
        tv_changepwd_title.setTitle("修改密码");
       

    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        tv_changepwd_title.setBackClickListener(v -> onBackPressed());
        bt_changepwd_finsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1，验证旧密码
                String oldpwd= et_changepwd_oldpwd.getText().toString();
                iPresenter.testpwd(oldpwd);
                    //2. 确认新密码
                    String newpwd = et_changepwd_newpwd.getText().toString();
                    String confirm=et_changepwd_confirm.getText().toString();
                    if (newpwd.equals(confirm)){
                        iPresenter.changepwd(newpwd);
                    }else{
                        ToastUtil.showShort("输入的密码不一致");
                    }


            }
        });

    }

    @Override
    protected IChangePwdPresenter createIPresenter() {
        return new IChangePwdPresenterImpl();
    }

    @Override
    public void changeSuccess(String message) {

    }

    @Override
    public void chanegFail(String message) {

    }
}
