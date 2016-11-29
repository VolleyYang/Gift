package base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by yangshenglong on 16/11/22.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initView();
        initData();
    }
    //设置布局的
    public abstract int setLayout();
    //初始化组件
    public abstract void initView();
    //初始化数据的方法
    public abstract void initData();


    public <T extends View>  T bindView(int id){
        return (T)findViewById(id);
    }
}
