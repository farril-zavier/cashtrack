package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.Transaction;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.WalletWithTransactions;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.repositories.AppRepository;

/**
 * ViewModel untuk menyediakan LiveData berupa list Transaction berdasarkan ID wallet
 */
public class TransactionViewModel extends AndroidViewModel {

    private AppRepository repository;
    private LiveData<List<WalletWithTransactions>> walletsWithTransactions;

    public TransactionViewModel(@NonNull Application application) {
        super(application);
        repository = new AppRepository(application);
        walletsWithTransactions = repository.getWalletsWithTransactions();
    }

    public void insert(Transaction transaction) {
        repository.insert(transaction);
    }

    public void update(Transaction transaction) {
        repository.update(transaction);
    }

    public void delete(Transaction transaction) {
        repository.delete(transaction);
    }

    public LiveData<List<WalletWithTransactions>> getWalletsWithTransactions() {
        return walletsWithTransactions;
    }

    public LiveData<List<Transaction>> findTransactionsWithWallet(int walletId) {
        return repository.findTransactionsWithWallet(walletId);
    }
}
