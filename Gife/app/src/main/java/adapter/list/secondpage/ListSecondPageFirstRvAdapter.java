package adapter.list.secondpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import bean.list.ListSecondPageBean;

/**
 * Created by yangshenglong on 16/12/3.
 */

public class ListSecondPageFirstRvAdapter extends RecyclerView.Adapter<ListSecondPageFirstRvAdapter.FirstViewHolder> {
    private Context context;
    private ListSecondPageBean data;


    public ListSecondPageFirstRvAdapter(Context context) {
        this.context = context;
    }

    public void setData(ListSecondPageBean data) {
        this.data = data;
    }

    @Override
    public FirstViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_second_page_first_img,parent,false);
        FirstViewHolder holder = new FirstViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FirstViewHolder holder, int position) {

        Picasso.with(context).load(data.getData().getRecommend_posts().get(position).getCover_image_url()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data!=null&&data.getData().getRecommend_posts().size()>0?data.getData().getRecommend_posts().size():0;
    }

    class FirstViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public FirstViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.nmb);
        }
    }
}
