package fragment.list;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.VolleyError;
import com.yangshenglong.gife.R;

import activity.listactivity.ListSecondAty;
import adapter.list.listpage.ListPageRvAdapter;
import base.BaseFragment;
import bean.list.ListPageItemOneBean;
import bean.list.ListPageRvBean;
import bean.list.ListRvBean;
import volley.NetHelper;
import volley.NetListener;


/**
 * Created by yangshenglong on 16/12/2.
 */
//榜单详情页-----单品

public class ListSecondSingleFragment extends BaseFragment {

    private String id;
    private String url;
    private RecyclerView recyclerView;

    @Override
    public int setLayout() {
        return R.layout.fragment_list_second_single;
    }

    @Override
    public void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.list_second_single_rv);
    }

    @Override
    public void initData() {

      id = ListSecondAty.sendId();


        url = "http://api.liwushuo.com/v2/items/" +
                id ;

        //解析
        getInternet();
    }

    //解析
    private void getInternet() {
        NetHelper.MyRequest(url, ListPageItemOneBean.class, new NetListener<ListPageItemOneBean>() {
            @Override
            public void successListener(ListPageItemOneBean data) {
                ListPageRvAdapter adapter = new ListPageRvAdapter(getContext());
                adapter.setData(data);
                recyclerView.setAdapter(adapter);
                LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(manager);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

}
