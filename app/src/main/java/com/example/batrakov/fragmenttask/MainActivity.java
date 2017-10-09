package com.example.batrakov.fragmenttask;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

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

    ListPresenter mListPresenter;
    RegisterNewCatPresenter mRegisterNewCatPresenter;
    ListFragment mListFragment;
    RegisterNewCatFragment mRegisterNewCatFragment;
    DataFragment mDataFragment;

    @Override
    protected void onCreate(@Nullable Bundle aSavedInstanceState) {
        System.out.println("OnCreateAct");
        super.onCreate(aSavedInstanceState);
        setContentView(R.layout.activity_main);

        ListFragment listFragment =
                (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFragment);
        if (listFragment == null) {
            listFragment = ListFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), subscriptionListFragment, R.id.contentFrame);
        }

        mDataFragment = new DataFragment();
        mListFragment = new ListFragment();
        mRegisterNewCatFragment = new RegisterNewCatFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(mDataFragment, "DATABASE");
        fragmentTransaction.add(R.id.listFragment, mListFragment);
        System.out.println(fragmentTransaction);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentTransaction.add(R.id.registerNewCatFragment, mRegisterNewCatFragment);
        }

        fragmentTransaction.commit();
        mListPresenter = new ListPresenter(mListFragment);



        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();

            fragmentTransaction2.commit();
        }
        mRegisterNewCatPresenter = new RegisterNewCatPresenter(mRegisterNewCatFragment);



        mListPresenter.setDataReference(mDataFragment);
    }

    @Override
    public void sendTextFieldsContent(@NonNull Cat aCat) {
        mDataFragment.addCatToBase(aCat);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportFragmentManager().beginTransaction().remove(mDataFragment);
        getSupportFragmentManager().beginTransaction().remove(mListFragment);
        getSupportFragmentManager().beginTransaction().remove(mRegisterNewCatFragment);
//        getSupportFragmentManager().beginTransaction().remove(mListFragment);
//        getSupportFragmentManager().beginTransaction().remove(mRegisterNewCatFragment);
//        mListFragment = null;
//        mRegisterNewCatFragment = null;
//        mListPresenter = null;
//        mRegisterNewCatPresenter = null;
    }
}
