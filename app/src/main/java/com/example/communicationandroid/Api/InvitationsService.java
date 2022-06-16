package com.example.communicationandroid.Api;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.Invitation;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface InvitationsService {
    @POST("Invitations")
    Call<Contact> postInvitation(@Body Invitation invitation);
//                             @Header("Authorization") String token);
}
