package com.jaskkit.news.Manager.Http;

import com.jaskkit.news.Dao.NewItemCollectionDao;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by jaskkit on 4/20/16.
 */
public interface ApiService {
    @GET("/cuisine/news/4")
    void getNewsList(Callback<NewItemCollectionDao> cb);



}
