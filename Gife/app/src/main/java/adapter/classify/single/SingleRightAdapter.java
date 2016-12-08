package adapter.classify.single;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import activity.classifyactivity.SinglePageAty;
import bean.classify.SingleBean;
import fragment.classify.NoScrollGridView;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import staticclass.StaticClass;
import volley.NetHelper;
import volley.NetListener;

/**
 * Created by yangshenglong on 16/11/30.
 */

public class SingleRightAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private Context context;
    private SingleBean rightDataHead;
    private BodyViewHolder bodyViewHolder;
    private String nmbId;
    private int checkPos;

    public void setCheckPos(int checkPos) {
        this.checkPos = checkPos;
    }

    public SingleRightAdapter(Context context) {
        this.context = context;
    }

    public void setRightDataHead(SingleBean rightDataHead) {
        this.rightDataHead = rightDataHead;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return rightDataHead==null?0:rightDataHead.getData().getCategories().size();
    }

    @Override
    public Object getItem(int position) {


        return rightDataHead!=null?rightDataHead.getData().getCategories().get(position).getSubcategories().get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

       // checkPos =position;
        bodyViewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_single_right_boby,parent,false);
            bodyViewHolder = new BodyViewHolder(convertView);
            convertView.setTag(bodyViewHolder);
        }else {
            bodyViewHolder = (BodyViewHolder) convertView.getTag();
        }
        SingleGvAdapter gvAdapter = new SingleGvAdapter(context);
        gvAdapter.setData(rightDataHead);


        gvAdapter.setPos(position);
        bodyViewHolder.bodyGv.setAdapter(gvAdapter);

        bodyViewHolder.bodyGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                nmbId = rightDataHead.getData().getCategories().get(position).getSubcategories().get(i).getId()+"";
                Intent intent = new Intent(context, SinglePageAty.class);
                intent.putExtra("key",nmbId);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeadViewHolder headViewHolder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_single_right_head,parent,false);
            headViewHolder=new HeadViewHolder(convertView);
            convertView.setTag(headViewHolder);
        }else {
            headViewHolder = (HeadViewHolder) convertView.getTag();
        }
            headViewHolder.headTv.setText(rightDataHead.getData().getCategories().get(position).getName());

        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return position;
    }
    class HeadViewHolder{
        private TextView headTv;
        public HeadViewHolder(View view) {
            headTv = (TextView) view.findViewById(R.id.single_right_head_tv);
        }
    }
    class BodyViewHolder{
        private NoScrollGridView bodyGv;
        public BodyViewHolder(View view) {
            bodyGv = (NoScrollGridView) view.findViewById(R.id.single_right_body_gv);
        }
    }
}
