package staticclass;

/**
 * Created by yangshenglong on 16/11/23.
 */

public class StaticClass {
    //首页title接口;
    public static final String TITLE = "http://api.liwushuo.com/v2/channels/preset?gender=1&generation=2";
    //首页精选接口(ListView)
    public static final String SIFT = "http://api.liwushuo.com/v2/channels/101/items_v2?ad=2&gender=1&generation=2&limit=20&offset=0";
    //轮播图接口
    public static final String BANNER = "http://api.liwushuo.com/v2/banners";
    //六宫格接口
    public static final String SIXRULE ="http://api.liwushuo.com/v2/secondary_banners?gender=1&generation=2";
    //榜单TabLayout
    public static final String ListTab  ="http://api.liwushuo.com/v2/ranks_v2/ranks";
    //分类攻略栏目接口
    public static final String CLASSIFYSTRA = "http://api.liwushuo.com/v2/columns?limit=20&offset=0";
    //分类攻略品类,风格,对象接口
    public static final String CLASSIFYTWO = "http://api.liwushuo.com/v2/channel_groups/all";
}
