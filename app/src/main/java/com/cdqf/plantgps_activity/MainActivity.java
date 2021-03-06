package com.cdqf.plantgps_activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cdqf.plantgps.R;
import com.cdqf.plantgps_image.PersonalDilogFragment;
import com.cdqf.plantgps_image.ShelvesImageFind;
import com.cdqf.plantgps_map.AddrStrFind;
import com.cdqf.plantgps_map.BaiduLBS;
import com.cdqf.plantgps_state.BaseActivity;
import com.cdqf.plantgps_state.Preferences;
import com.cdqf.plantgps_state.State;
import com.cdqf.plantgps_txmap.TXMapLoad;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xw.repo.XEditText;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by liu on 2017/10/31.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = MainActivity.class.getSimpleName();

    private EventBus eventBus = EventBus.getDefault();

    private Context context = null;

    private State state = State.getState();

    private ImageLoader imageLoader = ImageLoader.getInstance();

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

    //刷新
    private TextView tvGpsPull = null;

    private ImageView ivGpsManual = null;

    private String imageFile = "";

    private Gson gson = new Gson();

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
        imageLoader = state.getImageLoader(context);
        String error = TXMapLoad.init(MainActivity.this);
        Log.e(TAG, "---error---" + error);
        if (Preferences.getGPS(context) != null) {
            String gps = Preferences.getGPS(context);
            List<Gps> gpsList = gson.fromJson(gps, new TypeToken<List<Gps>>() {
            }.getType());
            state.setGpsList(gpsList);
            Log.e(TAG, "---gps---" + gps);
        } else {
            Log.e(TAG, "---gps---" + Preferences.getGPS(context));
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

        //图片
        ivGpsManual = this.findViewById(R.id.iv_gps_manual);

        //刷新
        tvGpsPull = this.findViewById(R.id.tv_gps_pull);


    }

    private void initAdapter() {

    }

    private void initListener() {
        tvGpsView.setOnClickListener(this);
        tvGpsSave.setOnClickListener(this);
        ivGpsManual.setOnClickListener(this);
        tvGpsPull.setOnClickListener(this);
    }

    private void initBack() {
//        BaiduLBS.init(MainActivity.this);

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
                Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_gps_pull:
                tvGpsLongitude.setText("");
                tvGpsLatitude.setText("");
                tvGpsAddress.setText("");
                xetGpsManual.setText("");
                ivGpsManual.setImageResource(R.mipmap.wodedingdan);
                TXMapLoad.remove();
                TXMapLoad.init(MainActivity.this);
                break;
            case R.id.tv_gps_save:
                //保存
                //经度
                String longitude = tvGpsLongitude.getText().toString();
                //纬度
                String latitude = tvGpsLatitude.getText().toString();
                //地址
                String address = tvGpsAddress.getText().toString();
                //景名
                String name = xetGpsManual.getText().toString();
                //图片地址
                if (longitude.length() != 0 && latitude.length() != 0 && address.length() != 0 && name.length() != 0 && imageFile.length() != 0) {
                    Gps gps = new Gps(longitude, latitude, address, name, imageFile);
                    state.getGpsList().add(gps);
                    String gpsList = gson.toJson(state.getGpsList(), new TypeToken<List<Gps>>() {
                    }.getType());
                    Preferences.setGps(context, gpsList);
                    Log.e(TAG, "---gps采集---" + gpsList);
                    Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
                    tvGpsLongitude.setText("");
                    tvGpsLatitude.setText("");
                    tvGpsAddress.setText("");
                    xetGpsManual.setText("");
                    ivGpsManual.setImageResource(R.mipmap.wodedingdan);
                    TXMapLoad.remove();
                    TXMapLoad.init(MainActivity.this);
                } else {
                    Toast.makeText(context, "条件不满足", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_gps_manual:
                PersonalDilogFragment personalDilogFragment = new PersonalDilogFragment();
                personalDilogFragment.show(getSupportFragmentManager(), "选择");
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
//        if (a.map.getCity() != null) {
//            Log.e(TAG, "---" + a.map.getLongitude() + "---" + a.map.getLatitude());
//            tvGpsLongitude.setText(a.map.getLongitude() + "");
//            tvGpsLatitude.setText(a.map.getLatitude() + "");
//            tvGpsAddress.setText(a.map.getLocation());
//        }
    }

    public void onEventMainThread(com.cdqf.plantgps_txmap.LoadFind a) {
        Log.e(TAG, "---经度---" + a.longitude + "---纬度---" + a.latitude);
        tvGpsLongitude.setText(a.longitude + "");
        tvGpsLatitude.setText(a.latitude + "");
        tvGpsAddress.setText(a.load);
    }

    public void onEventMainThread(ShelvesImageFind s) {
        imageFile = s.file;
        imageLoader.displayImage("file://" + imageFile, ivGpsManual, state.getImageLoaderOptions(R.mipmap.wodedingdan, R.mipmap.wodedingdan, R.mipmap.wodedingdan
        ));
        Log.e(TAG, "---图片文件---" + s.file);
    }
}
