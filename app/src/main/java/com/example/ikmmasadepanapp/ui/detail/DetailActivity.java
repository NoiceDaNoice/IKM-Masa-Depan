package com.example.ikmmasadepanapp.ui.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.ikmmasadepanapp.R;
import com.example.ikmmasadepanapp.data.remote.DAOIkm;
import com.example.ikmmasadepanapp.data.remote.IkmModel;
import com.example.ikmmasadepanapp.ui.ViewModelFactory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    ImageView image;
    TextView company,type,email,desc;
    Button btn;
    ProgressBar pb;
    DAOIkm daoIkm = new DAOIkm();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle bundle = getIntent().getExtras();
        String idModel = bundle.getString("id");
        Objects.requireNonNull(getSupportActionBar()).setTitle("Detail");
        pb = findViewById(R.id.pb2);
        pb.setVisibility(View.VISIBLE);
        daoIkm.getId(idModel).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                IkmModel firebases = new IkmModel();
                for(DataSnapshot data: snapshot.getChildren()){
                    IkmModel IkmModel = data.getValue(IkmModel.class);
                    firebases.setCompany(IkmModel.getCompany());
                    firebases.setPhone(IkmModel.getPhone());
                    firebases.setName(IkmModel.getName());
                    firebases.setType(IkmModel.getType());
                    firebases.setDescription(IkmModel.getDescription());
                }
                populateDetail(firebases);
                pb.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    void populateDetail(IkmModel model){
        company = findViewById(R.id.companyDetail);
        company.setText(model.getCompany());

        type = findViewById(R.id.typeDetail);
        type.setText(model.getType());

        email = findViewById(R.id.emailDetail);
        email.setText(model.getEmail());

        desc = findViewById(R.id.descDetail);
        desc.setText(model.getDescription());

        btn = findViewById(R.id.buttonCall);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+model.getPhone()));
            startActivity(intent);
        });

        image = findViewById(R.id.imageView);
    }
}