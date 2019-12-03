package ca.mcgill.ecse321.tutoringsystem.ui.reviews;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReviewsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReviewsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Browse your reviews");
    }

    public LiveData<String> getText() {
        return mText;
    }
}