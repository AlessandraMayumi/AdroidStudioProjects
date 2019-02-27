package com.devmasterteam.carrosv2.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.devmasterteam.carrosv2.R;
import com.devmasterteam.carrosv2.adapter.CarListAdapter;
import com.devmasterteam.carrosv2.constants.CarrosConstants;
import com.devmasterteam.carrosv2.data.CarMock;
import com.devmasterteam.carrosv2.entities.Car;
import com.devmasterteam.carrosv2.listener.OnListClickInteractionListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewHolder mViewHolder = new ViewHolder();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        this.mContext = this;

        CarMock carMock = new CarMock(this);
        List<Car> carList = new ArrayList<>(carMock.getList());

        // Obter a recyclerview
        this.mViewHolder.recyclerCars = (RecyclerView) this.findViewById(R.id.recycler_cars);

        OnListClickInteractionListener listener = new OnListClickInteractionListener() {
            @Override
            public void onClick(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt(CarrosConstants.CARROS_ID, id);

                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        };

        // Definir adapter
        CarListAdapter carListAdapter = new CarListAdapter(carList, listener);
        this.mViewHolder.recyclerCars.setAdapter(carListAdapter);

        // Definir layout
        LinearLayoutManager livearLayoutManager = new LinearLayoutManager(this);
        this.mViewHolder.recyclerCars.setLayoutManager(livearLayoutManager);
    }

    private static class ViewHolder {
        RecyclerView recyclerCars;
    }
}
