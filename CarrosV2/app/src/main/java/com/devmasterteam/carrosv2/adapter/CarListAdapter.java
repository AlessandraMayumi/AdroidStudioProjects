package com.devmasterteam.carrosv2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devmasterteam.carrosv2.R;
import com.devmasterteam.carrosv2.entities.Car;
import com.devmasterteam.carrosv2.listener.OnListClickInteractionListener;
import com.devmasterteam.carrosv2.viewholder.CarViewHolder;

import java.util.List;

public class CarListAdapter extends RecyclerView.Adapter<CarViewHolder> {
    private List<Car> mListCars;
    private OnListClickInteractionListener mOnListClickInteractionListener;

    public CarListAdapter(List<Car> cars, OnListClickInteractionListener listener) {
        this.mListCars = cars;
        this.mOnListClickInteractionListener = listener;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View carView = inflater.inflate(R.layout.row_car_list, viewGroup, false);

        return new CarViewHolder(carView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = this.mListCars.get(position);
        holder.bindData(car, mOnListClickInteractionListener);
    }

    @Override
    public int getItemCount() {
        return this.mListCars.size();
    }
}