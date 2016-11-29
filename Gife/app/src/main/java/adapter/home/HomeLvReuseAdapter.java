package adapter.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;


import bean.home.SiftReuseBean;

/**
 * Created by yangshenglong on 16/11/24.
 */

public class HomeLvReuseAdapter extends BaseAdapter {
    private SiftReuseBean data;
    private Context context;

    public HomeLvReuseAdapter(Context context) {
        this.context = context;
    }

    public void setData(SiftReuseBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data!=null&&data.getData().getItems().size()>0?data.getData().getItems().size():0;
    }

    @Override
    public Object getItem(int position) {
        return data.getData().getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sift_reuse,parent,false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MyViewHolder) convertView.getTag();
        }
            Picasso.with(context).load(data.getData().getItems().get(position).getAuthor().getAvatar_url()).into(holder.imgAuthor);
            Picasso.with(context).load(data.getData().getItems().get(position).getCover_image_url()).into(holder.imgPic);
            holder.tvAuthor.setText(data.getData().getItems().get(position).getAuthor().getNickname());
            holder.tvTitle.setText(data.getData().getItems().get(position).getAuthor().getIntroduction());
            holder.tvDp.setText(data.getData().getItems().get(position).getTitle());
            holder.tvContent.setText(data.getData().getItems().get(position).getIntroduction());
            holder.tvColumnContent.setText(data.getData().getItems().get(position).getColumn().getTitle());
            holder.tvNumber.setText(data.getData().getItems().get(position).getLikes_count() + "");
        Log.d("HomeLvReuseAdapter", "holder.tvNumber:" + holder.tvNumber);

        return convertView;
    }
    class MyViewHolder {
        private ImageView imgAuthor,imgPic;
        private TextView tvAuthor,tvDp,tvTitle,tvContent,tvColumnContent,tvNumber;
        public MyViewHolder(View view) {
            imgAuthor = (ImageView) view.findViewById(R.id.reuse_img_author);
            imgPic = (ImageView) view.findViewById(R.id.reuse_pic);
            tvAuthor = (TextView) view.findViewById(R.id.reuse_tv_author);
            tvContent = (TextView) view.findViewById(R.id.reuse_tv_content);
            tvDp = (TextView) view.findViewById(R.id.reuse_tv_dp);
            tvTitle = (TextView) view.findViewById(R.id.reuse_tv_title);
            tvColumnContent = (TextView) view.findViewById(R.id.reuse_tv_column_content);
            tvNumber = (TextView) view.findViewById(R.id.number_temp);
        }
    }
}
