package com.yeqiu.hydra.view.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yeqiu.hydra.R;
import com.yeqiu.hydra.constant.AppConstant;
import com.yeqiu.hydra.utils.APPInfoUtil;
import com.yeqiu.hydra.utils.ActivityUtils;
import com.yeqiu.hydra.utils.SharedUtil;
import com.yeqiu.hydra.widget.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @project：jinjuyunchuang
 * @author：小卷子
 * @date 2019-07-29
 * @describe：
 * @fix：
 */
public class LeadActivity extends BaseActivity {

    private ViewPager vpLead;
    private TextView tvLeadNext;
    private ViewPagerIndicator indicator;
    private ArrayList<ImageView> views;


    @Override
    protected Object getContentView() {
        return R.layout.activity_lead;
    }

    @Override
    protected void initView() {

        showHeadLayout(false);
        vpLead = (ViewPager) findViewById(R.id.vp_lead);
        tvLeadNext = (TextView) findViewById(R.id.tv_lead_next);
        indicator = (ViewPagerIndicator) findViewById(R.id.vci_lead);

        tvLeadNext.setVisibility(View.INVISIBLE);


    }


    @Override
    protected void addStatusViewWithColor(int colorId) {
//        super.addStatusViewWithColor(colorId);
    }

    @Override
    protected boolean isSwipeBack() {
        return false;
    }

    @Override
    protected void initData() {

        List<Integer> imgs = new ArrayList<>();

        imgs.add(getRandomId());
        imgs.add(getRandomId());
        imgs.add(getRandomId());


        views = new ArrayList<>();
        for (int i = 0; i < imgs.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            //设置图片
            imageView.setImageResource(imgs.get(i));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //添加到集合中
            views.add(imageView);
        }


        vpLead.setAdapter(new LeadPagerAdapter(views));


//        indicator.setIndicatorHeight(2)
//                .setIndicatorWidth(20)
//                .setIndicatorMargin(10)
//                .setIndicatorSelectId(R.drawable.shape_line_select)
//                .setIndicatorUnselectId(R.drawable.shape_line_normal)
//                .setViewPager(vpLead);

        indicator.setViewPager(vpLead);

    }

    @Override
    protected void initListener() {

        tvLeadNext.setOnClickListener(this);


        vpLead.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {

                tvLeadNext.setVisibility((position == views.size() - 1) ? View.VISIBLE : View
                        .INVISIBLE);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private int getRandomId() {

        int id = new Random().nextInt(13) + 1;
        String idName = "bg_hydra_" + id;


        int resId = getResources().getIdentifier(idName, "drawable", getPackageName());

        if (resId == 0) {
            resId = R.drawable.bg_hydra_1;
        }

        return resId;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_lead_next:
                toMainActivity();
                break;

            default:
                break;
        }
    }


    private void toMainActivity() {


        //存入本地versionCode
        SharedUtil.getInstance().putInt(AppConstant.currentVersion, APPInfoUtil.getVersionCode());
        ActivityUtils.toMainActivity();
        finish();

    }


    class LeadPagerAdapter extends PagerAdapter {

        private List<ImageView> views;

        public LeadPagerAdapter(List<ImageView> mViewList) {
            this.views = mViewList;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
    }

}
