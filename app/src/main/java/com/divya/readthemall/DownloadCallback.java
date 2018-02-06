package com.divya.readthemall;

import android.net.NetworkInfo;

/**
 * Created by divya on 2/5/2018.
 */

public interface DownloadCallback {

    interface Progress{
        int ERROR=-1;
        int CONNECT_SUCCESS=0;
        int GET_INPUT_STREAM_SUCCESS=1;
        int PROCESS_INPUT_STREAM_IN_PRGRESS=2;
        int PROCESS_INPUT_STREAM_SUCCESS=3;
    }

    void updateFromDownload(String result);

    NetworkInfo getActiveNetworkInfo();

    void onProgressUpdate(int progressCode, int percentComplete);

    void finishDownloading();
}

