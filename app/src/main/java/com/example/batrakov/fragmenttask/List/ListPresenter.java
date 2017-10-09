package com.example.batrakov.fragmenttask.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.example.batrakov.fragmenttask.Cat;
import com.example.batrakov.fragmenttask.Data.DataAccess;
import com.example.batrakov.fragmenttask.Data.DataFragment;
import com.example.batrakov.fragmenttask.R;
import com.example.batrakov.fragmenttask.RegisterNewCat.RegisterNewCatFragment;
import com.example.batrakov.fragmenttask.RegisterNewCat.RegisterNewCatPresenter;

import java.util.ArrayList;

/**
 * Created by batrakov on 06.10.17.
 */

public class ListPresenter implements ListContract.Presenter {

    public static final int ADD_ACT = 1;
    private static final String CAT_ARRAY = "cat array";
    private static final String CAT_INDEX = "cat index";
    private static final String CUSTOM_ACTION = "com.example.batrakov.fragmenttaskgrid.ACTION";
    private static final String DIALOG_TAG = "dialog";
    private static final int GRID_ACT = 0;
    private static final String TAG = "ListPresenter";
    DataAccess mData;
    ListContract.View mListView;

    public ListPresenter(ListContract.View aListView) {
        Log.i(TAG, "ListPresenter: Constructor ");
        mListView = aListView;
        mListView.setPresenter(this);
        Log.i(TAG, "ListPresenter: mListView " + String.valueOf(mListView));
 //       Log.i(TAG, "ListPresenter: this " + String.valueOf(this));
    }


    @Override
    public ArrayList<Cat> readFromData() {
        return mData.getData();
    }

    @Override
    public void startFirstIntentCall() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        Intent intent = new Intent();
        intent.setAction(CUSTOM_ACTION);
        for (int i = 0; i < mData.getData().size(); i++) {
            stringArrayList.add(mData.getData().get(i).getName());
            stringArrayList.add(mData.getData().get(i).getBreed());
            stringArrayList.add(mData.getData().get(i).getAge());
        }
        intent.putStringArrayListExtra(CAT_ARRAY, stringArrayList);
        if (intent.resolveActivity(mListView.getActivity().getPackageManager()) != null) {
            mListView.startActivityForResult(intent, GRID_ACT);
        }
    }

    @Override
    public void startSecondIntentCall() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        for (int i = 0; i < mData.getData().size(); i++) {
            stringArrayList.add(mData.getData().get(i).getName());
            stringArrayList.add(mData.getData().get(i).getBreed());
            stringArrayList.add(mData.getData().get(i).getAge());
        }
        intent.putStringArrayListExtra(CAT_ARRAY, stringArrayList);
        if (intent.resolveActivity(mListView.getActivity().getPackageManager()) != null) {
            mListView.startActivityForResult(intent, GRID_ACT);
        }
    }

    @Override
    public void startRegisterNewCatDialog(FragmentTransaction aFragmentTransaction) {
        aFragmentTransaction.addToBackStack(null);
        RegisterNewCatFragment registerNewCatFragment = new RegisterNewCatFragment();
        registerNewCatFragment.setTargetFragment(mListView.getCurrentFragment(), ADD_ACT);
        registerNewCatFragment.setPresenter(new RegisterNewCatPresenter(registerNewCatFragment));
        registerNewCatFragment.show(aFragmentTransaction, DIALOG_TAG);
    }

    @Override
    public void checkActivityResult(int aRequestCode, int aResultCode, @NonNull Intent aData) {
        if (aRequestCode == GRID_ACT) {
            if (aResultCode == Activity.RESULT_OK) {
                int index = aData.getIntExtra(CAT_INDEX, 0);
                mListView.showPressedGridElement(index);
            }
        }
    }

    @Override
    public void setDataReference(DataAccess aDataReference) {
        mData = aDataReference;
    }

    @Override
    public void start() {

    }

    @Override
    public android.support.v4.app.FragmentManager getFragmentManager() {
        return mListView.getCurrentFragment().getFragmentManager();
    }
}
