package fragment.classify;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.yangshenglong.gife.R;

import adapter.classify.single.SingleLeftAdapter;
import adapter.classify.single.SingleRightAdapter;
import base.BaseFragment;
import bean.classify.SingleBean;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import staticclass.StaticClass;
import volley.NetHelper;
import volley.NetListener;

/**
 * Created by yangshenglong on 16/11/25.
 */

public class SingleFragment extends BaseFragment {
    private ListView leftLv;
    private StickyListHeadersListView rightLv;
    private SingleRightAdapter rightAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_single;
    }

    @Override
    public void initView(View view) {
        leftLv = (ListView) view.findViewById(R.id.single_left_lv);
        rightLv = (StickyListHeadersListView) view.findViewById(R.id.single_right_lv);
    }

    @Override
    public void initData() {

        String  url = StaticClass.SINGLE;
        NetHelper.MyRequest(url, SingleBean.class, new NetListener<SingleBean>() {
            @Override
            public void successListener(SingleBean data) {
                SingleLeftAdapter adapter = new SingleLeftAdapter(getContext());
                rightAdapter = new SingleRightAdapter(getContext());

                rightAdapter.setRightDataHead(data);
                rightLv.setAdapter(rightAdapter);

                adapter.setLeftData(data);
                leftLv.setAdapter(adapter);

                leftLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        rightLv.setSelection(position);
                        rightAdapter.setCheckPos(position);
                    }
                });


                rightLv.setOnScrollListener(new AbsListView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {

                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                        leftLv.smoothScrollToPositionFromTop(firstVisibleItem,0);
                    }
                });
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
