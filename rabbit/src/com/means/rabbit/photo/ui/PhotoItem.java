package com.means.rabbit.photo.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.means.rabbit.R;
import com.means.rabbit.RabbitValueFix;
import com.means.rabbit.photo.model.PhotoModel;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @author Aizaz AZ
 */

public class PhotoItem extends LinearLayout implements OnCheckedChangeListener, OnLongClickListener, OnClickListener {

    private ImageView ivPhoto;

    private CheckBox cbPhoto;

    private onPhotoItemCheckedListener listener;

    private PhotoModel photo;

    private boolean isCheckAll;

    private onItemClickListener l;

    private int position;

    private PhotoItem(Context context) {
        super(context);
    }

    public PhotoItem(Context context, onPhotoItemCheckedListener listener) {
        this(context);
        LayoutInflater.from(context).inflate(R.layout.layout_photoitem, this, true);
        this.listener = listener;
        // setOnLongClickListener(this);
        setOnClickListener(this);
        ivPhoto = (ImageView) findViewById(R.id.iv_photo_lpsi);
        cbPhoto = (CheckBox) findViewById(R.id.cb_photo_lpsi);
        cbPhoto.setOnCheckedChangeListener(this);
//        ivPhoto.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cbPhoto.setChecked(!cbPhoto.isChecked());
//            }
//        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // if (!isCheckAll) {
        // listener.onCheckedChanged(photo, buttonView, isChecked,ivPhoto);
        // }
        if (isChecked) {
            setDrawingable();
            ivPhoto.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        } else {
            ivPhoto.clearColorFilter();
        }
        photo.setChecked(isChecked);
        if (!isCheckAll) {
            listener.onCheckedChanged(photo, buttonView, isChecked, ivPhoto);
        }
    }

    public void setImageDrawable(final PhotoModel photo) {
        this.photo = photo;
        // You may need this setting form some custom ROM(s)
        /*
         * new Handler().postDelayed(new Runnable() {
         * 
         * @Override public void run() { ImageLoader.getInstance().displayImage(
         * "file://" + photo.getOriginalPath(), ivPhoto); } }, new
         * Random().nextInt(10));
         */

        ImageLoader.getInstance().displayImage("file://" + photo.getOriginalPath(), ivPhoto,
        		RabbitValueFix.optionsDefault);
    }

    private void setDrawingable() {
        ivPhoto.setDrawingCacheEnabled(true);
        ivPhoto.buildDrawingCache();
    }

    @Override
    public void setSelected(boolean selected) {
        if (photo == null) {
            return;
        }
        isCheckAll = true;
        cbPhoto.setChecked(selected);
        isCheckAll = false;
    }

    public void setOnClickListener(onItemClickListener l, int position) {
        this.l = l;
        this.position = position;
    }

    @Override
    public void onClick(View v) {
        if (l != null)
            l.onItemClick(position);
    }

    public static interface onPhotoItemCheckedListener {
        public void onCheckedChanged(PhotoModel photoModel, CompoundButton buttonView, boolean isChecked,
                                     ImageView imgView);
    }

    public interface onItemClickListener {
        public void onItemClick(int position);
    }

    @Override
    public boolean onLongClick(View v) {
        if (l != null)
            l.onItemClick(position);
        return true;
    }

}
