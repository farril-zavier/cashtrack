package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.views;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.JsonPlaceHolderApi;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.R;
import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.CurrencyRates;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrencyRatesFragment extends Fragment {

    private TextView tvCurrencyBase;
    private TextView tvCurrencyRates;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.currency_rates, container, false);
        tvCurrencyBase = v.findViewById(R.id.currency_base);
        tvCurrencyRates = v.findViewById(R.id.currency_rates);

        String title = getResources().getString(R.string.currency_rates);
        getActivity().setTitle(title);

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (!isConnected) {
            new AlertDialog.Builder(getContext())
                    .setMessage(getResources().getString(R.string.no_internet))
                    .setPositiveButton(getResources().getString(R.string.ok), null)
                    .create()
                    .show();
        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://data.fixer.io/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

            Call<CurrencyRates> call = jsonPlaceHolderApi.getCurrencyRates();
            call.enqueue(new Callback<CurrencyRates>() {
                @Override
                public void onResponse(Call<CurrencyRates> call, Response<CurrencyRates> response) {
                    if (!response.isSuccessful()) {
                        tvCurrencyBase.setText("Code: " + response.code());
                        return;
                    }
                    CurrencyRates currencyRates = response.body();
                    tvCurrencyBase.setText(currencyRates.getBase());
                    tvCurrencyRates.setText(
                            String.format(
                                    "IDR: %s\n" +
                                            "JPY: %s\n" +
                                            "USD: %s\n" +
                                            "AUD: %s\n" +
                                            "SGD: %s",
                                    currencyRates.getRates().getIDR(),
                                    currencyRates.getRates().getJPY(),
                                    currencyRates.getRates().getUSD(),
                                    currencyRates.getRates().getAUD(),
                                    currencyRates.getRates().getSGD()
                            )
                    );
                }

                @Override
                public void onFailure(Call<CurrencyRates> call, Throwable t) {
                    tvCurrencyBase.setText(t.getMessage());
                }
            });
        }

        return v;
    }
}
