package fragment.list;


import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.yangshenglong.gife.R;

import activity.listactivity.ListSecondAty;
import adapter.list.secondpage.ListSecondLvAdapter;
import base.BaseFragment;
import bean.list.ListSecondPageBean;
import volley.NetHelper;
import volley.NetListener;


/**
 * Created by yangshenglong on 16/12/2.
 */

public class ListSecondSingleFragment extends BaseFragment {
    private ListView lv;
    private String id;
    private String url;

    @Override
    public int setLayout() {
        return R.layout.fragment_list_second_single;
    }

    @Override
    public void initView(View view) {
        lv = (ListView) view.findViewById(R.id.list_second_single_lv);
    }

    @Override
    public void initData() {

      id = ListSecondAty.sendId();


        url = "http://api.liwushuo.com/v2/items/" +
                id + "/recommend?num=20&post_num=5";
        Log.d("ListSecondSingleFragmen", url);
        //解析
        getInternet();
    }

    //解析
    private void getInternet() {
        NetHelper.MyRequest(url, ListSecondPageBean.class, new NetListener<ListSecondPageBean>() {
            @Override
            public void successListener(ListSecondPageBean data) {
                ListSecondLvAdapter adapter = new ListSecondLvAdapter(getContext());
                adapter.setData(data);
                lv.setAdapter(adapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

}
