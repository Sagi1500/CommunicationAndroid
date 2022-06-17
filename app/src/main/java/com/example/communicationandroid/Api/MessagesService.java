package com.example.communicationandroid.Api;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MessagesService {
    @GET("Contacts/{id}/Messages")
    Call<List<Message>> getAllMessages(@Path("id") String id,
                                       @Header("Authorization") String token);

    @GET("Contacts/{id}/Messages/{id2}")
    Call<Message> getMessage(@Path("id") String id,
                             @Path("id2") String id2,
                             @Header("Authorization") String token);

    @POST("Contacts/{id}/Messages")
    Call<Message> addMessage(@Path("id") String id,
                             @Body Message message,
                             @Header("Authorization") String token);

    @DELETE("Contacts/{id}/Messages/{id2}")
    Call<Void> deleteMessage(@Path("id") String id,
                             @Path("id2") int id2,
                             @Header("Authorization") String token);

    @PUT("Contacts/{id}/Messages/{id2}")
    Call<Void> changeMessage(@Path("id") String id,
                             @Path("id2") String id2,
                             @Header("Authorization") String token,
                             @Body Message message);
}
