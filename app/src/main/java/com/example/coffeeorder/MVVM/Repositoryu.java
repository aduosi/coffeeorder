package com.example.coffeeorder.MVVM;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.coffeeorder.Model.CofeModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Repositoryu {

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    List<CofeModel> cofeModelList = new ArrayList<>();

    CoffeeList interfacecoffeelist;

    public Repositoryu(CoffeeList interfacecoffeelist) {
        this.interfacecoffeelist = interfacecoffeelist;
    }

    public void getCoffee(){

        firebaseFirestore.collection("Coffees").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {


                if (task.isSuccessful()){

                    cofeModelList.clear();

                    for (DocumentSnapshot ds: task.getResult().getDocuments()){

                        CofeModel cofeModel = ds.toObject(CofeModel.class);
                        cofeModelList.add(cofeModel);


                        interfacecoffeelist.coffeeList(cofeModelList);

                    }
                }

            }
        });
    }

    public  interface CoffeeList{
        void coffeeList(List<CofeModel> cofeModels);
    }
}
