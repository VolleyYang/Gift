package activity.siftactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.yangshenglong.gife.R;

import adapter.home.BannerSecondAdapter;
import adapter.home.HomeLvReuseAdapter;
import bean.home.BannerSecondBean;
import bean.home.SiftSecondBean;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import volley.NetHelper;
import volley.NetListener;

public class SiftActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView img;
    private ListView lv;
    private int pos;
    private String web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sift);

        lv = (ListView) findViewById(R.id.sift_second_lv);

        img = (ImageView) findViewById(R.id.sift_aty_img);
        //share
        img.setOnClickListener(this);
        //点击事件
        lv.setOnItemClickListener(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String  url = "http://api.liwushuo.com/v2/collections/"+id+"/posts?limit=20&offset=0";
        if (id != null&&id!= "0"){
            NetHelper.MyRequest(url, BannerSecondBean.class, new NetListener<BannerSecondBean>() {
                @Override
                public void successListener(BannerSecondBean data) {
                    web = data.getData().getPosts().get(pos).getContent_url();
                    BannerSecondAdapter adapter = new BannerSecondAdapter(getBaseContext());
                    adapter.setData(data);
                    lv.setAdapter(adapter);

                }

                @Override
                public void errorListener(VolleyError error) {

                }
            });
        }else if (id == "0"){


        }
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
    @Override
    public void onClick(View v) {
        showShare();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        pos = position;
        Intent intent = new Intent(this,BannerThirdAty.class);
        intent.putExtra("key",web);
        startActivity(intent);
    }
}
