package adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import bean.home.SiftBean;

/**
 * Created by yangshenglong on 16/11/23.
 */

public class HomeListViewAdapter extends BaseAdapter {
    private SiftBean data;
    private Context context;

    public HomeListViewAdapter(Context context) {
        this.context = context;
    }

    public void setData(SiftBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null && data.getData().getItems().size() > 0 ? data.getData().getItems().size() : 0;
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sift, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }
        if (data.getData().getItems().get(position).getContent_type() != 1) {
            Picasso.with(context).load(data.getData().getItems().get(position).getAuthor().getAvatar_url()).into(holder.imgAuthor);
            holder.tvTitle.setText(data.getData().getItems().get(position).getAuthor().getIntroduction());
            holder.tvColumnContent.setText(data.getData().getItems().get(position).getColumn().getTitle());
            holder.tvAuthor.setText(data.getData().getItems().get(position).getAuthor().getNickname());
        }
        Picasso.with(context).load(data.getData().getItems().get(position).getCover_image_url()).into(holder.imgPic);
        holder.tvContent.setText(data.getData().getItems().get(position).getIntroduction());
        holder.tvDp.setText(data.getData().getItems().get(position).getTitle());
        holder.tvNumber.setText(data.getData().getItems().get(position).getLikes_count()+"");
        return convertView;
    }

    class ViewHolder {
        private ImageView imgAuthor, imgPic;
        private TextView tvAuthor, tvDp, tvTitle, tvContent, tvColumnContent, tvNumber;

        public ViewHolder(View view) {
            imgAuthor = (ImageView) view.findViewById(R.id.sift_img_author);
            imgPic = (ImageView) view.findViewById(R.id.sift_pic);
            tvAuthor = (TextView) view.findViewById(R.id.sift_tv_author);
            tvContent = (TextView) view.findViewById(R.id.sift_tv_content);
            tvDp = (TextView) view.findViewById(R.id.sift_tv_dp);
            tvTitle = (TextView) view.findViewById(R.id.sift_tv_title);
            tvColumnContent = (TextView) view.findViewById(R.id.sift_tv_column_content);
            tvNumber = (TextView) view.findViewById(R.id.number);
        }
    }
}
