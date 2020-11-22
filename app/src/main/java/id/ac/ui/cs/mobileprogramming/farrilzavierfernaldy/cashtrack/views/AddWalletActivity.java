package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.R;

public class AddWalletActivity extends AppCompatActivity {

    public static final String EXTRA_WALLET_NAME =
            "id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.views.EXTRA_WALLET_NAME";
    public static final String EXTRA_BALANCE =
            "id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.views.EXTRA_BALANCE";
    public static final String EXTRA_MONTHLY_BUDGET =
            "id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.views.EXTRA_MONTHLY_BUDGET";

    private EditText editTextWalletName;
    private EditText editTextBalance;
    private EditText editTextMonthlyBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wallet);

        editTextWalletName = findViewById(R.id.edit_text_wallet_name);
        editTextBalance = findViewById(R.id.edit_text_balance);
        editTextMonthlyBudget = findViewById(R.id.edit_text_monthly_budget);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        String title = getResources().getString(R.string.add_wallet);
        setTitle(title);
    }

    private void saveWallet() {
        String walletName = editTextWalletName.getText().toString();
        String balanceString = editTextBalance.getText().toString();
        String monthlyBudgetString = editTextMonthlyBudget.getText().toString();

        if (walletName.trim().isEmpty() || balanceString.equals("") || monthlyBudgetString.equals("")) {
            Toast.makeText(this, getResources().getString(R.string.insert_wallet_invalid_toast), Toast.LENGTH_SHORT).show();
            return;
        }

        long balance = Long.parseLong(balanceString);
        long monthlyBudget = Long.parseLong(monthlyBudgetString);

        if (balance < 0 || monthlyBudget < 1) {
            Toast.makeText(this, getResources().getString(R.string.insert_wallet_invalid_toast), Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_WALLET_NAME, walletName);
        data.putExtra(EXTRA_BALANCE, balance);
        data.putExtra(EXTRA_MONTHLY_BUDGET, monthlyBudget);

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_wallet_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_wallet:
                saveWallet();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}