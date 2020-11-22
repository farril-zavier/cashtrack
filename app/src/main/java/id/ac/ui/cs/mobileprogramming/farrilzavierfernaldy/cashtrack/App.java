package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

/**
 * Class yang meng-extend Application dan berfungsi membuat NotificationChannel
 */
public class App extends Application {

    public static final String CHANNEL_ID = "id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.WALLET_SERVICE_CHANNEL";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }

    /**
     * Method yang membuat NotificationChannel untuk WalletService
     */
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel walletServiceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Wallet Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(walletServiceChannel);
        }
    }
}
