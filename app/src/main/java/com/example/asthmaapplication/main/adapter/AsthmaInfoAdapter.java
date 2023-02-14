package com.example.asthmaapplication.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asthmaapplication.R;
import com.example.asthmaapplication.databinding.CardItemLearningBinding;
import com.example.asthmaapplication.main.model.AsthmaInfoModel;

import java.util.ArrayList;

public class AsthmaInfoAdapter extends RecyclerView.Adapter<AsthmaInfoAdapter.ViewHolder> {
    private final Context context;
    private Boolean expanded = false;
    private Integer previousPosition = -1;
    private ArrayList<AsthmaInfoModel> asthmaInfoModels;

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

        holder.binding.title.setText(model.getTitle());
        holder.binding.description.setText(model.getDescription());
        holder.binding.imageView.setImageResource(model.getImageview());

        holder.binding.actionExpand.setOnClickListener(v -> {
            if (position == previousPosition) {
                notifyItemChanged(position);
                previousPosition = -1;
            } else {
                if (previousPosition != -1) {
                    notifyItemChanged(previousPosition);
                }
                previousPosition = position;
                notifyItemChanged(position);
            }
        });

        if (position == previousPosition) {
            holder.binding.actionExpand.setImageResource(R.drawable.ic_retract);
            holder.binding.description.setVisibility(View.VISIBLE);
            holder.binding.imageView.setVisibility(View.VISIBLE);
        } else {
            holder.binding.actionExpand.setImageResource(R.drawable.ic_expand);
            holder.binding.description.setVisibility(View.GONE);
            holder.binding.imageView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return asthmaInfoModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardItemLearningBinding binding;


        public ViewHolder(CardItemLearningBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
