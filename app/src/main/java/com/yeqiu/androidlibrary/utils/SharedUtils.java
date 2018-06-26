package com.yeqiu.androidlibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.yeqiu.androidlibrary.app.App;


public class SharedUtils {

    public static final String TOKEN = "Token";
    public static final String MEMBER_ID = "MemberId";
    public static final String NIMACC_ID= "NIMAccId";
    public static final String USER_FACE = "UserFace";
    public static final String NICK_NAME = "NickName";
    public static final String SPLASH = "Splash";
    public static final String MESSAGE_TIMER = "MESSAGE_TIMER";
    public static final String LATITUDE = "Latitude";
    public static final String LONGITUDE= "Longitude";
    private static final String SP_NAME = "YJ";
    public static final String LEARN_SEARCH_HOT_KEY = "HotSearchKey";
    public static final String COURSE_SEARCH_HISTORY = "CourseSearchHistory";


    public static final String SP_EMPTY = "";
    public static final String IS_TEACHER = "is_teacher";
    public static final String IS_Liveing = "is_liveing";
    public static final String IsInAnchor = "isinanchor";
    public static final String LIVEID = "liveid";
    public static final String COURSE_PLAY_VIDEO_TYPE_STATUS = "VideoType";
    public static final String COURSE_LIMIT_FREE = "LimitFree";
    public static final String MESSAGE_SETTING_CLOSE = "message_setting_close";
    public static final String MESSAGE_SETTING_DISTURBING = "message_setting_disturbing";
    public static final String MESSAGE_SHADOW= "MESSAGE_SHADOW";
    public static final String VIPCARDCANUSECOUNT = "VIPCardCanUseCount";
    public static final String SINGDATE = "singDate";
    public static final String LIBRARY_SEARCH_HISTORY = "library_search_history";
    public static final String TEACHER_LEARN_TIMES = "TEACHER_LEARN_TIMES";
    public static final String TOPIC_MY_LIKE_ID = "TOPIC_MY_LIKE_ID";
    public static final String REPLY_MY_LIKE_ID = "REPLY_MY_LIKE_ID";

    public static final String LOGIN_AWARD_GOLD = "LOGIN_AWARD_GOLD";
    private static SharedUtils instance = new SharedUtils();

    public SharedUtils() {

    }

    private static synchronized void syncInit() {
        if (instance == null) {
            instance = new SharedUtils();
        }
    }

    public static SharedUtils getInstance() {
        if (instance == null) {
            syncInit();
        }
        return instance;
    }

    private SharedPreferences getSp() {
        return App.getInstance().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public int getInt(String key, int def) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null)
                def = sp.getInt(key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public void putInt(String key, int val) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                Editor e = sp.edit();
                e.putInt(key, val);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getLong(String key, long def) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null)
                def = sp.getLong(key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public void putLong(String key, long val) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                Editor e = sp.edit();
                e.putLong(key, val);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getString(String key, String def) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null)
                def = sp.getString(key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public void putString(String key, String val) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                Editor e = sp.edit();
                e.putString(key, val);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getBoolean(String key, boolean def) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null)
                def = sp.getBoolean(key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public float getFloat(String key, float def) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null)
                def = sp.getFloat(key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public void putFloat(String key, float val) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                Editor e = sp.edit();
                e.putFloat(key, val);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putBoolean(String key, boolean val) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                Editor e = sp.edit();
                e.putBoolean(key, val);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除一个存储，统一调用
     * @param key
     */
    public void remove(String key) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                Editor e = sp.edit();
                e.remove(key);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
