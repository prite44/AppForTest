package com.jaskkit.news.Manager;

import android.content.Context;

import com.jaskkit.news.Dao.NewItemCollectionDao;
/**
 * Created by jaskkit on 4/21/16.
 */
public class NewListManager {

    private static NewListManager instance;

    public static NewListManager getInstance() {
        if (instance == null)
            instance = new NewListManager();
        return instance;
    }

    private Context mContext;
    private NewItemCollectionDao dao;
    private NewListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public NewItemCollectionDao getDao() {
        return dao;
    }

    public void setDao(NewItemCollectionDao dao) {
        this.dao = dao;
    }
}
