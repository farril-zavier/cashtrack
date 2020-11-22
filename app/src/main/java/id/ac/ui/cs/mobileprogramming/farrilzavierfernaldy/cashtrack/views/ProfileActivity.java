package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ProfileFragment profileFragment = new ProfileFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.profile_fl_fragment, profileFragment)
                .commit();

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
    }
}