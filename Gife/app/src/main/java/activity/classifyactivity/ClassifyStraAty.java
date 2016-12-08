package activity.classifyactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.VolleyError;
import com.yangshenglong.gife.R;

import adapter.classify.partpage.StraPageRvAdapter;
import bean.classify.StraPageBean;
import volley.NetHelper;
import volley.NetListener;

public class ClassifyStraAty extends AppCompatActivity {

    private String id;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_stra_aty);
        rv = (RecyclerView) findViewById(R.id.class_stra_aty_rv);
        Intent intent = getIntent();
        id = intent.getStringExtra("key");
        String url = "http://api.liwushuo.com/v2/channels/"+id+"/items_v2?order_by=now&limit=20&offset=0";
        NetHelper.MyRequest(url, StraPageBean.class, new NetListener<StraPageBean>() {
            @Override
            public void successListener(StraPageBean data) {
                StraPageRvAdapter adapter = new StraPageRvAdapter(getBaseContext());
                adapter.setData(data);
                rv.setAdapter(adapter);
                LinearLayoutManager manager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
                rv.setLayoutManager(manager);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
