package com.yeqiu.hydra.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.yeqiu.hydra.HydraUtilsManager;

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


    /**
     * apply方法是将share的修改提交到内存而后异步写入磁盘，
     * 但是commit是直接写入磁盘，这就造成两者性能上的差异，
     * 犹如apply不直接写入磁盘而share本身是单例创建，
     * apply方法会覆写之前内存中的值，异步写入磁盘的值只是最后的值
     * 而commit每次都要写入磁盘，而磁盘的写入相对来说是很低效的，
     * 所以apply方法在频繁调用时要比commit效率高很多

     * @param editor
     */
    private void apply(SharedPreferences.Editor editor) {

        editor.apply();

    }

    private void commit(SharedPreferences.Editor editor) {

        editor.commit();

    }


    // ==================存储==================


    public void putLong(String key, long val) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putLong(key, val);
                apply(editor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void putString(String key, String val) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(key, val);
                apply(editor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void putFloat(String key, float val) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putFloat(key, val);
                apply(editor);
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
                apply(editor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void putInt(String key, int val) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt(key, val);
                apply(editor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ==================取出==================


    public int getInt(String key, int def) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                def = sp.getInt(key, def);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return def;
    }


    public long getLong(String key, long def) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                def = sp.getLong(key, def);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
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


    public boolean getBoolean(String key, boolean def) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                def = sp.getBoolean(key, def);
            }
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


    /**
     * 删除 key
     *
     * @param key
     */
    public void remove(String key) {

        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                SharedPreferences.Editor editor = sp.edit();
                editor.remove(key);
                apply(editor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 清空
     */
    public void clear() {

        try {
            SharedPreferences sp = getSp();
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            apply(editor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
