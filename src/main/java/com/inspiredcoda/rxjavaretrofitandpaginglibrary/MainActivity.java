package com.inspiredcoda.rxjavaretrofitandpaginglibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.inspiredcoda.rxjavaretrofitandpaginglibrary.adapter.CustomAdapter;
import com.inspiredcoda.rxjavaretrofitandpaginglibrary.model.User;
import com.inspiredcoda.rxjavaretrofitandpaginglibrary.util.Resource;
import com.inspiredcoda.rxjavaretrofitandpaginglibrary.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    MainActivityViewModel mainActivityViewModel;
    CustomAdapter adapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init();
        refreshObserver();


    }

    private void initRecyclerView(){
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void refreshObserver(){
        mainActivityViewModel.getData().observe(this, new Observer<Resource<List<User>>>() {
            @Override
            public void onChanged(Resource<List<User>> listResource) {
                switch (listResource.getStatus()){
                    case LOADING:
                        Log.d(TAG, "onChanged: LOADING........................................");
                        showProgressBar(true);
                        break;
                    case ERROR:
                        Log.d(TAG, "onChanged: ERROR..........................................");
                        Toast.makeText(MainActivity.this, "\nAn error occurred", Toast.LENGTH_SHORT).show();
                        showProgressBar(false);
                        break;
                    case SUCCESS:
                        Log.d(TAG, "onChanged: SUCCESS.........................................");
                        if(adapter == null){
                            adapter = new CustomAdapter(listResource.getData());
                            initRecyclerView();
                        }else{
                            adapter.notifyDataSetChanged();
                        }

                        showProgressBar(false);
                        break;
                }
            }
        });
    }

    private void showProgressBar(boolean isVisible){
        if(isVisible){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.refresh:
                showProgressBar(true);
                refreshObserver();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
