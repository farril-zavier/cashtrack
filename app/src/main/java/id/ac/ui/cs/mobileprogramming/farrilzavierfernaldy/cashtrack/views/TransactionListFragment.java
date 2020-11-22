package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.views;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.R;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.adapters.TransactionAdapter;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.Transaction;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.viewmodels.TransactionViewModel;

public class TransactionListFragment extends Fragment {

    public static final String EXTRA_WALLET_ID =
            "id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.views.EXTRA_WALLET_ID";

    public static final int ADD_TRANSACTION_REQUEST = 2;

    private int walletId;
    private String walletName;
    private TransactionViewModel transactionViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.transaction_list_fragment, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            walletId = bundle.getInt("walletId");
            walletName = bundle.getString("walletName");
        }

        FloatingActionButton btnAddTransaction = v.findViewById(R.id.btn_add_transaction);
        btnAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTransactionActivity.class);
                intent.putExtra(EXTRA_WALLET_ID, walletId);
                startActivityForResult(intent, ADD_TRANSACTION_REQUEST);
            }
        });

        RecyclerView transactionRecyclerView = v.findViewById(R.id.transaction_recycler_view);
        transactionRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        transactionRecyclerView.setHasFixedSize(true);

        final TransactionAdapter transactionAdapter = new TransactionAdapter();
        transactionRecyclerView.setAdapter(transactionAdapter);

        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionViewModel.findTransactionsWithWallet(walletId).observe(getViewLifecycleOwner(), new Observer<List<Transaction>>() {
            @Override
            public void onChanged(List<Transaction> transactions) {
                // update RecyclerView
                if (transactions != null) {
                    TextView emptyText = v.findViewById(R.id.text_view_transactions_no_data);

                    if (getActivity().findViewById(R.id.activity_main_phone_portrait) != null) {
                        emptyText.setTextColor(Color.DKGRAY);
                    } else if (getActivity().findViewById(R.id.activity_main_tablet_landscape) != null) {
                        emptyText.setTextColor(Color.WHITE);
                    }

                    if (transactions.isEmpty()) {
                        emptyText.setVisibility(View.VISIBLE);
                    } else {
                        emptyText.setVisibility(View.INVISIBLE);
                    }
                }
                transactionAdapter.setTransactions(transactions);
            }
        });

        String title = getResources().getString(R.string.transactions);
        if (title.equals("Transactions")) {
            getActivity().setTitle(String.format("%s Transactions", walletName));
        } else if (title.equals("Transaksi")) {
            getActivity().setTitle(String.format("Transaksi %s", walletName));
        } else {
            getActivity().setTitle(String.format("%s Transactions", walletName));
        }

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TRANSACTION_REQUEST && resultCode == Activity.RESULT_OK) {
            int walletId = data.getIntExtra(TransactionListFragment.EXTRA_WALLET_ID, 0);
            Date date = new Date(data.getLongExtra(AddTransactionActivity.EXTRA_TRANSACTION_DATE, 0));
            long amount = data.getLongExtra(AddTransactionActivity.EXTRA_TRANSACTION_AMOUNT, 0);

            Transaction transaction = new Transaction(walletId, date, amount, true);
            transactionViewModel.insert(transaction);

            Toast.makeText(getContext(), getResources().getString(R.string.transaction_saved), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getResources().getString(R.string.transaction_cancelled), Toast.LENGTH_SHORT).show();
        }
    }
}
