package com.devmasterteam.carros.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.devmasterteam.carros.R;
import com.devmasterteam.carros.entities.Car;
import com.devmasterteam.carros.listener.OnListClickInteractionListener;

public class CarViewHolder extends RecyclerView.ViewHolder {
    private TextView mTextModel;

    public CarViewHolder(View itemView) {
        super(itemView);
        this.mTextModel = (TextView) itemView.findViewById(R.id.text_model);
    }

    public void bindData(final Car car, final OnListClickInteractionListener listener) {
        this.mTextModel.setText(car.model);
        this.mTextModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnClick(car.id);
            }
        });
    }
}
