package com.example.zapbuild.weather_app.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.zapbuild.weather_app.R;
import com.example.zapbuild.weather_app.api.Apiclient;
import com.example.zapbuild.weather_app.base.BaseActivity;
import com.example.zapbuild.weather_app.models.Sample;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.loc_ed)
    EditText mLocation_edit;
    @OnClick(R.id.submit_bttn) void submitClicked(){
        serviceCall();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

    }

    public void serviceCall(){

        String location_txt=mLocation_edit.getText().toString();

        if(TextUtils.isEmpty(location_txt)){
            onShowError("Field Left Blank");
            return;
        }

        fetch_weather_info(location_txt);


    }

    private void fetch_weather_info(String location_txt) {

        onShowProgress("Fetching...");
        Apiclient.Api_interface service = Apiclient.get_request();
        Log.v("L", "F" + location_txt);
        Call<Sample> call = service.getWeather(location_txt, "faa80a375f55c8aebe4b685d432f669e");
        call.enqueue(new Callback<Sample>() {
            @Override
            public void onResponse(Response<Sample> response, Retrofit retrofit) {
                onHideProgress();
                System.out.println("Response status:" + response.code());
                if (response.isSuccess()) {
                    Sample sample = (Sample) response.body();
                    System.out.println("Wind status:" + sample.getMain().getTemp().toString());
                    Snackbar.make(mLocation_edit,"Temperature detected: "+sample.getMain().getTemp(),Snackbar.LENGTH_LONG).show();


                }
            }

            @Override
            public void onFailure(Throwable t) {
                onHideProgress();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
