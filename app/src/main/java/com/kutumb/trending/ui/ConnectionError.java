package com.kutumb.trending.ui;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.kutumb.trending.R;
import com.kutumb.trending.databinding.NetworkErrorBinding;
import com.kutumb.trending.utility.Util;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ConnectionError extends AppCompatActivity  {
    private NetworkErrorBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.network_error);

        binding.btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRetryButton();
            }
        });
        setRetryButton();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setRetryButton() {
        if(Util.isNetworkAvailable(getApplicationContext())) {
            startActivity(new Intent(ConnectionError.this, MainActivity.class));
            finish();
        }
        else {
            Util.showSnack(binding.errorLayout, true, "No Internet Connection! ");
            binding.errorConnection.setVisibility(View.VISIBLE);
        }
    }
}
