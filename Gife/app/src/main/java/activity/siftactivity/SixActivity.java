package activity.siftactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.yangshenglong.gife.R;

import adapter.home.SixSecondAdapter;
import bean.home.SixSecondBean;
import volley.NetHelper;
import volley.NetListener;

public class SixActivity extends AppCompatActivity {

    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);
        lv = (ListView) findViewById(R.id.six_second_lv);


        Intent intent = getIntent();
        String data = intent.getStringExtra("url");
        if (!data.isEmpty()){
            data = data.substring(data.length()-3,data.length());
            Log.d("SixActivity", data);
        }

        String url = "http://api.liwushuo.com/v2/collections/"+data+"/posts?limit=20&offset=0";
        NetHelper.MyRequest(url, SixSecondBean.class, new NetListener<SixSecondBean>() {
            @Override
            public void successListener(SixSecondBean data) {
                SixSecondAdapter adapter = new SixSecondAdapter(getBaseContext());
                adapter.setData(data);
                lv.setAdapter(adapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
