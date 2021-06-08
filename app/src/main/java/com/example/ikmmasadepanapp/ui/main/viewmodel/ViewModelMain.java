package com.example.ikmmasadepanapp.ui.main.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ikmmasadepanapp.data.Repository;
import com.example.ikmmasadepanapp.data.remote.DAOIkm;
import com.example.ikmmasadepanapp.data.remote.IkmModel;
import com.example.ikmmasadepanapp.ui.main.model.IkmModelFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ViewModelMain extends ViewModel {

    private DAOIkm daoIkm;

    private Repository mRepository;

    public ViewModelMain(Repository repository){
        this.mRepository = repository;
    };

    public LiveData<List<IkmModel>> getAllIkm() {
        return mRepository.getAllIkm();
    }

    public LiveData<List<IkmModel>> getIkmByType(String type) {
        return mRepository.getIkmByType(type);
    }

}
