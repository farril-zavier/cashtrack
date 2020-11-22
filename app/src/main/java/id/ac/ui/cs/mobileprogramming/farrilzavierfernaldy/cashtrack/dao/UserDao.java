package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.User;

/**
 * Data Access Object untuk entity User
 */
@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user ORDER BY id ASC")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT count(*) FROM user")
    LiveData<Integer> getUserCount();

    @Query("SELECT * FROM user WHERE id = :id")
    LiveData<User> findUserById(int id);
}
