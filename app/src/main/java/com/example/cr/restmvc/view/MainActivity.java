package com.example.cr.restmvc.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.cr.restmvc.R;
import com.example.cr.restmvc.controller.Controller;
import com.example.cr.restmvc.model.adapter.FlowersAdapter;
import com.example.cr.restmvc.model.pojo.Flower;

import java.util.ArrayList;
import java.util.List;

    public class MainActivity extends AppCompatActivity implements Controller.FlowerCallbackListener
{
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Flower> flowerList = new ArrayList<>();
    private FlowersAdapter flowersAdapter;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configToolBar();
        controller = new Controller(MainActivity.this);
        configViews();
        controller.startFetching();
    }

    @SuppressWarnings("deprecation")
    private void configViews()
    {
        RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.list);
        swipeRefreshLayout = (SwipeRefreshLayout) this.findViewById(R.id.swipe);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());

        flowersAdapter = new FlowersAdapter(flowerList);
        recyclerView.setAdapter(flowersAdapter);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimaryDark));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                controller.startFetching();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id= item.getItemId();
        return (id == R.id.action_settings || super.onOptionsItemSelected(item));
    }

    private void configToolBar()
    {
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onFetchProgress(Flower flower)
    {
        flowersAdapter.addFlower(flower);
    }

    @Override
    public void onFetchComplete()
    {
        swipeRefreshLayout.setRefreshing(false);
    }
}
