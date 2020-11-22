package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.Transaction;

/**
 * Data Access Object untuk entity Transaction
 */
@Dao
public interface TransactionDao {

    @Insert
    void insert(Transaction transaction);

    @Update
    void update(Transaction transaction);

    @Delete
    void delete(Transaction transaction);

    @Query("SELECT * FROM `transaction` ORDER BY date DESC")
    LiveData<List<Transaction>> getAllTransactions();

    @Query("SELECT * FROM `transaction` WHERE walletId = :walletId")
    LiveData<List<Transaction>> findTransactionsWithWallet(int walletId);
}
