package com.means.rabbit.activity.main;

/**
 * @author Aizaz AZ
 */

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.duohuo.dhroid.util.PhotoUtil;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.means.rabbit.R;
import com.means.rabbit.api.Constant;
import com.means.rabbit.photo.domain.PhotoSelectorDomain;
import com.means.rabbit.photo.model.AlbumModel;
import com.means.rabbit.photo.model.PhotoModel;
import com.means.rabbit.photo.ui.AlbumAdapter;
import com.means.rabbit.photo.ui.PhotoItem.onItemClickListener;
import com.means.rabbit.photo.ui.PhotoItem.onPhotoItemCheckedListener;
import com.means.rabbit.photo.ui.PhotoSelectorAdapter;
import com.means.rabbit.utils.AnimationUtil;
import com.means.rabbit.utils.CommonUtils;

/**
 * @author Aizaz AZ
 */
public class PhotoSelectorActivity extends Activity implements onItemClickListener, onPhotoItemCheckedListener,
        OnItemClickListener, OnClickListener {

    public static final int SINGLE_IMAGE = 1;

    public static final String KEY_MAX = "key_max";

    private int MAX_IMAGE = 9;

    public static final int REQUEST_PHOTO = 0;

    private static final int REQUEST_CAMERA = 1;

    public static String RECCENT_PHOTO = null;

    private GridView gvPhotos;

    private ListView lvAblum;

    private Button btnOk;

    private TextView tvAlbum;

    private PhotoSelectorDomain photoSelectorDomain;

    private PhotoSelectorAdapter photoAdapter;

    private AlbumAdapter albumAdapter;

    private RelativeLayout layoutAlbum;

    private ArrayList<PhotoModel> selected;

    private TextView tvNumber;

    private ImageView imgCamera;

    // 图片缓存根目录
    private File mCacheDir;
    private String mPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RECCENT_PHOTO = getResources().getString(R.string.recent_photos);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        setContentView(R.layout.activity_photoselector);

        if (getIntent().getExtras() != null) {
            MAX_IMAGE = getIntent().getIntExtra(KEY_MAX, 10);
        }

        initImageLoader();

        mCacheDir = new File(getExternalCacheDir(), "CarPlay");
        mCacheDir.mkdirs();

        photoSelectorDomain = new PhotoSelectorDomain(getApplicationContext());

        selected = new ArrayList<PhotoModel>();

        gvPhotos = (GridView) findViewById(R.id.gv_photos_ar);
        lvAblum = (ListView) findViewById(R.id.lv_ablum_ar);
        btnOk = (Button) findViewById(R.id.btn_right_lh);
        if ("".equals(getIntent().getStringExtra("type"))) {
            btnOk.setText("发送");
        }
        tvAlbum = (TextView) findViewById(R.id.tv_album_ar);
        layoutAlbum = (RelativeLayout) findViewById(R.id.layout_album_ar);
        tvNumber = (TextView) findViewById(R.id.tv_number);
//        tvNumber.setText("(0/" + MAX_IMAGE + ")");
        tvNumber.setText("0");
        imgCamera = (ImageView) findViewById(R.id.camera);

        btnOk.setOnClickListener(this);
        tvAlbum.setOnClickListener(this);
        imgCamera.setOnClickListener(this);

        photoAdapter = new PhotoSelectorAdapter(getApplicationContext(), new ArrayList<PhotoModel>(),
                CommonUtils.getWidthPixels(this), this, this, this);
        gvPhotos.setAdapter(photoAdapter);

        gvPhotos.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        albumAdapter = new AlbumAdapter(getApplicationContext(), new ArrayList<AlbumModel>());
        lvAblum.setAdapter(albumAdapter);
        lvAblum.setOnItemClickListener(this);

        findViewById(R.id.bv_back_lh).setOnClickListener(this); // 返回

        photoSelectorDomain.getReccent(reccentListener); // 更新最近照片
        photoSelectorDomain.updateAlbum(albumListener); // 跟新相册信息
    }

    private void initImageLoader() {
        // DisplayImageOptions imageOptions = new DisplayImageOptions.Builder()
        // .showImageOnLoading(R.drawable.ic_picture_loading)
        // .showImageOnFail(R.drawable.ic_picture_loadfailed)
        // .cacheInMemory(true).cacheOnDisk(true)
        // .resetViewBeforeLoading(true).considerExifParams(false)
        // .bitmapConfig(Bitmap.Config.RGB_565).build();
        //
        // ImageLoaderConfiguration config = new
        // ImageLoaderConfiguration.Builder(
        // this)
        // .memoryCacheExtraOptions(400, 400)
        // // default = device screen dimensions
        // .diskCacheExtraOptions(400, 400, null)
        // .threadPoolSize(5)
        // // default Thread.NORM_PRIORITY - 1
        // .threadPriority(Thread.NORM_PRIORITY)
        // // default FIFO
        // .tasksProcessingOrder(QueueProcessingType.LIFO)
        // // default
        // .denyCacheImageMultipleSizesInMemory()
        // .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
        // .memoryCacheSize(2 * 1024 * 1024)
        // .memoryCacheSizePercentage(13)
        // // default
        // .diskCache(
        // new UnlimitedDiscCache(StorageUtils.getCacheDirectory(
        // this, true)))
        // // default
        // .diskCacheSize(50 * 1024 * 1024).diskCacheFileCount(100)
        // .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
        // // default
        // .imageDownloader(new BaseImageDownloader(this))
        // // default
        // .imageDecoder(new BaseImageDecoder(false))
        // // default
        // .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
        // // default
        // .defaultDisplayImageOptions(imageOptions).build();
        //
        // ImageLoader.getInstance().init(config);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_right_lh)
            ok(); // 选完照片
        else if (v.getId() == R.id.tv_album_ar)
            album();
        else if (v.getId() == R.id.tv_camera_vc)
            catchPicture();
        else if (v.getId() == R.id.bv_back_lh)
            finish();
        else if (v.getId() == R.id.camera)
            catchPicture();
    }

    /**
     * 拍照
     */
    private void catchPicture() {
//        CommonUtils.launchActivityForResult(this, new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_CAMERA);
        mPhotoPath = new File(mCacheDir, System.currentTimeMillis() + ".jpg").getAbsolutePath();
        final File tempFile = new File(mPhotoPath);
        Intent getImageByCamera = new Intent(
                "android.media.action.IMAGE_CAPTURE");
        getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(tempFile));
        startActivityForResult(getImageByCamera,
                Constant.TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
//            PhotoModel photoModel = new PhotoModel(CommonUtils.query(getApplicationContext(), data.getData()));
//            // selected.clear();
//            // //--keep all
//            // selected photos
//            // tvNumber.setText("(0)");
//            // //--keep all
//            // selected photos
//            // ///////////////////////////////////////////////////////////////////////////////////////////
//            if (selected.size() >= MAX_IMAGE) {
//                Toast.makeText(this, String.format(getString(R.string.max_img_limit_reached), MAX_IMAGE),
//                        Toast.LENGTH_SHORT).show();
//                photoModel.setChecked(false);
//                photoAdapter.notifyDataSetChanged();
//            } else {
//                if (!selected.contains(photoModel)) {
//                    selected.add(photoModel);
//                }
//            }
//            ok();
//        }

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.TAKE_PHOTO:
                    Bitmap btp1 = PhotoUtil.getLocalImage(new File(mPhotoPath));
                    int degree = PhotoUtil.getBitmapDegree(mPhotoPath);
                    String newPath = PhotoUtil.saveLocalImage(btp1, degree, PhotoSelectorActivity.this);
                    btp1.recycle();
                    PhotoModel model = new PhotoModel();
                    model.setChecked(false);
                    model.setOriginalPath(newPath);
                    photoAdapter.addData(model);
                    break;
            }
        }
    }

    /**
     * 完成
     */
    private void ok() {
        if (selected.isEmpty()) {
            setResult(RESULT_CANCELED);
        } else {
            Intent data = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("photos", selected);
            data.putExtras(bundle);
            setResult(RESULT_OK, data);
        }
        finish();
    }

    /**
     * 预览照片
     */
    private void priview() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("photos", selected);
        CommonUtils.launchActivity(this, PhotoPreviewActivity.class, bundle);
    }

    /**
     * 预览照片
     */
    private void priview(ArrayList<PhotoModel> photos, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("photos", photos);
        bundle.putInt("position", position);
        CommonUtils.launchActivity(this, PhotoPreviewActivity.class, bundle);
    }

    private void album() {
        if (layoutAlbum.getVisibility() == View.GONE) {
            popAlbum();
        } else {
            hideAlbum();
        }
    }

    /**
     * 弹出相册列表
     */
    private void popAlbum() {
        layoutAlbum.setVisibility(View.VISIBLE);
        new AnimationUtil(getApplicationContext(), R.anim.translate_up_current).setLinearInterpolator().startAnimation(
                layoutAlbum);
    }

    /**
     * 隐藏相册列表
     */
    private void hideAlbum() {
        new AnimationUtil(getApplicationContext(), R.anim.translate_down).setLinearInterpolator().startAnimation(
                layoutAlbum);
        layoutAlbum.setVisibility(View.GONE);
    }

    /**
     * 清空选中的图片
     */
    private void reset() {
        selected.clear();
//        tvNumber.setText("(0/" + MAX_IMAGE + ")");
        tvNumber.setText("0");
    }

    @Override
    /** 点击查看照片 */
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        if (tvAlbum.getText().toString().equals(RECCENT_PHOTO))
            // bundle.putInt("position", position - 1);
            bundle.putInt("position", position);
        else
            bundle.putInt("position", position);
        bundle.putString("album", tvAlbum.getText().toString());
        if( tvAlbum.getText().toString().equals("选择照片")) {
            bundle.putString("album","最近照片");
        }
        CommonUtils.launchActivity(this, PhotoPreviewActivity.class, bundle);

//        PhotoModel model = (PhotoModel) photoAdapter.getItem(position);
//        model.setChecked(!model.isChecked());
//        photoAdapter.notifyDataSetChanged();
    }

    @Override
    /** 照片选中状态改变之后 */
    public void onCheckedChanged(PhotoModel photoModel, CompoundButton buttonView, boolean isChecked, ImageView imgView) {
        if (isChecked) {
            if (!selected.contains(photoModel)) {
                if (selected.size() < MAX_IMAGE) {
                    selected.add(photoModel);
                } else {
                    Toast.makeText(this, "最大可以上传" + MAX_IMAGE + "张", Toast.LENGTH_SHORT).show();
                    buttonView.setChecked(false);
                    photoModel.setChecked(false);
                    imgView.clearColorFilter();
                    return;
                }
            }
        } else {
            selected.remove(photoModel);
        }
//        tvNumber.setText("(" + selected.size() + "/" + MAX_IMAGE + ")");
        tvNumber.setText(selected.size() + "");

    }

    @Override
    public void onBackPressed() {
        if (layoutAlbum.getVisibility() == View.VISIBLE) {
            hideAlbum();
        } else
            super.onBackPressed();
    }

    @Override
    /** 相册列表点击事件 */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlbumModel current = (AlbumModel) parent.getItemAtPosition(position);
        for (int i = 0; i < parent.getCount(); i++) {
            AlbumModel album = (AlbumModel) parent.getItemAtPosition(i);
            if (i == position)
                album.setCheck(true);
            else
                album.setCheck(false);
        }
        albumAdapter.notifyDataSetChanged();
        hideAlbum();
        tvAlbum.setText(current.getName());
        // tvTitle.setText(current.getName());

        // 更新照片列表
        if (current.getName().equals(RECCENT_PHOTO))
            photoSelectorDomain.getReccent(reccentListener);
        else
            photoSelectorDomain.getAlbum(current.getName(), reccentListener); // 获取选中相册的照片
    }

    /**
     * 获取本地图库照片回调
     */
    public interface OnLocalReccentListener {
        public void onPhotoLoaded(List<PhotoModel> photos);
    }

    /**
     * 获取本地相册信息回调
     */
    public interface OnLocalAlbumListener {
        public void onAlbumLoaded(List<AlbumModel> albums);
    }

    private OnLocalAlbumListener albumListener = new OnLocalAlbumListener() {
        @Override
        public void onAlbumLoaded(List<AlbumModel> albums) {
            albumAdapter.update(albums);
        }
    };

    private OnLocalReccentListener reccentListener = new OnLocalReccentListener() {
        @Override
        public void onPhotoLoaded(List<PhotoModel> photos) {
            for (PhotoModel model : photos) {
                if (selected.contains(model)) {
                    model.setChecked(true);
                }
            }
            photoAdapter.update(photos);
            gvPhotos.smoothScrollToPosition(0); // 滚动到顶端
            // reset(); //--keep selected photos

        }
    };
}
