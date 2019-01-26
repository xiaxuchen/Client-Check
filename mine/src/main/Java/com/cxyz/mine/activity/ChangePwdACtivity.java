package com.cxyz.mine.activity;

import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.manager.ActivityStackManager;
import com.cxyz.commons.utils.SpUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.IPresenter.IChangePwdPresenter;
import com.cxyz.mine.IPresenter.impl.IChangePwdPresenterImpl;
import com.cxyz.mine.R;
import com.cxyz.mine.iview.IChangePwdView;


/**
 * Created by ${喻济生} on 2018/11/12.
 */

public class ChangePwdACtivity extends BaseActivity <IChangePwdPresenter>implements IChangePwdView{

    private TitleView tv_title;
    private EditText et_origin_pwd;//旧密码
    private EditText et_new_pwd;//新密码
    private EditText et_confirm_pwd;//新2密码
    private Button btn_alter;//确认按钮
    @Override
    public int getContentViewId() {
        return R.layout.activity_changepwd_layout;
    }

    @Override
    public void initView() {
        tv_title = findViewById(R.id.tv_title);
        et_origin_pwd = findViewById(R.id.et_origin_pwd);
        et_new_pwd = findViewById(R.id.et_new_pwd);
        et_confirm_pwd = findViewById(R.id.et_new_pwd);
        btn_alter = findViewById(R.id.btn_alter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        tv_title.setBackClickListener(v -> onBackPressed());
        btn_alter.setOnClickListener(v -> {
            //1，验证旧密码
            String originPwd= et_origin_pwd.getText().toString();
            String newPwd = null;
            if(!originPwd.isEmpty()||originPwd.equals(SpUtil.getInstance().getString("pwd","")))
            {
                //2. 确认新密码
                newPwd = et_new_pwd.getText().toString();
                String confirm=et_confirm_pwd.getText().toString();
                if (newPwd.equals(confirm))
                {
                    iPresenter.alterPwd(originPwd,newPwd);
                }else
                {
                    ToastUtil.showShort("输入的新密码不一致");
                }
            }else
            {
                ToastUtil.showShort("原密码错误");
            }

        });

    }

    @Override
    protected IChangePwdPresenter createIPresenter() {
        return new IChangePwdPresenterImpl();
    }

    @Override
    public void changeSuccess(String message) {
        ToastUtil.showShort(message);
        ActivityStackManager.getActivityStackManager().popAllActivity();
        ARouter.getInstance().build("/main/LoginActivity").navigation();
    }

    @Override
    public void chanegFail(String message) {
        ToastUtil.showShort(message);
    }
}
