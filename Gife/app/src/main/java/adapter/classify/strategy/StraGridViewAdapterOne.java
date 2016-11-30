package adapter.classify.strategy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import bean.classify.StraTwoBean;
import bean.home.SixRuleBean;

/**
 * Created by yangshenglong on 16/11/24.
 */

public class StraGridViewAdapterOne extends BaseAdapter {
    private StraTwoBean data;
    private Context context;
    private int pos;

    public StraGridViewAdapterOne(Context context) {
        this.context = context;
    }

    public void setPos(int pos) {
        this.pos = pos;
        notifyDataSetChanged();
    }

    public void setData(StraTwoBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return data.getData().getChannel_groups().get(pos).getChannels().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder = null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_gridview,parent,false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MyViewHolder) convertView.getTag();
        }
            Picasso.with(context).load(data.getData().getChannel_groups().get(pos).getChannels().get(position).getCover_image_url()).into(holder.img);
        return convertView;
    }
    class MyViewHolder{
        private ImageView img;
        public MyViewHolder(View view) {
            img = (ImageView) view.findViewById(R.id.grid_img);
        }
    }
}
