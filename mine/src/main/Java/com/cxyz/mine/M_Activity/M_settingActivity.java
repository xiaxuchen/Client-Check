package com.cxyz.mine.M_Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.mine.R;
import com.cxyz.mine.M_settingActivity.Setting_responseActivity;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
/**
 * Created by Administrator on 2018/9/25.
 */

public class M_settingActivity extends BaseActivity implements View.OnClickListener {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    @Override
    public int getContentViewId() {
        return R.layout.m_setting_layout;
    }
    Switch m_settingswitch;
    @Override
    public void initView() {
        TextView m_setting_update, m_setting_response;

        m_setting_update = (TextView) findViewById(R.id.m_setting_update);
        m_setting_response= (TextView) findViewById(R.id.m_setting_response);
        m_settingswitch= (Switch) findViewById(R.id.m_settingswitch);

        m_setting_update.setOnClickListener(this);
        m_setting_response.setOnClickListener(this);
        m_settingswitch.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {

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
    public  void mine_settingswitch(){
        if (m_settingswitch.isChecked()){
            ToastUtil.showShort("已开启");
        }


    }
    public void   mine_setting_update(){
        ToastUtil.showLong("已是最新版本");
    }
    public void mine_setting_response(){
        Intent intent=new Intent(getApplicationContext(),Setting_responseActivity.class);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.m_setting_update:mine_setting_update();break;
            case R.id.m_setting_response:mine_setting_response();break;
            case  R.id.m_settingswitch: m_settingswitch();break;

        }

    }

    private void m_settingswitch() {
        if (m_settingswitch.isChecked()) {
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
                .setName("M_settingActivity Page") // TODO: Define a title for the content shown.
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
