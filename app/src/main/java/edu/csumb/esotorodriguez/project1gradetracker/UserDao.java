package edu.csumb.esotorodriguez.project1gradetracker;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user_table ORDER BY userID DESC")
    List<User> getAllUsers();

    @Query("SELECT * FROM user_table WHERE username = :user")
    User getSpecificUser(String user);
}
