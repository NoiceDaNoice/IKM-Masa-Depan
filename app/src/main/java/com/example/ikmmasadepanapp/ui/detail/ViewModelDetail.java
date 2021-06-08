package com.example.ikmmasadepanapp.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ikmmasadepanapp.data.Repository;
import com.example.ikmmasadepanapp.data.remote.IkmModel;

public class ViewModelDetail extends ViewModel {
    private Repository mRepository;
    String id;
    LiveData<IkmModel> detail = null;

    public void setId(String id) {
        this.id = id;
    }

    public ViewModelDetail(Repository repository) {
        this.mRepository = repository;
    }

    LiveData<IkmModel> getDetailMovie() {
        detail = mRepository.getIkmById(id);
        return detail;
    }

}
