package com.example.ikmmasadepanapp.di;

import android.content.Context;

import com.example.ikmmasadepanapp.data.Repository;
import com.example.ikmmasadepanapp.data.remote.RemoteDataSource;
import com.example.ikmmasadepanapp.utils.JsonHelper;

public class Injection {
    public static Repository provideRepository(Context context) {

        RemoteDataSource remoteRepository = RemoteDataSource.getInstance(new JsonHelper(context));

        return Repository.getInstance(remoteRepository);
    }
}
