package adapter.classify.single;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yangshenglong.gife.R;

import java.util.ArrayList;

import bean.classify.SingleBean;

/**
 * Created by yangshenglong on 16/11/30.
 */

public class SingleLeftAdapter extends BaseAdapter {
    private SingleBean leftData;
    private Context context;




    public SingleLeftAdapter(Context context) {
        this.context = context;
    }

    public void setLeftData(SingleBean leftData) {
        this.leftData = leftData;
    }



    @Override
    public int getCount() {
        return leftData != null && leftData.getData().getCategories().size() > 0 ? leftData.getData().getCategories().size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return leftData.getData().getCategories().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LeftViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_single_left, parent, false);
            holder = new LeftViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (LeftViewHolder) convertView.getTag();
        }
        holder.leftTv.setText(leftData.getData().getCategories().get(position).getName());


        return convertView;
    }

    class LeftViewHolder {
        private TextView leftTv;

        public LeftViewHolder(View view) {
            leftTv = (TextView) view.findViewById(R.id.single_left_tv);
        }
    }
}
