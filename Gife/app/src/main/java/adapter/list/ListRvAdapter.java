package adapter.list;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import bean.list.ListRvBean;
import port.ListRvOnClick;

/**
 * Created by yangshenglong on 16/11/25.
 */

public class ListRvAdapter extends RecyclerView.Adapter<ListRvAdapter.ListViewHolder> {
    private static ListRvBean data;
    private Context context;
    int pot;
    private ListRvOnClick onClick;

    public ListRvAdapter(Context context,int pot) {
        this.context = context;
        this.pot = pot;
    }


    public void setOnClick(ListRvOnClick onClick) {
        this.onClick = onClick;
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
    public void onBindViewHolder(final ListViewHolder holder, final int position) {
        Picasso.with(context).load(data.getData().getItems().get(position).getCover_image_url()).config(Bitmap.Config.ARGB_4444).into(holder.rvImg);
        holder.tvDp.setText(data.getData().getItems().get(position).getShort_description());
        holder.tvName.setText(data.getData().getItems().get(position).getName());
        holder.tvPrice.setText(data.getData().getItems().get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkPos = holder.getAdapterPosition();
                onClick.MyOnClick(checkPos);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.getData() != null ? data.getData().getItems().size() : 0;
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
