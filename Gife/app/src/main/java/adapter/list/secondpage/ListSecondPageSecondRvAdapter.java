package adapter.list.secondpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import bean.list.ListSecondPageBean;

/**
 * Created by yangshenglong on 16/12/3.
 */

public class ListSecondPageSecondRvAdapter extends RecyclerView.Adapter<ListSecondPageSecondRvAdapter.SecondViewHolder> {
    private Context context;
    private ListSecondPageBean data;

    public ListSecondPageSecondRvAdapter(Context context) {
        this.context = context;
    }

    public void setData(ListSecondPageBean data) {
        this.data = data;
    }

    @Override
    public SecondViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_second_page_second_img,parent,false);
        SecondViewHolder holder = new SecondViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SecondViewHolder holder, int position) {
        Picasso.with(context).load(data.getData().getRecommend_items().get(position).getCover_image_url()).into(holder.img);
        holder.tvOne.setText(data.getData().getRecommend_items().get(position).getName());
        holder.tvTwo.setText(data.getData().getRecommend_items().get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    class SecondViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView tvOne,tvTwo;
        public SecondViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.cnm);
            tvOne = (TextView) itemView.findViewById(R.id.page_tv);
            tvTwo = (TextView) itemView.findViewById(R.id.page_price);
        }
    }
}
