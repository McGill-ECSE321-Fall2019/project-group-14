package ca.mcgill.ecse321.tutoringsystem.ui.welcome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import ca.mcgill.ecse321.tutoringsystem.R;

public class WelcomeFragment extends Fragment {

    private WelcomeViewModel welcomeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        welcomeViewModel =
                ViewModelProviders.of(this).get(WelcomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_welcome, container, false);
        final TextView textView = root.findViewById(R.id.text_welcome);
        welcomeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}