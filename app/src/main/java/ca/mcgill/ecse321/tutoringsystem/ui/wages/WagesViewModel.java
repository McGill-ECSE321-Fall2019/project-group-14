package ca.mcgill.ecse321.tutoringsystem.ui.wages;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WagesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public WagesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is wages fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}