package activity.siftactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yangshenglong.gife.R;

public class SiftLvActivity extends AppCompatActivity {

    private WebView webView;
    private String url;
    private String urlReuse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sift_lv);
        webView = (WebView) findViewById(R.id.webView);
        Intent intent = getIntent();
        url = intent.getStringExtra("lvKey");
        urlReuse = intent.getStringExtra("key");
        init();

    }

    private void init() {
        webView.loadUrl(url);
        webView.loadUrl(urlReuse);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }
}
