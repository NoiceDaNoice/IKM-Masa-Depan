package com.example.ikmmasadepanapp.data.remote;

import androidx.lifecycle.LiveData;

import com.example.ikmmasadepanapp.ui.main.model.IkmModelFirebase;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.List;

public class DAOIkm {

    private DatabaseReference databaseReference;

    public DAOIkm(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(IkmModelFirebase.class.getSimpleName());
    }

    public Task<Void> add (IkmModelFirebase ikmModelFirebase){
        return databaseReference.push().setValue(ikmModelFirebase);
    }


    public Task<Void> update (String key, HashMap<String,Object>hashMap){
        return databaseReference.updateChildren(hashMap);
    }

    public Task<Void> remove (String key){
        return databaseReference.removeValue();
    }

    public Query get (){
        return databaseReference.orderByKey();
    }

    public Query getType (String type){
        return databaseReference.orderByChild("type").equalTo(type);
    }

    public Query getId (String id){
        return databaseReference.orderByChild("id").equalTo(id);
    }

}
