package com.means.rabbit.photo.ui;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;

import com.means.rabbit.R;
import com.means.rabbit.photo.model.PhotoModel;
import com.means.rabbit.photo.ui.PhotoItem.onItemClickListener;
import com.means.rabbit.photo.ui.PhotoItem.onPhotoItemCheckedListener;

public class PhotoSelectorAdapter extends MBaseAdapter<PhotoModel> {

    private int itemWidth;

    private int horizentalNum = 3;

    private onPhotoItemCheckedListener listener;

    private LayoutParams itemLayoutParams;

    private onItemClickListener mCallback;

    private OnClickListener cameraListener;

    private PhotoSelectorAdapter(Context context, ArrayList<PhotoModel> models) {
        super(context, models);
    }

    public PhotoSelectorAdapter(Context context, ArrayList<PhotoModel> models, int screenWidth,
                                onPhotoItemCheckedListener listener, onItemClickListener mCallback, OnClickListener cameraListener) {
        this(context, models);
        setItemWidth(screenWidth);
        this.listener = listener;
        this.mCallback = mCallback;
        this.cameraListener = cameraListener;
    }

    public void setItemWidth(int screenWidth) {
        int horizentalSpace = context.getResources().getDimensionPixelSize(R.dimen.sticky_item_horizontalSpacing);
        this.itemWidth = (screenWidth - (horizentalSpace * (horizentalNum - 1))) / horizentalNum;
        this.itemLayoutParams = new LayoutParams(itemWidth, itemWidth);
    }

    public void addData(PhotoModel model) {
        models.add(0, model);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PhotoItem item = null;
        if (convertView == null || !(convertView instanceof PhotoItem)) {
            item = new PhotoItem(context, listener);
            item.setLayoutParams(itemLayoutParams);
            convertView = item;
        } else {
            item = (PhotoItem) convertView;
        }
        item.setImageDrawable(models.get(position));
        item.setSelected(models.get(position).isChecked());
        item.setOnClickListener(mCallback, position);
        return convertView;
    }
}
