package com.yeqiu.hailhaydra.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.yeqiu.hailhaydra.R;
import com.yeqiu.hydra.utils.LogUtils;
import com.yeqiu.hydra.utils.StringUtils;

import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/10/16
 * @describe：
 * @fix：
 */
public class GsonActivity extends BaseActivity {


    TextView tvGsonText;
    Button btGsonGetJson;
    Button btGsonArray;
    Button btGsonList;
    Button btGsonSerialize;
    Button btGsonNoSerialize;
    Button btGsonName;
    Button btGsonExpose;
    Button btGsonNull;
    Button btGsonJson;


    @Override
    protected Object getContentView() {
        return R.layout.activity_gson;
    }

    @Override
    protected void initView() {

        tvGsonText = (TextView) findViewById(R.id.tv_gson_text);
        btGsonGetJson = (Button) findViewById(R.id.bt_gson_get_json);
        btGsonArray = (Button) findViewById(R.id.bt_gson_array);
        btGsonList = (Button) findViewById(R.id.bt_gson_list);
        btGsonSerialize = (Button) findViewById(R.id.bt_gson_serialize);
        btGsonNoSerialize = (Button) findViewById(R.id.bt_gson_no_serialize);
        btGsonName = (Button) findViewById(R.id.bt_gson_name);
        btGsonExpose = (Button) findViewById(R.id.bt_gson_expose);
        btGsonNull = (Button) findViewById(R.id.bt_gson_null);
        btGsonJson = (Button) findViewById(R.id.bt_gson_json);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

        setHeaderTitle("Gson");

        btGsonGetJson.setOnClickListener(this);
        btGsonArray.setOnClickListener(this);
        btGsonList.setOnClickListener(this);
        btGsonSerialize.setOnClickListener(this);
        btGsonNoSerialize.setOnClickListener(this);
        btGsonName.setOnClickListener(this);
        btGsonExpose.setOnClickListener(this);
        btGsonNull.setOnClickListener(this);
        btGsonJson.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bt_gson_get_json:
                getJson();
                break;
            case R.id.bt_gson_array:
                gsonArray();
                break;
            case R.id.bt_gson_list:
                gsonList();
                break;
            case R.id.bt_gson_serialize:
                serialize();
                break;
            case R.id.bt_gson_no_serialize:
                noSerialize();
                break;
            case R.id.bt_gson_name:
                name();
                break;
            case R.id.bt_gson_expose:
                expose();
                break;
            case R.id.bt_gson_null:
                gsonNull();
                break;
            case R.id.bt_gson_json:
                printJson();
                break;


            default:
                break;
        }
    }

    private Gson getGson() {
        //通过构造函数来获取
        Gson gson = new Gson();
        //通过 GsonBuilder 来获取，可以进行多项特殊配置
        /// Gson gson = new GsonBuilder().create();
        return gson;
    }

    /**
     * 生成json
     */
    private void getJson() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("职业", "上单");
        jsonObject.addProperty("生命值", 1000);
        jsonObject.addProperty("攻击力", 888.8);
        jsonObject.addProperty("神装", true);

        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("职业", "中单");
        jsonObject2.addProperty("生命值", 3000);
        jsonObject2.addProperty("法强", 888.8);
        jsonObject2.addProperty("神装", true);
        jsonObject2.add("json", jsonObject);


        LogUtils.i(jsonObject2);

        tvGsonText.setText(jsonObject.toString());

    }

    /**
     * 数组互转
     */
    private void gsonArray() {

        String array = "[\"上单\",\"中路\",\"打野\",\"adc\",\"辅助\"]";
        String[] strings = getGson().fromJson(array, String[].class);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strings.length; i++) {
            if (i < strings.length - 1) {
                sb.append(strings[i] + ",");
            } else {
                sb.append(strings[i]);
            }
        }
        tvGsonText.setText(sb.toString());
        ///字符串数组 转为 Json数组
        String json = getGson().toJson(strings);
        LogUtils.i(json);

    }

    /**
     * 集合互转
     */
    private void gsonList() {

        String array = "[\"上单\",\"中路\",\"打野\",\"adc\",\"辅助\"]";
        List<String> strings = getGson().fromJson(array, new TypeToken<List<String>>() {
        }.getType());
        StringBuffer sb = new StringBuffer();


        String s = StringUtils.listToStringWithSeparator(strings);
        LogUtils.i("s = " + s);

    }

    /**
     * 序列化
     */
    private void serialize() {
        ///即 将Model装换为Json

        ///注：此方法只使用与简单对象，对象中包含对象不能简单的用此方法
        User user = new User("leavesC", 24, true);
        tvGsonText.setText("即 将Model装换为Json" + "\n " + getGson().toJson(user));


    }

    /**
     * 反序列化
     */
    private void noSerialize() {
        ///即 将Json装换为Model
        String userJson = "{\"name\":\"leavesC\",\"age\":24,\"sex\":true}";
        User user = getGson().fromJson(userJson, User.class);

        tvGsonText.setText("即 将Json装换为Model" + "\n " + user.toString());
    }

    /**
     * 字段重命名
     */
    private void name() {

        ///即 期望的字段和实际返回的字段有误差，即使是大小写的差异。直接使用fromJson都会出错

        String userJson = "{\"userName\":\"leavesC\",\"age\":24,\"sex\":true}";
        User user = getGson().fromJson(userJson, User.class);

        LogUtils.i(user.toString());

        tvGsonText.setText(user.toString());


        ///在序列时，Json 格式就会相应改变 转化成json的时候name字段会变成userName

        String s = getGson().toJson(user);

        LogUtils.i(s);

    }

    /**
     * 字段过滤
     */
    private void expose() {

    }


    /**
     * 序列化 输入null
     */
    private void gsonNull() {
        ///仅用于序列化  在序列化时如果某个属性值为 null 的话，
        /// 那么在序列化时该字段不会参与进来，如果想要显示输出该字段的话，可以通过 GsonBuilder 进行配置

        Gson gson = new GsonBuilder()
                .serializeNulls() //输出null
                .create();
        User user = new User(null, 10, false);
        tvGsonText.setText(user.toString());


    }

    /**
     * 格式化json
     */
    private void printJson() {

        Gson gson = new GsonBuilder()
                .serializeNulls() //输出null
                .setPrettyPrinting()//格式化输出
                .create();

        User user = new User("小卷子", 10, false);

        tvGsonText.setText(gson.toJson(user));
    }


    public class User {


        /**
         * SerializedName注解提供了两个属性，上面用到了其中一个，别外还有一个属性alternate，接收一个String数组。
         * 注：alternate需要2.4版本
         * 当三个属性都中出现任意一个时均可以得到正确的结果。
         * 注：当多种情况同时出时，以最后一个出现的值为准。
         */
        @SerializedName(value = "emailAddress", alternate = {"email", "email_address"})
        private String name;

        private int age;

        private boolean sex;

        public User(String name, int age, boolean sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", sex=" + sex +
                    '}';
        }


    }

}
