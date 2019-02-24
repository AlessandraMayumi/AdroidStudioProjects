package com.devmasterteam.carros.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devmasterteam.carros.R;
import com.devmasterteam.carros.entities.Car;
import com.devmasterteam.carros.viewholder.CarViewHolder;

import java.util.List;

public class CarListAdapter extends RecyclerView.Adapter<CarViewHolder> {
    private List<Car> mListCar;
    public CarListAdapter(List<Car> cars){
        this.mListCar = cars;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Context context  = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View carView = inflater.inflate(R.layout.row_car_list, parent, false);
        return new CarViewHolder(carView);
    }

    @Override
    public void onBindViewHolder(CarViewHolder carViewHolder, int position) {
        Car car = this.mListCar.get(position);
        carViewHolder.bindData(car);
    }

    @Override
    public int getItemCount() {
        return this.mListCar.size();
    }
}
