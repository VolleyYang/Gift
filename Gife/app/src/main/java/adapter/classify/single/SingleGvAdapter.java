package adapter.classify.single;

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

import bean.classify.SingleBean;

/**
 * Created by yangshenglong on 16/11/30.
 */

public class SingleGvAdapter extends BaseAdapter {
    private Context context;
    private SingleBean data;
    private int pos;
    private static String id;


    public SingleGvAdapter(Context context) {
        this.context = context;
    }

    public void setData(SingleBean data) {
        this.data = data;
        notifyDataSetChanged();
    }


    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public int getCount() {
        return data==null?0:data.getData().getCategories().get(pos).getSubcategories().size();
    }

    @Override
    public Object getItem(int position) {
        return data!=null?data.getData().getCategories().get(pos).getSubcategories().get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        GvViewHolder holder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_single_gridview,parent,false);
            holder = new GvViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (GvViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(data.getData().getCategories().get(pos).getSubcategories().get(position).getIcon_url()).into(holder.gvImg);
        holder.gvTv.setText(data.getData().getCategories().get(pos).getSubcategories().get(position).getName());

        return convertView;
    }
    class GvViewHolder{
        private ImageView gvImg;
        private TextView gvTv;
        public GvViewHolder(View view) {
            gvImg = (ImageView) view.findViewById(R.id.single_gv_img);
            gvTv = (TextView) view.findViewById(R.id.single_gv_tv);
        }
    }

}
