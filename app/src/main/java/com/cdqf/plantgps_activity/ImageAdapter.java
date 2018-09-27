package com.cdqf.plantgps_activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdqf.plantgps.R;
import com.cdqf.plantgps_state.State;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ImageAdapter extends BaseAdapter {
    private String TAG = ImageAdapter.class.getSimpleName();

    private Context context = null;

    private State state = State.getState();

    private ImageLoader imageLoader = ImageLoader.getInstance();

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return state.getGpsList().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_image, null);
            viewHolder = new ViewHolder();
            viewHolder.ivItemGpsManual = convertView.findViewById(R.id.iv_item_gps_manual);
            viewHolder.tvItemGpsLongitude = convertView.findViewById(R.id.tv_item_gps_longitude);
            viewHolder.tvItemGpsLatitude = convertView.findViewById(R.id.tv_item_gps_latitude);
            viewHolder.tvItemGpsAddress = convertView.findViewById(R.id.tv_item_gps_address);
            viewHolder.tvItemGpsManual = convertView.findViewById(R.id.tv_item_gps_manual);
        } else {

        }
        imageLoader.displayImage("file://" + state.getGpsList().get(position).getImageFile(), viewHolder.ivItemGpsManual, state.getImageLoaderOptions(R.mipmap.not_loaded, R.mipmap.not_loaded, R.mipmap.not_loaded));
        viewHolder.tvItemGpsLongitude.setText(state.getGpsList().get(position).getLongitude());
        viewHolder.tvItemGpsLatitude.setText(state.getGpsList().get(position).getLatitude());
        viewHolder.tvItemGpsAddress.setText(state.getGpsList().get(position).getAddress());
        viewHolder.tvItemGpsManual.setText(state.getGpsList().get(position).getName());
        return convertView;
    }

    class ViewHolder {
        public ImageView ivItemGpsManual = null;

        public TextView tvItemGpsLongitude = null;

        public TextView tvItemGpsLatitude = null;

        public TextView tvItemGpsAddress = null;

        public TextView tvItemGpsManual = null;
    }

}
