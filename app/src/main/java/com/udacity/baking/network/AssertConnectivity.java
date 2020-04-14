package com.udacity.baking.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Handles the connectivity check of the app
 */
public class AssertConnectivity {

    private static Context mContext;
    private static final String ERROR_CONNECTIVITY = "Phone seems offline. Please check your internet connection.";


    public AssertConnectivity(Context context) {
        mContext = context;
    }


    // Check if the phone / device has connectivity.
    public static boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    // Toast error message for connectivity issue
    public static void errorConnectMessage() {
        Toast.makeText(mContext, ERROR_CONNECTIVITY, Toast.LENGTH_LONG).show();
    }

}
