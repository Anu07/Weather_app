package com.example.zapbuild.weather_app.api;

import com.example.zapbuild.weather_app.models.Sample;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by zapbuild on 24/12/15.
 */
public class Apiclient {


    public static String Base_url = "http://api.openweathermap.org/data/2.5";
    public static Api_interface api_interface;


    public static Api_interface get_request() {
        if (api_interface == null) {
            final OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit retro = new Retrofit.Builder()
                    .baseUrl(Base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okClient)
                    .build();
            api_interface = retro.create(Api_interface.class);
        }

        return api_interface;
    }

    public interface Api_interface {

        @GET("/weather")
        Call<Sample> getWeather(@Query("q") String location, @Query("appid") String app_id);


    }

}
