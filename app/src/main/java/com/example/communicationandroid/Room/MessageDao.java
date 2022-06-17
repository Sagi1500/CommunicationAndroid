package com.example.communicationandroid.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.communicationandroid.Entities.Message;

import java.util.List;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM message WHERE receiverUsername =:username")
    LiveData<List<Message>> index(String username);

    @Query("SELECT * FROM message WHERE receiverUsername =:username")
    List<Message> getAllMessages(String username);

    @Query("SELECT * FROM message WHERE id = :id")
    Message get(int id);

    @Insert
    void insert(Message... Messages);

    @Update
    void update(Message... Messages);

    @Delete
    void delete(Message... Messages);
}
