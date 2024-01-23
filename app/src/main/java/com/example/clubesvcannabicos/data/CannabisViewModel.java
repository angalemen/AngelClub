package com.example.clubesvcannabicos.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;

public class CannabisViewModel extends AndroidViewModel {
    private final Application app;
    private MutableLiveData<FirebaseUser> user = new MutableLiveData<>();;
    public CannabisViewModel(@NonNull Application application) {
        super(application);
        this.app = application;
    }

    public MutableLiveData<FirebaseUser> getUser() {
        return user;
    }

    public void setUser(FirebaseUser passedUser) {
        user.postValue(passedUser);
    }
}
