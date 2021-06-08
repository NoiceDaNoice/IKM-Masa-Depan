package com.example.ikmmasadepanapp.ui.main.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ikmmasadepanapp.R;
import com.example.ikmmasadepanapp.data.remote.DAOIkm;
import com.example.ikmmasadepanapp.data.remote.IkmModel;
import com.example.ikmmasadepanapp.ui.ViewModelFactory;
import com.example.ikmmasadepanapp.ui.main.adapter.MainAdapter;
import com.example.ikmmasadepanapp.ui.main.model.IkmModelFirebase;
import com.example.ikmmasadepanapp.ui.main.viewmodel.ViewModelMain;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private RecyclerView mainRv;
    private Spinner spinner;
    private ProgressBar pb;
    private DAOIkm daoIkm = new DAOIkm();
    public MainFragment() {
        // Required empty public constructor
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainRv = view.findViewById(R.id.rvMain);
        spinner = view.findViewById(R.id.spinner);
        mainRv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        pb = view.findViewById(R.id.pb1);
        ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
        ViewModelMain viewModel = new ViewModelProvider(this, factory).get(ViewModelMain.class);
        MainAdapter mainAdapter = new MainAdapter(getContext(),getActivity());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.VISIBLE);
                mainRv.setVisibility(View.INVISIBLE);
                if(position==0){
                    daoIkm.get().addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            List<IkmModel> firebases = new ArrayList<>();
                            for(DataSnapshot data: snapshot.getChildren()){
                                IkmModel IkmModel = data.getValue(IkmModel.class);
                                firebases.add(IkmModel);
                                Log.d("TAG", "onDataChange: "+ IkmModel.getType());
                            }
                            mainAdapter.setIkm(firebases);
                            mainAdapter.notifyDataSetChanged();
                            pb.setVisibility(View.INVISIBLE);
                            mainRv.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });
                }
                if(position!=0){
                    daoIkm.getType(text).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            List<IkmModel> firebases = new ArrayList<>();
                            for(DataSnapshot data: snapshot.getChildren()){
                                IkmModel IkmModel = data.getValue(IkmModel.class);
                                firebases.add(IkmModel);
                                Log.d("TAG", "onDataChange: " + IkmModel.getType());

                            }
                            mainAdapter.setIkm(firebases);
                            mainAdapter.notifyDataSetChanged();
                            pb.setVisibility(View.INVISIBLE);
                            mainRv.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        daoIkm.get().addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                List<IkmModel> firebases = new ArrayList<>();
//                for(DataSnapshot data: snapshot.getChildren()){
//                    IkmModel ikmModel = data.getValue(IkmModel.class);
//                    firebases.add(ikmModel);
//                }
//                Log.d("TAG", "onDataChange: " + snapshot.getValue());
//
//                mainAdapter.setIkm(firebases);
//                mainAdapter.notifyDataSetChanged();
//                pb.setVisibility(View.INVISIBLE);
//                mainRv.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
//                R.array.type, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String text = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
//                pb.setVisibility(View.VISIBLE);
//                mainRv.setVisibility(View.INVISIBLE);
//                viewModel.getAllIkm();
//                if(position == 0){
//                    viewModel.getAllIkm().observe(getViewLifecycleOwner(), (List<IkmModel> detail) -> {
//                        mainAdapter.setIkm(detail);
//                        mainAdapter.notifyDataSetChanged();
//                        pb.setVisibility(View.INVISIBLE);
//                        mainRv.setVisibility(View.VISIBLE);
//                    });
//                }else{
//                    viewModel.getIkmByType(text).observe(getViewLifecycleOwner(), (List<IkmModel> detail) -> {
//                        mainAdapter.setIkm(detail);
//                        mainAdapter.notifyDataSetChanged();
//                        pb.setVisibility(View.INVISIBLE);
//                        mainRv.setVisibility(View.VISIBLE);
//                    });
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // TODO Auto-generated method stub
//            }
//        });

        mainRv.setHasFixedSize(true);
        mainRv.setAdapter(mainAdapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}