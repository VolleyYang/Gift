package fragment.my;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import java.util.HashMap;

import activity.listactivity.CollectAty;
import activity.loginaty.LoginAty;
import base.BaseFragment;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by yangshenglong on 16/11/22.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener {
    private ImageView img,loginQq,myIcon;
    private String name;
    private String icon;
    private TextView  tvName;
    private PlatformActionListener platformActionListener;

    @Override
    public int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView(View view) {
        img = (ImageView) view.findViewById(R.id.set);
        loginQq = (ImageView) view.findViewById(R.id.login_qq);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        myIcon = (ImageView) view.findViewById(R.id.my_icon);
    }

    @Override
    public void initData() {

        //登入
        login();

        ShareSDK.initSDK(getContext());



        img.setOnClickListener(this);

        //登入
        loginQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Platform qq = ShareSDK.getPlatform(QQ.NAME);

                qq.authorize();//单独授权,OnComplete返回的hashmap是空的
                qq.showUser(null);//授权并获取用户信息

                // 回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        PlatformDb platformDb = platform.getDb();
                        name = platformDb.getUserName();
                        icon = platformDb.getUserIcon();


                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        Intent intent = new Intent(getContext(), LoginAty.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    //登入
    private void login() {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);

        //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行

        try {
            //获取信息
            PlatformDb platformDb = qq.getDb();

            name = platformDb.getUserName();
            icon = platformDb.getUserIcon();

            if (!TextUtils.isEmpty(name)) {
                tvName.setText(name);
                Picasso.with(getContext()).load(icon).into(myIcon);
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

    //收藏跳转
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), CollectAty.class);
        startActivity(intent);
    }
}
