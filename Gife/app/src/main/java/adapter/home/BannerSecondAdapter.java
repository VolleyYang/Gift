package adapter.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import bean.home.BannerSecondBean;

/**
 * Created by yangshenglong on 16/12/1.
 */

public class BannerSecondAdapter extends BaseAdapter {
    private Context context;
    private BannerSecondBean data;

    public BannerSecondAdapter(Context context) {
        this.context = context;
    }

    public void setData(BannerSecondBean data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data!=null&&data.getData().getPosts().size()>0?data.getData().getPosts().size():0;
    }

    @Override
    public Object getItem(int position) {
        return data!=null&&data.getData().getPosts().size()>0?data.getData().getPosts().get(position): null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BannerViewHolder holder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_banner_second_lv,parent,false);
            holder = new BannerViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (BannerViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(data.getData().getPosts().get(position).getAuthor().getAvatar_url()).into(holder.authorImg);
        holder.bannerTitle.setText(data.getData().getPosts().get(position).getAuthor().getIntroduction());
        holder.bannerColumnContent.setText(data.getData().getPosts().get(position).getColumn().getTitle());
        holder.authorNameTv.setText(data.getData().getPosts().get(position).getAuthor().getNickname());
        Picasso.with(context).load(data.getData().getPosts().get(position).getCover_image_url()).into(holder.bannerPic);
        holder.bannerContent.setText(data.getData().getPosts().get(position).getIntroduction());
        holder.bannerDp.setText(data.getData().getPosts().get(position).getTitle());
        holder.bannerNum.setText(data.getData().getPosts().get(position).getLikes_count()+"");

        return convertView;
    }

    class BannerViewHolder{
        private ImageView  authorImg,bannerPic;
        private TextView authorNameTv,bannerTitle,bannerDp,bannerContent,
        bannerColumn,bannerColumnContent,bannerNum;
        public BannerViewHolder(View view) {
            authorImg = (ImageView) view.findViewById(R.id.banner_img_author);
            bannerPic = (ImageView) view.findViewById(R.id.banner_pic);

            authorNameTv = (TextView) view.findViewById(R.id.banner_tv_author);
            bannerTitle = (TextView) view.findViewById(R.id.banner_tv_title);
            bannerDp = (TextView) view.findViewById(R.id.banner_tv_dp);
            bannerContent = (TextView) view.findViewById(R.id.banner_tv_content);
            bannerColumn = (TextView) view.findViewById(R.id.banner_tv_column);
            bannerColumnContent = (TextView) view.findViewById(R.id.banner_tv_column_content);
            bannerNum = (TextView) view.findViewById(R.id.banner_number_temp);
        }
    }
}
