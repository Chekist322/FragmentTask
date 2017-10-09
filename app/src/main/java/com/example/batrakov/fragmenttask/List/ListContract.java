package com.example.batrakov.fragmenttask.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.batrakov.fragmenttask.BasePresenter;
import com.example.batrakov.fragmenttask.BaseView;
import com.example.batrakov.fragmenttask.Cat;
import com.example.batrakov.fragmenttask.Data.DataAccess;

import java.util.ArrayList;

/**
 * Created by batrakov on 06.10.17.
 */

public interface ListContract {
    interface View extends BaseView<Presenter> {
        Activity getActivity();

        void startActivityForResult(Intent aIntent, int tag);

        Fragment getCurrentFragment();

        void showPressedGridElement(int aIndex);

        FragmentManager getSupportFragmentManager();
    }

    interface Presenter extends BasePresenter {

        ArrayList<Cat> readFromData();

        void startFirstIntentCall();

        void startSecondIntentCall();

        void startRegisterNewCatDialog(FragmentTransaction aFragmentTransaction);

        void checkActivityResult(int aRequestCode, int aResultCode, @NonNull Intent aData);

        void setDataReference(DataAccess aDataR);
    }
}
