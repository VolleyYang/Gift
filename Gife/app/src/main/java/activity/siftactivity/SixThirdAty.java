package activity.siftactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.yangshenglong.gife.R;

public class SixThirdAty extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six_third_aty);

        webView = (WebView) findViewById(R.id.six_third_web);
        Intent intent = getIntent();
        String url = intent.getStringExtra("key");
        webView.loadUrl(url);
    }
}
