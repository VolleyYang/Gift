package adapter.classify.singlepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import bean.classify.SinglePageBean;

/**
 * Created by VolleyYang on 16/12/8.
 */

public class SinglePageRvAdapter extends RecyclerView.Adapter<SinglePageRvAdapter.PageViewHolder> {
    private Context context;
    private SinglePageBean data;

    public SinglePageRvAdapter(Context context) {
        this.context = context;
    }

    public void setData(SinglePageBean data) {
        this.data = data;
    }

    @Override
    public PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_classify_single_page,parent,false);
        PageViewHolder holder = new PageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PageViewHolder holder, int position) {
        Picasso.with(context).load(data.getData().getItems().get(position).getCover_image_url()).into(holder.pageImg);
        holder.tvPrice.setText(data.getData().getItems().get(position).getPrice());
        holder.tvDp.setText(data.getData().getItems().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data!=null?data.getData().getItems().size():0;
    }

    class PageViewHolder extends RecyclerView.ViewHolder{
        private ImageView  pageImg;
        private TextView tvDp,tvPrice;
        public PageViewHolder(View itemView) {
            super(itemView);
            pageImg = (ImageView) itemView.findViewById(R.id.single_page_pic);
            tvDp = (TextView) itemView.findViewById(R.id.single_page_dp);
            tvPrice = (TextView) itemView.findViewById(R.id.single_page_price);
        }
    }
}
