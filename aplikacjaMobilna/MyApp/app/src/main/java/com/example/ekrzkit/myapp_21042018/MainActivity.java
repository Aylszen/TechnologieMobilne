package com.example.ekrzkit.myapp_21042018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    final String BASE_URL = "http://192.168.1.7:8080";
    private AwesomeApi awesomeApi;
    List<User> userList;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        awesomeApi = retrofit.create(AwesomeApi.class);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callGetUsers();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });



}    private void callGetUsers() {
        //Toast.makeText(this, "DADA", Toast.LENGTH_SHORT).show();
        //textView.setText("DADA");
        Call<List<User>> callGetUsers = awesomeApi.getUsers();
        callGetUsers.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                textView.setText("");
                if (response.isSuccessful())
                {
                    userList = response.body();
                    for (int i = 0; i<userList.size();i++)
                    {
                        textView.append(userList.get(i).getName() + "\n");
                    }

                }
                else
                {
                    textView.setText("Fail1" +  response.message());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                textView.setText("Fail2");
                Log.e("cos", t.getLocalizedMessage(), t);
                t.printStackTrace();
            }
        });
    }

    private void addUser(){
        User user = new User();
        user.setId("12241");
        user.setName("Kita Krzysztof");
        UserObject userobject = new UserObject(user);
        Call<User> callGetUsers = awesomeApi.createUser(userobject);
        callGetUsers.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                textView.setText("");
                if (response.isSuccessful())
                {
                    textView.append(response.body().getName() + "\n");
                }
                else {
                    textView.setText("Fail with creating new user");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                    textView.setText("Fail with creating new user2");
                Log.e("cos", t.getLocalizedMessage(), t);
                t.printStackTrace();
                }
        });
    }
    }