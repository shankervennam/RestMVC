package com.example.cr.restmvc.model.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cr.restmvc.R;
import com.example.cr.restmvc.model.pojo.Flower;

import java.util.List;

public class FlowersAdapter extends RecyclerView.Adapter<FlowersAdapter.Holder>
{
    private List<Flower> flowers;

    public FlowersAdapter(List<Flower> flower)
    {
        flowers = flower;
    }

    public void addFlower(Flower flower)
    {
        flowers.add(flower);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position)
    {
        Flower currentFlower = flowers.get(position);
        holder.mName.setText(currentFlower.name);
        holder.mCategory.setText(currentFlower.category);
        holder.mPrice.setText(Double.toString(currentFlower.price));
        holder.mInstructions.setText(currentFlower.instructions);

//        Picasso.with(holder.itemView.getContext()).load(Constants)
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Holder extends RecyclerView.ViewHolder
    {
        private TextView mName, mCategory, mPrice, mInstructions;
        private ImageView mImage;

        public Holder(View itemView)
        {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.flowerImage);
            mName = (TextView) itemView.findViewById(R.id.flowerName);
            mCategory = (TextView) itemView.findViewById(R.id.flowerCategory);
            mPrice = (TextView) itemView.findViewById(R.id.flowerPrice);
            mInstructions = (TextView) itemView.findViewById(R.id.flowerInstruction);
        }
    }
}


