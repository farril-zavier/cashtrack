package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Class yang merepresentasikan pengguna aplikasi
 */
@Entity
public class User {

    public User() {}

    public User(Bitmap profilePicture) {
        this.profilePicture = profilePicture;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "profile_picture")
    private Bitmap profilePicture;

    public int getId() {
        return id;
    }

    public Bitmap getProfilePicture() {
        return profilePicture;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProfilePicture(Bitmap profilePicture) {
        this.profilePicture = profilePicture;
    }
}
