package com.example.ikmmasadepanapp.utils;

import android.content.Context;

import com.example.ikmmasadepanapp.data.remote.IkmModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    private Context context;

    public JsonHelper(Context context) {
        this.context = context;
    }

    private String parsingFileToString(String fileName) {
        try {
            InputStream is = context.getAssets().open(fileName);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<IkmModel> ikmList() {
        ArrayList<IkmModel> list = new ArrayList<>();
        try{
            String json = parsingFileToString("data_ikm.json");
            JSONObject responseObject = null;
            if(json != null){
                responseObject = new JSONObject(json);
                JSONArray listArray = responseObject.getJSONArray("items");
                for(int i = 0;i<listArray.length();i++){
                    JSONObject ikm = listArray.getJSONObject(i);
                    String id = ikm.getString("id");
                    String name = ikm.getString("name");
                    String company = ikm.getString("company");
                    String phone = ikm.getString("phone");
                    String type = ikm.getString("type");
                    String email = ikm.getString("email");
                    String description = ikm.getString("description");

                    IkmModel ikmModel = new IkmModel(id, name, company, phone, type,email,description);
                    list.add(ikmModel);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
