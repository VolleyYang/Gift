package fragment.list;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.yangshenglong.gife.R;

import adapter.list.ListPagerAdapter;
import base.BaseFragment;
import bean.list.ListBean;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import staticclass.StaticClass;
import volley.NetHelper;
import volley.NetListener;

/**
 * Created by yangshenglong on 16/11/22.
 */

public class ListFragment extends BaseFragment implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ListPagerAdapter adapter;

    private ImageView img;
    @Override
    public int setLayout() {
        return R.layout.fragment_list;
    }

    @Override
    public void initView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.list_tb);
        viewPager = (ViewPager) view.findViewById(R.id.list_vp);
        img = (ImageView) view.findViewById(R.id.list_share_img);
    }

    @Override
    public void initData() {
        adapter = new ListPagerAdapter(getChildFragmentManager());

        //TabLayout网络解析
        getInternet();


        //分享
        img.setOnClickListener(this);
    }

    private void getInternet() {
        String url = StaticClass.ListTab;
        NetHelper.MyRequest(url, ListBean.class, new NetListener<ListBean>() {
            @Override
            public void successListener(ListBean data) {
                adapter.setTitle(data);
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
    private void showShare() {
        ShareSDK.initSDK(getContext());
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
        oks.show(getContext());
    }

    @Override
    public void onClick(View v) {
        showShare();
    }
}
