package com.example.batrakov.fragmenttask.Data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.batrakov.fragmenttask.Cat;

import java.util.ArrayList;

/**
 * Database fragment contain cat database and keep his retain instance.
 */

public class DataFragment extends Fragment implements DataAccess {

    private static final String TAG = "DataFragment";
    private ArrayList<Cat> mDataList;

    /**
     * Constructor.
     */
    public DataFragment() {
        Log.i(TAG, "DataFragment: Constructor");
        mDataList = new ArrayList<>();
    }


    @Override
    public void onCreate(@Nullable Bundle aSavedInstanceState) {
        super.onCreate(aSavedInstanceState);
        setRetainInstance(true);
        Log.i(TAG, "onCreate: Database");
    }

    /**
     * Add cat to database.
     * @param aCat target cat item.
     */
    public void addCatToBase(Cat aCat) {
        mDataList.add(aCat);
    }

    @Override
    public ArrayList<Cat> getData() {
        return mDataList;
    }
}
