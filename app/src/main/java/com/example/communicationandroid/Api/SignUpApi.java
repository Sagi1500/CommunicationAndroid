package com.example.communicationandroid.Api;

import androidx.lifecycle.MutableLiveData;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.User;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.MyApp;
import com.example.communicationandroid.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SignUpApi {
    public static final int CODE_OK = 200;

    Response<String> responseSignUp;
    MutableLiveData<String> token;
    Retrofit retrofit;
    SignUpService webServiceAPI;

    public SignUpApi() {
        token = null;
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApp.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        webServiceAPI = retrofit.create(SignUpService.class);
    }


    public Response<String> post(User user) {
        Call<String> call = webServiceAPI.postSignIn(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == CODE_OK) {
                    token = new MutableLiveData<String>(response.body());//response.body();
                    responseSignUp = response;
                    Global.setToken(token,user.getId());
                } else {
                    //"Username or password is invalid"
                    Global.setToken(null,null);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
        return responseSignUp;
    }
}
