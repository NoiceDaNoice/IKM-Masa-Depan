package com.example.ikmmasadepanapp.data.remote;

import android.os.Handler;
import android.os.Looper;

import com.example.ikmmasadepanapp.utils.JsonHelper;

import java.util.List;

public class RemoteDataSource {

    private static RemoteDataSource INSTANCE;
    private JsonHelper jsonHelper;
    private Handler handler = new Handler(Looper.getMainLooper());
    private final long SERVICE_LATENCY_IN_MILLIS = 1000;

    private RemoteDataSource(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteDataSource getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(helper);
        }
        return INSTANCE;
    }

    public void getIkmList(LoadIkmCallback callback) {
        handler.postDelayed(() ->{
            callback.onIkmReceived(jsonHelper.ikmList());

        },SERVICE_LATENCY_IN_MILLIS);
    }

    public interface LoadIkmCallback {
        void onIkmReceived(List<IkmModel> courseResponses);
    }

}
