package fragment.classify;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yangshenglong.gife.R;

import adapter.classify.strategy.StraRvAdapter;
import base.BaseFragment;

/**
 * Created by yangshenglong on 16/11/25.
 */

public class StrategyFragment extends BaseFragment {
    private RecyclerView strategyRv;

    @Override
    public int setLayout() {
        return R.layout.fragment_strategy;
    }

    @Override
    public void initView(View view) {
        strategyRv = (RecyclerView) view.findViewById(R.id.strategy_rv);
    }

    @Override
    public void initData() {

        StraRvAdapter adapter = new StraRvAdapter(getContext());
        strategyRv.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        strategyRv.setLayoutManager(manager);
        adapter.notifyDataSetChanged();
    }
}
