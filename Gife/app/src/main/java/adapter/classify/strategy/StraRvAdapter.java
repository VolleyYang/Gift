package adapter.classify.strategy;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.VolleyError;
import com.yangshenglong.gife.R;

import bean.classify.StraOneBean;
import bean.classify.StraTwoBean;
import staticclass.StaticClass;
import volley.NetHelper;
import volley.NetListener;

/**
 * Created by yangshenglong on 16/11/26.
 */

public class StraRvAdapter extends RecyclerView.Adapter {
    private Context context;
    private View view;


    public StraRvAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;
        }
        return 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.item_stra_head_one, parent, false);
                holder = new OneViewHolder(view);
                break;
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.item_stra_head_two, parent, false);
                holder = new TwoViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int item = getItemViewType(position);
        switch (item) {
            case 0:
                final OneViewHolder oneViewHolder = (OneViewHolder) holder;
                String urlOne = StaticClass.CLASSIFYSTRA;
                NetHelper.MyRequest(urlOne, StraOneBean.class, new NetListener<StraOneBean>() {
                    @Override
                    public void successListener(StraOneBean data) {
                        StraHeadOneAdapter oneAdapter = new StraHeadOneAdapter(context, 0);
                        oneAdapter.setData(data);
                        oneViewHolder.oneRv.setAdapter(oneAdapter);
                        GridLayoutManager manager = new GridLayoutManager(context, 3, LinearLayoutManager.HORIZONTAL, false);
                        oneViewHolder.oneRv.setLayoutManager(manager);
                    }

                    @Override
                    public void errorListener(VolleyError error) {

                    }
                });
                break;
            case 1:
                final TwoViewHolder twoViewHolder = (TwoViewHolder) holder;

                String twoUrl = StaticClass.CLASSIFYTWO;
                NetHelper.MyRequest(twoUrl, StraTwoBean.class, new NetListener<StraTwoBean>() {
                    @Override
                    public void successListener(StraTwoBean data) {
                        StraGridViewAdapterOne FirstAdapter = new StraGridViewAdapterOne(context);
                        StraGridViewAdapterTwo SecondAdapter = new StraGridViewAdapterTwo(context);
                        StraGridViewAdapterThree ThirdAdapter = new StraGridViewAdapterThree(context);
                        FirstAdapter.setData(data);
                        SecondAdapter.setData(data);
                        ThirdAdapter.setData(data);
                        twoViewHolder.oneGridView.setAdapter(FirstAdapter);
                        twoViewHolder.twoGridView.setAdapter(SecondAdapter);
                        twoViewHolder.threeGridView.setAdapter(ThirdAdapter);

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
        return 2;
    }

    class OneViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView oneRv;

        public OneViewHolder(View itemView) {
            super(itemView);
            oneRv = (RecyclerView) itemView.findViewById(R.id.stra_head_rv_one);
        }
    }

    class TwoViewHolder extends RecyclerView.ViewHolder {
        private GridView oneGridView, twoGridView, threeGridView;

        public TwoViewHolder(View itemView) {
            super(itemView);
            oneGridView = (GridView) itemView.findViewById(R.id.stra_gv_one);
            twoGridView = (GridView) itemView.findViewById(R.id.stra_gv_two);
            threeGridView = (GridView) itemView.findViewById(R.id.stra_gv_three);
        }
    }
}
