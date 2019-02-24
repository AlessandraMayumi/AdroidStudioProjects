package com.devmasterteam.carros.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.devmasterteam.carros.R;
import com.devmasterteam.carros.adapter.CarListAdapter;
import com.devmasterteam.carros.data.CarMock;
import com.devmasterteam.carros.entities.Car;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewHolder mViewHolder = new ViewHolder();

    private static class ViewHolder {
        RecyclerView recyclerCars;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.recyclerCars = (RecyclerView) this.findViewById(R.id.recycler_cars);

        // Definir Adapter
        CarMock carMock = new CarMock();
        List<Car> carList = new ArrayList<>();
        carList.addAll(carMock.getList());
        CarListAdapter carListAdapter = new CarListAdapter(carList);
        this.mViewHolder.recyclerCars.setAdapter(carListAdapter);

        // Definir Layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.mViewHolder.recyclerCars.setLayoutManager(linearLayoutManager);
    }
}
