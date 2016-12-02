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

import bean.home.SixSecondBean;

/**
 * Created by yangshenglong on 16/12/1.
 */

public class SixSecondAdapter extends BaseAdapter {
    private Context context;
    private SixSecondBean data;

    public SixSecondAdapter(Context context) {
        this.context = context;
    }

    public void setData(SixSecondBean data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data!=null&&data.getData().getPosts().size()>0?data.getData().getPosts().size():0;
    }

    @Override
    public Object getItem(int position) {
        return data!=null&&data.getData().getPosts().size()>0?data.getData().getPosts().get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SixSecondViewHolder holder= null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_six_second,parent,false);
            holder = new SixSecondViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (SixSecondViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(data.getData().getPosts().get(position).getAuthor().getAvatar_url()).into(holder.sixAuthorImg);
        holder.sixTitle.setText(data.getData().getPosts().get(position).getAuthor().getIntroduction());
        holder.sixColumnContent.setText(data.getData().getPosts().get(position).getColumn().getTitle());
        holder.sixAuthorNameTv.setText(data.getData().getPosts().get(position).getAuthor().getNickname());
        Picasso.with(context).load(data.getData().getPosts().get(position).getCover_image_url()).into(holder.sixPic);
        holder.sixContent.setText(data.getData().getPosts().get(position).getIntroduction());
        holder.sixDp.setText(data.getData().getPosts().get(position).getTitle());
        holder.sixNum.setText(data.getData().getPosts().get(position).getLikes_count()+"");

        return convertView;
    }
    class SixSecondViewHolder{
        private ImageView sixAuthorImg,sixPic;
        private TextView sixAuthorNameTv,sixTitle,sixDp,sixContent,
                sixColumnContent,sixNum;
        public SixSecondViewHolder(View view) {
            sixAuthorImg = (ImageView) view.findViewById(R.id.six_img_author);
            sixPic = (ImageView) view.findViewById(R.id.six_pic);

            sixAuthorNameTv = (TextView) view.findViewById(R.id.six_tv_author);
            sixTitle = (TextView) view.findViewById(R.id.six_tv_title);
            sixDp = (TextView) view.findViewById(R.id.six_tv_dp);
            sixContent = (TextView) view.findViewById(R.id.six_tv_content);
            sixColumnContent = (TextView) view.findViewById(R.id.six_tv_column_content);
            sixNum = (TextView) view.findViewById(R.id.six_number_temp);
        }
    }
}
