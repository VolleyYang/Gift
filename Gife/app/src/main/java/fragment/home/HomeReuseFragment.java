package fragment.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.yangshenglong.gife.R;

import adapter.home.HomeLvReuseAdapter;
import adapter.home.MyFragPagerAdapter;
import base.BaseFragment;
import bean.home.SiftReuseBean;
import volley.NetHelper;
import volley.NetListener;

/**
 * Created by yangshenglong on 16/11/23.
 */

public class HomeReuseFragment extends BaseFragment {
    private String url;
    private ListView lv;
    private HomeLvReuseAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_attention;
    }

    @Override
    public void initView(View view) {
        lv = (ListView) view.findViewById(R.id.home_reuse_lv);
    }

    @Override
    public void initData() {


        adapter = new HomeLvReuseAdapter(getContext());
        //接受bundle传过来的值
        Bundle bundle = getArguments();
        String name = bundle.get("key").toString();


        url = "http://api.liwushuo.com/v2/channels/" +
                name + "/items_v2?gender=1&limit=20&offset=0&generation=2";

        //网络解析
        getInternet();
    }

    //Fragment 复用机制
    public static HomeReuseFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("key",position);
        String message = MyFragPagerAdapter.getMessage(position);
        args.putString("key",message);
        HomeReuseFragment fragment = new HomeReuseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //网络解析
    private void getInternet() {
        NetHelper.MyRequest(url, SiftReuseBean.class, new NetListener<SiftReuseBean>() {
            @Override
            public void successListener(SiftReuseBean data) {
                adapter.setData(data);
                lv.setAdapter(adapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }




}
