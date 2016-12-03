package activity.listactivity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yangshenglong.gife.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import adapter.list.secondpage.ListSecondPagerAdapter;
import fragment.list.ListSecondDetailsFragment;
import fragment.list.ListSecondSingleFragment;

public class ListSecondAty extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> data;
    private Intent intent;
    private EventBus bus;
    private static String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_second_aty);
        tabLayout = (TabLayout) findViewById(R.id.list_second_tb);
        viewPager = (ViewPager) findViewById(R.id.list_second_vp);

        data = new ArrayList<>();



        data.add(new ListSecondSingleFragment());
        data.add(new ListSecondDetailsFragment());
        ListSecondPagerAdapter adapter = new ListSecondPagerAdapter(getSupportFragmentManager());
        adapter.setData(data);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        intent = getIntent();
        id = intent.getStringExtra("numId");

    }
    public static String sendId(){
        return id;
    }


}
