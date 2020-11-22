package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.services.WalletService;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.viewmodels.NumWalletsViewModel;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.views.ProfileActivity;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.views.ProfileFragment;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.views.WalletListFragment;

/**
 * Activity utama dari aplikasi
 */
public class MainActivity extends AppCompatActivity {

    private int selectedNavItem;
    private Integer numWallets;
    private static final String CURRENT_SELECTED_FRAGMENT = "CURRENT_SELECTED_FRAGMENT";
    public static final String NUMBER_OF_WALLETS = "NUMBER_OF_WALLETS";

    private NumWalletsViewModel numWalletsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // handling penampilan fragment yang sesuai dengan yang sebelumnya apabila MainActivity di-destroy
        if (savedInstanceState != null) {
            selectedNavItem = savedInstanceState.getInt(CURRENT_SELECTED_FRAGMENT);
            switch (selectedNavItem) {
                case 2:
                    ProfileFragment profileFragment = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_fl_fragment, profileFragment)
                            .commit();
                    break;
                default:
                    WalletListFragment walletListFragment = new WalletListFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_fl_fragment, walletListFragment)
                            .commit();
            }
        } else {
            WalletListFragment walletListFragment = new WalletListFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fl_fragment, walletListFragment)
                    .commit();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        numWalletsViewModel = new ViewModelProvider(this).get(NumWalletsViewModel.class);
        numWalletsViewModel.getWalletCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer i) {
                numWallets = i;
                startWalletService();
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_wallets:
                            selectedNavItem = 1;
                            selectedFragment = new WalletListFragment();
                            break;
                        case R.id.nav_profile:
                            if (findViewById(R.id.activity_main_phone_portrait) != null) {
                                selectedNavItem = 2;
                                selectedFragment = new ProfileFragment();
                            } else if (findViewById(R.id.activity_main_tablet_landscape) != null) {
                                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                                startActivity(intent);
                                return false;
                            }
                            break;
                    }
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_fl_fragment, selectedFragment)
                            .commit();

                    return true;
                }
            };

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(CURRENT_SELECTED_FRAGMENT, selectedNavItem);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(
                "id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.WALLETS_EXCEED_5");
        registerReceiver(walletBroadcastReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(walletBroadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopWalletService();
    }

    /**
     * BroadcastReceiver yang menampilkan AlertDialog apabila jumlah wallet sudah mencapai 5
     * BroadcastReceiver ini di-register pada lifecycle method onStart dan di-unregister pada
     * lifecycle method onStop
     */
    private BroadcastReceiver walletBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            showBroadcastMessage();
        }
    };

    private void showBroadcastMessage() {
        new AlertDialog.Builder(this)
                .setMessage(getResources().getString(R.string.wallets_exceed_5))
                .setPositiveButton(getResources().getString(R.string.ok), null)
                .create()
                .show();
    }

    /**
     * Method untuk memulai WalletService
     */
    public void startWalletService() {
        String numWalletsString = String.valueOf(numWallets);

        Intent walletServiceIntent = new Intent(this, WalletService.class);
        walletServiceIntent.putExtra(NUMBER_OF_WALLETS, numWalletsString);

        startService(walletServiceIntent);
    }

    /**
     * Method untuk menghentikan WalletService
     */
    public void stopWalletService() {
        Intent walletServiceIntent = new Intent(this, WalletService.class);

        stopService(walletServiceIntent);
    }
}