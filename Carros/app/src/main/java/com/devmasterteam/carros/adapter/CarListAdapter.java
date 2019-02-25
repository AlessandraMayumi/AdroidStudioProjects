package com.devmasterteam.carros.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devmasterteam.carros.R;
import com.devmasterteam.carros.entities.Car;
import com.devmasterteam.carros.listener.OnListClickInteractionListener;
import com.devmasterteam.carros.viewholder.CarViewHolder;

import java.util.List;

public class CarListAdapter extends RecyclerView.Adapter<CarViewHolder> {
    private List<Car> mListCar;
    private OnListClickInteractionListener mOnListClickInteractionListener;

    public CarListAdapter(List<Car>cars, OnListClickInteractionListener listener){
        this.mListCar = cars;
        this.mOnListClickInteractionListener = listener;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Context context  = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View carView = inflater.inflate(R.layout.row_car_list, parent, false);
        return new CarViewHolder(carView);
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        Car car = this.mListCar.get(position);
        holder.bindData(car, this.mOnListClickInteractionListener);

    }

    @Override
    public int getItemCount() {
        return this.mListCar.size();
    }
}
