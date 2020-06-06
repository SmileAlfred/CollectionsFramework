package com.example.collectionsframework.c2okhttp.domain;

import java.util.List;

/**
 * 作者：尚硅谷-杨光福 on 27/07/2016 16:28
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：xxxx
 */
public class DataBean {


    /**
     * id : 61684
     * movieName : 《猜火车2》先导预告片
     * coverImg : http://img31.mtime.cn/mg/2016/07/26/143142.64770465.jpg
     * movieId : 228230
     * url : http://vfx.mtime.cn/Video/2016/07/26/mp4/160726074707321432_480.mp4
     * hightUrl : http://vfx.mtime.cn/Video/2016/07/26/mp4/160726074707321432.mp4
     * videoTitle : 猜火车2 先导预告片
     * videoLength : 46
     * rating : 0
     * type : ["剧情"]
     * summary : 苏格兰四兄弟回来了！
     */

    private List<ItemData> trailers;

    public void setTrailers(List<ItemData> trailers) {
        this.trailers = trailers;
    }

    public List<ItemData> getTrailers() {
        return trailers;
    }

    public static class ItemData {
        private int id;
        private String movieName;
        private String coverImg;
        private int movieId;
        private String url;
        private String hightUrl;
        private String videoTitle;
        private int videoLength;
        private int rating;
        private String summary;
        private List<String> type;

        public void setId(int id) {
            this.id = id;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setHightUrl(String hightUrl) {
            this.hightUrl = hightUrl;
        }

        public void setVideoTitle(String videoTitle) {
            this.videoTitle = videoTitle;
        }

        public void setVideoLength(int videoLength) {
            this.videoLength = videoLength;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public void setType(List<String> type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public String getMovieName() {
            return movieName;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public int getMovieId() {
            return movieId;
        }

        public String getUrl() {
            return url;
        }

        public String getHightUrl() {
            return hightUrl;
        }

        public String getVideoTitle() {
            return videoTitle;
        }

        public int getVideoLength() {
            return videoLength;
        }

        public int getRating() {
            return rating;
        }

        public String getSummary() {
            return summary;
        }

        public List<String> getType() {
            return type;
        }
    }
}
