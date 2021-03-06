package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.views;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.App;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.MainActivity;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.R;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.adapters.WalletAdapter;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.Wallet;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.viewmodels.NumWalletsViewModel;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.viewmodels.WalletViewModel;

public class WalletListFragment extends Fragment {

    public static final int ADD_WALLET_REQUEST = 1;

    private WalletViewModel walletViewModel;

    static {
        System.loadLibrary("subtract");
    }

    public native long subtract(long minuend, long subtrahend);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.wallet_list_fragment, container, false);

        FloatingActionButton btnAddWallet = v.findViewById(R.id.btn_add_wallet);
        btnAddWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddWalletActivity.class);
                startActivityForResult(intent, ADD_WALLET_REQUEST);
            }
        });

        RecyclerView walletRecyclerView = v.findViewById(R.id.wallet_recycler_view);
        walletRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        walletRecyclerView.setHasFixedSize(true);

        final WalletAdapter walletAdapter = new WalletAdapter();
        walletRecyclerView.setAdapter(walletAdapter);

        walletViewModel = new ViewModelProvider(this).get(WalletViewModel.class);
        walletViewModel.getAllWallets().observe(getViewLifecycleOwner(), new Observer<List<Wallet>>() {
            @Override
            public void onChanged(final List<Wallet> wallets) {
                // update RecyclerView
                for (final Wallet wallet : wallets) {
                    Log.d("wallet id", String.valueOf(wallet.getId()));
                    walletViewModel.getSumAmount(wallet.getId()).observe(getViewLifecycleOwner(), new Observer<Long>() {
                        @Override
                        public void onChanged(Long transactionSumAmount) {
                            if (transactionSumAmount == null) {
                                return;
                            }
                            Log.d("transaction sum amount", String.valueOf(transactionSumAmount));
                            long diff = subtract(wallet.getBalance(), transactionSumAmount);
                            wallet.setBalance(diff);
                            walletAdapter.setWallets(wallets);
                        }
                    });
                }
                // send broadcast if wallets == 5
                walletAdapter.setWallets(wallets);
                if (wallets.size() == 5) {
                    Intent intent = new Intent("id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.WALLETS_EXCEED_5");
                    getActivity().sendBroadcast(intent);
                }
            }
        });

        walletAdapter.setOnItemClickListener(new WalletAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Wallet wallet) {
                TransactionListFragment transactionListFragment = new TransactionListFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("walletId", wallet.getId());
                bundle.putString("walletName", wallet.getName());
                transactionListFragment.setArguments(bundle);

                if (getActivity().findViewById(R.id.activity_main_phone_portrait) != null) {
                    FragmentTransaction mainFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    mainFragmentTransaction.replace(R.id.main_fl_fragment, transactionListFragment)
                            .addToBackStack(null)
                            .commit();
                } else if (getActivity().findViewById(R.id.activity_main_tablet_landscape) != null) {
                    FragmentTransaction mainFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    mainFragmentTransaction.replace(R.id.main_fl_fragment_right, transactionListFragment)
                            .commit();
                }
            }
        });

        String title = getResources().getString(R.string.wallets);
        getActivity().setTitle(title);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_WALLET_REQUEST && resultCode == Activity.RESULT_OK) {
            String walletName = data.getStringExtra(AddWalletActivity.EXTRA_WALLET_NAME);
            long balance = data.getLongExtra(AddWalletActivity.EXTRA_BALANCE, 0);
            long monthlyBudget = data.getLongExtra(AddWalletActivity.EXTRA_MONTHLY_BUDGET, 1);

            Wallet wallet = new Wallet(walletName, balance, monthlyBudget);
            walletViewModel.insert(wallet);

            Intent notificationIntent = new Intent(getContext(), MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),
                    0, notificationIntent, 0);

            Notification notification = new NotificationCompat.Builder(getContext(), App.CHANNEL_ID_2)
                    .setContentTitle(getResources().getString(R.string.notification_wallet_added))
                    .setContentText(String.format("%s %s", walletName, getResources().getString(R.string.notification_wallet_info)))
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
            notificationManager.notify(2, notification);
//            Toast.makeText(getContext(), getResources().getString(R.string.wallet_saved), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getResources().getString(R.string.wallet_cancelled), Toast.LENGTH_SHORT).show();
        }
    }
}
