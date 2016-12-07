package adapter.list.listpage;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.yangshenglong.gife.R;

import activity.listactivity.ListSecondAty;
import bean.list.ListPageItemOneBean;
import bean.list.ListPageRvBean;
import bean.list.ListRvBean;
import volley.NetHelper;
import volley.NetListener;

/**
 * Created by yangshenglong on 16/12/5.
 */
//榜单详情--总适配器

public class ListPageRvAdapter extends RecyclerView.Adapter {
    private Context context;
    private View view;
    private ListPageItemOneBean data;
    private String id;
    private String url;


    public ListPageRvAdapter(Context context) {
        this.context = context;
    }

    public void setData(ListPageItemOneBean data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return 0;
        }else if (position == 1){
            return 1;
        }else if (position == 2){
            return 2;
        }
        return 3;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.item_list_second_page_head,parent,false);
                holder = new OneViewHolder(view);
                break;
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.item_list_second_page_first,parent,false);
                holder = new TwoViewHolder(view);
                break;
            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.item_list_second_page_second,parent,false);
                holder = new ThreeViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int item = getItemViewType(position);
        switch (item){
            case 0:
                OneViewHolder oneViewHolder = (OneViewHolder) holder;
                ListPageVpAdapter vpAdapter = new ListPageVpAdapter(context);
                vpAdapter.setData(data);
                oneViewHolder.vp.setAdapter(vpAdapter);
                oneViewHolder.tvSdp.setText(data.getData().getShort_description());
                oneViewHolder.tvPrice.setText(data.getData().getPrice());
                oneViewHolder.tvDp.setText(data.getData().getDescription());
                oneViewHolder.tvName.setText(data.getData().getName());
                oneViewHolder.tvCount.setText(data.getData().getFavorites_count()+"");
                break;
            case 1:
                final TwoViewHolder twoViewHolder = (TwoViewHolder) holder;
                id = ListSecondAty.sendId();
                url = "http://api.liwushuo.com/v2/items/"+id+"/recommend?num=20&post_num=5";
                NetHelper.MyRequest(url, ListPageRvBean.class, new NetListener<ListPageRvBean>() {
                    @Override
                    public void successListener(ListPageRvBean data) {
                        ListPageRvItemTwoAdapter twoAdapter = new ListPageRvItemTwoAdapter(context);
                        twoAdapter.setData(data);
                        twoViewHolder.twoRv.setAdapter(twoAdapter);
                        LinearLayoutManager twoManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
                        twoViewHolder.twoRv.setLayoutManager(twoManager);
                    }

                    @Override
                    public void errorListener(VolleyError error) {
                    }
                });
                break;
            case 2:
                final ThreeViewHolder threeViewHolder = (ThreeViewHolder) holder;
                NetHelper.MyRequest(url, ListPageRvBean.class, new NetListener<ListPageRvBean>() {
                    @Override
                    public void successListener(ListPageRvBean data) {
                        ListPageRvItemThreeAdapter threeAdapter = new ListPageRvItemThreeAdapter(context);
                        threeAdapter.setData(data);
                        threeViewHolder.threeRv.setAdapter(threeAdapter);
                        GridLayoutManager threeManager = new GridLayoutManager(context,2);
                        threeViewHolder.threeRv.setLayoutManager(threeManager);
                    }

                    @Override
                    public void errorListener(VolleyError error) {

                    }
                });
                break;
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }


    class OneViewHolder extends RecyclerView.ViewHolder{
        private TextView tvSdp,tvName,tvPrice,tvCount,tvDp;
        private ViewPager vp;
        public OneViewHolder(View itemView) {
            super(itemView);
            vp = (ViewPager) itemView.findViewById(R.id.list_page_vp);
            tvSdp = (TextView) itemView.findViewById(R.id.list_page_tv_sdp);
            tvCount = (TextView) itemView.findViewById(R.id.list_page_tv_num);
            tvDp = (TextView) itemView.findViewById(R.id.list_page_tv_dp);
            tvName = (TextView) itemView.findViewById(R.id.list_page_tv_name);
            tvPrice = (TextView) itemView.findViewById(R.id.list_page_tv_price);
        }
    }
    class TwoViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView twoRv;
        public TwoViewHolder(View itemView) {
            super(itemView);
            twoRv = (RecyclerView) itemView.findViewById(R.id.list_second_page_first_rv);
        }
    }
    class ThreeViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView threeRv;
        public ThreeViewHolder(View itemView) {
            super(itemView);
            threeRv = (RecyclerView)itemView. findViewById(R.id.list_second_page_second_rv);
        }
    }
}
