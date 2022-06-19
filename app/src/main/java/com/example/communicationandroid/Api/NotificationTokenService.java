package com.example.communicationandroid.Api;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface NotificationTokenService {

    @POST("NotificationToken")
    Call<Void> postNotificationToken(@Body String androidToken,
                                       @Header("Authorization") String token);
}
