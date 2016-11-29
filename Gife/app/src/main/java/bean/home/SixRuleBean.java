package bean.home;

import java.util.List;

/**
 * Created by yangshenglong on 16/11/25.
 */

public class SixRuleBean {

    /**
     * code : 200
     * data : {"secondary_banners":[{"ad_monitors":[],"id":76,"image_url":"http://img02.liwushuo.com/image/161111/tl4dbnr7q.jpg-w720","target_url":"liwushuo:///page?type=dailylucky","webp_url":"http://img02.liwushuo.com/image/161111/tl4dbnr7q.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":78,"image_url":"http://img03.liwushuo.com/image/161111/b066d612r.jpg-w720","target_url":"liwushuo:///page?type=topic&topic_id=359","webp_url":"http://img03.liwushuo.com/image/161111/b066d612r.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":80,"image_url":"http://img03.liwushuo.com/image/161111/e8aas80h1.jpg-w720","target_url":"liwushuo:///page?type=post&post_id=1046679&page_action=navigation","webp_url":"http://img03.liwushuo.com/image/161111/e8aas80h1.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":79,"image_url":"http://img01.liwushuo.com/image/161111/jweto2yli.jpg-w720","target_url":"liwushuo:///page?type=topic&topic_id=388","webp_url":"http://img01.liwushuo.com/image/161111/jweto2yli.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":81,"image_url":"http://img03.liwushuo.com/image/161111/1gp1956r2.jpg-w720","target_url":"liwushuo:///page?type=topic&topic_id=389","webp_url":"http://img03.liwushuo.com/image/161111/1gp1956r2.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":77,"image_url":"http://img01.liwushuo.com/image/161111/hooz52gwl.jpg-w720","target_url":"liwushuo:///page?type=post&post_id=1046636&page_action=navigation","webp_url":"http://img01.liwushuo.com/image/161111/hooz52gwl.jpg?imageView2/2/w/720/q/85/format/webp"}]}
     * message : OK
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private List<SecondaryBannersBean> secondary_banners;

        public List<SecondaryBannersBean> getSecondary_banners() {
            return secondary_banners;
        }

        public void setSecondary_banners(List<SecondaryBannersBean> secondary_banners) {
            this.secondary_banners = secondary_banners;
        }

        public static class SecondaryBannersBean {
            /**
             * ad_monitors : []
             * id : 76
             * image_url : http://img02.liwushuo.com/image/161111/tl4dbnr7q.jpg-w720
             * target_url : liwushuo:///page?type=dailylucky
             * webp_url : http://img02.liwushuo.com/image/161111/tl4dbnr7q.jpg?imageView2/2/w/720/q/85/format/webp
             */

            private int id;
            private String image_url;
            private String target_url;
            private String webp_url;
            private List<?> ad_monitors;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getTarget_url() {
                return target_url;
            }

            public void setTarget_url(String target_url) {
                this.target_url = target_url;
            }

            public String getWebp_url() {
                return webp_url;
            }

            public void setWebp_url(String webp_url) {
                this.webp_url = webp_url;
            }

            public List<?> getAd_monitors() {
                return ad_monitors;
            }

            public void setAd_monitors(List<?> ad_monitors) {
                this.ad_monitors = ad_monitors;
            }
        }
    }
}
