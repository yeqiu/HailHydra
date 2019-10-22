package com.yeqiu.hydra.view.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;

import com.yeqiu.hydra.R;
import com.yeqiu.hydra.utils.NotificationUtils;
import com.yeqiu.hydra.view.dialog.CommonDialog;
import com.yeqiu.hydra.view.dialog.callback.DialogListener;

import java.util.ArrayList;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019-07-25
 * @describe：
 * @fix：
 */
public class NotificationActivity extends BaseActivity {

    TextView tvNotification1;
    TextView tvNotification2;
    TextView tvNotification3;
    TextView tvNotification4;
    TextView tvNotification5;
    TextView tvNotification6;
    TextView tvNotification7;

    private String channelId = "message";
    private String channelName = "消息提示";


    @Override
    protected Object getContentView() {
        return R.layout.activity_notification;
    }

    @Override
    protected void initView() {

        setHeadTitle("通知的各种写法");
        tvNotification1 = (TextView) findViewById(R.id.tv_notification_1);
        tvNotification2 = (TextView) findViewById(R.id.tv_notification_2);
        tvNotification3 = (TextView) findViewById(R.id.tv_notification_3);
        tvNotification4 = (TextView) findViewById(R.id.tv_notification_4);
        tvNotification5 = (TextView) findViewById(R.id.tv_notification_5);
        tvNotification6 = (TextView) findViewById(R.id.tv_notification_6);
        tvNotification7 = (TextView) findViewById(R.id.tv_notification_7);

        NotificationUtils notificationUtils = new NotificationUtils();
        int importance = NotificationManager.IMPORTANCE_HIGH;
        notificationUtils.createNotificationChannel(channelId, channelName, importance);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

        tvNotification1.setOnClickListener(this);
        tvNotification2.setOnClickListener(this);
        tvNotification3.setOnClickListener(this);
        tvNotification4.setOnClickListener(this);
        tvNotification5.setOnClickListener(this);
        tvNotification6.setOnClickListener(this);
        tvNotification7.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!NotificationUtils.checkNotifyOpen()) {

            new CommonDialog(getActivity())
                    .setTitleText("请开启通知权限")
                    .setDescText("检测到您没有开启通知，请在设置中打开")
                    .setOnDialogListener(new DialogListener() {

                        @Override
                        public void onConfirmClick() {
                            super.onConfirmClick();
                            NotificationUtils.toNotificationSetting();
                        }
                    })
                    .show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_notification_1:
                common();
                break;
            case R.id.tv_notification_2:
                multiLine();
                break;
            case R.id.tv_notification_3:
                list();
                break;
            case R.id.tv_notification_4:
                custom();
                break;
            case R.id.tv_notification_5:
                bigPic();
                break;

            case R.id.tv_notification_6:
                progress();
                break;
            case R.id.tv_notification_7:
                clear();
                break;

            default:
                break;
        }
    }


    private void common() {


        NotificationManager notificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(getActivity(), NotificationActivity.class);
        PendingIntent pi = PendingIntent.getService(getActivity(), 0, intent, 0);

        Notification notification = new NotificationCompat.Builder(this, channelId)
                //标题
                .setContentTitle("收到一条聊天消息")
                //内容
                .setContentText("今天中午吃什么")
                //设置发送的时间
                .setWhen(System.currentTimeMillis())
                //设置小图标（通知栏没有下拉的图标）
                .setSmallIcon(R.drawable.icon_done)
                //设置右侧大图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.drawable.icon_head_hydra_2))
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                .setContentIntent(pi)
                .build();
        notificationManager.notify(1, notification);


        //似乎只有设置了setContentIntent，AutoCancel才能生效

    }


    private void multiLine() {


        NotificationManager notificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(getActivity(), NotificationActivity.class);
        PendingIntent pi = PendingIntent.getService(getActivity(), 0, intent, 0);

        String title = "多行文字标题";
        String content =
                "多行文字内容，多行文字内容，多行文字内容，多行文字内容，多行文字内容，多行文字内容，多行文字内容，多行文字内容，多行文字内容，多行文字内容，多行文字内容，多行文字内容，多行文字内容，多行文字内容，";


        //创建多文字样式
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle()
                .setBigContentTitle(title)
                .bigText(content);
        Notification notification = new NotificationCompat.Builder(this, channelId)
                //标题
                .setContentTitle("你有一条新消息")
                //设置小图标（通知栏没有下拉的图标）
                .setSmallIcon(R.drawable.icon_done)
                //设置右侧大图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.drawable.icon_head_hydra_2))
                //设置发送的时间
                .setWhen(System.currentTimeMillis())
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                .setContentIntent(pi)
                .setStyle(bigTextStyle)
                .build();
        notificationManager.notify(1, notification);

        //这里设置setContentText 也不会生效，会直接显示bigTextStyle里的内容，下拉之后不会显示setContentTitle，直接显示bigText


    }

    private void list() {

        NotificationManager notificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(getActivity(), NotificationActivity.class);
        PendingIntent pi = PendingIntent.getService(getActivity(), 0, intent, 0);

        String title = "冰冰";
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add("今晚有空吗？");
        messageList.add("晚上跟我一起去玩吧?");
        messageList.add("怎么不回复我？？我生气了！！");
        messageList.add("我真生气了！！！！！你听见了吗!");
        messageList.add("别不理我！！！");
        String content = "[" + messageList.size() + "条]" + title + ": " + messageList.get(0);


        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        for (String msg : messageList) {
            inboxStyle.addLine(msg);
        }
        inboxStyle.setSummaryText("[" + messageList.size() + "条]" + title);


        Notification notification = new NotificationCompat.Builder(this, channelId)
                //标题
                .setContentTitle(title)
                //内容
                .setContentText(content)
                //设置小图标（通知栏没有下拉的图标）
                .setSmallIcon(R.drawable.icon_done)
                //设置右侧大图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.drawable.icon_head_hydra_2))
                //设置发送的时间
                .setWhen(System.currentTimeMillis())
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                .setContentIntent(pi)
                .setStyle(inboxStyle)
                .build();
        notificationManager.notify(1, notification);


    }

    private void custom() {

        RemoteViews remoteViews = getRemoteViews();

        NotificationManager notificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(getActivity(), NotificationActivity.class);
        PendingIntent pi = PendingIntent.getService(getActivity(), 0, intent, 0);

        Notification notification = new NotificationCompat.Builder(this, channelId)
                //设置发送的时间
                .setWhen(System.currentTimeMillis())
                //设置小图标（通知栏没有下拉的图标）
                .setSmallIcon(R.drawable.icon_done)
                //设置右侧大图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.drawable.icon_head_hydra_2))
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                .setContentIntent(pi)
                .setContent(remoteViews)
                .build();
        notificationManager.notify(1, notification);

    }

    private void bigPic() {
        NotificationManager notificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(getActivity(), NotificationActivity.class);
        PendingIntent pi = PendingIntent.getService(getActivity(), 0, intent, 0);


        NotificationCompat.BigPictureStyle bigPictureStyle =
                new NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(getResources(),
                                R.drawable.icon_head_hydra_5))
                        .setBigContentTitle("图片标题");


        Notification notification = new NotificationCompat.Builder(this, channelId)
                //标题
                .setContentTitle("你有一条新消息")
                .setContentText("图片")
                //设置小图标（通知栏没有下拉的图标）
                .setSmallIcon(R.drawable.icon_done)
                //设置右侧大图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.drawable.icon_head_hydra_2))
                //设置发送的时间
                .setWhen(System.currentTimeMillis())
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                .setContentIntent(pi)
                .setStyle(bigPictureStyle)
                .build();
        notificationManager.notify(1, notification);
    }


    private void progress() {

        NotificationManager notificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(getActivity(), NotificationActivity.class);
        PendingIntent pi = PendingIntent.getService(getActivity(), 0, intent, 0);

        Notification notification = new NotificationCompat.Builder(this, channelId)
                //标题
                .setContentTitle("正在下载")
                //内容
                .setContentText("50%")
                //设置发送的时间
                .setWhen(System.currentTimeMillis())
                //设置小图标（通知栏没有下拉的图标）
                .setSmallIcon(R.drawable.icon_done)
                //设置右侧大图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.drawable.icon_head_hydra_2))
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                .setContentIntent(pi)
                //主要是这句
                .setProgress(100, 50, false)
                .build();
        notificationManager.notify(1, notification);

        //setProgress(100, 50, false)

    }

    private void clear() {

        NotificationManager notificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();

    }


    private RemoteViews getRemoteViews() {


        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.custom_view_layout);
        // 设置点击事件
        remoteViews.setOnClickPendingIntent(R.id.iv_play_or_pause, getActivityPendingIntent(1));
        remoteViews.setOnClickPendingIntent(R.id.iv_next, getActivityPendingIntent(2));
        remoteViews.setOnClickPendingIntent(R.id.iv_cancel, getActivityPendingIntent(3));

        remoteViews.setTextViewText(R.id.tv_title, "标题");
        remoteViews.setTextViewText(R.id.tv_summery, "艺术家");
        return remoteViews;
    }

    private PendingIntent getActivityPendingIntent(int data) {
        Intent intent = new Intent(this, NotificationClickActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("data", data);
        //注意这里第二个参数requestCode 一定不能是一样的，否则会造成Intent重复，会以最后一个Intent为准
        PendingIntent pendingIntent = PendingIntent.getActivity(this, data, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);


        return pendingIntent;
    }


}
