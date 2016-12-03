package adapter.list.secondpage;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yangshenglong.gife.R;

import bean.list.ListSecondPageBean;

/**
 * Created by yangshenglong on 16/12/3.
 */

public class ListSecondLvAdapter extends BaseAdapter {

    private Context context;
    private ListSecondPageBean data;

    //第一种行布局类型
    public static final int TYPE_ONE = 0;
    //第二种行布局类型
    public static final int TYPE_TWO = 1;
    //类型总数
    public static final int TYPE_COUNT = 2;

    public ListSecondLvAdapter(Context context) {
        this.context = context;
    }

    public void setData(ListSecondPageBean data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return TYPE_ONE;
        } else {
            return TYPE_TWO;
        }

    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return data != null ? data.getData().getRecommend_items().get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OneViewHolder oneViewHolder = null;
        TwoViewHolder twoViewHolder = null;
        if (convertView == null) {
            switch (getItemViewType(position)) {
                case 0:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_list_second_page_first, parent, false);
                    oneViewHolder = new OneViewHolder(convertView);
                    convertView.setTag(oneViewHolder);
                    break;
                case 1:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_list_second_page_second, parent, false);
                    twoViewHolder = new TwoViewHolder(convertView);
                    convertView.setTag(twoViewHolder);
                    break;
            }
        } else {
            switch (getItemViewType(position)) {
                case 0:
                    oneViewHolder = (OneViewHolder) convertView.getTag();
                    break;
                case 1:
                    twoViewHolder = (TwoViewHolder) convertView.getTag();
                    break;
            }
        }
        switch (getItemViewType(position)) {
            case 0:
                ListSecondPageFirstRvAdapter firstRvAdapter = new ListSecondPageFirstRvAdapter(context);
                firstRvAdapter.setData(data);
                oneViewHolder.oneRv.setAdapter(firstRvAdapter);
                LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                oneViewHolder.oneRv.setLayoutManager(manager);
                break;
            case 1:
                ListSecondPageSecondRvAdapter secondRvAdapter = new ListSecondPageSecondRvAdapter(context);
                secondRvAdapter.setData(data);
                twoViewHolder.twoRv.setAdapter(secondRvAdapter);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
                twoViewHolder.twoRv.setLayoutManager(gridLayoutManager);
                break;
        }
        return convertView;
    }

    class OneViewHolder {
        private RecyclerView oneRv;

        public OneViewHolder(View view) {
            oneRv = (RecyclerView) view.findViewById(R.id.list_second_page_first_rv);
        }
    }

    class TwoViewHolder {
        private RecyclerView twoRv;

        public TwoViewHolder(View view) {
            twoRv = (RecyclerView) view.findViewById(R.id.list_second_page_second_rv);
        }
    }
}
