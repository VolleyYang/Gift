package adapter.list.listpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import java.util.ArrayList;

import GreenDao.Person;

/**
 * Created by VolleyYang on 16/12/12.
 */

public class ListCollectAdapter extends BaseAdapter {
    private ArrayList<Person> data;
    private Context context;

    public ListCollectAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Person> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data!=null&&data.size()>0?data.size():0;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_collect,parent,false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.tvPrice.setText(data.get(position).getPrice());
        holder.tvDp.setText(data.get(position).getDescription());
        holder.tvTitle.setText(data.get(position).getContent());
        Picasso.with(context).load(data.get(position).getImg()).into(holder.img);
        return convertView;
    }

    class MyViewHolder {
        private ImageView img;
        private TextView tvTitle,tvDp,tvPrice;
        public MyViewHolder(View view) {
            img = (ImageView) view.findViewById(R.id.collect_img);
            tvTitle = (TextView) view.findViewById(R.id.collect_title);
            tvDp = (TextView) view.findViewById(R.id.collect_dp);
            tvPrice = (TextView) view.findViewById(R.id.collect_price);
        }
    }
}
