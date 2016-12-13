package activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.yangshenglong.gife.R;

import base.BaseActivity;
import cn.sharesdk.framework.ShareSDK;
import fragment.classify.ClassifyFragment;
import fragment.home.HomeFragment;
import fragment.list.ListFragment;
import fragment.my.MyFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private RadioButton rbBtnHome,rbBtnList,rbBtnClassify,rbBtnMy;
    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        rbBtnHome = (RadioButton) findViewById(R.id.rb_btn_home);
        rbBtnList = (RadioButton) findViewById(R.id.rb_btn_list);
        rbBtnClassify = (RadioButton) findViewById(R.id.rb_btn_classify);
        rbBtnMy = (RadioButton) findViewById(R.id.rb_btn_my);
    }

    @Override
    public void initData() {
        ShareSDK.initSDK(this);


        rbBtnHome.setOnClickListener(this);
        rbBtnList.setOnClickListener(this);
        rbBtnClassify.setOnClickListener(this);
        rbBtnMy.setOnClickListener(this);
        replace(new HomeFragment());
    }

    public void replace(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.replace,fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rb_btn_home:
                replace(new HomeFragment());
                break;
            case R.id.rb_btn_list:
                replace(new ListFragment());
                break;
            case R.id.rb_btn_classify:
                replace(new ClassifyFragment());
                break;
            case R.id.rb_btn_my:
                replace(new MyFragment());
                break;
        }
    }
}
