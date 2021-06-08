package com.example.ikmmasadepanapp.data.remote;

import android.os.Handler;
import android.os.Looper;

import com.example.ikmmasadepanapp.utils.JsonHelper;

import java.util.List;

public class FirebaseDataSource {
    private static FirebaseDataSource INSTANCE;
    private DAOIkm daoIkm;
    private Handler handler = new Handler(Looper.getMainLooper());
    private final long SERVICE_LATENCY_IN_MILLIS = 1000;

    private FirebaseDataSource(DAOIkm daoIkm) {
        this.daoIkm = daoIkm;
    }

    public static FirebaseDataSource getInstance(DAOIkm daoIkm) {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseDataSource(daoIkm);
        }
        return INSTANCE;
    }

    public void getIkmListFirebase(RemoteDataSource.LoadIkmCallback callback) {
        handler.postDelayed(() ->{
            callback.onIkmReceived((List<IkmModel>) daoIkm.get());

        },SERVICE_LATENCY_IN_MILLIS);
    }

    public interface LoadIkmCallback {
        void onIkmReceived(List<IkmModel> courseResponses);
    }
}
