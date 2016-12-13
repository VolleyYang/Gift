package activity.siftactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.yangshenglong.gife.R;

import adapter.home.SixSecondAdapter;
import bean.home.SixSecondBean;
import volley.NetHelper;
import volley.NetListener;

public class SixActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lv;
    private Intent intent;

    SixSecondBean web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);
        lv = (ListView) findViewById(R.id.six_second_lv);

        //点击事件
        lv.setOnItemClickListener(this);

        intent = getIntent();
        String data = intent.getStringExtra("url");
        if (!data.isEmpty()) {
            data = data.substring(data.length() - 3, data.length());
        }

        String url = "http://api.liwushuo.com/v2/collections/" + data + "/posts?limit=20&offset=0";
        NetHelper.MyRequest(url, SixSecondBean.class, new NetListener<SixSecondBean>() {
            @Override
            public void successListener(SixSecondBean data) {
                web = data;
                SixSecondAdapter adapter = new SixSecondAdapter(getBaseContext());
                adapter.setData(data);
                lv.setAdapter(adapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String webUrl = web.getData().getPosts().get(position).getContent_url();
        intent = new Intent(this,SixThirdAty.class);
        intent.putExtra("key",webUrl);
        startActivity(intent);
    }
}
