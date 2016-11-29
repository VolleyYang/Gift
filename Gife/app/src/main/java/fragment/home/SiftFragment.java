package fragment.home;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.yangshenglong.gife.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;

import adapter.home.GridViewAdapter;
import adapter.home.HomeListViewAdapter;
import base.BaseFragment;
import bean.home.BannerBean;
import bean.home.SiftBean;
import bean.home.SixRuleBean;
import staticclass.StaticClass;
import volley.NetHelper;
import volley.NetListener;

/**
 * Created by yangshenglong on 16/11/23.
 */

public class SiftFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private ListView listView;
    private GridView gridView;


    private ArrayList<String> pics= new ArrayList<>();;
    private HomeListViewAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_sift;
    }

    @Override
    public void initView(View view) {
        listView = (ListView) view.findViewById(R.id.sift_lv);

        view = View.inflate(getContext(), R.layout.item_sift_head, null);
        gridView = (GridView) view.findViewById(R.id.gv);
        listView.addHeaderView(view);
        //六宫格点击事件
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void initData() {

        adapter = new HomeListViewAdapter(getContext());

        //轮播图解析网络数据
        bannerInternet();

        //listView 解析数据
        getInternet();

        //六宫格网络解析
        sixRuleInternet();

    }


    //六宫格网络解析
    private void sixRuleInternet() {
        String sixUrl = StaticClass.SIXRULE;
        NetHelper.MyRequest(sixUrl, SixRuleBean.class, new NetListener<SixRuleBean>() {
            @Override
            public void successListener(SixRuleBean data) {
                GridViewAdapter gridAdapter = new GridViewAdapter(getContext());
                gridAdapter.setData(data);
                gridView.setAdapter(gridAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    //轮播图网络解析
    private void bannerInternet() {
        String bannerUrl = StaticClass.BANNER;
        NetHelper.MyRequest(bannerUrl, BannerBean.class, new NetListener<BannerBean>() {
            @Override
            public void successListener(BannerBean data) {
                for (int i = 0; i < data.getData().getBanners().size(); i++) {
                    pics.add(data.getData().getBanners().get(i).getImage_url());
                }
                //轮播图
                bannerImg();
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }


    //设置轮播图
    private void bannerImg() {
        Banner banner = (Banner) listView.findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(pics);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置(当banner模式中有指示器时)
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    //listView 解析
    private void getInternet() {
        String url = StaticClass.SIFT;//接口(预知详情,那就点进去啊大笨蛋!!!!!!!!!!!)
        NetHelper.MyRequest(url, SiftBean.class, new NetListener<SiftBean>() {
            @Override
            public void successListener(SiftBean data) {
                adapter.setData(data);
                listView.setAdapter(adapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    //六宫格点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
