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

    public PartPageAdapter(Context context) {
        this.context = context;
    }

    public void setData(PartPageBean data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 ){
            return 0;
        }else if (position == 1){
            return 1;
        }
        return 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder  holder = null;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.item_part_page_one,parent,false);
                holder = new OneViewHolder(view);
                break;
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.item_part_page_two,parent,false);
                holder = new TwoViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int item = getItemViewType(position);
        switch (item){
            case 0:
                OneViewHolder oneViewHolder = (OneViewHolder) holder;
                oneViewHolder.tvTitle.setText(data.getData().getTitle());
                oneViewHolder.tvCount.setText(data.getData().getLikes_count()+"");
                oneViewHolder.tvDp.setText(data.getData().getDescription());
                Picasso.with(context).load(data.getData().getCover_image_url()).into(oneViewHolder.img);
                break;
            case 1:
                TwoViewHolder twoViewHolder = (TwoViewHolder) holder;
                PartPageItemAdapter itemAdapter = new PartPageItemAdapter(context);
                itemAdapter.setData(data);
                twoViewHolder.rv.setAdapter(itemAdapter);
                LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
                twoViewHolder.rv.setLayoutManager(manager);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
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
    class TwoViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView rv;
        public TwoViewHolder(View itemView) {
            super(itemView);
            rv = (RecyclerView) itemView.findViewById(R.id.part_rv);
        }
    }
}
