package fragment.home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.yangshenglong.gife.R;

import adapter.home.MyFragPagerAdapter;
import base.BaseFragment;
import bean.home.TitleBean;
import staticclass.StaticClass;
import volley.NetHelper;
import volley.NetListener;

/**
 * Created by yangshenglong on 16/11/22.
 */

public class HomeFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    public int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tb);
        viewPager = (ViewPager) view.findViewById(R.id.vp);
    }

    @Override
    public void initData() {

        //解析网络数据
        getContent();
    }

    private void getContent() {

        String url = StaticClass.TITLE;
        NetHelper.MyRequest(url, TitleBean.class, new NetListener<TitleBean>() {
            @Override
            public void successListener(TitleBean data) {
                MyFragPagerAdapter adapter = new MyFragPagerAdapter(getChildFragmentManager());
                adapter.setTitle(data);
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
