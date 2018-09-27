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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cdqf.plantgps.R;
import com.cdqf.plantgps_image.PersonalDilogFragment;
import com.cdqf.plantgps_image.ShelvesImageFind;
import com.cdqf.plantgps_map.AddrStrFind;
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
 * 查看
 */
public class ImageActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = MainActivity.class.getSimpleName();

    private EventBus eventBus = EventBus.getDefault();

    private Context context = null;

    private State state = State.getState();

    private ImageLoader imageLoader = ImageLoader.getInstance();

    private TextView tvGpsView = null;

    private TextView tvGpsPull = null;

    private ListView listView = null;

    private ImageAdapter imageAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //加载布局
        setContentView(R.layout.activity_image);

        initAgo();

        initView();

        initAdapter();

        initListener();

        initBack();

    }

    private void initAgo() {
        context = this;
        imageLoader = state.getImageLoader(context);
    }

    private void initView() {
        tvGpsView = this.findViewById(R.id.tv_gps_view);
        tvGpsPull = this.findViewById(R.id.tv_gps_pull);
        listView = this.findViewById(R.id.tv_gps_list);
    }

    private void initAdapter() {
        imageAdapter = new ImageAdapter(context);
        listView.setAdapter(imageAdapter);
    }

    private void initListener() {
        tvGpsView.setOnClickListener(this);
        tvGpsPull.setOnClickListener(this);
    }

    private void initBack() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_gps_view:
                finish();
                break;
            case R.id.tv_gps_pull:
                Toast.makeText(context, "刷新成功", Toast.LENGTH_SHORT).show();
                imageAdapter.notifyDataSetChanged();
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

    }
}
