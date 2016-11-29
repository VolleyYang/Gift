package fragment.list;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

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
import staticclass.StaticClass;
import volley.NetHelper;
import volley.NetListener;

/**
 * Created by yangshenglong on 16/11/22.
 */

public class ListFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ListPagerAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_list;
    }

    @Override
    public void initView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.list_tb);
        viewPager = (ViewPager) view.findViewById(R.id.list_vp);
    }

    @Override
    public void initData() {
        adapter = new ListPagerAdapter(getChildFragmentManager());

        //TabLayout网络解析
        getInternet();
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
}
