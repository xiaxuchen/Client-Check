package com.cxyz.mine.MineView.mineactivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.MinePresenter.presenter.ISettingPresenter;
import com.cxyz.mine.R;
import com.cxyz.mine.MineView.mineactivity.settingactivity.Setting_responseActivity;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
/**
 * Created by Administrator on 2018/9/25.
 */

public class SettingActivity extends BaseActivity <IBasePresenter>implements View.OnClickListener {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    @Override
    public int getContentViewId() {
        return R.layout.activity_setting_layout;
    }

    private Switch stsettingswitch;
    private TitleView tvsetting_title;
    private TextView tvsetting_update, tvsetting_response;
    @Override
    public void initView() {

        tvsetting_update = (TextView) findViewById(R.id.tvsetting_update);
        tvsetting_response= (TextView) findViewById(R.id.tvsetting_response);
        stsettingswitch= (Switch) findViewById(R.id.stsettingswitch);
        tvsetting_title= (TitleView) findViewById(R.id.tvsetting_title);
        tvsetting_title.setTitle("设置");
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        tvsetting_update.setOnClickListener(this);
        tvsetting_response.setOnClickListener(this);
        stsettingswitch.setOnClickListener(this);
        tvsetting_title.setOnClickListener(new TitleView.OnClickListener() {
            @Override
            public void onBackClick() {
                onBackPressed();
            }

            @Override
            public void onEditClick() {

            }

            @Override
            public void onNameClick() {

            }

            @Override
            public void onSetClick() {

            }
        });
    }

    @Override
    protected IBasePresenter createIPresenter() {
        return null;
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvsetting_update:tvsetting_update();break;
            case R.id.tvsetting_response:tvsetting_response();break;
            case  R.id.stsettingswitch: stsettingswitch(stsettingswitch);break;

        }

    }
    //用户反馈activity跳转
    public void tvsetting_response(){
        Intent intent=new Intent(getApplicationContext(),Setting_responseActivity.class);
        startActivity(intent);
    }

    //版本更新监听
    public void   tvsetting_update(){
        ToastUtil.showLong("已是最新版本");
    }

    //更新推送开关switch监听
    public void stsettingswitch(Switch s) {
        if (s.isChecked()) {
            ToastUtil.showShort("已开启");}
        else {
            ToastUtil.showShort("已关闭");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SettingActivity Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
