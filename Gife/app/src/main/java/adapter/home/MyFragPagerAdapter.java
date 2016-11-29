package adapter.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import bean.home.TitleBean;
import fragment.home.HomeReuseFragment;
import fragment.home.SiftFragment;

/**
 * Created by yangshenglong on 16/11/23.
 */

public  class MyFragPagerAdapter extends FragmentStatePagerAdapter {

    private static TitleBean title;
    public MyFragPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public static void setTitle(TitleBean title) {
        MyFragPagerAdapter.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return new SiftFragment();
        }else {
            return HomeReuseFragment.newInstance(position);
        }
    }

    @Override
    public int getCount() {
        return title!=null?title.getData().getChannels().size():0;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  title.getData().getChannels().get(position).getName();
    }

    public static String getMessage(int position){
        return title.getData().getChannels().get(position).getId() + "" ;
    }


}
