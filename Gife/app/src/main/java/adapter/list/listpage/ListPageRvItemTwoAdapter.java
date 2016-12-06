package adapter.list.listpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import bean.list.ListPageRvBean;

/**
 * Created by VolleyYang on 16/12/5.
 */

//榜单详情--不同行布局二

public class ListPageRvItemTwoAdapter extends RecyclerView.Adapter<ListPageRvItemTwoAdapter.ItemTwoViewHolder> {
    private Context context;
    private ListPageRvBean data;

    public ListPageRvItemTwoAdapter(Context context) {
        this.context = context;
    }

    public void setData(ListPageRvBean data) {
        this.data = data;
    }

    @Override
    public ItemTwoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_second_page_first_img,parent,false);
        ItemTwoViewHolder viewHolder = new ItemTwoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemTwoViewHolder holder, int position) {
        Picasso.with(context).load(data.getData().getRecommend_posts().get(position).getCover_image_url()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return data!=null&&data.getData().getRecommend_posts().size()>0?data.getData().getRecommend_posts().size():0;
    }

    class ItemTwoViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        public ItemTwoViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.nmb);
        }
    }
}
