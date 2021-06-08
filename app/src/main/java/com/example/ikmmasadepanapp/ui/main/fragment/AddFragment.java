package com.example.ikmmasadepanapp.ui.main.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ikmmasadepanapp.R;
import com.example.ikmmasadepanapp.data.remote.DAOIkm;
import com.example.ikmmasadepanapp.data.remote.IkmModel;
import com.example.ikmmasadepanapp.ui.main.model.IkmModelFirebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class AddFragment extends Fragment {
    Spinner spinner;
    EditText name,company,phone,email,desc;
    Button btn;
    String nameTemp ="";
    String companyTemp ="";
    String phoneTemp ="";
    String typeTemp ="";
    String emailTemp ="";
    String descTemp ="";
    String id = "";
    String temp = "";
    Random random = new Random();
    DAOIkm daoIkm = new DAOIkm();

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add, container, false);
        spinner = view.findViewById(R.id.typeSpinner);
        name = view.findViewById(R.id.insertName);
        company = view.findViewById(R.id.insertCompany);
        phone = view.findViewById(R.id.insertPhone);
        email = view.findViewById(R.id.insertEmail);
        desc = view.findViewById(R.id.insertDesc);
        btn = view.findViewById(R.id.insertBtn);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.insert_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                temp = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        btn.setOnClickListener(v -> {
            nameTemp = name.getText().toString().trim();
            companyTemp = company.getText().toString().trim();
            phoneTemp = phone.getText().toString().trim();
            typeTemp = temp;
            emailTemp = email.getText().toString().trim();
            descTemp = desc.getText().toString().trim();
            id = String.valueOf(random.nextInt(100000));

            if (TextUtils.isEmpty(nameTemp)) {
                name.setError("Field can not be blank");
                return;
            }
            if (TextUtils.isEmpty(descTemp)) {
                desc.setError("Field can not be blank");
                return;
            }
            if (TextUtils.isEmpty(phoneTemp)) {
                phone.setError("Field can not be blank");
                return;
            }
            if (TextUtils.isEmpty(emailTemp)) {
                email.setError("Field can not be blank");
                return;
            }
            IkmModelFirebase temp = new IkmModelFirebase(id,nameTemp,companyTemp,phoneTemp,typeTemp,emailTemp,descTemp);
            daoIkm.add(temp).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }
            });

        });

        return view;
    }
}