package activity.siftactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.yangshenglong.gife.R;

import adapter.home.BannerSecondAdapter;
import adapter.home.HomeLvReuseAdapter;
import bean.home.BannerSecondBean;
import bean.home.SiftSecondBean;
import volley.NetHelper;
import volley.NetListener;

public class SiftActivity extends AppCompatActivity {

    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sift);

        lv = (ListView) findViewById(R.id.sift_second_lv);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Log.d("SiftActivity", id);
        String  url = "http://api.liwushuo.com/v2/collections/"+id+"/posts?limit=20&offset=0";
        if (id != null&&id!= "0"){
            NetHelper.MyRequest(url, BannerSecondBean.class, new NetListener<BannerSecondBean>() {
                @Override
                public void successListener(BannerSecondBean data) {
                    BannerSecondAdapter adapter = new BannerSecondAdapter(getBaseContext());
                    adapter.setData(data);
                    lv.setAdapter(adapter);

                }

                @Override
                public void errorListener(VolleyError error) {

                }
            });
        }else if (id == "0"){


        }
    }
}
