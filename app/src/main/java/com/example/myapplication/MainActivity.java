package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.api.Api;
import com.example.myapplication.model.AndVersion;
import com.example.myapplication.model.Hero;
import com.google.gson.Gson;

import java.util.List;

//https://www.simplifiedcoding.net/retrofit-android-example/ ---> sample website refereed
public class MainActivity extends AppCompatActivity {
    String TAG = this.getClass().getSimpleName();

    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click = (Button) findViewById(R.id.get);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHeroes();
            }
        });

    }

    private void getHeroes() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL1)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);
        Api api2 = retrofit2.create(Api.class);



        Call<List<Hero>> call = api.getHeros();
        Call<AndVersion> ver = api2.getVersions();


        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                List<Hero> heroList = response.body();
                Log.e("Success", new Gson().toJson(response.body()));

                //Creating an String array for the ListView
                String[] heroes = new String[heroList.size()];

               /* //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getName();
                }*/


                //displaying the string array into listview
                //listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));

            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        /*ver.enqueue(new Callback<AndVersion>() {
            @Override
            public void onResponse(Call<AndVersion> call, Response<AndVersion> response) {
                Log.e("Success", new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<AndVersion> call, Throwable t) {
                Log.e("Failure", t.getMessage());
            }
        });*/
    }
}
