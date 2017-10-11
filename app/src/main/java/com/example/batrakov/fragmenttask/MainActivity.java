package com.example.batrakov.fragmenttask;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.example.batrakov.fragmenttask.Data.DataFragment;
import com.example.batrakov.fragmenttask.List.ListFragment;
import com.example.batrakov.fragmenttask.List.ListPresenter;
import com.example.batrakov.fragmenttask.RegisterNewCat.RegisterNewCatFragment;
import com.example.batrakov.fragmenttask.RegisterNewCat.RegisterNewCatPresenter;

/**
 * Main app activity.
 * Container and provider for ListFragment and RegisterNewCatFragment.
 */
public class MainActivity extends FragmentActivity implements RegisterNewCatPresenter.SendDataFromRegistrationFragment {

    private static final String TAG = "ListMainActivity";
    private static final String DATABASE = "DATABASE";

    private ListPresenter mListPresenter;
    private DataFragment mDataFragment;

    @Override
    protected void onCreate(@Nullable Bundle aSavedInstanceState) {
        Log.i(TAG, "onCreate: Activity");
        super.onCreate(aSavedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();

        mDataFragment = (DataFragment) getSupportFragmentManager().findFragmentByTag(DATABASE);
        if (mDataFragment == null) {
            mDataFragment = new DataFragment();
            fragmentManager.beginTransaction()
                    .add(mDataFragment, DATABASE)
                    .commit();
        }

        ListFragment listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFragment);
        if (listFragment == null) {
            listFragment = new ListFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.listFragment, listFragment)
                    .commit();
        }



        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            RegisterNewCatFragment registerNewCatFragment = (RegisterNewCatFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.registerNewCatFragment);
            if (registerNewCatFragment == null) {
                registerNewCatFragment = new RegisterNewCatFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.registerNewCatFragment, registerNewCatFragment)
                        .commit();
            }
            RegisterNewCatPresenter registerNewCatPresenter = new RegisterNewCatPresenter(registerNewCatFragment);
        }

        mListPresenter = new ListPresenter(listFragment);
        mListPresenter.setDataReference(mDataFragment);
    }

    @Override
    public void sendTextFieldsContent(@NonNull Cat aCat) {
        mDataFragment.addCatToBase(aCat);
        mListPresenter.updateListView();
    }
}
