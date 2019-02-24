package com.devmasterteam.carros.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.devmasterteam.carros.R;
import com.devmasterteam.carros.constants.CarrosConstants;
import com.devmasterteam.carros.data.CarMock;
import com.devmasterteam.carros.entities.Car;

public class DetailsActivity extends AppCompatActivity {
    private ViewHolder mViewHolder = new ViewHolder();
    private CarMock mCarMock;
    private Car mCar;

    public static class ViewHolder {
        TextView textModel;
        TextView textHorsePower;
        TextView textPrice;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mCarMock = new CarMock();

        this.mViewHolder.textModel = (TextView) this.findViewById(R.id.text_model);
        this.mViewHolder.textHorsePower = (TextView) this.findViewById(R.id.text_power);
        this.mViewHolder.textPrice = (TextView) this.findViewById(R.id.text_price);

        this.getDataFromActivity();
        this.setData();
    }

    private void getDataFromActivity() {
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            this.mCar = this.mCarMock.get(extras.getInt(CarrosConstants.CARRO_ID));
        }
    }

    private void setData() {
        this.mViewHolder.textModel.setText(this.mCar.model);
        this.mViewHolder.textHorsePower.setText(String.valueOf(this.mCar.horsePower));
        this.mViewHolder.textPrice.setText(String.valueOf(this.mCar.price));
    }
}
