package com.example.communicationandroid.Api;

import com.example.communicationandroid.Entities.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface
SignUpService {
    @POST("Users")
    Call<String> postSignIn(@Body User user);

}
