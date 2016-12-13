package GreenDao;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import volley.MyApp;

/**
 * Created by VolleyYang on 16/12/7.
 */
public class DBToll {

    private static PersonDao  personDao;
    private static DBToll ourInstance = new DBToll();

    //对外提供getInstance方法获取本类的单例对象
    public static DBToll getInstance() {
        if (ourInstance == null){
            synchronized (DBToll.class){
                if (ourInstance == null){

                    ourInstance = new DBToll();
                }
            }
        }
        //初始化xxxDao对象
        personDao = MyApp.getDaoSession().getPersonDao();
        return ourInstance;
    }

    private DBToll() {
    }

    //增加单一对象的方法
    public void insertPerson(Person person){
        personDao.insert(person);
    }
    //增加集合的方法
    public void insertList(List<Person> list){
        personDao.insertInTx(list);
    }

    //删除单一对象方法
    public void deletePerson(Person person){
        personDao.delete(person);
    }
    //删除所有内容
    public void deleteAll(){
        personDao.deleteAll();
    }

    //根据Id 进行删除
    public void deleteBuId(Long id){
        personDao.deleteByKey(id);
    }
    //根据某一个字段进行删除
    public void deleteByContent(String content){
        DeleteQuery<Person> delayQueue = personDao.queryBuilder().where(PersonDao.Properties.Content.eq(content)).buildDelete();
        delayQueue.executeDeleteWithoutDetachingEntities();
    }
    public void deleteBy(String content,String description,String price,String url, int img){
        DeleteQuery<Person> deleteQuery = personDao.queryBuilder().
                where(PersonDao.Properties.Content.eq(content),
                        PersonDao.Properties.Description.eq(description),
                        PersonDao.Properties.Price.eq(price)).buildDelete();
        if (deleteQuery != null) {
            deleteQuery.executeDeleteWithoutDetachingEntities();
        }
    }
    //查询所有的方法
    public List<Person> queryAll(){
        //查询方法一
        List<Person> list = personDao.loadAll();
        //查询方法二
        List<Person>  personList = personDao.queryBuilder().list();
        return list;
    }
    //查重方法
    //根据内容查询
    public boolean isSave(String content){
        QueryBuilder<Person> queryBuilder = personDao.
                queryBuilder().where(PersonDao.Properties.Content.eq(content));
        //获取到我们要查询的内容的size
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true : false;
    }
    public boolean isSave(Person person){
        QueryBuilder<Person> queryBuilder = personDao.queryBuilder().
                where(PersonDao.Properties.Content.eq(person.getContent()),
                        PersonDao.Properties.Description.eq(person.getDescription()),
                        PersonDao.Properties.Price.eq(person.getPrice()));
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true : false;
    }
}
