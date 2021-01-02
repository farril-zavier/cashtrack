package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Class yang merepresentasikan Dompet pengguna
 */
@Entity
public class Wallet {

    public Wallet(String name, long balance, long monthlyBudget) {
        this.name = name;
        this.balance = balance;
        this.monthlyBudget = monthlyBudget;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "balance")
    private long balance;

    @ColumnInfo(name = "monthly_budget")
    private long monthlyBudget;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getBalance() {
        return balance;
    }

    public long getMonthlyBudget() {
        return monthlyBudget;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
