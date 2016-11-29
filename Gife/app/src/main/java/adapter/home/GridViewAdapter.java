package adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import bean.home.SixRuleBean;

/**
 * Created by yangshenglong on 16/11/24.
 */

public class GridViewAdapter extends BaseAdapter {
    private SixRuleBean data;
    private Context context;

    public GridViewAdapter(Context context) {
        this.context = context;
    }

    public void setData(SixRuleBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data!=null&& data.getData().getSecondary_banners().size()>0?data.getData().getSecondary_banners().size():0;
    }

    @Override
    public Object getItem(int position) {
        return data.getData().getSecondary_banners().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SixViewHolder holder = null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_sixrule,parent,false);
            holder = new SixViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (SixViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(data.getData().getSecondary_banners().get(position).getImage_url()).into(holder.img);
        return convertView;
    }
    class SixViewHolder{
        private ImageView img;
        public SixViewHolder(View view) {
            img = (ImageView) view.findViewById(R.id.six_img);
        }
    }
}
