package fragment.list;

import android.view.View;
import android.webkit.WebView;

import com.yangshenglong.gife.R;

import activity.listactivity.ListSecondAty;
import base.BaseFragment;

/**
 * Created by yangshenglong on 16/12/2.
 */

public class ListSecondDetailsFragment extends BaseFragment {
    private String url;
    private WebView wb;
    @Override
    public int setLayout() {
        return R.layout.fragment_list_second_details;
    }

    @Override
    public void initView(View view) {

        wb = (WebView) view.findViewById(R.id.details_wb);
    }

    @Override
    public void initData() {
        url = ListSecondAty.sendUrl();

        wb.loadUrl(url);
    }
}
