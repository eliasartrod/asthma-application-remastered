package com.example.asthmaapplication.main.mainpage.subpages.learningpages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asthmaapplication.databinding.CardItemLearningBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AsthmaInfoAdapter extends RecyclerView.Adapter<AsthmaInfoAdapter.ViewHolder> {
    private final Context context;
    private ArrayList<AsthmaInfoModel> asthmaInfoModels = new ArrayList<>();

    public AsthmaInfoAdapter(Context context, ArrayList<AsthmaInfoModel> asthmaInfoModels) {
        this.context = context;
        this.asthmaInfoModels = asthmaInfoModels;
    }

    @NonNull
    @Override
    public AsthmaInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CardItemLearningBinding binding = CardItemLearningBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AsthmaInfoAdapter.ViewHolder holder, int position) {
        AsthmaInfoModel model = asthmaInfoModels.get(position);

        holder.description.setVisibility(View.GONE);
        holder.imageView.setVisibility(View.GONE);

        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        holder.imageView.setImageResource(model.getImageview());

        holder.title.setOnClickListener(v -> {
            holder.description.setVisibility(View.VISIBLE);
            holder.imageView.setVisibility(View.VISIBLE);
        });

        holder.description.setOnClickListener(v -> {
            holder.description.setVisibility(View.GONE);
            holder.imageView.setVisibility(View.GONE);
        });

    }

    @Override
    public int getItemCount() {
        return asthmaInfoModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView imageView;


        public ViewHolder(CardItemLearningBinding binding) {
            super(binding.getRoot());
            title = binding.title;
            description = binding.description;
            imageView = binding.imageView;
        }
    }
}
