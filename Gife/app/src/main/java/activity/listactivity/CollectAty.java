package activity.listactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.widget.GridView;

import com.squareup.picasso.Picasso;
import com.yangshenglong.gife.R;

import java.util.ArrayList;
import java.util.Date;

import GreenDao.DBToll;
import GreenDao.Person;
import adapter.list.listpage.ListCollectAdapter;
import fragment.classify.NoScrollGridView;

public class CollectAty extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<Person> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_aty);
        gridView = (GridView) findViewById(R.id.stra_collect_gv);
        //查询所有
        data = new ArrayList<>();
        for (int i = 0; i < DBToll.getInstance().queryAll().size(); i++) {

            data.add(DBToll.getInstance().queryAll().get(i));
        }
        ListCollectAdapter adapter = new ListCollectAdapter(getBaseContext());
        adapter.setData(data);
        gridView.setAdapter(adapter);
    }
}
