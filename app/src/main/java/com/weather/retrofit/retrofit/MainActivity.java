package com.weather.retrofit.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import Data.Model.Weather;
import Data.Model.Query;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import remote.WeatherAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.city)TextView city;
    @Bind(R.id.temp)TextView temp;
    @Bind(R.id.condition)TextView condition;
    @Bind(R.id.button)TextView btn_refresh;
    @Bind(R.id.Date)TextView Date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.button) public void btn_refresh_onclick(){
        WeatherAPI.factory.getInstance().getWeather().enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
             if(response.isSuccessful()){

                 temp.setText(response.body().getQuery().getResults().getChannel().getItem().getCondition().getTemp().toString()+ " "+ response.body().getQuery().getResults().getChannel().getUnits().getTemperature());
                 city.setText(response.body().getQuery().getResults().getChannel().getLocation().getCity().toString());
                 condition.setText(response.body().getQuery().getResults().getChannel().getItem().getCondition().getText().toString());
                 Date.setText(response.body().getQuery().getResults().getChannel().getLastBuildDate());
             }
                else {

             }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });
}

    @Override
    protected void onResume() {
        super.onResume();
        btn_refresh_onclick();

    }
}
