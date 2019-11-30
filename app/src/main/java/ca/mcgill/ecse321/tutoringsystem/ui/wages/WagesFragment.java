package ca.mcgill.ecse321.tutoringsystem.ui.wages;

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

public class WagesFragment extends Fragment {

    private WagesViewModel wagesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        wagesViewModel =
                ViewModelProviders.of(this).get(WagesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_wages, container, false);
        final TextView textView = root.findViewById(R.id.text_wages);
        wagesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}