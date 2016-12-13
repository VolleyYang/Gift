package activity.listactivity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.yangshenglong.gife.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import GreenDao.DBToll;
import GreenDao.Person;
import adapter.list.listpage.ListSecondPagerAdapter;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import fragment.list.ListSecondDetailsFragment;
import fragment.list.ListSecondSingleFragment;

public class ListSecondAty extends AppCompatActivity implements View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> data;
    private Intent intent;
    private static String id;
    private static String url;

    private CheckBox  checkBox;
    private ImageView  img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_second_aty);
        tabLayout = (TabLayout) findViewById(R.id.list_second_tb);
        viewPager = (ViewPager) findViewById(R.id.list_second_vp);
        checkBox = (CheckBox) findViewById(R.id.cb);
        img = (ImageView) findViewById(R.id.img);

        data = new ArrayList<>();



        data.add(new ListSecondSingleFragment());
        data.add(new ListSecondDetailsFragment());
        ListSecondPagerAdapter adapter = new ListSecondPagerAdapter(getSupportFragmentManager());
        adapter.setData(data);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        intent = getIntent();
        id = intent.getStringExtra("numId");

        //详情--webView
        url = intent.getStringExtra("url");

        //存数据库
        final String title = intent.getStringExtra("title");
        final String dp = intent.getStringExtra("dp");
        final String price = intent.getStringExtra("price");
        final String imgNmb = intent.getStringExtra("imgUrl");

        //img 点击事件  --- 分享
        img.setOnClickListener(this);

        //判断checkBox
        if (DBToll.getInstance().isSave(title)){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }

        //checkBox 点击事件,存数据库---分享
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Person person = new Person();
                    person.setContent(title);
                    person.setDescription(dp);
                    person.setPrice(price);
                    person.setImg(imgNmb);
                    //先查重
                    if (!DBToll.getInstance().isSave(title)){
                        DBToll.getInstance().insertPerson(person);
                    }
                }else {
                    DBToll.getInstance().deleteByContent(title);
                }
            }
        });


    }



    //静态方法 ---传值
    public static String sendId(){
        return id;
    }
    //静态方法 ---传值
    public static String sendUrl(){
        return url;
    }


    //share
    @Override
    public void onClick(View v) {
        showShare();
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
}
