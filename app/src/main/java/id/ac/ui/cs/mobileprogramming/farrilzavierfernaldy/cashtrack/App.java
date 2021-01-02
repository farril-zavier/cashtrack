package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

/**
 * Class yang meng-extend Application dan berfungsi membuat NotificationChannel
 */
public class App extends Application {

    public static final String CHANNEL_ID_1 = "id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.WALLET_SERVICE_CHANNEL_1";
    public static final String CHANNEL_ID_2 = "id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.WALLET_SERVICE_CHANNEL_2";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    /**
     * Method yang membuat NotificationChannel untuk WalletService
     */
    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel walletServiceChannel = new NotificationChannel(
                    CHANNEL_ID_1,
                    "Wallet Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationChannel cashtrackChannel = new NotificationChannel(
                    CHANNEL_ID_2,
                    "CashTrack",
                    NotificationManager.IMPORTANCE_HIGH
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(walletServiceChannel);
            manager.createNotificationChannel(cashtrackChannel);
        }
    }
}
