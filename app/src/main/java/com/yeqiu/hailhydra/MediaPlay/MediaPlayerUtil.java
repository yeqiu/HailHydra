package com.yeqiu.hailhydra.MediaPlay;

import android.media.MediaPlayer;
import android.text.TextUtils;

import com.yeqiu.hydra.utils.LogUtils;

import java.io.IOException;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/3/28
 * @describe：
 * @fix：
 */
public class MediaPlayerUtil {
    private static volatile MediaPlayerUtil mediaPlayer;
    // 当前播放的音频地址
    private static String nowPlaySongUrl;

    private MediaPlayerUtil() {
    }

    public static MediaPlayerUtil getMediaPlayer() {
        if (mediaPlayer == null) {
            synchronized (MediaPlayerUtil.class) {
                if (mediaPlayer == null) {
                    mediaPlayer = new MediaPlayerUtil();
                }
            }
        }
        return mediaPlayer;
    }

    private MediaPlayer mPlayer;

    /**
     * 播放音频
     *
     * @param songUrl 网络音频Url
     */
    public void player(String songUrl) {

        if (TextUtils.equals(songUrl, nowPlaySongUrl)) {
            LogUtils.i("重复的url");
            return;
        }
        stopPlay();
        nowPlaySongUrl = songUrl;

        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
        }
        try {
            LogUtils.i("当前要播放的歌曲Url === " + songUrl);

            mPlayer.reset();
            //====这种方式只能http，https会抛IO异常
            mPlayer.setDataSource(songUrl);

            mPlayer.prepareAsync();
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    LogUtils.i("播放 " + mp.getDuration());
                    mp.start();
                }
            });
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    nowPlaySongUrl = "";
                    if (mPlayer != null) {
                        mPlayer.release();
                    }

                    if (mPlayer != null) {
                        mPlayer = null;
                    }

                }
            });
        } catch (IOException e) {
            LogUtils.i(" 播放音乐异常" + e.getMessage());

        }
    }

    /**
     * 列表中的音频播放
     * <p>
     * 点击播放，再点击停止
     *
     * @param songUrl
     */
    public void playOrStop(String songUrl) {
        if (mPlayer != null) {
            stopPlay();
        } else {
            player(songUrl);
        }
    }

    /**
     * 停止播放音频，lastSongUrl置空
     */
    public void stopPlay() {
        nowPlaySongUrl = "";
        try {
            if (mPlayer != null){
                mPlayer.stop();
            }
            if (mPlayer != null){
                mPlayer.release();
            }
            if (mPlayer != null){
                mPlayer = null;
            }
        } catch (Exception e) {
            LogUtils.i("stopPlay: " + e.toString());
        }
    }

}