package adapter.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import bean.list.ListRvBean;

/**
 * Created by yangshenglong on 16/11/25.
 */

public class ListRvAdapter extends RecyclerView.Adapter<ListRvAdapter.ListViewHolder> {
    private static ListRvBean data;
    private Context context;
    int pot;

    public ListRvAdapter(Context context,int pot) {
        this.context = context;
        this.pot = pot;
    }

    public void setData(ListRvBean data) {
        this.data = data;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_rv,parent,false);
        ListViewHolder holder = new ListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        Picasso.with(context).load(data.getData().getItems().get(position).getCover_image_url()).into(holder.rvImg);
        holder.tvDp.setText(data.getData().getItems().get(position).getShort_description());
        holder.tvName.setText(data.getData().getItems().get(position).getName());
        holder.tvPrice.setText(data.getData().getItems().get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return data.getData().getItems().size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{
        private ImageView rvImg;
        private TextView tvDp,tvName,tvPrice;
        public ListViewHolder(View itemView) {
            super(itemView);
            rvImg = (ImageView) itemView.findViewById(R.id.list_rv_img);
            tvDp = (TextView) itemView.findViewById(R.id.list_tv_dp);
            tvName = (TextView) itemView.findViewById(R.id.list_tv_name);
            tvPrice = (TextView) itemView.findViewById(R.id.list_tv_price);
        }
    }
}
