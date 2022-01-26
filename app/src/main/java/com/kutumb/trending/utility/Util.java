package com.kutumb.trending.utility;

import android.content.Context;

import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import androidx.annotation.RequiresApi;

import com.kutumb.trending.R;


public class Util {
    public static int page = 1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkCapabilities mCapabilities = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            mCapabilities = mConnectivityManager.getNetworkCapabilities(mConnectivityManager.getActiveNetwork());
        }
        return mCapabilities != null &&
                (mCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || mCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
    }

    public static void showSnack(View view, boolean isError, String message) {
        int color = Color.WHITE;
        if(isError) color = Color.RED;

        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);

        View snackBarView = snackbar.getView();
        TextView textview = snackBarView.findViewById(R.id.snackbar_text);
        textview.setTextColor(color);
        snackbar.show();
    }
}
