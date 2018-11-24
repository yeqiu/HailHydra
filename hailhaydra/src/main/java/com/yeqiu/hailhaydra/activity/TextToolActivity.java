package com.yeqiu.hailhaydra.activity;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hailhaydra.app.Url;
import com.yeqiu.hydrautils.utils.JumpUtils;
import com.yeqiu.hydrautils.utils.SpanUtils;
import com.yeqiu.hydrautils.utils.UIHelper;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/17
 * @describe：
 * @fix：
 */
public class TextToolActivity extends BaseActivity {


    private TextView tvTextUrl;
    private TextView tvTextClick;

    private TextView tvTextUnderline;
    private TextView tvTextDelline;

    private TextView tvTextAll;

    @Override
    protected Object getContentView() {
        return R.layout.activity_text_tool;
    }

    @Override
    protected void initView() {
        setHeaderTitle("TextTool");
        tvTextUrl = (TextView) findViewById(R.id.tv_text_url);
        tvTextClick = (TextView) findViewById(R.id.tv_text_click);
        tvTextUnderline = (TextView) findViewById(R.id.tv_text_underline);
        tvTextDelline = (TextView) findViewById(R.id.tv_text_delline);
        tvTextAll = (TextView) findViewById(R.id.tv_text_all);

        findViewById(R.id.bt_all).setOnClickListener(this);
    }

    @Override
    protected void initData() {

        // 响应点击事件的话必须设置以下属性
        tvTextUrl.setMovementMethod(LinkMovementMethod.getInstance());
        tvTextClick.setMovementMethod(LinkMovementMethod.getInstance());

        //如果url等属性需要变色请在设置颜色后重新在设置属性

        tvTextUrl.setText(new SpanUtils().append("test  ").appendLine("Url").setForegroundColor
                (Color.BLUE).setUrl(Url.jianshu).create());


        tvTextClick.setText(new SpanUtils().append("test  ").appendLine("点击事件").setForegroundColor
                (Color.BLUE).setClickSpan(clickableSpan).create());


        tvTextDelline.setText(new SpanUtils().append("test  ").appendLine("删除线").setForegroundColor
                (Color.BLUE).setStrikethrough().create());


        tvTextUnderline.setText(new SpanUtils().append("test  ").appendLine("下划线")
                .setForegroundColor(Color.BLUE).setUnderline().create());

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

        JumpUtils.jumpToBrowserActivity(Url.SpannableString);


    }


    private ClickableSpan clickableSpan = new ClickableSpan() {
        @Override
        public void onClick(View widget) {

            UIHelper.showToast("点击事件");
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            //可点击区域的颜色
            ds.setColor(Color.BLUE);
            ds.setUnderlineText(false);
        }
    };


}
