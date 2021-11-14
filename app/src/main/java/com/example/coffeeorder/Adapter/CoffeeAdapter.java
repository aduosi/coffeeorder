package com.example.coffeeorder.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.example.coffeeorder.Model.CofeModel;
import com.example.coffeeorder.R;


import java.util.List;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeListHolder> {

    List<CofeModel> cofeModelList;

    GetOneCoffee interfacegetCoffee;

    public CoffeeAdapter(GetOneCoffee interfacegetCoffee) {
        this.interfacegetCoffee = interfacegetCoffee;
    }

    @Override
    public CoffeeListHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coffeeliststyle,parent,false);
        return  new CoffeeListHolder(view);
    }

    @Override
    public void onBindViewHolder( CoffeeListHolder holder, int position) {

        holder.coffeename.setText(cofeModelList.get(position).getCoffeename());
        holder.description.setText(cofeModelList.get(position).getDescription());

        Glide.with(holder.itemView.getContext()).load(cofeModelList.get(position).getImageURL()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return cofeModelList.size();
    }
//  sets the coffee list from viewmodel
    public void setCofeModelList(List<CofeModel>cofeModelList){
        this.cofeModelList = cofeModelList;
    }

    class CoffeeListHolder extends ViewHolder implements View.OnClickListener{

        TextView coffeename, description;
        ImageView imageView;

        public CoffeeListHolder(View itemView) {


            super(itemView);

            coffeename = itemView.findViewById(R.id.coffeeName);
            description = itemView.findViewById(R.id.coffeedetail);
            imageView = itemView.findViewById(R.id.coffeeImage);

            coffeename.setOnClickListener(this);
            description.setOnClickListener(this);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {


            interfacegetCoffee.clickedCoffee(getAdapterPosition(),cofeModelList);


        }
    }

     public interface GetOneCoffee {
         void  clickedCoffee( int position, List<CofeModel>cofeModels);
     }
}
