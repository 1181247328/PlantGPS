package com.cdqf.plantgps_activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.cdqf.plantgps.R;
import com.cdqf.plantgps_map.AddrStrFind;
import com.cdqf.plantgps_map.BaiduLBS;
import com.cdqf.plantgps_state.BaseActivity;
import com.xw.repo.XEditText;

import de.greenrobot.event.EventBus;

/**
 * Created by liu on 2017/10/31.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = MainActivity.class.getSimpleName();

    private EventBus eventBus = EventBus.getDefault();

    private Context context = null;

    //查看
    private TextView tvGpsView = null;

    //经度
    private TextView tvGpsLongitude = null;

    //纬度
    private TextView tvGpsLatitude = null;

    //地址
    private TextView tvGpsAddress = null;

    //手动
    private XEditText xetGpsManual = null;

    //保存
    private TextView tvGpsSave = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //加载布局
        setContentView(R.layout.activity_main);

        initAgo();

        initView();

        initAdapter();

        initListener();

        initBack();

    }

    private void initAgo() {
        context = this;
        permission(this);
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    private void initView() {
        //查看
        tvGpsView = (TextView) this.findViewById(R.id.tv_gps_view);

        //经度
        tvGpsLongitude = (TextView) this.findViewById(R.id.tv_gps_longitude);

        //纬度
        tvGpsLatitude = (TextView) this.findViewById(R.id.tv_gps_latitude);

        //地址
        tvGpsAddress = (TextView) this.findViewById(R.id.tv_gps_address);

        //手动
        xetGpsManual = (XEditText) this.findViewById(R.id.xet_gps_manual);

        //保存
        tvGpsSave = (TextView) this.findViewById(R.id.tv_gps_save);
    }

    private void initAdapter() {

    }

    private void initListener() {
        tvGpsView.setOnClickListener(this);
        tvGpsSave.setOnClickListener(this);
    }

    private void initBack() {
        BaiduLBS.init(MainActivity.this);
    }

    /**
     * 权限
     */
    public void permission(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    || activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    || activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED
                    || activity.checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED
                    || activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED
                    || activity.checkSelfPermission(Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.CALL_PHONE,},
                        0
                );
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_gps_view:
                break;
            case R.id.tv_gps_save:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "---启动---");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "---恢复---");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "---暂停---");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "---停止---");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "---重启---");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "---销毁---");
        eventBus.unregister(this);
    }

    public void onEventMainThread(AddrStrFind a) {
        if (a.map.getCity() != null) {
            Log.e(TAG, "---" + a.map.getLongitude() + "---" + a.map.getLatitude());
            tvGpsLongitude.setText(a.map.getLongitude() + "");
            tvGpsLatitude.setText(a.map.getLatitude() + "");
            tvGpsAddress.setText(a.map.getLocation());
        }
    }
}
