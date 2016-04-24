package com.jaskkit.news.Dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jaskkit on 4/17/16.
 */
public class NewItemCollectionDao {
    @SerializedName("news")
    private List<NewItemDao> news;
    public List<NewItemDao> getNews() {
        return news;
    }
    public void setNews(List<NewItemDao> news) {
        this.news = news;
    }
}
