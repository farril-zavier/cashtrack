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
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.Wallet;

/**
 * Adapter untuk view WalletListFragment yang menampilkan RecyclerView berupa daftar Wallet
 */
public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.WalletHolder> {

    private List<Wallet> wallets = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public WalletHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wallet_item, parent, false);
        return new WalletHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletHolder holder, int position) {
        Wallet currentWallet = wallets.get(position);
        holder.textViewName.setText(currentWallet.getName());
        holder.textViewBalance.setText(String.format("Rp. %s", currentWallet.getBalance()));
    }

    @Override
    public int getItemCount() {
        return wallets.size();
    }

    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder untuk menyimpan detail setiap Wallet
     */
    class WalletHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewBalance;

        public WalletHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewBalance = itemView.findViewById(R.id.text_view_balance);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(wallets.get(position));
                    }
                }
            });
        }
    }

    /**
     * Listener dengan method onItemClick yang akan di-implement oleh view
     */
    public interface OnItemClickListener {
        void onItemClick(Wallet wallet);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
