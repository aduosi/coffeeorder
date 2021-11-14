package com.example.coffeeorder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coffeeorder.Adapter.CoffeeAdapter;
import com.example.coffeeorder.MVVM.CoffeeViewModel;
import com.example.coffeeorder.Model.CofeModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class AllCoffeeListFragment extends Fragment implements CoffeeAdapter.GetOneCoffee {


    FirebaseFirestore firebaseFirestore;
    CoffeeAdapter mAdapter;
    RecyclerView recyclerView;
    CoffeeViewModel viewModel;
    NavController navController;

    public AllCoffeeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_coffee_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recViewAll);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CoffeeAdapter(this);
        navController = Navigation.findNavController(view);
        viewModel = new ViewModelProvider(getActivity()).get(CoffeeViewModel.class);
        viewModel.getCoffeelist().observe(getViewLifecycleOwner(), new Observer<List<CofeModel>>() {
            @Override
            public void onChanged(List<CofeModel> cofeModels) {

                mAdapter.setCofeModelList(cofeModels);
                recyclerView.setAdapter(mAdapter);
            }
        });


    }

    @Override
    public void clickedCoffee(int position, List<CofeModel> cofeModels) {

        String coffeeid = cofeModels.get(position).getCoffeeid();
        String description = cofeModels.get(position).getDescription();
        String coffeename = cofeModels.get(position).getCoffeename();
        int price = cofeModels.get(position).getPrice();
        String imageURL = cofeModels.get(position).getImageURL();



        AllCoffeeListFragmentDirections.ActionAllCoffeeListFragmentToCoffeeDetaFragment
                action = AllCoffeeListFragmentDirections.actionAllCoffeeListFragmentToCoffeeDetaFragment();



        action.setCoffeename(coffeename);
        action.setDescription(description);
        action.setImageurl(imageURL);
        action.setPrice(price);
        action.setId(coffeeid);

    navController.navigate(action);
    }
}
