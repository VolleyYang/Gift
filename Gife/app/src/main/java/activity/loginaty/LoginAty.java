package activity.loginaty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import java.util.ArrayList;
import java.util.HashMap;

import GreenDao.DBToll;
import GreenDao.Person;
import activity.MainActivity;
import adapter.list.listpage.ListCollectAdapter;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

import static android.R.attr.cacheColorHint;
import static android.R.attr.name;

public class LoginAty extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgIcon;
    private TextView tvName;
    private Intent intent;
    private PlatformActionListener platformActionListener;
    private String icon;
    private String name;
    private GridView gridView;
    private ArrayList<Person> data;
    private TextView tvBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_aty);

        imgIcon = (ImageView) findViewById(R.id.img_icon);
        tvName = (TextView) findViewById(R.id.tv_name);
        gridView = (GridView) findViewById(R.id.collect_gv);
        tvBack = (TextView) findViewById(R.id.id_back);


        intent = getIntent();
        name = intent.getStringExtra("name");
        icon = intent.getStringExtra("icon");

        //登录
        login();

        //退出登录点击事件
        imgIcon.setOnClickListener(this);

        //返回首页
        tvBack.setOnClickListener(this);

        //查询数据库,设置到GridView
        gridDada();

    }


    //查询数据库,设置到GridView
    private void gridDada() {
        //查询所有
        data = new ArrayList<>();
        for (int i = 0; i < DBToll.getInstance().queryAll().size(); i++) {

            data.add(DBToll.getInstance().queryAll().get(i));
        }
        ListCollectAdapter adapter = new ListCollectAdapter(getBaseContext());
        adapter.setData(data);
        gridView.setAdapter(adapter);
    }

    //登录方法
    private void login() {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);

        //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行

        try {
            //获取信息
            PlatformDb platformDb = qq.getDb();

            name =  platformDb.getUserName();
            icon = platformDb.getUserIcon();

            if (!TextUtils.isEmpty(name)) {
                tvName.setText(name);
                Picasso.with(this).load(icon).into(imgIcon);

            }
        } catch (NullPointerException e) {

        }


        platformActionListener = new PlatformActionListener() {

            //错误
            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                arg2.printStackTrace();
            }

            //完成
            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                //输出所有授权信息
                arg0.getDb().exportData();

            }

            //关闭
            @Override
            public void onCancel(Platform arg0, int arg1) {


            }
        };

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //退出登录
            case R.id.img_icon:
                Platform platform = ShareSDK.getPlatform(QQ.NAME);
                if (platform.isAuthValid()) {
                    platform.removeAccount(true);
                } else {
                    Toast.makeText(this, "退出登录", Toast.LENGTH_SHORT).show();
                }
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.id_back:
                //返回主页
                intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;

        }
    }


}
