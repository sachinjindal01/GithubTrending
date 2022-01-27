package com.kutumb.trending.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.facebook.shimmer.ShimmerFrameLayout;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.kutumb.trending.adapter.trendingAdapter;
import com.kutumb.trending.databinding.ActivityMainBinding;
import com.kutumb.trending.utility.Util;
import com.kutumb.trending.R;
import com.kutumb.trending.adapter.trendingAdapter;
import com.kutumb.trending.model.ItemModel;
import com.kutumb.trending.viewModel.TrendingViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

   private ActivityMainBinding binding;
   private TrendingViewModel viewModel;
   private trendingAdapter adapter;

   @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setSupportActionBar(binding.toolbar);
        binding.toolbarTitle.setText(binding.toolbar.getTitle());
       getSupportActionBar().setDisplayShowTitleEnabled(false);

       viewModel = new ViewModelProvider(this).get(TrendingViewModel.class);

       initView();
       setUpRecyclerView();

   }

    @Override
    protected void onStart() {
        super.onStart();
        observeData();
        errorData();

        updateRefreshLayout(false);
        loadTrendingList();
    }

    private void initView() {
       binding.swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
               android.R.color.holo_green_light,
               android.R.color.holo_orange_light,
               android.R.color.holo_red_light);
        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.shimmerFrameLayout.startShimmer();
    }

    private void setUpRecyclerView() {
       adapter = new trendingAdapter(this);
       binding.recyclerView.setAdapter(adapter);
    }

    private void observeData() {
       if(viewModel != null && viewModel.getTrendingList() != null) {
           viewModel.getTrendingList().observe(this, new Observer<List<ItemModel>>() {
               @Override
               public void onChanged(List<ItemModel> itemModelsList) {
                   adapter.setTrendList(itemModelsList);
                   updateRefreshLayout(false);
               }
           });
       }
    }

    private void errorData() {
       viewModel.getError().observe(this, isError -> {
           if(isError) {
               displaySnackBar(true, "Can't load");
               updateRefreshLayout(false);
           }
       });
    }

    private void loadTrendingList() {
       viewModel.loadTrendingList(Util.getFormattedDateOneMonthAgo());
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onRefresh() {
        Util.page = 1;
        adapter.clearData();
        if(Util.isNetworkAvailable(this)) {
            loadTrendingList();
        }
        else {
            updateRefreshLayout(false);
            observeData();
            displaySnackBar(true, "No Internet Connection");
        }
    }

    private void updateRefreshLayout(boolean refresh) {
       binding.swipeRefreshLayout.setRefreshing(refresh);
    }

    private void displaySnackBar(boolean isError, String message) {
        Util.showSnack(binding.mainLayout , isError, message);
    }

}