package com.example.batrakov.fragmenttask.RegisterNewCat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.batrakov.fragmenttask.Cat;
import com.example.batrakov.fragmenttask.Data.DataAccess;
import com.example.batrakov.fragmenttask.Data.DataFragment;
import com.example.batrakov.fragmenttask.List.ListContract;
import com.example.batrakov.fragmenttask.R;

/**
 * Created by batrakov on 06.10.17.
 */

public class RegisterNewCatPresenter implements RegisterNewCatContract.Presenter {

    /**
     * Intent key for name.
     */
    public static final String NAME_KEY = "name key";
    /**
     * Intent key for breed.
     */
    public static final String BREED_KEY = "breed key";
    /**
     * Intent key for age.
     */
    public static final String AGE_KEY = "age key";

    private SendDataToMainFragment mCallback;
    private RegisterNewCatContract.View mRegisterView;

    /**
     * Allow callback communication with ListFragment.
     */
    public interface SendDataToMainFragment {
        /**
         * Send text field content.
         */
        void sendTextFieldsContent(@NonNull Cat aCat);
    }

    public RegisterNewCatPresenter(RegisterNewCatContract.View aRegisterView) {
        mRegisterView = aRegisterView;
        mRegisterView.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public FragmentManager getFragmentManager() {
        return null;
    }

    @Override
    public void sendDataToList(String aName, String aBreed, String aAge) {
        if (checkName(aName) && checkBreed(aBreed) && checkAge(aAge)) {
            try {
                mCallback = (SendDataToMainFragment) mRegisterView.getActivity();
                mCallback.sendTextFieldsContent(new Cat(aName, aBreed, aAge));
                if (mRegisterView.getTargetFragment() != null) {
                    mRegisterView.dismiss();
                }
            } catch (ClassCastException e) {
                throw new ClassCastException(mRegisterView.getActivity().toString()
                        + " must implement SendDataToMainFragment");
            }
        }
    }

    @Override
    public void setDataReference(DataAccess aDataReference) {

    }

    private boolean checkName(@NonNull String aName) {
        if (!aName.matches("([a-zA-Zа-яА-Я]+\\s?)+")) {
            mRegisterView.showNameError();
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check breed field.
     * @param aBreed target string
     * @return check result
     */
    private boolean checkBreed(@NonNull String aBreed) {
        if (!aBreed.matches("([a-zA-Zа-яА-Я]+\\s?)+")) {
            mRegisterView.showBreedError();
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check age field.
     * @param aAge target string
     * @return check result
     */
    private boolean checkAge(@NonNull String aAge) {
        if (!aAge.matches("\\d+")) {
            mRegisterView.showAgeError();
            return false;
        } else {
            return true;
        }
    }
}
