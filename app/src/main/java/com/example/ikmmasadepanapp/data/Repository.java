package com.example.ikmmasadepanapp.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ikmmasadepanapp.data.remote.IkmModel;
import com.example.ikmmasadepanapp.data.remote.RemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class Repository implements IkmInterface{

    private volatile static Repository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;

    private Repository(@NonNull RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static Repository getInstance(RemoteDataSource remoteData) {
        if (INSTANCE == null) {
            synchronized (Repository.class) {
                INSTANCE = new Repository(remoteData);
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<List<IkmModel>> getAllIkm() {
        MutableLiveData<List<IkmModel>> ikmResult = new MutableLiveData<>();
        remoteDataSource.getIkmList((List<IkmModel> ikmRespone) -> {
            ArrayList<IkmModel> ikmList = new ArrayList<>();
            for (IkmModel response : ikmRespone) {
                IkmModel ikmModel = new IkmModel(
                        response.getId(),
                        response.getName(),
                        response.getCompany(),
                        response.getPhone(),
                        response.getType(),
                        response.getEmail(),
                        response.getDescription()
                        );
                ikmList.add(ikmModel);
            }
            ikmResult.postValue(ikmList);
        });
        return ikmResult;
    }

    @Override
    public LiveData<List<IkmModel>> getIkmByType(String type) {
        MutableLiveData<List<IkmModel>> ikmType = new MutableLiveData<>();
        remoteDataSource.getIkmList((List<IkmModel> ikmDetailRespond) -> {
            ArrayList<IkmModel> ikmListType = new ArrayList<>();
            for(IkmModel response : ikmDetailRespond){
                if(response.getType().equals(type)){
                    IkmModel detailIkm = new IkmModel(
                            response.getId(),
                            response.getName(),
                            response.getCompany(),
                            response.getPhone(),
                            response.getType(),
                            response.getEmail(),
                            response.getDescription());
                    ikmListType.add(detailIkm);
                }
            }
            ikmType.postValue(ikmListType);
        });
        return ikmType;
    }

    @Override
    public LiveData<IkmModel> getIkmById(String id) {
        MutableLiveData<IkmModel> ikmType = new MutableLiveData<>();
        remoteDataSource.getIkmList((List<IkmModel> ikmDetailRespond) -> {
            IkmModel detailIkm = null;
            for(IkmModel response : ikmDetailRespond){
                if(response.getId().equals(id)){
                    detailIkm = new IkmModel(
                            response.getId(),
                            response.getName(),
                            response.getCompany(),
                            response.getPhone(),
                            response.getType(),
                            response.getEmail(),
                            response.getDescription());
                }
            }
            ikmType.postValue(detailIkm);
        });
        return ikmType;
    }
}
