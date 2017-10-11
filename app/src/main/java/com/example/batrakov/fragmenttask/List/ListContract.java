package com.example.batrakov.fragmenttask.List;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.batrakov.fragmenttask.BaseView;
import com.example.batrakov.fragmenttask.Cat;
import com.example.batrakov.fragmenttask.Data.DataAccess;

import java.util.ArrayList;

/**
 * Describe communication contract between {@link ListFragment} and {@link ListPresenter}.
 * Created by batrakov on 06.10.17.
 */

interface ListContract {
    /**
     * Interface provide {@link ListFragment} methods for {@link ListPresenter}.
     */
    interface View extends BaseView<Presenter> {

        /**
         * @return application Activity.
         */
        Activity getActivity();

        /**
         * Allow to start new app from fragment to get result after its action.
         * @param aIntent intent for Activity launch.
         * @param aTag tag for getting right result.
         */
        void startActivityForResult(Intent aIntent, int aTag);

        /**
         * @return current fragment reference.
         */
        Fragment getCurrentFragment();

        /**
         * Make and show SnackBar with pressed element index.
         * @param aIndex pressed element index.
         */
        void showPressedGridElement(int aIndex);

        /**
         * @return current FragmentManager reference.
         */
        FragmentManager getSupportFragmentManager();

        /**
         * Call ListView update.
         */
        void updateListView();
    }

    /**
     * Interface provide {@link ListPresenter} methods for {@link ListFragment}.
     */
    interface Presenter  {

        /**
         * @return data list from {@link com.example.batrakov.fragmenttask.Data.DataFragment}
         */
        ArrayList<Cat> readFromData();

        /**
         * Start second app by custom action.
         */
        void startFirstIntentCall();

        /**
         * Start second app by base View action.
         */
        void startSecondIntentCall();

        /**
         * Launch {@link com.example.batrakov.fragmenttask.RegisterNewCat.RegisterNewCatFragment} as a dialog.
         * @param aFragmentTransaction current fragment transaction.
         */
        void startRegisterNewCatDialog(FragmentTransaction aFragmentTransaction);

        /**
         * Check result from second app.
         * @param aRequestCode request code.
         * @param aResultCode result code.
         * @param aData data from second app.
         */
        void checkActivityResult(int aRequestCode, int aResultCode, @NonNull Intent aData);

        /**
         * Set reference to current {@link com.example.batrakov.fragmenttask.Data.DataFragment}.
         * @param aDataR current {@link com.example.batrakov.fragmenttask.Data.DataFragment}.
         */
        void setDataReference(DataAccess aDataR);

        /**
         * Call updateListView in {@link ListFragment}.
         */
        void updateListView();
    }
}
