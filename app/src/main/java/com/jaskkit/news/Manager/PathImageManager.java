package com.jaskkit.news.Manager;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by jaskkit on 4/21/16.
 */
public class PathImageManager extends HashMap{

    private static PathImageManager instance;

    public static PathImageManager getInstance() {
        if (instance == null)
            instance = new PathImageManager();
        return instance;
    }

    private Context mContext;

    private PathImageManager() {
    }

}
