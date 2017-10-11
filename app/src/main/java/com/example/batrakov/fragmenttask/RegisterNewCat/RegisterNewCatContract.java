package com.example.batrakov.fragmenttask.RegisterNewCat;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.batrakov.fragmenttask.BaseView;

/**
 * Describe communication contract between {@link RegisterNewCatFragment} and {@link RegisterNewCatPresenter}.
 * Created by batrakov on 06.10.17.
 */
interface RegisterNewCatContract {

    /**
     * Interface provide {@link RegisterNewCatFragment} methods for {@link RegisterNewCatPresenter}.
     */
    interface View extends BaseView<Presenter> {

        /**
         * Make and show SnackBar with name error.
         */
        void showNameError();

        /**
         * Make and show SnackBar with breed error.
         */
        void showBreedError();

        /**
         * Make and show SnackBar with age error.
         */
        void showAgeError();

        /**
         * Allow to get application Main Activity.
         * @return application Activity.
         */
        Activity getActivity();

        /**
         * Allow to get target Fragment if exist.
         * @return target Fragment.
         */
        Fragment getTargetFragment();

        /**
         * Dismiss current Fragment.
         */
        void dismiss();

        /**
         * Clear text fields.
         */
        void clearFields();
    }

    /**
     * Interface provide {@link RegisterNewCatPresenter} methods for {@link RegisterNewCatFragment}.
     */
    interface Presenter {

        /**
         * Allow to send data from text fields to {@link com.example.batrakov.fragmenttask.Data.DataFragment}.
         * @param aName target name.
         * @param aBreed target breed.
         * @param aAge target age.
         */
        void sendDataToList(String aName, String aBreed, String aAge);

    }
}
