package com.example.asthmaapplication.main.adapter

import com.example.asthmaapplication.main.model.AsthmaInfoModel
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.asthmaapplication.R
import com.example.asthmaapplication.databinding.CardItemLearningBinding
import java.util.ArrayList

class AsthmaInfoAdapter(
    private val asthmaInfoModels: ArrayList<AsthmaInfoModel>
) : RecyclerView.Adapter<AsthmaInfoAdapter.ViewHolder>() {
    private var previousPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardItemLearningBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = asthmaInfoModels[position]
        holder.binding.title.text = model.getTitle()
        holder.binding.description.text = model.getDescription()
        holder.binding.imageView.setImageResource(model.getImageview())
        holder.binding.actionExpand.setOnClickListener {
            if (position == previousPosition) {
                notifyItemChanged(position)
                previousPosition = -1
            } else {
                if (previousPosition != -1) {
                    notifyItemChanged(previousPosition)
                }
                previousPosition = position
                notifyItemChanged(position)
            }
        }
        holder.binding.title.setOnClickListener {
            if (position == previousPosition) {
                notifyItemChanged(position)
                previousPosition = -1
            } else {
                if (previousPosition != -1) {
                    notifyItemChanged(previousPosition)
                }
                previousPosition = position
                notifyItemChanged(position)
            }
        }
        if (position == previousPosition) {
            holder.binding.actionExpand.setImageResource(R.drawable.ic_retract)
            holder.binding.description.visibility = View.VISIBLE
            holder.binding.imageView.visibility = View.VISIBLE
        } else {
            holder.binding.actionExpand.setImageResource(R.drawable.ic_expand)
            holder.binding.description.visibility = View.GONE
            holder.binding.imageView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return asthmaInfoModels.size
    }

    class ViewHolder(var binding: CardItemLearningBinding) : RecyclerView.ViewHolder(
        binding.root
    )
}