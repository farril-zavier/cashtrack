package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.App;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.MainActivity;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.R;

/**
 * Service yang dijalankan ketika app dimulai, di-update saat jumlah wallet berubah, dan dihentikan
 * apabila MainActivity ter-destroy. Service berjalan sebagai foreground service yang dapat
 * dilihat di menu notifikasi Android
 */
public class WalletService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String numWalletsString = intent.getStringExtra(MainActivity.NUMBER_OF_WALLETS);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_ID_1)
                .setContentTitle(getResources().getString(R.string.wallet_service_title))
                .setContentText(String.format("%s: %s", getResources().getString(R.string.wallet_service_text), numWalletsString))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
