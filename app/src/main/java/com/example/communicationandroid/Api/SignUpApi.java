package com.example.communicationandroid.Api;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.User;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.MyApp;
import com.example.communicationandroid.R;
import com.example.communicationandroid.ViewModel.UserViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;

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
                .baseUrl(MyApp.context.getString(R.string.ServerStartUrl)+Global.getServer()+"/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        webServiceAPI = retrofit.create(SignUpService.class);
    }


    public Response<String> post(User user, ImageView imageView, UserViewModel userViewModel) {
        Call<String> call = webServiceAPI.postSignIn(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == CODE_OK) {
                    token = new MutableLiveData<String>(response.body());//response.body();
                    responseSignUp = response;
                    Global.setToken(token, user.getId(), null);
                    Drawable drawable = imageView.getDrawable();
                    if ( drawable != null) {
                        user.setImage(encodeImage(imageView));
                    } else {
                        user.setImage(null);
                    }
                    Global.setCurrentUser(user);
                    userViewModel.addUser(user);
                } else {
                    //"Username or password is invalid"
                    String errorMessage = "Username is already exist";
                    Global.setToken(null, null, errorMessage);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
        return responseSignUp;
    }


    private byte[] encodeImage(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageInByte = baos.toByteArray();
        try {
            baos.close();
        } catch (Exception e) {
        }
        return imageInByte;

    }
}
