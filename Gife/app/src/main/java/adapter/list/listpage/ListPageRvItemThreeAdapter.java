package adapter.list.listpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import bean.list.ListPageRvBean;

/**
 * Created by VolleyYang on 16/12/5.
 */
//榜单详情--不同行布局三


public class ListPageRvItemThreeAdapter extends RecyclerView.Adapter<ListPageRvItemThreeAdapter.ItemTwoViewHolder> {
    private Context context;
    private ListPageRvBean data;

    public ListPageRvItemThreeAdapter(Context context) {
        this.context = context;
    }

    public void setData(ListPageRvBean data) {
        this.data = data;
    }

    @Override
    public ItemTwoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_second_page_second_img,parent,false);
        ItemTwoViewHolder viewHolder = new ItemTwoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemTwoViewHolder holder, int position) {
        Picasso.with(context).load(data.getData().getRecommend_items().get(position).getCover_image_url()).into(holder.img);
        holder.pageName.setText(data.getData().getRecommend_items().get(position).getName());
        holder.pagePrice.setText(data.getData().getRecommend_items().get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    class ItemTwoViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView pageName,pagePrice;
        public ItemTwoViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.page_cnm);
            pageName = (TextView) itemView.findViewById(R.id.page_name);
            pagePrice = (TextView) itemView.findViewById(R.id.page_price);
        }
    }
}
