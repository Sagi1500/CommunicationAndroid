package com.example.communicationandroid.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.communicationandroid.Entities.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE id = :username")
    User get(String username);

    @Insert
    void insert(User... Users);

    @Update
    void update(User... Users);

    @Delete
    void delete(User... Users);

}
