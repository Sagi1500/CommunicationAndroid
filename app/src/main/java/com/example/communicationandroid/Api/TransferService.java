package com.example.communicationandroid.Api;

import com.example.communicationandroid.Entities.Message;
import com.example.communicationandroid.Entities.Transfer;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TransferService {
    @POST("Transfer")
    Call<Message> postTransfer(@Body Transfer transfer);

}
