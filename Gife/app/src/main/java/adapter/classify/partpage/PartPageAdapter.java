package adapter.classify.partpage;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.InflateException;
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

public class PartPageAdapter extends RecyclerView.Adapter {
    private Context context;
    private PartPageBean data;
    private View view;
    private static  final int ITEM_TYPE_ONE = 1;
    private static  final int ITEM_TYPE_TWO = 2;


    public PartPageAdapter(Context context) {
        this.context = context;
    }

    public void setData(PartPageBean data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {

       if (position == 0){
           return ITEM_TYPE_ONE;
       }else
           return ITEM_TYPE_TWO;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder  holder = null;
        switch (viewType){
            case ITEM_TYPE_ONE:
                view = LayoutInflater.from(context).inflate(R.layout.item_part_page_one,parent,false);
                holder = new OneViewHolder(view);
                break;
            case ITEM_TYPE_TWO:
                view = LayoutInflater.from(context).inflate(R.layout.item_part_page_below,parent,false);
                holder = new CheckViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int item = getItemViewType(position);
        switch (item){
            case ITEM_TYPE_ONE:
                OneViewHolder oneViewHolder = (OneViewHolder) holder;
                oneViewHolder.tvTitle.setText(data.getData().getTitle());
                oneViewHolder.tvCount.setText(data.getData().getLikes_count()+"");
                oneViewHolder.tvDp.setText(data.getData().getDescription());
                Picasso.with(context).load(data.getData().getCover_image_url()).into(oneViewHolder.img);
                break;
            case ITEM_TYPE_TWO:
                CheckViewHolder checkViewHolder = (CheckViewHolder) holder;
                checkViewHolder.tvCount.setText(data.getData().getPosts().get(position).getLikes_count()+"");
                checkViewHolder.tvTitle.setText(data.getData().getPosts().get(position).getTitle());
                checkViewHolder.tvName.setText(data.getData().getPosts().get(position).getAuthor().getNickname());
                Picasso.with(context).load(data.getData().getPosts().get(position).getCover_image_url()).into(checkViewHolder.img);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data!=null?data.getData().getPosts().size():0;
    }

    class OneViewHolder extends RecyclerView.ViewHolder{
        private ImageView  img;
        private TextView tvDp,tvTitle,tvCount;
        public OneViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.part_page_img);
            tvDp = (TextView) itemView.findViewById(R.id.part_page_dp);
            tvCount = (TextView) itemView.findViewById(R.id.part_tv_count);
            tvTitle = (TextView) itemView.findViewById(R.id.part_tv_title);
        }
    }

    class CheckViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView tvTitle,tvName,tvCount;
        public CheckViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.below_img);
            tvCount = (TextView) itemView.findViewById(R.id.below_count);
            tvName = (TextView) itemView.findViewById(R.id.below_name);
            tvTitle = (TextView) itemView.findViewById(R.id.below_title);
        }
    }

}
