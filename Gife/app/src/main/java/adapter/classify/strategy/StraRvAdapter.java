package adapter.classify.strategy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.VolleyError;
import com.yangshenglong.gife.R;

import activity.classifyactivity.ClassifyPagePartAty;
import activity.classifyactivity.ClassifyStraAty;
import bean.classify.StraOneBean;
import bean.classify.StraTwoBean;
import port.ListRvOnClick;
import staticclass.StaticClass;
import volley.NetHelper;
import volley.NetListener;

/**
 * Created by yangshenglong on 16/11/26.
 */

public class StraRvAdapter extends RecyclerView.Adapter {
    private Context context;
    private View view;
    private StraOneBean getData;
    private StraTwoBean datas;
    private Intent intent;


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
                        getData = data;
                        oneAdapter.setData(data);
                        oneViewHolder.oneRv.setAdapter(oneAdapter);
                        GridLayoutManager manager = new GridLayoutManager(context, 3, LinearLayoutManager.HORIZONTAL, false);
                        oneViewHolder.oneRv.setLayoutManager(manager);
                        //接口回调
                        oneAdapter.setOnClick(new ListRvOnClick() {
                            @Override
                            public void MyOnClick(int position) {
                                String id = getData.getData().getColumns().get(position).getId() + "";
                                Intent intent = new Intent(context, ClassifyPagePartAty.class);
                                intent.putExtra("key", id);
                                context.startActivity(intent);

                            }
                        });
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

                        datas = data;
                        StraGridViewAdapterOne FirstAdapter = new StraGridViewAdapterOne(context);
                        StraGridViewAdapterTwo SecondAdapter = new StraGridViewAdapterTwo(context);
                        StraGridViewAdapterThree ThirdAdapter = new StraGridViewAdapterThree(context);

                        FirstAdapter.setData(data);
                        SecondAdapter.setData(data);
                        ThirdAdapter.setData(data);

                        twoViewHolder.oneGridView.setAdapter(FirstAdapter);
                        twoViewHolder.twoGridView.setAdapter(SecondAdapter);
                        twoViewHolder.threeGridView.setAdapter(ThirdAdapter);

                        intent = new Intent(context, ClassifyStraAty.class);

                        twoViewHolder.oneGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String oneId = datas.getData().getChannel_groups().get(0).getChannels().get(position).getId()+"";
                                intent.putExtra("key", oneId);
                                context.startActivity(intent);
                            }
                        });
                        twoViewHolder.twoGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String twoId = datas.getData().getChannel_groups().get(1).getChannels().get(position).getId()+"";
                                Log.d("StraRvAdapter", twoId);
                                intent.putExtra("key",twoId);
                                context.startActivity(intent);
                            }
                        });
                        twoViewHolder.threeGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String threeId = datas.getData().getChannel_groups().get(2).getChannels().get(position).getId()+"";
                                intent.putExtra("key",threeId);
                                context.startActivity(intent);
                            }
                        });
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
