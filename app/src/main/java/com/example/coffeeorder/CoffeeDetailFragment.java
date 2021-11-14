package com.example.coffeeorder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;


public class CoffeeDetailFragment extends Fragment {

    NavController navController;
    int quantity = 0;
    FirebaseFirestore firebaseFirestore;
    TextView coffeename ,description,quantityview,orderINFO;
    Button add,sub,order;
    ImageView imageView;
    String coffeeid,name,coffeedescription,imageURL;
    int price = 0;


    public CoffeeDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coffee_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = view .findViewById(R.id.coffeeDetailImage);
        coffeename = view .findViewById(R.id.coffeeNameDetail);
        description = view .findViewById(R.id.coffeedetaildetial);
        add = view .findViewById(R.id.incrementcoffee);
        sub = view .findViewById(R.id.decrementcoffee);
        quantityview = view.findViewById(R.id.quantityDetailNumber);
        firebaseFirestore = FirebaseFirestore.getInstance();
        navController = Navigation.findNavController(view);
        order = view .findViewById(R.id.orderdetail);
        orderINFO =  view.findViewById(R.id.orderINFO);

        name = CoffeeDetailFragmentArgs.fromBundle(getArguments()).getCoffeename();
        coffeeid = CoffeeDetailFragmentArgs.fromBundle(getArguments()).getId();
        imageURL = CoffeeDetailFragmentArgs.fromBundle(getArguments()).getImageurl();
        coffeedescription = CoffeeDetailFragmentArgs.fromBundle(getArguments()).getDescription();
        price = CoffeeDetailFragmentArgs.fromBundle(getArguments()).getPrice();

        Glide.with(view.getContext()).load(imageURL).into(imageView);
        coffeename.setText(name + "$" + String.valueOf(price));
        description.setText(coffeedescription);
    }
}