package com.example.batrakov.fragmenttask;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.batrakov.fragmenttask.Data.DataAccess;
import com.example.batrakov.fragmenttask.Data.DataFragment;
import com.example.batrakov.fragmenttask.List.ListFragment;
import com.example.batrakov.fragmenttask.List.ListPresenter;
import com.example.batrakov.fragmenttask.R;
import com.example.batrakov.fragmenttask.RegisterNewCat.RegisterNewCatFragment;
import com.example.batrakov.fragmenttask.RegisterNewCat.RegisterNewCatPresenter;

/**
 * Main app activity.
 * Container and provider for ListFragment and RegisterNewCatFragment.
 */
public class MainActivity extends FragmentActivity implements RegisterNewCatPresenter.SendDataToMainFragment {

    private static final String TAG = "ListMainActivity";
    ListPresenter mListPresenter;
    RegisterNewCatPresenter mRegisterNewCatPresenter;
    ListFragment mListFragment;
    RegisterNewCatFragment mRegisterNewCatFragment;
    DataFragment mDataFragment;

    @Override
    protected void onCreate(@Nullable Bundle aSavedInstanceState) {
        Log.i(TAG, "onCreate: Activity");
        super.onCreate(aSavedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (aSavedInstanceState == null){
            mDataFragment = new DataFragment();
            mListFragment = new ListFragment();
            Log.i(TAG, "onCreate: mDataFragment " + String.valueOf(mDataFragment));
            mRegisterNewCatFragment = new RegisterNewCatFragment();
            mListPresenter = new ListPresenter(mListFragment);
        }
        fragmentTransaction.add(mDataFragment, "DATABASE");
        fragmentTransaction.replace(R.id.listFragment, mListFragment);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentTransaction.replace(R.id.registerNewCatFragment, mRegisterNewCatFragment);
        }

        fragmentTransaction.commit();


        mRegisterNewCatPresenter = new RegisterNewCatPresenter(mRegisterNewCatFragment);



        mListPresenter.setDataReference(mDataFragment);
    }

    @Override
    public void sendTextFieldsContent(@NonNull Cat aCat) {
        mDataFragment.addCatToBase(aCat);
    }
}
