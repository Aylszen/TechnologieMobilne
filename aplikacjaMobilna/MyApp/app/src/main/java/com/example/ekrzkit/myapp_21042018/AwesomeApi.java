package com.example.ekrzkit.myapp_21042018;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by ekrzkit on 2018-04-21.
 */

public interface AwesomeApi {

    @GET("login/")
    Call<List<User>> getUsers();

    @POST("register/")
    Call<User> createUser(@Body UserObject userobject);

}
