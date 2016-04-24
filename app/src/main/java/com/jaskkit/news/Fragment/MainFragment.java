package com.jaskkit.news.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jaskkit.news.Activity.DetailActivity;
import com.jaskkit.news.Adapter.NewsAdapter;
import com.jaskkit.news.Dao.NewItemCollectionDao;
import com.jaskkit.news.Dao.NewItemDao;
import com.jaskkit.news.Manager.Contextor;
import com.jaskkit.news.Manager.Http.ApiService;
import com.jaskkit.news.Manager.HttpManager;
import com.jaskkit.news.Manager.NewListManager;
import com.jaskkit.news.R;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by jaskkit on 4/20/16.
 */
public class MainFragment extends Fragment {
    private ListView listView;
    private NewsAdapter adapterview;

    public MainFragment() {
        super();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        listView = (ListView) rootView.findViewById(R.id.listView);
        adapterview = new NewsAdapter();
        listView.setAdapter(adapterview);
        ApiService service = HttpManager.getInstance().getService();
        service.getNewsList(new Callback<NewItemCollectionDao>() {
            @Override
            public void success(NewItemCollectionDao newItemCollectionDao, Response response) {
                NewListManager.getInstance().setDao(newItemCollectionDao);
                adapterview.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(Contextor.getInstance().getContext(),error.getMessage(),Toast.LENGTH_SHORT);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detail = new Intent(Contextor.getInstance().getContext(), DetailActivity.class);
                NewItemDao  ac = NewListManager.getInstance().getDao().getNews().get(position);
                detail.putExtra("detail",ac);
                startActivity(detail);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }
    /*public void setPathImage(List<NewItemDao> NewItem){
        for(NewItemDao item : NewItem){
            Log.d("Key",item.getUrlImage().substring(14,16));
        }

    }*/
}
