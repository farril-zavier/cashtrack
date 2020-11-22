package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.R;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.Transaction;

/**
 * Adapter untuk view TransactionListFragment yang menampilkan RecyclerView berupa daftar
 * Transaction pada suatu Wallet
 */
public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionHolder> {

    private List<Transaction> transactions = new ArrayList<>();

    @NonNull
    @Override
    public TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_item, parent, false);
        return new TransactionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionHolder holder, int position) {
        Transaction currentTransaction = transactions.get(position);
        holder.textViewAmount.setText(String.format("Rp. %s", currentTransaction.getAmount()));
        holder.textViewDate.setText(currentTransaction.getDate().toString());
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder untuk menyimpan detail setiap Transaction
     */
    class TransactionHolder extends RecyclerView.ViewHolder {

        private TextView textViewAmount;
        private TextView textViewDate;

        public TransactionHolder(@NonNull View itemView) {
            super(itemView);
            textViewAmount = itemView.findViewById(R.id.text_view_amount);
            textViewDate = itemView.findViewById(R.id.text_view_date);
        }
    }
}
