package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.User;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.repositories.AppRepository;

/**
 * ViewModel untuk menyediakan LiveData berupa list semua User dan jumlah User pada database
 */
public class UserViewModel extends AndroidViewModel {

    private AppRepository repository;
    private LiveData<List<User>> allUsers;
    private LiveData<Integer> numUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new AppRepository(application);
        allUsers = repository.getAllUsers();
        numUsers = repository.getUserCount();
    }

    public void insert(User user) {
        repository.insert(user);
    }

    public void update(User user) {
        repository.update(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<Integer> getUserCount() {
        return numUsers;
    }

    public LiveData<User> findUserById(int id) {
        return repository.findUserById(id);
    }
}
