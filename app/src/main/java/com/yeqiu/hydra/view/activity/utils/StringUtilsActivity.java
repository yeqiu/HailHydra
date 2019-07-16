package com.yeqiu.hydra.view.activity.utils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.yeqiu.hydra.R;
import com.yeqiu.hydra.utils.StringUtils;
import com.yeqiu.hydra.view.activity.BaseActivity;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/15
 * @describe：
 * @fix：
 */
public class StringUtilsActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEtStringUtis;
    /**  */
    private TextView mTvStringUtilsResult;
    /**
     * 是否有小数
     */
    private Button mBtStringUtilsDecimal;
    /**
     * 关键字变色 （hailhaydra）
     */
    private Button mBtStringUtilsDiscoloration;
    /**
     * 验证号手机号
     */
    private Button mBtStringUtilsPhont;
    /**
     * 验证号身份证
     */
    private Button mBtStringUtilsIdcard;
    /**
     * 验证号邮箱
     */
    private Button mBtStringUtilsEmail;
    /**
     * 字母+数字校验
     */
    private Button mBtStringUtilsIsnumberandletter;
    /**
     * 隐藏手机中间4位号码
     */
    private Button mBtStringUtilsHidePhone;
    /**
     * 格式化银行卡
     */
    private Button mBtStringUtilsBankCard;
    /**
     * 获取银行卡后四位
     */
    private Button mBtStringUtilsBankCard4;
    /**
     * 获取当前时间
     */
    private Button mBtStringUtilsTime;
    /**
     * 反转字符串
     */
    private Button mBtStringUtilsReverse;

    @Override
    protected Object getContentView() {
        return R.layout.activity_string_util;
    }

    @Override
    protected void initView() {

        setHeaderTitle("StringUtils");

        mEtStringUtis = (EditText) findViewById(R.id.et_string_utis);
        mTvStringUtilsResult = (TextView) findViewById(R.id.tv_string_utils_result);
        mBtStringUtilsDecimal = (Button) findViewById(R.id.bt_string_utils_decimal);
        mBtStringUtilsDecimal.setOnClickListener(this);
        mBtStringUtilsDiscoloration = (Button) findViewById(R.id.bt_string_utils_discoloration);
        mBtStringUtilsDiscoloration.setOnClickListener(this);
        mBtStringUtilsPhont = (Button) findViewById(R.id.bt_string_utils_phont);
        mBtStringUtilsPhont.setOnClickListener(this);
        mBtStringUtilsIdcard = (Button) findViewById(R.id.bt_string_utils_idcard);
        mBtStringUtilsIdcard.setOnClickListener(this);
        mBtStringUtilsEmail = (Button) findViewById(R.id.bt_string_utils_email);
        mBtStringUtilsEmail.setOnClickListener(this);
        mBtStringUtilsIsnumberandletter = (Button) findViewById(R.id.bt_string_utils_isnumberandletter);
        mBtStringUtilsIsnumberandletter.setOnClickListener(this);
        mBtStringUtilsHidePhone = (Button) findViewById(R.id.bt_string_utils_hide_phone);
        mBtStringUtilsHidePhone.setOnClickListener(this);
        mBtStringUtilsBankCard = (Button) findViewById(R.id.bt_string_utils_bank_card);
        mBtStringUtilsBankCard.setOnClickListener(this);
        mBtStringUtilsBankCard4 = (Button) findViewById(R.id.bt_string_utils_bank_card_4);
        mBtStringUtilsBankCard4.setOnClickListener(this);
        mBtStringUtilsTime = (Button) findViewById(R.id.bt_string_utils_time);
        mBtStringUtilsTime.setOnClickListener(this);
        mBtStringUtilsReverse = (Button) findViewById(R.id.bt_string_utils_reverse);
        mBtStringUtilsReverse.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    @Override
    public void onClick(View v) {

        String string = mEtStringUtis.getText().toString();

        switch (v.getId()) {
            default:
                break;
            case R.id.bt_string_utils_decimal:

                mTvStringUtilsResult.setText(StringUtils.isHaveDecimal(string) + "");
                break;
            case R.id.bt_string_utils_discoloration:

                mTvStringUtilsResult.setText(StringUtils.discoloration(R.color.color_3e95fd, string, "hailhaydra"));
                break;
            case R.id.bt_string_utils_phont:

                mTvStringUtilsResult.setText(StringUtils.isPhoneMobile(string) + "");
                break;
            case R.id.bt_string_utils_idcard:
                mTvStringUtilsResult.setText(StringUtils.isIdCardNo(string) + "");
                break;
            case R.id.bt_string_utils_email:
                mTvStringUtilsResult.setText(StringUtils.isEmailMobile(string) + "");
                break;
            case R.id.bt_string_utils_isnumberandletter:
                mTvStringUtilsResult.setText(String.valueOf(StringUtils.isNumberAndLetter(string)));
                break;
            case R.id.bt_string_utils_hide_phone:
                mTvStringUtilsResult.setText(StringUtils.hideMobilePhone(string));
                break;
            case R.id.bt_string_utils_bank_card:
                mTvStringUtilsResult.setText(StringUtils.formatCard(string));
                break;
            case R.id.bt_string_utils_bank_card_4:
                mTvStringUtilsResult.setText(StringUtils.getCardEnd(string));
                break;
            case R.id.bt_string_utils_time:
                mTvStringUtilsResult.setText(StringUtils.getTime());
                break;
            case R.id.bt_string_utils_reverse:
                mTvStringUtilsResult.setText(StringUtils.reverse(string));
                break;
        }
    }
}
