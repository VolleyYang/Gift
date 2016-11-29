package adapter.classify;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by yangshenglong on 16/11/25.
 */

public class MyClassFragPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> data;
    private String[] title = {"攻略","单品"};
    public MyClassFragPagerAdapter(FragmentManager fm,ArrayList<Fragment> data) {
        super(fm);
        this.data= data;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
