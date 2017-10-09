package com.example.batrakov.fragmenttask.RegisterNewCat;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.batrakov.fragmenttask.BasePresenter;
import com.example.batrakov.fragmenttask.BaseView;
import com.example.batrakov.fragmenttask.Data.DataAccess;

/**
 * Created by batrakov on 06.10.17.
 */

public interface RegisterNewCatContract {

    interface View extends BaseView<Presenter>{

        void showNameError();

        void showBreedError();

        void showAgeError();

        Activity getActivity();

        Fragment getTargetFragment();

        void dismiss();

    }

    interface Presenter extends BasePresenter {

        void sendDataToList(String aName, String aBreed, String aAge);

        void setDataReference(DataAccess aDataReference);

    }
}
