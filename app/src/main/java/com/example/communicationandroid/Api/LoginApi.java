package com.example.communicationandroid.Api;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.communicationandroid.Entities.User;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.MyApp;
import com.example.communicationandroid.R;
import com.example.communicationandroid.ViewModel.UserViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginApi {
    public static final int CODE_OK = 200;

    Response<String> responseLogin;
    MutableLiveData<String> token;
    Retrofit retrofit;
    LoginService webServiceAPI;

    public LoginApi() {
        token = null;
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApp.context.getString(R.string.ServerStartUrl)+Global.getServer()+"/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        webServiceAPI = retrofit.create(LoginService.class);
    }


    public Response<String> post(User user, UserViewModel userViewModel) {
        Call<String> call = webServiceAPI.postLogin(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.code() == CODE_OK) {
                    token = new MutableLiveData<>(response.body());//response.body();
                    responseLogin = response;
                    Global.setToken(token,user.getId(),null);
                    user.setImage(null);
                    Global.setCurrentUser(user);
                    userViewModel.addUser(user);
                } else {
                    //"Username or password is invalid"
                    String errorMessage ="Username or password is incorrect";
                    Global.setToken(null,null,errorMessage);
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
            }
        });
        return responseLogin;
    }
}
