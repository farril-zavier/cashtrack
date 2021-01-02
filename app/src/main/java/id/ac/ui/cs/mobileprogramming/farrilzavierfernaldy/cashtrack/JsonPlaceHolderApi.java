package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.entities.CurrencyRates;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("latest?access_key=94e28d5443ced1f126d732d3c7985abf&symbols=IDR,JPY,USD,AUD,SGD")
    Call<CurrencyRates> getCurrencyRates();
}
