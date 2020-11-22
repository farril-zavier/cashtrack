package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.Wallet;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.repositories.AppRepository;

/**
 * ViewModel untuk menyediakan LiveData berupa jumlah Wallet pada database
 */
public class NumWalletsViewModel extends AndroidViewModel {

    private AppRepository repository;
    private LiveData<Integer> numWallets;

    public NumWalletsViewModel(@NonNull Application application) {
        super(application);
        repository = new AppRepository(application);
        numWallets = repository.getWalletCount();
    }

    public void insert(Wallet wallet) {
        repository.insert(wallet);
    }

    public void update(Wallet wallet) {
        repository.update(wallet);
    }

    public void delete(Wallet wallet) {
        repository.delete(wallet);
    }

    public LiveData<Integer> getWalletCount() {
        return numWallets;
    }
}
