package bean.list;

import java.util.List;

/**
 * Created by yangshenglong on 16/11/25.
 */

public class ListBean {

    /**
     * code : 200
     * data : {"ranks":[{"id":1,"mark_style":0,"name":"每日推荐","title":"每日推荐好礼：一天都不愿错过 ","update_at":1480003200},{"id":2,"mark_style":1,"name":"TOP100","title":"TOP100榜单，把最受欢迎的好礼打包送上","update_at":1479716253},{"id":3,"mark_style":1,"name":"独立原创榜","title":"独立原创榜，帮你找到独一无二的好礼","update_at":1480003200},{"id":4,"mark_style":1,"name":"新星榜","title":"礼物新星榜，最新最火单品推荐","update_at":1479484800}]}
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
        private List<RanksBean> ranks;

        public List<RanksBean> getRanks() {
            return ranks;
        }

        public void setRanks(List<RanksBean> ranks) {
            this.ranks = ranks;
        }

        public static class RanksBean {
            /**
             * id : 1
             * mark_style : 0
             * name : 每日推荐
             * title : 每日推荐好礼：一天都不愿错过
             * update_at : 1480003200
             */

            private int id;
            private int mark_style;
            private String name;
            private String title;
            private int update_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMark_style() {
                return mark_style;
            }

            public void setMark_style(int mark_style) {
                this.mark_style = mark_style;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getUpdate_at() {
                return update_at;
            }

            public void setUpdate_at(int update_at) {
                this.update_at = update_at;
            }
        }
    }
}
