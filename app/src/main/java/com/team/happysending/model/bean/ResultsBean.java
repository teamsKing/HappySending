package com.team.happysending.model.bean;

import java.util.List;


/**
 * Created by 樊、先生 on 2017/2/7.
 * 测试类
 */

public class ResultsBean extends BaseBean<ResultsBean> {


        @Override
        public String toString() {
            return "ResultsBean{" +
                    "_id='" + _id + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", source='" + source + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", used=" + used +
                    ", who=" + who +
                    ", images=" + images +
                    '}';
        }

        /**
         * _id : 5895d845421aa970b845238c
         * createdAt : 2017-02-04T21:33:57.847Z
         * desc :  TabLayout 和 CoordinatorLayout 相结合的折叠控件
         * publishedAt : 2017-02-06T11:36:12.36Z
         * source : web
         * type : Android
         * url : https://github.com/hugeterry/CoordinatorTabLayout
         * used : true
         * who : null
         * images : ["http://img.gank.io/97c303f3-8ba0-4396-8c67-ce67f581f52b","http://img.gank.io/910ba3c9-de22-45c9-ba47-a30791d236ec"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private Object who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public Object getWho() {
            return who;
        }

        public void setWho(Object who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

