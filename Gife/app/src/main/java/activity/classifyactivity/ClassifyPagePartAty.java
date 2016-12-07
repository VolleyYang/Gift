package activity.classifyactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.VolleyError;
import com.yangshenglong.gife.R;

import adapter.classify.partpage.PartPageAdapter;
import bean.classify.PartPageBean;
import volley.NetHelper;
import volley.NetListener;

public class ClassifyPagePartAty extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_page_part_aty);

        recyclerView = (RecyclerView) findViewById(R.id.class_page_rv);


        Intent intent = getIntent();
        String id = intent.getStringExtra("key");

        //解析
        String url = "http://api.liwushuo.com/v2/columns/" + id + "?limit=20&offset=0";
        Log.d("ClassifyPagePartAty", url);
        NetHelper.MyRequest(url, PartPageBean.class, new NetListener<PartPageBean>() {
            @Override
            public void successListener(PartPageBean data) {
                PartPageAdapter adapter = new PartPageAdapter(getBaseContext());
                adapter.setData(data);
                recyclerView.setAdapter(adapter);
                LinearLayoutManager manager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(manager);
            }

            @Override
            public void errorListener(VolleyError error) {
            }
        });
    }
}
