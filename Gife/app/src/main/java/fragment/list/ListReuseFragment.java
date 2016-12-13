package fragment.list;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.google.gson.Gson;
import com.yangshenglong.gife.R;

import adapter.list.ListHeaderAdapter;
import adapter.list.ListPagerAdapter;
import adapter.list.ListRvAdapter;
import base.BaseFragment;
import bean.list.ListRvBean;
import volley.NetHelper;
import volley.NetListener;

/**
 * Created by yangshenglong on 16/11/24.
 */

public class ListReuseFragment extends BaseFragment {

    private String url;
    private LRecyclerView lRecyclerView;
    private ListHeaderAdapter adapter;
    private String name;
    private LRecyclerViewAdapter  lRecyclerViewAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_listreuse;
    }

    @Override
    public void initView(View view) {
        lRecyclerView = (LRecyclerView) view.findViewById(R.id.list_rv);
        lRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
    }
    @Override
    public void initData() {
        Bundle bundle = getArguments();

        name = bundle.get("key").toString();
            url = "http://api.liwushuo.com/v2/ranks_v3/ranks/" +
                    name + "?limit=20&offset=0";


        Log.d("ListReuseFragment", url);
        //RecyclerView网络解析
        getInternet();


    }



    //RecyclerView网络解析
    private void getInternet() {
        NetHelper.MyRequest(url, ListRvBean.class, new NetListener<ListRvBean>() {
            @Override
            public void successListener(ListRvBean data) {


                //上拉刷新
                adapter = new ListHeaderAdapter(getContext());
                lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
                adapter.setData(data);
                lRecyclerView.setAdapter(lRecyclerViewAdapter);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                lRecyclerView.setLayoutManager(manager);


                lRecyclerView.setOnRefreshListener(new OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        lRecyclerView.refreshComplete();
                        lRecyclerViewAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });

    }

    public static ListReuseFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("key",position);
        String message= ListPagerAdapter.getMessage(position).toString();
        args.putString("key",message);
        ListReuseFragment fragment = new ListReuseFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
