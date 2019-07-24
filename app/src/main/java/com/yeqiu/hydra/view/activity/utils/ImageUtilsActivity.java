package com.yeqiu.hydra.view.activity.utils;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yeqiu.hydra.R;
import com.yeqiu.hydra.ui.circleprogressview.CircleProgressView;
import com.yeqiu.hydra.utils.ResourceUtil;
import com.yeqiu.hydra.utils.UIHelper;
import com.yeqiu.hydra.utils.image.ImageSavaListener;
import com.yeqiu.hydra.utils.image.ImageUtils;
import com.yeqiu.hydra.utils.image.progress.ProgressListener;
import com.yeqiu.hydra.utils.image.transform.BlurTransformation;
import com.yeqiu.hydra.view.activity.BaseActivity;

import java.io.File;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/17
 * @describe：
 * @fix：
 */
public class ImageUtilsActivity extends BaseActivity {

    ImageView ivImageUtils;
    Button btImageUtilsFitcenter;
    Button btImageUtilsCentercrop;
    Button btImageUtilsCenterInside;
    Button btImageUtilsCircle;
    Button btImageUtilsRound;
    Button btImageUtilsBg;
    Button btImageUtilsListener;
    Button btImageUtilsScaleType;
    TextView tvImageUtilsListener;
    TextView tvImageUtilsSave;
    TextView tvImageUtilsBlur;
    CircleProgressView progressView;

    String url = "http://i4.3conline" +
            ".com/images/piclib/201011/19/batch/1/74824/1290127095498e4n36hhgqz.jpg";

    @Override
    protected Object getContentView() {
        return R.layout.activity_image_utils;
    }

    @Override
    protected void initView() {

        setHeaderTitle("ImageUtils的使用");


    }

    @Override
    protected void initData() {
        ivImageUtils = (ImageView) findViewById(R.id.iv_image_utils);
        progressView = (CircleProgressView) findViewById(R.id.cpv_image_utils);

        btImageUtilsFitcenter = (Button) findViewById(R.id.bt_image_utils_fitcenter);
        btImageUtilsCentercrop = (Button) findViewById(R.id.bt_image_utils_centercrop);
        btImageUtilsCenterInside = (Button) findViewById(R.id.bt_image_utils_centerInside);
        btImageUtilsCircle = (Button) findViewById(R.id.bt_image_utils_circle);
        btImageUtilsRound = (Button) findViewById(R.id.bt_image_utils_round);
        btImageUtilsBg = (Button) findViewById(R.id.bt_image_utils_bg);
        btImageUtilsListener = (Button) findViewById(R.id.bt_image_utils_listener);
        btImageUtilsScaleType = (Button) findViewById(R.id.bt_image_utils_scaleType);
        tvImageUtilsListener = (TextView) findViewById(R.id.tv_image_utils_listener);
        tvImageUtilsSave = (TextView) findViewById(R.id.bt_image_utils_save);
        tvImageUtilsBlur = (TextView) findViewById(R.id.bt_image_utils_blur);

    }

    @Override
    protected void initListener() {
        btImageUtilsFitcenter.setOnClickListener(this);
        btImageUtilsCentercrop.setOnClickListener(this);
        btImageUtilsCenterInside.setOnClickListener(this);
        btImageUtilsCircle.setOnClickListener(this);
        btImageUtilsRound.setOnClickListener(this);
        btImageUtilsBg.setOnClickListener(this);
        btImageUtilsListener.setOnClickListener(this);
        btImageUtilsScaleType.setOnClickListener(this);
        tvImageUtilsSave.setOnClickListener(this);
        tvImageUtilsBlur.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        ivImageUtils.setBackgroundColor(ResourceUtil.getColor(R.color.color_d2ebff));

        switch (view.getId()) {
            case R.id.bt_image_utils_fitcenter:

                new ImageUtils()
                        .setOptions(ImageUtils.fitCenter)
                        .setImgPlaceholder(R.drawable.hydra)
                        .setCacheStrategy(DiskCacheStrategy.NONE)
                        .load(getContext(), url, ivImageUtils);

                break;
            case R.id.bt_image_utils_centercrop:

                new ImageUtils()
                        .setOptions(ImageUtils.centerCrop)
                        .setImgPlaceholder(R.drawable.hydra)
                        .setCacheStrategy(DiskCacheStrategy.NONE)
                        .load(getContext(), url, ivImageUtils);

                break;
            case R.id.bt_image_utils_centerInside:

                new ImageUtils()
                        .setOptions(ImageUtils.centerInside)
                        .setImgPlaceholder(R.drawable.hydra)
                        .setCacheStrategy(DiskCacheStrategy.NONE)
                        .load(getContext(), url, ivImageUtils);

                break;
            case R.id.bt_image_utils_circle:

                new ImageUtils()
                        .setOptions(ImageUtils.circle)
                        .setImgPlaceholder(R.drawable.hydra)
                        .setCacheStrategy(DiskCacheStrategy.NONE)
                        .load(getContext(), url, ivImageUtils);

                break;
            case R.id.bt_image_utils_round:

                new ImageUtils()
                        .setOptions(ImageUtils.centerCrop)
                        .setImgPlaceholder(R.drawable.hydra)
                        .setCacheStrategy(DiskCacheStrategy.NONE)
                        .loadWithRound(getContext(), url, ivImageUtils, 20);

                break;
            case R.id.bt_image_utils_bg:

                new ImageUtils()
                        .setOptions(ImageUtils.fitCenter)
                        .setImgPlaceholder(R.drawable.hydra)
                        .setCacheStrategy(DiskCacheStrategy.NONE)
                        .setBg(getContext(), url, ivImageUtils);

                break;
            case R.id.bt_image_utils_listener:

                progressView.setVisibility(View.VISIBLE);

                new ImageUtils()
                        .setCacheStrategy(DiskCacheStrategy.NONE)
                        .loadWhitListener(getContext(), url, ivImageUtils, new ProgressListener() {
                            @Override
                            public void onLoadProgress(boolean isDone, int progress) {

                                progressView.setVisibility(isDone ? View.GONE : View.VISIBLE);
                                progressView.setProgress(progress);
                            }

                            @Override
                            public void onLoadFailed() {
                                UIHelper.showToast("加载失败");
                            }
                        });


                break;
            case R.id.bt_image_utils_scaleType:

                startActivity(new Intent(getContext(), ScaleTypeActivity.class));

                break;

            case R.id.bt_image_utils_save:

                new ImageUtils().saveToGallery(url, new ImageSavaListener() {
                    @Override
                    public void onSuccess(File file) {
                        UIHelper.showToast("保存成功 保存到 == " + file.getAbsolutePath());
                    }

                    @Override
                    public void onFail(String msg) {
                        UIHelper.showToast("保存失败 " + msg);
                    }
                });

                break;

            case R.id.bt_image_utils_blur:

                BlurTransformation blurTransformation = new BlurTransformation(getContext(), 25, 1);

                new ImageUtils()
                        .setOptions(ImageUtils.centerCrop)
                        .setImgPlaceholder(R.drawable.hydra)
                        .setCacheStrategy(DiskCacheStrategy.NONE)
                        .setTransformation(blurTransformation)
                        .load(getContext(), R.drawable.img_scaletype, ivImageUtils);

                break;


            default:
                break;
        }
    }


}
