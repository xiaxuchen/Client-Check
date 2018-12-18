package com.cxyz.mine.activity;

import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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

public class ChangePwdACtivity extends BaseActivity<IChangePwdPresenter> implements IChangePwdView {
    private int isVisible = 1;
    private ImageView  iv_changepwd_oldpwd;
    private ImageView iv_changepwd_newpwd;
    private ImageView iv_changepwd_confirm;
    private TitleView tv_changepwd_title;//标题
    private EditText et_changepwd_oldpwd;//旧密码
    private EditText et_changepwd_newpwd;//新密码
    private EditText et_changepwd_confirm;//确认密码
    private Button bt_changepwd_finsh;//确认按钮
    @Override
    public int getContentViewId() {
        return R.layout.activity_changepwd_layout;
    }

    @Override
    public void initView() {
        iv_changepwd_oldpwd=findViewById(R.id.iv_changepwd_oldpwd);
        iv_changepwd_newpwd=findViewById(R.id.iv_changepwd_newpwd);
        iv_changepwd_confirm=findViewById(R.id.iv_changepwd_confirm);
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
       tv_changepwd_title.setOnClickListener(new TitleView.OnClickListener() {
           @Override
           public void onBackClick(View v) {
               onBackPressed();
           }

           @Override
           public void onMoreClick(View v) {

           }
       });
        //原密码显示和隐藏
        iv_changepwd_oldpwd.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isVisible==1){
                    iv_changepwd_oldpwd.setImageResource(R.mipmap.visible);
                    et_changepwd_oldpwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);//显示密码
                    isVisible=0;

                }else if(isVisible==0){
                    iv_changepwd_oldpwd.setImageResource(R.mipmap.invisible);
                    et_changepwd_oldpwd.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);//隐藏密码
                    isVisible=1;
                }
            }
        });
        //新密码显示和隐藏
        iv_changepwd_newpwd.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isVisible==1){
                    iv_changepwd_newpwd.setImageResource(R.mipmap.visible);
                    et_changepwd_newpwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);//显示密码
                    isVisible=0;

                }else if(isVisible==0){
                    iv_changepwd_newpwd.setImageResource(R.mipmap.invisible);
                    et_changepwd_newpwd.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);//隐藏密码
                    isVisible=1;
                }
            }
        });
        //确认密码显示和隐藏
        iv_changepwd_confirm.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isVisible==1){
                    iv_changepwd_confirm.setImageResource(R.mipmap.visible);
                    et_changepwd_confirm.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);//显示密码
                    isVisible=0;

                }else if(isVisible==0){
                    iv_changepwd_confirm.setImageResource(R.mipmap.invisible);
                    et_changepwd_confirm.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);//隐藏密码
                    isVisible=1;
                }
            }
        });
        bt_changepwd_finsh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //1，验证旧密码
                String oldpwd= et_changepwd_oldpwd.getText().toString();
                if(et_changepwd_oldpwd.getText().toString().equals("")){
                    ToastUtil.showShort("密码为空");
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
        ToastUtil.showShort(message);

    }

    @Override
    public void chanegFail(String message) {
        ToastUtil.showShort(message);
    }
    //密码显示和隐藏监听


}

