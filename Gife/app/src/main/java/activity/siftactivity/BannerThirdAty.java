package activity.siftactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.yangshenglong.gife.R;

public class BannerThirdAty extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_third_aty);

        webView = (WebView) findViewById(R.id.banner_third_web);

        Intent  intent = getIntent();
        String web = intent.getStringExtra("key");

        webView.loadUrl(web);
    }
}
