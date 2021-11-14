package com.example.coffeeorder.MVVM;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeeorder.Model.CofeModel;

import java.util.List;

public class CoffeeViewModel  extends ViewModel implements Repositoryu.CoffeeList {

    MutableLiveData<List<CofeModel>> mutableLiveData = new MutableLiveData<List<CofeModel>>();
    Repositoryu repositoryu = new Repositoryu(this);

    public CoffeeViewModel() {

        repositoryu.getCoffee();
    }

    public LiveData<List<CofeModel>> getCoffeelist(){
        return mutableLiveData;
    }


    @Override
    public void coffeeList(List<CofeModel> cofeModels) {
        mutableLiveData.setValue(cofeModels);

    }
}
