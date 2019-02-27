package com.devmasterteam.carrosv2.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.devmasterteam.carrosv2.R;
import com.devmasterteam.carrosv2.entities.Car;
import com.devmasterteam.carrosv2.listener.OnListClickInteractionListener;

public class CarViewHolder extends RecyclerView.ViewHolder {
    private ImageView mImgCarPicture;
    private TextView mTextCarModel;
    private TextView mTextViewDetails;

    public CarViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mImgCarPicture = (ImageView) itemView.findViewById(R.id.img_car_pic);
        this.mTextCarModel = (TextView) itemView.findViewById(R.id.text_model);
        this.mTextViewDetails = (TextView) itemView.findViewById(R.id.text_view_details);
    }

    public void bindData(final Car car, final OnListClickInteractionListener listener) {
        this.mImgCarPicture.setImageDrawable(car.picture);
        this.mTextCarModel.setText(car.model);
        this.mTextViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(car.id);
            }
        });
    }
}
