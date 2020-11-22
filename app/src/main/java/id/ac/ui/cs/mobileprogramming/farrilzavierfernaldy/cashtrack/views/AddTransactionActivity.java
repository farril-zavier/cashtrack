package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.R;

public class AddTransactionActivity extends AppCompatActivity {

    public static final String EXTRA_TRANSACTION_DATE =
            "id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.views.EXTRA_TRANSACTION_DATE";
    public static final String EXTRA_TRANSACTION_AMOUNT =
            "id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.views.EXTRA_TRANSACTION_AMOUNT";

    private DatePickerDialog picker;
    private Long transactionDateInMillis;
    private EditText editTextTransactionDate;
    private EditText editTextTransactionAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        editTextTransactionDate = findViewById(R.id.edit_text_transaction_date);
        editTextTransactionAmount = findViewById(R.id.edit_text_transaction_amount);
        editTextTransactionDate.setInputType(InputType.TYPE_NULL);
        editTextTransactionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                picker = new DatePickerDialog(AddTransactionActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        transactionDateInMillis = calendar.getTimeInMillis();

                        editTextTransactionDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        String title = getResources().getString(R.string.add_transaction);
        setTitle(title);
    }

    private void saveTransaction() {
        Long date = transactionDateInMillis;
        String amountString = editTextTransactionAmount.getText().toString();

        if (transactionDateInMillis == null || amountString.equals("")) {
            Toast.makeText(this, getResources().getString(R.string.insert_transaction_invalid_toast), Toast.LENGTH_SHORT).show();
            return;
        }

        long amount = Long.parseLong(amountString);

        if (amount < 1) {
            Toast.makeText(this, getResources().getString(R.string.insert_transaction_invalid_toast), Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TRANSACTION_DATE, date);
        data.putExtra(EXTRA_TRANSACTION_AMOUNT, amount);
        int walletId = getIntent().getIntExtra(TransactionListFragment.EXTRA_WALLET_ID, 0);
        if (walletId != 0) {
            data.putExtra(TransactionListFragment.EXTRA_WALLET_ID, walletId);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_transaction_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_transaction:
                saveTransaction();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}