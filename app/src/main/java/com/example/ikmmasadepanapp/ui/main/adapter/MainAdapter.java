package com.example.ikmmasadepanapp.ui.main.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ikmmasadepanapp.R;
import com.example.ikmmasadepanapp.data.remote.IkmModel;
import com.example.ikmmasadepanapp.ui.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ListViewHolder>{
    private List<IkmModel> ikm = new ArrayList<>();
    private Activity activity;
    private Context context;

    public MainAdapter(Context context,Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void setIkm(List<IkmModel> ikms) {
        this.ikm = ikms;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_main, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.title.setText(ikm.get(position).getCompany());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("id", ikm.get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return ikm.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        final ImageView image;
        final TextView title;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.usernameRv);
        }
    }
}
