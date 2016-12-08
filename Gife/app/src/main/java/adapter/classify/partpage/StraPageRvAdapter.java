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

import java.util.Date;

import bean.classify.StraPageBean;

/**
 * Created by VolleyYang on 16/12/8.
 */

public class StraPageRvAdapter extends RecyclerView.Adapter<StraPageRvAdapter.PageViewHolder> {
    private Context context;
    private StraPageBean data;

    public StraPageRvAdapter(Context context) {
        this.context = context;
    }

    public void setData(StraPageBean data) {
        this.data = data;
    }

    @Override
    public PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_stra_page_rv,parent,false);
        PageViewHolder holder = new PageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PageViewHolder holder, int position) {
        if (data.getData().getItems().get(position).getContent_type() != 1) {
            holder.tvColumnContent.setText(data.getData().getItems().get(position).getColumn().getTitle());
        }
        Picasso.with(context).load(data.getData().getItems().get(position).getAuthor().getAvatar_url()).into(holder.imgAuthor);
        holder.tvTitle.setText(data.getData().getItems().get(position).getAuthor().getIntroduction());
        holder.tvAuthor.setText(data.getData().getItems().get(position).getAuthor().getNickname());
        Picasso.with(context).load(data.getData().getItems().get(position).getCover_image_url()).into(holder.imgPic);
        holder.tvContent.setText(data.getData().getItems().get(position).getIntroduction());
        holder.tvDp.setText(data.getData().getItems().get(position).getTitle());
        holder.tvNumber.setText(data.getData().getItems().get(position).getLikes_count()+"");

    }

    @Override
    public int getItemCount() {
        return data!=null?data.getData().getItems().size():0;
    }

    class PageViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgAuthor, imgPic;
        private TextView tvAuthor, tvDp, tvTitle, tvContent, tvColumnContent, tvNumber;
        public PageViewHolder(View itemView) {
            super(itemView);
            imgAuthor = (ImageView) itemView.findViewById(R.id.page_img_author);
            imgPic = (ImageView) itemView.findViewById(R.id.page_pic);
            tvAuthor = (TextView) itemView.findViewById(R.id.page_tv_author);
            tvContent = (TextView) itemView.findViewById(R.id.page_tv_content);
            tvDp = (TextView) itemView.findViewById(R.id.page_tv_dp);
            tvTitle = (TextView) itemView.findViewById(R.id.page_tv_title);
            tvColumnContent = (TextView) itemView.findViewById(R.id.page_tv_column_content);
            tvNumber = (TextView) itemView.findViewById(R.id.page_number);
        }
    }
}
