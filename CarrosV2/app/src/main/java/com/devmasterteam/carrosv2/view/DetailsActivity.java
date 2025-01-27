package com.devmasterteam.carrosv2.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.devmasterteam.carrosv2.R;
import com.devmasterteam.carrosv2.constants.CarrosConstants;
import com.devmasterteam.carrosv2.data.CarMock;
import com.devmasterteam.carrosv2.entities.Car;

public class DetailsActivity extends AppCompatActivity {
    private ViewHolder mViewHolder = new ViewHolder();
    private CarMock mCarMock;
    private Car mCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        this.mCarMock = new CarMock(this);

        this.mViewHolder.imgCarPitcture = (ImageView) this.findViewById(R.id.img_car_pic);
        this.mViewHolder.textModel = (TextView) this.findViewById(R.id.text_model);
        this.mViewHolder.textManufacturer = (TextView) this.findViewById(R.id.text_manufacturer);
        this.mViewHolder.textHorsePower = (TextView) this.findViewById(R.id.text_horse_power);
        this.mViewHolder.textPrice = (TextView) this.findViewById(R.id.text_price);

        this.getDatatFromActivity();
        this.setData();
    }

    private void setData() {
        this.mViewHolder.imgCarPitcture.setImageDrawable(this.mCar.picture);
        this.mViewHolder.textModel.setText(this.mCar.model);
        this.mViewHolder.textManufacturer.setText((this.mCar.manufacturer));
        this.mViewHolder.textHorsePower.setText(String.valueOf(this.mCar.horsePower));
        this.mViewHolder.textPrice.setText(String.valueOf(this.mCar.price));
    }

    private void getDatatFromActivity() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mCar = this.mCarMock.get(extras.getInt(CarrosConstants.CARROS_ID));
        }
    }

    private static class ViewHolder {
        ImageView imgCarPitcture;
        TextView textModel;
        TextView textManufacturer;
        TextView textHorsePower;
        TextView textPrice;
    }
}
