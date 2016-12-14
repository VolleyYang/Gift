package fragment.my;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.yangshenglong.gife.R;

import java.util.HashMap;

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
    private ImageView loginQq;
    private String name;
    private String icon;
    private Intent intent;

    @Override
    public int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView(View view) {
        loginQq = (ImageView) view.findViewById(R.id.login_qq);
    }

    @Override
    public void initData() {


        //第三方
        ShareSDK.initSDK(getContext());


        //登录
        loginQq.setOnClickListener(this);

    }


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

                //当登入成功后跳转到已经登录界面
                if (!TextUtils.isEmpty(name)) {
                    intent = new Intent(getContext(), LoginAty.class);
                    intent.putExtra("name", name);
                    intent.putExtra("icon", icon);
                    startActivity(intent);
                }

            }


            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
    }
}
