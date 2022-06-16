package com.example.communicationandroid.Api;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.Invitation;
import com.example.communicationandroid.Entities.Message;
import com.example.communicationandroid.Entities.Transfer;
import com.example.communicationandroid.MyApp;
import com.example.communicationandroid.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransferApi {


    Retrofit retrofit;
    TransferService webTransferService;

    public TransferApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApp.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webTransferService = retrofit.create(TransferService.class);
    }

    public void postTransfer(Transfer transfer){
        Call<Message> call = webTransferService.postTransfer(transfer);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                int code = response.code();
                if (201 == code){
                    Message message = response.body();
                }else{
                    //post failed
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }
}
