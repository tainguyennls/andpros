package com.example.nguyenhuutai.studentapp.models;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckNetwork {

    public static final int NO_CONNECT_NETWORK = 0;
    public static final int UNKNOWN_CONNECT_TYPE = -1;

    public CheckNetwork(){

    }
    public int getNetworkType(Context context){
        ConnectivityManager connectivityManager  = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo == null || !networkInfo.isConnected()){
            return  NO_CONNECT_NETWORK;
        }
        return 0; // only for demo...
    }

}
