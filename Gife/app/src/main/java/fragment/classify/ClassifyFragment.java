package fragment.classify;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yangshenglong.gife.R;

import java.util.ArrayList;
import java.util.Date;

import adapter.classify.MyClassFragPagerAdapter;
import base.BaseFragment;

/**
 * Created by yangshenglong on 16/11/22.
 */

public class ClassifyFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> data;
    @Override
    public int setLayout() {
        return R.layout.fragment_classify;
    }

    @Override
    public void initView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.class_tb);
        viewPager = (ViewPager) view.findViewById(R.id.class_vp);
    }

    @Override
    public void initData() {
        data=  new ArrayList<>();
        data.add(new StrategyFragment());
        data.add(new SingleFragment());
        MyClassFragPagerAdapter adapter = new MyClassFragPagerAdapter(getChildFragmentManager(),data);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
