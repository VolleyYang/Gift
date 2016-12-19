package volley;

import android.app.Application;
import android.content.Context;

import GreenDao.DaoMaster;
import GreenDao.DaoSession;

/**
 * Created by yangshenglong on 16/11/28.
 */
//切记如何使用!!!!!
    //清单文件中加入自己的App
public class MyApp extends Application {
    private static Context Context;

    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        Context = this;

    }
    //对外提供一个获取Context对象的方法
    public static Context getmContext() {
        return Context;
    }



    //对外提供DaoSession对象
    public static DaoMaster getDaoMaster() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getmContext(),"Person.db",null);
        //初始化DaoMaster对象
        daoMaster = new DaoMaster(helper.getWritableDatabase());

        return daoMaster;
    }


    //对外提供DaoMaster对象
    public static DaoSession getDaoSession() {
        if (daoSession ==null){
            if (daoMaster == null){
                daoMaster = getDaoMaster();
            }
            //初始化DaoSession对象
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
