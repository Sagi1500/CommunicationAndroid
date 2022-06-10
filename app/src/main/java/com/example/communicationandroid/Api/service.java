package com.example.communicationandroid.Api;
import com.example.communicationandroid.Entities.Contact;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface service {
@GET("Contacts")
    Call<List<Contact>> getContacts();
@POST("Contacts")
    Call<Void> createContact(@Body Contact contact);
@DELETE("Contacts/{id}")
Call<Void> deleteContact(@Path("id") int id);
}
