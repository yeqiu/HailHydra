package com.yeqiu.hydrautils.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.yeqiu.hydrautils.HydraUtilsManager;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/4/14
 * @describe：sp存储工具类
 * @fix：
 */
public class SharedUtil {


    private static final String SP_NAME = APPInfoUtil.getAppName();


    private static SharedUtil instance;

    public static SharedUtil getInstance() {
        if (instance == null) {
            instance = new SharedUtil();
        }
        return instance;
    }

    private SharedPreferences getSp() {
        SharedPreferences sharedPreferences = HydraUtilsManager.getInstance().getContext()
                .getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences;
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
                SharedPreferences.Editor e = sp.edit();
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
                SharedPreferences.Editor e = sp.edit();
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
            if (sp != null) {
                def = sp.getString(key, def);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public void putString(String key, String val) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                SharedPreferences.Editor e = sp.edit();
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
            if (sp != null) {
                def = sp.getFloat(key, def);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public void putFloat(String key, float val) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                SharedPreferences.Editor edit = sp.edit();
                edit.putFloat(key, val);
                edit.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putBoolean(String key, boolean val) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean(key, val);
                editor.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void remove(String key) {

        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                SharedPreferences.Editor editor = sp.edit();
                editor.remove(key);
                editor.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void clear() {

        try {
            SharedPreferences sp = getSp();
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
