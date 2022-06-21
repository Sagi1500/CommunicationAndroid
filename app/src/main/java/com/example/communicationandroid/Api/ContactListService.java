package com.example.communicationandroid.Api;
import com.example.communicationandroid.Entities.Contact;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContactListService {
    @GET("Contacts")
    Call<List<Contact>> getAllContacts(@Header("Authorization") String token);

    @GET("Contacts/{id}")
    Call<Contact> getContact(@Path("id") String id,
                             @Header("Authorization") String token);

    @POST("Contacts")
    Call<Contact> addContact(@Body Contact contact,
                             @Header("Authorization") String token);

    @DELETE("Contacts/{id}")
    Call<Void> deleteContact(@Path("id") String id,
                             @Header("Authorization") String token);

    @PUT("Contacts/{id}")
    Call<Void> changeContact(@Path("id") String id,
                             @Header("Authorization") String token,
                             @Body ContactListApi.temp contact);

}
