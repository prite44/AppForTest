package com.jaskkit.news.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jaskkit.news.Dao.NewItemDao;
import com.jaskkit.news.R;
import com.jaskkit.news.Util.MyCalendar;

import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
    private TextView dateView,titleView,detailView;
    private String Language;
    private  NewItemDao list;
    private Toolbar toolbar;
    private ImageView imgView;
    private String ImgUrl = "https://s3-ap-southeast-1.amazonaws.com/herbery/cuisine_image/news_image/etctutor/";
    MyCalendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        list = getIntent().getParcelableExtra("detail");
        initInstances();
    }

    private void initInstances() {
        Language = Locale.getDefault().getDisplayLanguage();
        //Toast.makeText(Contextor.getInstance().getContext(), Locale.getDefault().getDisplayLanguage(),Toast.LENGTH_SHORT).show();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dateView = (TextView) findViewById(R.id.dateView);
        titleView = (TextView) findViewById(R.id.titleView);
        detailView = (TextView) findViewById(R.id.DetailView);
        imgView = (ImageView) findViewById(R.id.ImgView);
        if(Language.equals("ไทย")) {
            dateView.setText(MyCalendar.getInstance().getThaidate(list.getUpdateDate()));
        }
        else{
            dateView.setText(MyCalendar.getInstance().getEngdate(list.getUpdateDate()));
        }
        titleView.setText(list.getTitle());
        detailView.setText(list.getDetail());
        //textView = (TextView) findViewById(R.id.dateview);
        //textView.setText(list.getTitle());
        Glide.with(DetailActivity.this)
                .load(ImgUrl+list.getUrlImage())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.detailmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.back) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("object",list);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        list = savedInstanceState.getParcelable("object");
        super.onRestoreInstanceState(savedInstanceState);
    }
}
