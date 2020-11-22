package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

/**
 * Class yang menjadi penghubung untuk relasi Wallet dengan Transaction
 */
public class WalletWithTransactions {

    @Embedded
    public Wallet wallet;

    @Relation(
            parentColumn = "id",
            entityColumn = "walletId"
    )
    public List<Transaction> transactions;
}
