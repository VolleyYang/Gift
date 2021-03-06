package adapter.list;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import bean.list.ListRvBean;

/**
 * Created by yangshenglong on 16/11/26.
 */

public class ListHeaderAdapter extends RecyclerView.Adapter {

    private Context context;
    private ListRvBean data;
    private View view;

    public ListHeaderAdapter(Context context) {
        this.context = context;
    }

    public void setData(ListRvBean data) {

        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return 0;
        }else if (position == 1){
            return 1;
        }
        return 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.item_list_head_one,parent,false);
                holder = new OneViewHolder(view);
                break;
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.item_list_head_two,parent,false);
                holder = new TwoViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int item = getItemViewType(position);
        switch (item){
            case 0:
                OneViewHolder  oneHolder= (OneViewHolder) holder;
                Picasso.with(context).load(data.getData().getCover_image()).into(oneHolder.img);
                break;
            case 1:
                TwoViewHolder twoHolder = (TwoViewHolder) holder;
                GridLayoutManager manager = new GridLayoutManager(context,2);
                twoHolder.rv.setLayoutManager(manager);
                ListRvAdapter rvAdapter = new ListRvAdapter(context,1);
                rvAdapter.setData(data);
                twoHolder.rv.setAdapter(rvAdapter);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class OneViewHolder extends RecyclerView.ViewHolder{
        private ImageView  img;
        public OneViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.list_rv_header_img);
        }
    }
    class TwoViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView  rv;
        public TwoViewHolder(View itemView) {
            super(itemView);
            rv = (RecyclerView) itemView.findViewById(R.id.list_head_two_rv);
        }
    }

}
