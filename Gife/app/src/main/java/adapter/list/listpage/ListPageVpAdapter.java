package adapter.list.listpage;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import bean.list.ListPageItemOneBean;

/**
 * Created by VolleyYang on 16/12/5.
 */

public class ListPageVpAdapter extends PagerAdapter {
    private ListPageItemOneBean data;
    private Context context;

    public ListPageVpAdapter(Context context) {
        this.context = context;
    }

    public void setData(ListPageItemOneBean data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data != null ? data.getData().getImage_urls().size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_page_vp, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.vp_nmb);
        Picasso.with(context).load(data.getData().getImage_urls().get(position)).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
