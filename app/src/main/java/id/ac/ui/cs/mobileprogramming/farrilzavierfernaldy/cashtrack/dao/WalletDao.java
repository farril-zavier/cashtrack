package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.Wallet;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.WalletWithTransactions;

/**
 * Data Access Object untuk entity Wallet
 */
@Dao
public interface WalletDao {

    @Insert
    void insert(Wallet wallet);

    @Update
    void update(Wallet wallet);

    @Delete
    void delete(Wallet wallet);

    @Query("SELECT * FROM wallet ORDER BY id ASC")
    LiveData<List<Wallet>> getAllWallets();

    @Query("SELECT count(*) FROM wallet")
    LiveData<Integer> getWalletCount();

    @Transaction
    @Query("SELECT * FROM wallet")
    LiveData<List<WalletWithTransactions>> getWalletsWithTransactions();
}
