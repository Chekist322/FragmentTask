package com.example.batrakov.fragmenttask.Data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.batrakov.fragmenttask.Cat;

import java.util.ArrayList;

/**
 * Created by batrakov on 06.10.17.
 */

public class DataFragment extends Fragment implements DataAccess {
    ArrayList<Cat> mDataList;

    public DataFragment(){
        mDataList = new ArrayList<>();
    }

    DataFragment(ArrayList<Cat> aCats){
        mDataList = aCats;
    }

    @Override
    public void onCreate(@Nullable Bundle aSavedInstanceState) {
        super.onCreate(aSavedInstanceState);
        setRetainInstance(true);
        System.out.println("OnCreateData");
    }

    public void addCatToBase(Cat aCat){
        mDataList.add(aCat);
    }

    public ArrayList<Cat> getDataList(){
        return mDataList;
    }

    @Override
    public ArrayList<Cat> getData() {
        return mDataList;
    }
}
