package com.example.ikmmasadepanapp.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.ikmmasadepanapp.data.Repository;
import com.example.ikmmasadepanapp.di.Injection;
import com.example.ikmmasadepanapp.ui.detail.ViewModelDetail;
import com.example.ikmmasadepanapp.ui.main.viewmodel.ViewModelMain;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory{

    private static volatile ViewModelFactory INSTANCE;

    private final Repository repository;

    private ViewModelFactory(Repository repos) {
        repository = repos;
    }

    public static ViewModelFactory getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                INSTANCE = new ViewModelFactory(Injection.provideRepository(context));
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(ViewModelMain.class)) {
            //noinspection unchecked
            return (T) new ViewModelMain(repository);
        }else if (modelClass.isAssignableFrom(ViewModelDetail.class)) {
            //noinspection unchecked
            return (T) new ViewModelDetail(repository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
