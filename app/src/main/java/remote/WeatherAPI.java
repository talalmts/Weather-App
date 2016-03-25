package remote;

import Data.Model.Weather;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Talal Saleem on 3/25/2016.
 */
public interface WeatherAPI {


    @GET("yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22islamabad%2C%20pk%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys") Call<Weather> getWeather();

    class factory{

        private static WeatherAPI service;

        public static WeatherAPI getInstance(){
        if(service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://query.yahooapis.com/v1/public/")
                    .build();
            service = retrofit.create(WeatherAPI.class);
            return service;
        }else{
            return service;
        }

        }
    }

}

