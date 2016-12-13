package adapter.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import activity.listactivity.ListSecondAty;
import bean.list.ListRvBean;
import port.ListRvOnClick;

/**
 * Created by yangshenglong on 16/11/26.
 */

public class ListHeaderAdapter extends RecyclerView.Adapter  {

    private Context context;
    private ListRvBean data;
    private View view;
    private ListRvAdapter rvAdapter;


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
                rvAdapter = new ListRvAdapter(context,1);
                rvAdapter.setData(data);
                twoHolder.rv.setAdapter(rvAdapter);
                GridLayoutManager manager = new GridLayoutManager(context,2);
                twoHolder.rv.setLayoutManager(manager);

                //接口回调
                rvAdapter.setOnClick(new ListRvOnClick() {
                    @Override
                    public void MyOnClick(int position) {
                        String title =data.getData().getItems().get(position).getName();
                        String description =data.getData().getItems().get(position).getShort_description();
                        String  price = data.getData().getItems().get(position).getPrice();
                        String img = data.getData().getItems().get(position).getCover_image_url();
                        final String i= data.getData().getItems().get(position).getId()+"";
                        String url = data.getData().getItems().get(position).getUrl();
                        Intent intent  = new Intent(context, ListSecondAty.class);
                        intent.putExtra("numId",i);
                        intent.putExtra("url",url);
                        //存数据库中
                        intent.putExtra("title",title);
                        intent.putExtra("dp",description);
                        intent.putExtra("price",price);
                        intent.putExtra("imgUrl",img);

                        context.startActivity(intent);


                    }
                });
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
