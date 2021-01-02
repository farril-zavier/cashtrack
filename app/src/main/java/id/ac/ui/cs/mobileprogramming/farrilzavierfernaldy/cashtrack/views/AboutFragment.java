package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.R;

public class AboutFragment extends Fragment {

    private AboutOpenGLView aboutOpenGLView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.about, container, false);
        aboutOpenGLView = v.findViewById(R.id.about_openglview);

        String title = getResources().getString(R.string.about);
        getActivity().setTitle(title);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        aboutOpenGLView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        aboutOpenGLView.onPause();
    }
}
