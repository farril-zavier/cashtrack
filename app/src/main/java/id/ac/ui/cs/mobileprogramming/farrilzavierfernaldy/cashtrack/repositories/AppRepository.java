package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.dao.TransactionDao;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.dao.UserDao;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.dao.WalletDao;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.databases.AppDatabase;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.Transaction;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.User;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.Wallet;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.WalletWithTransactions;

/**
 * Class yang menjadi abstraction layer antara data source dengan rest of the app
 */
public class AppRepository {

    private WalletDao walletDao;
    private TransactionDao transactionDao;
    private UserDao userDao;
    private LiveData<List<Wallet>> allWallets;
    private LiveData<List<Transaction>> allTransactions;
    private LiveData<List<User>> allUsers;
    private LiveData<List<WalletWithTransactions>> walletsWithTransactions;
    private LiveData<Integer> numUsers;
    private LiveData<Integer> numWallets;

    public AppRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        walletDao = database.walletDao();
        transactionDao = database.transactionDao();
        userDao = database.userDao();
        allWallets = walletDao.getAllWallets();
        allTransactions = transactionDao.getAllTransactions();
        allUsers = userDao.getAllUsers();
        walletsWithTransactions = walletDao.getWalletsWithTransactions();
        numUsers = userDao.getUserCount();
        numWallets = walletDao.getWalletCount();
    }

    public void insert(Wallet wallet) {
        new InsertWalletAsyncTask(walletDao).execute(wallet);
    }

    public void update(Wallet wallet) {
        new UpdateWalletAsyncTask(walletDao).execute(wallet);
    }

    public void delete(Wallet wallet) {
        new DeleteWalletAsyncTask(walletDao).execute(wallet);
    }

    public void insert(Transaction transaction) {
        new InsertTransactionAsyncTask(transactionDao).execute(transaction);
    }

    public void update(Transaction transaction) {
        new UpdateTransactionAsyncTask(transactionDao).execute(transaction);
    }

    public void delete(Transaction transaction) {
        new DeleteTransactionAsyncTask(transactionDao).execute(transaction);
    }

    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void update(User user) {
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public void delete(User user) {
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public LiveData<List<Wallet>> getAllWallets() {
        return allWallets;
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return allTransactions;
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<List<Transaction>> findTransactionsWithWallet(int walletId) {
        return transactionDao.findTransactionsWithWallet(walletId);
    }

    public LiveData<List<WalletWithTransactions>> getWalletsWithTransactions() {
        return walletsWithTransactions;
    }

    public LiveData<Long> getSumAmount(int walletId) {
        return walletDao.getSumAmount(walletId);
    }

    public LiveData<Integer> getUserCount() {
        return numUsers;
    }

    public LiveData<Integer> getWalletCount() {
        return numWallets;
    }

    public LiveData<User> findUserById(int id) {
        return userDao.findUserById(id);
    }

    private static class InsertWalletAsyncTask extends AsyncTask<Wallet, Void, Void> {

        private WalletDao walletDao;

        private InsertWalletAsyncTask(WalletDao walletDao) {
            this.walletDao = walletDao;
        }

        @Override
        protected Void doInBackground(Wallet... wallets) {
            walletDao.insert(wallets[0]);
            return null;
        }
    }

    private static class UpdateWalletAsyncTask extends AsyncTask<Wallet, Void, Void> {

        private WalletDao walletDao;

        private UpdateWalletAsyncTask(WalletDao walletDao) {
            this.walletDao = walletDao;
        }

        @Override
        protected Void doInBackground(Wallet... wallets) {
            walletDao.update(wallets[0]);
            return null;
        }
    }

    private static class DeleteWalletAsyncTask extends AsyncTask<Wallet, Void, Void> {

        private WalletDao walletDao;

        private DeleteWalletAsyncTask(WalletDao walletDao) {
            this.walletDao = walletDao;
        }

        @Override
        protected Void doInBackground(Wallet... wallets) {
            walletDao.delete(wallets[0]);
            return null;
        }
    }

    private static class InsertTransactionAsyncTask extends AsyncTask<Transaction, Void, Void> {

        private TransactionDao transactionDao;

        private InsertTransactionAsyncTask(TransactionDao transactionDao) {
            this.transactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(Transaction... transactions) {
            transactionDao.insert(transactions[0]);
            return null;
        }
    }

    private static class UpdateTransactionAsyncTask extends AsyncTask<Transaction, Void, Void> {

        private TransactionDao transactionDao;

        private UpdateTransactionAsyncTask(TransactionDao transactionDao) {
            this.transactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(Transaction... transactions) {
            transactionDao.update(transactions[0]);
            return null;
        }
    }

    private static class DeleteTransactionAsyncTask extends AsyncTask<Transaction, Void, Void> {

        private TransactionDao transactionDao;

        private DeleteTransactionAsyncTask(TransactionDao transactionDao) {
            this.transactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(Transaction... transactions) {
            transactionDao.delete(transactions[0]);
            return null;
        }
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        private DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }
}
