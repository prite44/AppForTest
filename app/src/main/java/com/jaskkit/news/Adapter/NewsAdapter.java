package com.jaskkit.news.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jaskkit.news.Dao.NewItemDao;
import com.jaskkit.news.Manager.NewListManager;
import com.jaskkit.news.View.NewsListView;

/**
 * Created by jaskkit on 4/20/16.
 */
public class NewsAdapter extends BaseAdapter{
    private NewListManager dao;
    private String ImgUrl = "https://s3-ap-southeast-1.amazonaws.com/herbery/cuisine_image/news_image/etctutor/";
    @Override
    public int getCount() {
        if(dao.getInstance().getDao()==null)
            return 0;
        if(dao.getInstance().getDao().getNews()==null)
            return 0;
        return dao.getInstance().getDao().getNews().size();
    }

    @Override
    public Object getItem(int position) {

        return dao.getInstance().getDao().getNews().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        NewsListView item;
        if (convertView != null) {
            item = (NewsListView) convertView;

        } else {
            item = new NewsListView(parent.getContext());
        }
        NewItemDao dao = (NewItemDao) getItem(position);
        Glide.with(parent.getContext())
                .load(ImgUrl+dao.getUrlImage())
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(item.getImg());
        item.setTitle(dao.getTitle());

        return item;
    }

}
