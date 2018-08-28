package com.yeqiu.hailhydra.activity;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.yeqiu.hailhydra.R;
import com.yeqiu.hailhydra.data.User;
import com.yeqiu.hailhydra.utils.UIHelper;
import com.yeqiu.hailhydra.utils.UserDbHelper;

import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/8/20
 * @describe：
 * @fix：
 */
public class LitePalActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 添加数据
     */
    private Button mBtLitepalAdd;
    /**
     * 删除数据
     */
    private Button mBtLitepalDel;
    /**
     * 修改数据
     */
    private Button mBtLitepalUpd;
    /**
     * 查找数据
     */
    private Button mBtLitepalFind;
    private ListView mLvLitepalLv;
    private UserAdapter userAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_litepal;
    }

    @Override
    protected void initData() {

        addUser();

        List<User> users = UserDbHelper.finAll(User.class);

        userAdapter = new UserAdapter(this, users);

        mLvLitepalLv.setAdapter(userAdapter);


    }

    private void addUser() {

        List<User> users = UserDbHelper.finAll(User.class);

        if (users == null || users.size() <= 0) {
            for (int i = 0; i < 20; i++) {
                User user = new User();
                user.setName("狗蛋" + i);
                user.setAge(i);
                user.setGrander(1);
                UserDbHelper.addUserData(user);
            }
        }

    }


    @Override
    public void initView() {
        mBtLitepalAdd = (Button) findViewById(R.id.bt_litepal_add);
        mBtLitepalAdd.setOnClickListener(this);
        mBtLitepalDel = (Button) findViewById(R.id.bt_litepal_del);
        mBtLitepalDel.setOnClickListener(this);
        mBtLitepalUpd = (Button) findViewById(R.id.bt_litepal_upd);
        mBtLitepalUpd.setOnClickListener(this);
        mBtLitepalFind = (Button) findViewById(R.id.bt_litepal_find);
        mBtLitepalFind.setOnClickListener(this);
        mLvLitepalLv = (ListView) findViewById(R.id.lv_litepal_lv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_litepal_add: {
                test();

                if (true) {
                    return;
                }
                User user = new User();
                user.setName("上单送人头");
                user.setAge(1);
                UserDbHelper.addUserData(user);
                userAdapter.notifyDataSetChanged();
            }

            break;
            case R.id.bt_litepal_del: {
                User first = UserDbHelper.findFirst(User.class);
                UserDbHelper.delete(User.class, first.getId());
                userAdapter.notifyDataSetChanged();
            }


            break;
            case R.id.bt_litepal_upd: {
                User firstUser = UserDbHelper.findFirst(User.class);
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", "快打主宰啊啊啊");
                UserDbHelper.update(User.class, contentValues, firstUser.getId());
                userAdapter.notifyDataSetChanged();
            }


            break;
            case R.id.bt_litepal_find:
                User firstUser = UserDbHelper.findFirst(User.class);

                User user = UserDbHelper.find(User.class, firstUser.getId());
                UIHelper.showToast(LitePalActivity.this, "查找信息：" + user.getName());


                break;
            default:
                break;
        }
    }


    /**
     * 测试子线程操作数据库
     */
    private void test() {
        new Thread() {
            @Override
            public void run() {
                super.run();

                User user = new User();
                user.setName("上单送人头");
                user.setAge(1);
                UserDbHelper.addUserData(user);

            }
        }.start();
    }

    private class UserAdapter extends BaseAdapter {

        private Context context;
        private List<User> datas;

        public UserAdapter(Context context, List<User> datas) {
            this.context = context;
            this.datas = datas;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public User getItem(int position) {
            User user = datas.get(position);
            return user;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_user, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            User item = getItem(position);

            holder.tvID.setText(item.getId() + "");
            holder.tvName.setText(item.getName());
            holder.tvAge.setText(item.getAge() + "");


            return convertView;
        }


        @Override
        public void notifyDataSetChanged() {


            datas = UserDbHelper.finAll(User.class);

            super.notifyDataSetChanged();
        }

        public class ViewHolder {
            private TextView tvName;
            private TextView tvAge;
            private TextView tvID;

            public ViewHolder(View view) {
                tvName = view.findViewById(R.id.tv_item_user_name);
                tvAge = view.findViewById(R.id.tv_item_user_age);
                tvID = view.findViewById(R.id.tv_item_user_id);

            }
        }

    }
}
