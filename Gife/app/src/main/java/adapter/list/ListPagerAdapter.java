package adapter.list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import bean.list.ListBean;
import fragment.list.ListReuseFragment;

/**
 * Created by yangshenglong on 16/11/24.
 */

public class ListPagerAdapter extends FragmentStatePagerAdapter {
    private static ListBean title;

    public void setTitle(ListBean title) {
        this.title = title;
    }

    public ListPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ListReuseFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return title.getData().getRanks().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.getData().getRanks().get(position).getName();
    }



    public static String getMessage(int position){
        return title.getData().getRanks().get(position).getId()+ "";

    }


}
