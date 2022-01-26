package com.kutumb.trending.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kutumb.trending.databinding.CardviewBinding;
import com.kutumb.trending.model.ItemModel;
import com.kutumb.trending.model.OwnerModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class trendingAdapter extends RecyclerView.Adapter<trendingAdapter.TrendViewHolder> {
    class TrendViewHolder extends RecyclerView.ViewHolder {
        private CardviewBinding binding;

        public TrendViewHolder(CardviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

    private CardviewBinding binding;
    private Context context;
    private static List<ItemModel> itemModels = new ArrayList<>();

    public trendingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TrendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = CardviewBinding.inflate(inflater, parent, false);
        return new trendingAdapter.TrendViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendViewHolder holder, int position) {
        //super.onBindViewHolder(holder, position);
        ItemModel data = itemModels.get(position);
        String count = (data.getStars() >= 1000 ? data.getStars() + "" : String.valueOf(data.getStars()));

        if(data.getName() != null) holder.binding.author.setText(new OwnerModel(data.getOwner()).getUsername());
        if(data.getName() != null) holder.binding.reponame.setText(data.getName());
        if(data.getDescription() != null) holder.binding.description.setText(data.getDescription());

        if(data.getOwner() != null) Picasso.get().load(new OwnerModel(data.getOwner()).getAvatar()).into(holder.binding.profile);
        if(data.getLanguage() != null) holder.binding.language.setText(data.getLanguage());
        holder.binding.star.setText(count);

        boolean isExpandable = itemModels.get(position).isExpandable();
        holder.binding.expandLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        int pos = holder.getAdapterPosition();
        holder.binding.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemModel expand = itemModels.get(pos);
                expand.setExpandable(!expand.isExpandable());
                notifyItemChanged(pos);
            }
        });
        }

    @Override
    public int getItemCount() {
        return (itemModels == null ? 0 : itemModels.size());
    }

    public void setTrendList(List<ItemModel> itemModels) {
            this.itemModels = itemModels;
            notifyDataSetChanged();
        }

        public void clearData() {
            itemModels = new ArrayList<>();
            notifyDataSetChanged();
        }
}

