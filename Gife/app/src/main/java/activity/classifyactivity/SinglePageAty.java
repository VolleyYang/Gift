package activity.classifyactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.VolleyError;
import com.yangshenglong.gife.R;

import adapter.classify.singlepage.SinglePageRvAdapter;
import bean.classify.SinglePageBean;
import volley.NetHelper;
import volley.NetListener;

public class SinglePageAty extends AppCompatActivity {

    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_page_aty);

        rv = (RecyclerView) findViewById(R.id.single_page_rv);
        Intent intent = getIntent();
        String id = intent.getStringExtra("key");
        Log.d("SinglePageAty", id);

        String url = "http://api.liwushuo.com/v2/item_subcategories/"+id+"/items?limit=20&offset=0";
        NetHelper.MyRequest(url, SinglePageBean.class, new NetListener<SinglePageBean>() {
            @Override
            public void successListener(SinglePageBean data) {
                SinglePageRvAdapter adapter = new SinglePageRvAdapter(getBaseContext());
                adapter.setData(data);
                rv.setAdapter(adapter);
                GridLayoutManager manager = new GridLayoutManager(getBaseContext(),2);
                rv.setLayoutManager(manager);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
