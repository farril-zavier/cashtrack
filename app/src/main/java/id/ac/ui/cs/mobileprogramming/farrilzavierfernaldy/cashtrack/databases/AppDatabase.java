package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.databases;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.Converters;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.dao.TransactionDao;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.dao.UserDao;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.dao.WalletDao;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.Transaction;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.User;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.Wallet;

/**
 * Class yang merepresentasikan SQL Database menggunakan framework Room
 */
@Database(entities = {Wallet.class, Transaction.class, User.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract WalletDao walletDao();
    public abstract TransactionDao transactionDao();
    public abstract UserDao userDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
//                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

//    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            new PopulateDbAsyncTask(instance).execute();
//        }
//    };
//
//    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
//        private WalletDao walletDao;
//        private TransactionDao transactionDao;
//        private UserDao userDao;
//
//        private PopulateDbAsyncTask(AppDatabase db) {
//            walletDao = db.walletDao();
//            transactionDao = db.transactionDao();
//            userDao = db.userDao();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            walletDao.insert(new Wallet("Wallet A", 5000000, 5000000));
//            walletDao.insert(new Wallet("Wallet B", 10000000, 3000000));
//            transactionDao.insert(new Transaction(1, new Date(), 50000, true));
//            transactionDao.insert(new Transaction(1, new Date(), 100000, false));
//            transactionDao.insert(new Transaction(2, new Date(), 25000, true));
//            return null;
//        }
//    }
}
