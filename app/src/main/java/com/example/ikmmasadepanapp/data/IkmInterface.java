package com.example.ikmmasadepanapp.data;

import androidx.lifecycle.LiveData;

import com.example.ikmmasadepanapp.data.remote.IkmModel;

import java.util.List;

public interface IkmInterface {
    LiveData<List<IkmModel>> getAllIkm();

    LiveData<List<IkmModel>> getIkmByType(String type);

    LiveData<IkmModel> getIkmById(String id);
}
