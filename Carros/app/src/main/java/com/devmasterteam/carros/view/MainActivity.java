package com.devmasterteam.carros.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.devmasterteam.carros.R;
import com.devmasterteam.carros.adapter.CarListAdapter;
import com.devmasterteam.carros.constants.CarrosConstants;
import com.devmasterteam.carros.data.CarMock;
import com.devmasterteam.carros.entities.Car;
import com.devmasterteam.carros.listener.OnListClickInteractionListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewHolder mViewHolder = new ViewHolder();
    Context mContext;

    OnListClickInteractionListener listener = new OnListClickInteractionListener() {
        @Override
        public void OnClick(int id) {
            Bundle bundle = new Bundle();
            bundle.putInt(CarrosConstants.CARRO_ID, id);

            Intent intent = new Intent(mContext, DetailsActivity.class);
            intent.putExtras(bundle);

            startActivity(intent);
        }
    };

    private static class ViewHolder {
        RecyclerView recyclerCars;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.recyclerCars = (RecyclerView) this.findViewById(R.id.recycler_cars);
        this.mContext = this;

        // Definir Adapter
        CarMock carMock = new CarMock();
        List<Car> carList = new ArrayList<>();
        carList.addAll(carMock.getList());
        CarListAdapter carListAdapter = new CarListAdapter(carList, listener);
        this.mViewHolder.recyclerCars.setAdapter(carListAdapter);

        // Definir Layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.mViewHolder.recyclerCars.setLayoutManager(linearLayoutManager);
    }
}
