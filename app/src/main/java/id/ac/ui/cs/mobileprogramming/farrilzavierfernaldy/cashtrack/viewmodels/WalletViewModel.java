package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.Wallet;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.repositories.AppRepository;

/**
 * ViewModel untuk menyediakan LiveData berupa list semua Wallet
 */
public class WalletViewModel extends AndroidViewModel {

    private AppRepository repository;
    private LiveData<List<Wallet>> allWallets;

    public WalletViewModel(@NonNull Application application) {
        super(application);
        repository = new AppRepository(application);
        allWallets = repository.getAllWallets();
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

    public LiveData<List<Wallet>> getAllWallets() {
        return allWallets;
    }

    public LiveData<Long> getSumAmount(int walletId) {
        return repository.getSumAmount(walletId);
    }
}
