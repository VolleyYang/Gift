package adapter.classify.strategy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import java.util.Date;

import bean.classify.StraOneBean;
import port.ListRvOnClick;

/**
 * Created by yangshenglong on 16/11/26.
 */

public class StraHeadOneAdapter extends RecyclerView.Adapter<StraHeadOneAdapter.OneHeadViewHolder> {
    private Context context;
    private StraOneBean data;
    private ListRvOnClick onClick;

    int pot;
    public StraHeadOneAdapter(Context context,int pot) {
        this.context = context;
        this.pot = pot;
    }

    public void setData(StraOneBean data) {
        this.data = data;
    }

    public void setOnClick(ListRvOnClick onClick) {
        this.onClick = onClick;
    }

    @Override
    public OneHeadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(context).inflate(R.layout.item_stra_head_one_rv,parent,false);
        OneHeadViewHolder holder = new OneHeadViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(OneHeadViewHolder holder, final int position) {
        holder.tvDp.setText(data.getData().getColumns().get(position).getTitle());
        holder.tvAuthor.setText(data.getData().getColumns().get(position).getAuthor());
        Picasso.with(context).load(data.getData().getColumns().get(position).getBanner_image_url()).into(holder.straImg);
        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.MyOnClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data!=null&&data.getData().getColumns().size()>0?data.getData().getColumns().size():0;
    }

    class OneHeadViewHolder extends RecyclerView.ViewHolder{

        private TextView tvDp,tvAuthor;
        private ImageView straImg;
        public OneHeadViewHolder(View itemView) {
            super(itemView);

            tvDp = (TextView) itemView.findViewById(R.id.stra_head_one_dp);
            tvAuthor= (TextView) itemView.findViewById(R.id.stra_head_one_author);
            straImg = (ImageView) itemView.findViewById(R.id.stra_head_one_pic);
        }
    }
}
