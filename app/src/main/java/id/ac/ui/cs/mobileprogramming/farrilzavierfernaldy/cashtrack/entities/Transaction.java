package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

/**
 * Class yang merepresentasikan Transaksi yang terjadi
 */
@Entity
public class Transaction {

    public Transaction(int walletId, Date date, long amount, boolean isExpense) {
        this.walletId = walletId;
        this.date = date;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int walletId;

    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "amount")
    private long amount;

    @ColumnInfo(name = "is_expense")
    private boolean isExpense;

    public int getId() {
        return id;
    }

    public int getWalletId() {
        return walletId;
    }

    public Date getDate() {
        return date;
    }

    public long getAmount() {
        return amount;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public void setId(int id) {
        this.id = id;
    }
}
