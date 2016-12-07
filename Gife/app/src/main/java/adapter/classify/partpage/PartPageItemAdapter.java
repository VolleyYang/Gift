package adapter.classify.partpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import bean.classify.PartPageBean;

/**
 * Created by VolleyYang on 16/12/6.
 */

public class PartPageItemAdapter extends RecyclerView.Adapter<PartPageItemAdapter.ItemViewHolder> {
    private Context context;
    private PartPageBean data;

    public PartPageItemAdapter(Context context) {
        this.context = context;
    }

    public void setData(PartPageBean data) {
        this.data = data;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_part_page_below,parent,false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.tvCount.setText(data.getData().getPosts().get(position).getLikes_count()+"");
        holder.tvTitle.setText(data.getData().getPosts().get(position).getTitle());
        holder.tvName.setText(data.getData().getPosts().get(position).getAuthor().getNickname());
        Picasso.with(context).load(data.getData().getPosts().get(position).getCover_image_url()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return data!=null?data.getData().getPosts().size():0;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView tvTitle,tvName,tvCount;
        public ItemViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.below_img);
            tvCount = (TextView) itemView.findViewById(R.id.below_count);
            tvName = (TextView) itemView.findViewById(R.id.below_name);
            tvTitle = (TextView) itemView.findViewById(R.id.below_title);
        }
    }
}
